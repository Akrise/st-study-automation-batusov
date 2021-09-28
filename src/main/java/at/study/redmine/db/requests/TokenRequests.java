package at.study.redmine.db.requests;

import at.study.redmine.db.connection.PostgresConnection;
import at.study.redmine.model.Token;
import at.study.redmine.model.User;
import jdk.nashorn.internal.codegen.ObjectClassGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;


@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
public class TokenRequests implements Create<Token>, ReadAll<Token> {
    private User user;

    @Override
    public void create(Token token) {
        String query = "INSERT INTO public.tokens\n" +
                "(id, user_id, \"action\", value, created_on, updated_on)\n" +
                "VALUES(DEFAULT, ?, ?, ?, ?, ?) RETURNING id;\n";
        List<Map<String, Object>> queryResult = PostgresConnection.INSTANCE.executeQuery(
                query,
                token.getUserId(),
                token.getAction().name().toLowerCase(),
                token.getValue(),
                token.getCreatedOn(),
                token.getUpdatedOn()
        );
        Integer tokenId = (Integer) queryResult.get(0).get("id");
        token.setId(tokenId);
    }

    /**
     * Получить все токены пользователя
     * @return Все токены пользователя
     */
    @Override
    public List<Token> readAll() {
        Integer userID = Objects.requireNonNull(user.getId());
        String query = "SELECT * FROM tokens WHERE user_id = ?";
        List<Map<String, Object>> queryResult = PostgresConnection.INSTANCE.executeQuery(query, userID);
        return queryResult.stream()
                .map(data -> from(data, user))
                .collect(Collectors.toList());
    }

    /**
     * Преобразование строки результата выполнения запроса в сущность Token
     *
     * @param data Одна строка из результата выполнения SQL запроса, представленная как Map
     * @param user Пользователь, которому будет присвоен Token
     * @return Сущность Token, созданная из записи в БД
     */
    private Token from(Map<String, Object> data, User user) {
        Token token = new Token(user);
        token.setId((Integer) data.get("id"));
        token.setAction(
                Token.TokenType.valueOf(
                        data.get("action").toString().toUpperCase()
                )
        ).setValue(data.get("value").toString());
        return token;
    }

    /**
     * Конвертер времени и даты
     *
     * @param timestampObj Время и дата, прочитанные из БД, в формате Timestamp
     * @return Время и дата в формате LocalDateTime
     */
    private LocalDateTime toLocalDate(Object timestampObj) {
        Timestamp timestamp = (Timestamp) timestampObj;
        return timestamp.toLocalDateTime();
    }
}

package at.study.redmine.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static at.study.redmine.utils.StringUtils.randomHexString;

@Getter
@Setter
public class Token extends CreatableEntity implements Creatable<Token> {

    private Integer userId;
    private TokenType action = TokenType.API;
    private String value = randomHexString(40);

    public Token(User user) {
        this.userId = user.id;
    }

    @Override
    public Token create() {
        // TODO: Реализовать с помощью SQL-Запроса
        throw new UnsupportedOperationException();
    }

    public enum TokenType {
        API,
        FEEDS,
        SESSION
    }

}

package at.study.redmine.db.requests;

import at.study.redmine.db.connection.PostgresConnection;
import at.study.redmine.model.Email;
import at.study.redmine.model.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class EmailRequests implements Create<Email>, Read<Email> {

    @Override
    public void create(Email email) {
        String query = "INSERT INTO public.email_addresses\n" +
                "(user_id, address, is_default, \"notify\", created_on, updated_on)\n" +
                "VALUES(?, ?, ?, ?, ?, ?) returning id;\n";

        List<Map<String, Object>> result = PostgresConnection.INSTANCE.executeQuery(query,
                email.getUserId(),
                email.getAddress(),
                email.getIsDefault(),
                email.getNotify(),
                email.getCreatedOn(),
                email.getUpdatedOn()
        );
        Integer emailID = (Integer) result.get(0).get("id");
        email.setId(emailID);
    }

    @Override
    public Email read(Integer id) {
        Email email = new Email();
        String query = "SELECT *\n" +
                "FROM public.email_addresses\n" +
                "WHERE id = ?;";
        List<Map<String, Object>> result = PostgresConnection.INSTANCE.executeQuery(query, id);
        if (result.size() == 0) {
            throw new NoSuchElementException("По id " + id + " email не найден.");
        }
        email.setId((Integer) result.get(0).get("id"));
        email.setUserId((Integer) result.get(0).get("user_id"));
        email.setAddress(result.get(0).get("address").toString());
        email.setIsDefault(Boolean.parseBoolean(result.get(0).get("is_default").toString()));
        email.setNotify(Boolean.parseBoolean(result.get(0).get("notify").toString()));
        email.setCreatedOn(LocalDateTime.parse(result.get(0).get("created_on").toString().substring(0, 19), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        email.setUpdatedOn(LocalDateTime.parse(result.get(0).get("created_on").toString().substring(0, 19), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        return email;
    }

    public List<Email> read(User user) {
        List<Email> emails = new ArrayList<>();
        String query = "SELECT *\n" +
                "FROM public.email_addresses\n" +
                "WHERE user_id = ?;";
        Integer userID = user.getId();
        List<Map<String, Object>> result = PostgresConnection.INSTANCE.executeQuery(query, userID);
        for (Map<String, Object> line : result) {
            emails.add(read((Integer) line.get("id")));
        }
        return emails;
    }
}

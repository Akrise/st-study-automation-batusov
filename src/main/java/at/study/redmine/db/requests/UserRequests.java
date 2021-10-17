package at.study.redmine.db.requests;

import at.study.redmine.db.connection.PostgresConnection;
import at.study.redmine.model.User;
import at.study.redmine.model.user.Language;
import at.study.redmine.model.user.MailNotification;
import at.study.redmine.model.user.Status;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;

@NoArgsConstructor
public class UserRequests implements Create<User>, Read<User>, Delete<User>, Update<User> {

    @Override
    public void create(User user) {
        String query = "INSERT INTO public.users\n" +
                "(id, login, hashed_password, firstname, lastname, \"admin\"" +
                ", status, last_login_on, \"language\", auth_source_id, created_on, " +
                "updated_on, \"type\", identity_url, mail_notification, salt," +
                " must_change_passwd, passwd_changed_on)\n" +
                "VALUES(DEFAULT, ? , ?, ?, ?, ? , ?, ?, ?, ? , ?, ?, ?, ? , ?, ?, ?, ?) RETURNING *;\n";
        //TODO понять, почему не работае с RETURNING id;

        List<Map<String, Object>> result = PostgresConnection.INSTANCE.executeQuery(query,
                user.getLogin(),
                user.getHashedPassword(),
                user.getFirstName(),
                user.getLastName(),
                user.getIsAdmin(),
                user.getStatus().statusCode,
                user.getLastLoginOn(),
                user.getLanguage().languageCode,
                user.getAuthSourceId(),
                user.getCreatedOn(),
                user.getUpdatedOn(),
                user.getType(),
                user.getIdentityUrl(),
                user.getMailNotification().name().toLowerCase(),
                user.getSalt(),
                user.getMustChangePassword(),
                user.getPasswordChangedOn()
        );

        Integer userID = (Integer) result.get(0).get("id");
        user.setId(userID);
    }


    @Override
    public void delete(Integer id) {
        String query = "DELETE FROM public.users WHERE id = ?";

        PostgresConnection.INSTANCE.executeUpdate(query, id);
    }

    @Override
    public void update(Integer id, User user) {
        String query = "UPDATE public.users\n" +
                "SET " +
                "login= ?, hashed_password= ?, firstname= ?, " +
                "lastname= ?, \"admin\"= ?, status= ?, " +
                "last_login_on= ?, \"language\"= ?, auth_source_id= ?, " +
                "created_on= ?, updated_on= ?, \"type\"= ?, " +
                "identity_url= ?, mail_notification= ?, salt= ?, " +
                "must_change_passwd= ?, passwd_changed_on= ?\n" +
                "WHERE id= ?\n" +
                "RETURNING id;";

        PostgresConnection.INSTANCE.executeUpdate(query,
                user.getLogin(),
                user.getHashedPassword(),
                user.getFirstName(),
                user.getLastName(),
                user.getIsAdmin(),
                user.getStatus().statusCode,
                user.getLastLoginOn(),
                user.getLanguage().languageCode,
                user.getAuthSourceId(),
                user.getCreatedOn(),
                user.getUpdatedOn(),
                user.getType(),
                user.getIdentityUrl(),
                user.getMailNotification().name().toLowerCase(),
                user.getSalt(),
                user.getMustChangePassword(),
                user.getPasswordChangedOn(),
                id
        );
    }

    @Override
    public User read(Integer id) {
        String query = "SELECT *\n" +
                "FROM public.users\n" +
                "WHERE id = ?;\n";

        List<Map<String, Object>> result = PostgresConnection.INSTANCE.executeQuery(query, id);
        if (result.size() == 0){
            throw new NoSuchElementException("По id " + id + " пользователей не найдено.");
        }
        User user = new User();
        user.setId((Integer)result.get(0).get("id"));
        user.setLogin(result.get(0).get("login").toString());
        user.setHashedPassword(result.get(0).get("hashed_password").toString());
        user.setFirstName(result.get(0).get("firstname").toString());
        user.setLastName(result.get(0).get("lastname").toString());
        user.setIsAdmin((Boolean) result.get(0).get("admin"));
        user.setStatus(Status.getValue((Integer) (result.get(0).get("status"))));
        Object lastLogin = result.get(0).get("last_login_in");
        user.setLastLoginOn(
                lastLogin == null ? null : LocalDateTime.parse(lastLogin.toString().substring(0, 19), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        user.setLanguage(Language.getValue(result.get(0).get("language").toString()));
        Object authSourceId = result.get(0).get("auth_source_id");
        user.setAuthSourceId(
                authSourceId == null ? null : authSourceId.toString());
        user.setCreatedOn(LocalDateTime.parse(result.get(0).get("created_on").toString().substring(0, 19), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        user.setUpdatedOn(LocalDateTime.parse(result.get(0).get("updated_on").toString().substring(0, 19), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        user.setType(result.get(0).get("type").toString());//TODO сделать через enum
        Object identityUrl = result.get(0).get("identity_url");
        user.setIdentityUrl(
                identityUrl == null ? null : identityUrl.toString());
        user.setMailNotification(MailNotification.valueOf(result.get(0).get("mail_notification").toString().toUpperCase()));
        user.setSalt(result.get(0).get("salt").toString());
        user.setMustChangePassword((Boolean) result.get(0).get("must_change_passwd"));
        Object passwordChangedOn = result.get(0).get("passwd_changed_on");
        user.setPasswordChangedOn(
                passwordChangedOn == null ? null : LocalDateTime.parse(passwordChangedOn.toString().substring(0, 19), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        return user;
    }
}

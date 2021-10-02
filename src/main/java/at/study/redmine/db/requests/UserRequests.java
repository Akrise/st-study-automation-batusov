package at.study.redmine.db.requests;

import at.study.redmine.db.connection.PostgresConnection;
import at.study.redmine.model.User;
import at.study.redmine.model.user.Language;
import at.study.redmine.model.user.MailNotification;
import at.study.redmine.model.user.Status;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
public class UserRequests implements Create<User>, Read<User>, Delete<User>, Update<User>{

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
        User user = new User();
        user.setLogin(result.get(0).get("login").toString());
        user.setHashedPassword(result.get(0).get("hashed_password").toString());
        user.setFirstName(result.get(0).get("firstname").toString());
        user.setLastName(result.get(0).get("lastname").toString());
        user.setIsAdmin((Boolean)result.get(0).get("admin"));
        user.setStatus(Status.valueOf(result.get(0).get("status").toString()));
        user.setLastLoginOn(LocalDateTime.parse(result.get(0).get("last_login_in").toString()));//TODO доделать
        user.setLanguage(Language.valueOf(result.get(0).get("language").toString()));
        user.setAuthSourceId(result.get(0).get("auth_source_id").toString());
        user.setCreatedOn(LocalDateTime.parse(result.get(0).get("created_on").toString()));
        user.setUpdatedOn(LocalDateTime.parse(result.get(0).get("updated_on").toString()));
        user.setType(result.get(0).get("type").toString());//TODO сделать через enum
        user.setIdentityUrl(result.get(0).get("identity_url").toString());
        user.setMailNotification(MailNotification.valueOf(result.get(0).get("").toString().toUpperCase()));
        user.setSalt(result.get(0).get("salt").toString());
        user.setMustChangePassword((Boolean)result.get(0).get("must_change_passwd"));
        user.setPasswordChangedOn(LocalDateTime.parse(result.get(0).get("passwd_changed_on").toString()));

        return user;
    }
}

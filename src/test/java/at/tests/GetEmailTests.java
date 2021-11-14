package at.tests;

import at.study.redmine.db.connection.PostgresConnection;
import at.study.redmine.model.Email;
import at.study.redmine.model.User;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class GetEmailTests {

    Email email;
    User user;

    @Test
    public void getEmailTest(){
        user = new User().create();
        email = new Email(user).create();
        String query = "SELECT * " +
                "FROM public.email_addresses " +
                "WHERE user_id = ?;";
        Integer id = user.getId();
        List<Map<String, Object>> result = PostgresConnection.INSTANCE.executeQuery(query, id);
        System.out.println();
    }
}

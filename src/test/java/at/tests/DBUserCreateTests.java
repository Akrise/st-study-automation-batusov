package at.tests;

import at.study.redmine.model.Email;
import at.study.redmine.model.Token;
import at.study.redmine.model.User;
import org.testng.annotations.Test;

import java.util.Random;

public class DBUserCreateTests {

    @Test
    public void createUserDBTest(){
        User user = new User();
        user.setPassword("Aa123qqqqqqq!");
        user.setFirstName("Name" + new Random().nextInt(100));
        user.setLastName("BAT" + new Random().nextInt(100));

        Token token = new Token(user);

        Email email = new Email(user);
        email.setAddress("BAToer@mail1.ru");
        email.setIsDefault(true);
        Email email2 = new Email(user);
        email2.setIsDefault(false);
        email.setAddress("BAToer@mail2.ru");
        Email email3 = new Email(user);
        email3.setIsDefault(false);
        email.setAddress("BAToer@mail3.ru");

        user.create();

        System.out.println(user.getLogin());
        System.out.println(user.getPassword());
    }

    public void deleteUpdateUserTest(){
        //TODO закончил тут
    }
}

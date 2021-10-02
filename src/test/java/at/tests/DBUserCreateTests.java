package at.tests;

import at.study.redmine.model.Email;
import at.study.redmine.model.Token;
import at.study.redmine.model.User;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
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

    @Test
    public void deleteUpdateUserTest(){
        User user = new  User();
        System.out.println(user.getLogin());
        user.create();
        System.out.println(user.getFirstName());
        User newUser = new User();
        newUser.setFirstName("ChangedName");
        System.out.println("ID user:" + user.getId());
        user.update(newUser);


        user.delete();
//        newUser.delete();
        System.out.println("Done");
    }
}

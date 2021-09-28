package at.tests;

import at.study.redmine.db.requests.TokenRequests;
import at.study.redmine.model.Token;
import at.study.redmine.model.User;
import org.testng.annotations.Test;

import java.util.List;

public class TokenRequestsTests {

    @Test
    public void tokenCreationTest(){
        User user = new User();
        user.setId(26477);
        TokenRequests tokenRequests = new TokenRequests(user);
        List<Token> tokens = tokenRequests.readAll();
        tokenRequests.create(new Token(user));

        List<Token> newTokens = tokenRequests.readAll();
        System.out.println();
    }

}

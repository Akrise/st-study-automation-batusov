package at.study.redmine.model;

import at.study.redmine.db.requests.TokenRequests;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import static at.study.redmine.utils.StringUtils.randomHexString;

@Getter
@Setter
@Accessors(chain = true)
public class Token extends CreatableEntity implements Creatable<Token> {

    private Integer userId;
    private TokenType action = TokenType.API;
    private String value = randomHexString(40);

    public Token(User user) {
        this.userId = user.id;
        user.getTokens().add(this);
    }

    @Override
    public Token create() {
       new TokenRequests().create(this);
       return this;
    }

    public enum TokenType {
        API,
        FEEDS,
        SESSION
    }

}

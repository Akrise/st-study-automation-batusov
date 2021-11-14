package at.study.redmine.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import at.study.redmine.db.requests.UserRequests;
import at.study.redmine.model.user.Language;
import at.study.redmine.model.user.MailNotification;
import at.study.redmine.model.user.Status;
import at.study.redmine.model.user.Type;
import io.qameta.allure.Step;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import static at.study.redmine.utils.StringUtils.randomEnglishString;
import static at.study.redmine.utils.StringUtils.randomHexString;
import static org.apache.commons.codec.digest.DigestUtils.sha1Hex;

@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class User extends CreatableEntity implements Creatable<User>, Readable<User>, Updatable<User>, Deletable<User> {

    private String login = "BATAutoLogin" + randomEnglishString(10);
    private String password = "1qaz@WSX";
    private String salt = randomHexString(32);
    private String hashedPassword = getHashedPassword();
    private String firstName = "AutoF" + randomEnglishString(10);
    private String lastName = "BATAutoL" + randomEnglishString(10);
    private Boolean isAdmin = false;
    private Status status = Status.ACTIVE;
    private LocalDateTime lastLoginOn;
    private Language language = Language.RUSSIAN;
    private String authSourceId;
    private Type type = Type.USER;
    private String identityUrl;
    private MailNotification mailNotification = MailNotification.NONE;
    private Boolean mustChangePassword = false;
    private LocalDateTime passwordChangedOn;
    private List<Token> tokens = new ArrayList<>();
    private List<Email> emails = new ArrayList<>();

    public String getHashedPassword() {
        return sha1Hex(salt + sha1Hex(password));
    }

    @Override
    @Step("Создать пользователя в БД")
    public User create() {
        new UserRequests().create(this);
        tokens.forEach(i -> i.setUserId(id));
        tokens.forEach(Token::create);
        emails.forEach(i -> i.setUserId(id));
        emails.forEach(Email::create);
        return this;
    }

    @Override
    @Step("Удалить пользователя из БД")
    public void delete() {
        new UserRequests().delete(id);
    }

    @Override
    @Step("Обновить пользователя в БД")
    public void update(User user) {
        new UserRequests().update(id, user);
    }

    @Override
    @Step("Получить пользователя из БД")
    public User read() {
        return new UserRequests().read(id);
    }
}

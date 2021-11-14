package at.study.redmine.model;

import at.study.redmine.db.requests.EmailRequests;
import io.qameta.allure.Step;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import static at.study.redmine.utils.StringUtils.randomEmail;

@Getter
@Setter
@Accessors(chain = true)
public class Email extends CreatableEntity implements Creatable<Email> {

    private Integer userId;
    private String address = randomEmail();
    private Boolean isDefault = true;
    private Boolean notify = true;

    public Email(User user) {
        this.userId = user.id;
        user.getEmails().add(this);
    }

    public Email(){
    }

    @Override
    @Step("Создан email в БД")
    public Email create() {
        new EmailRequests().create(this);
        return this;
    }

    @Override
    public String toString() {
        return address;
    }
}

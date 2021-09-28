package at.study.redmine.model;

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
    }

    @Override
    public Email create() {
        // TODO: Реализовать через SQL
        throw new UnsupportedOperationException();
    }
}

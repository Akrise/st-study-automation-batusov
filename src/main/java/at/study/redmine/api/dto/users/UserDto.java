package at.study.redmine.api.dto.users;

import at.study.redmine.model.Email;
import at.study.redmine.model.User;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@AllArgsConstructor
public class UserDto {

    private Integer id;
    private String login;
    private Boolean admin;
    private Integer status;

    @SerializedName("firstname")
    private String firstName;

    @SerializedName("lastname")
    private String lastName;

    private String mail;
    private String password;

    @SerializedName("created_on")
    private LocalDateTime createdOn;

    @SerializedName("updated_on")
    private LocalDateTime updatedOn;

    @SerializedName("api_key")
    private String apiKey;

    public UserDto(User user) {
        if(user.getId()!=null){
            id = user.getId();
        }
        login = user.getLogin();
        admin = user.getIsAdmin();
        status = user.getStatus().statusCode;
        firstName = user.getFirstName();
        lastName = user.getLastName();
        password = user.getPassword();
        if(user.getEmails()!=null && user.getEmails().stream().anyMatch(Email::getIsDefault)) {
            mail = user.getEmails().stream().filter(Email::getIsDefault).findFirst().get().toString();
        }
        createdOn = user.getCreatedOn();
        updatedOn = user.getUpdatedOn();
    }
}

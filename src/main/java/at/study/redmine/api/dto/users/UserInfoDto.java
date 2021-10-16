package at.study.redmine.api.dto.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class UserInfoDto {

    private UserDto user;
    private List<String> errors;

    public UserInfoDto(UserDto user) {
        this.user = user;
    }
}

package at.study.redmine.api.dto.users;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain=true)
public class UsersListDto {

    private List<UserDto> users;

    @SerializedName("total_count")
    private Integer totalCount;

    private Integer offset;
    private Integer limit;

}

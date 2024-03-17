package app.user.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserVO {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String createdAt;
    private String updatedAt;
}

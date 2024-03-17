package app.user.model;

import app.admin.enums.Role;
import app.common.model.BaseUser;
import app.user.enums.Gender;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class User extends BaseUser {
    private Gender gender;
}

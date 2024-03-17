package app.common.model;

import app.admin.enums.Role;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public abstract class BaseUser extends BaseModel{
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}

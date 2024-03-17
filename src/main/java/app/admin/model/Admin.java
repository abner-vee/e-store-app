package app.admin.model;

import app.admin.enums.Role;
import app.common.model.BaseUser;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Admin extends BaseUser {
    private Role role;
}

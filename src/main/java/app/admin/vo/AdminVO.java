package app.admin.vo;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdminVO {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String role;
    private String createdAt;
    private String updatedAt;
}

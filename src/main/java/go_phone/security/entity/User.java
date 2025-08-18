package go_phone.security.entity;

import go_phone.common.model.BaseEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User extends BaseEntity{

    Integer userId;
    String username;
    String email;
    String password; // hashed
    String fullName;
    String phone;
    String address;

    String createdBy;

    Integer isActive;
    Integer isDeleted;

}

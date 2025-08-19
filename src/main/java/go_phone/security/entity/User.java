package go_phone.security.entity;

import go_phone.common.model.BaseEntity;
import lombok.experimental.FieldDefaults;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder;
import lombok.AccessLevel;

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

    java.util.List<go_phone.security.entity.Role> roles;

    String createdBy;

    Integer isActive;
    Integer isDeleted;

}

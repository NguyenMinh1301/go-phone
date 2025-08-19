package go_phone.security.entity;

import lombok.experimental.FieldDefaults;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AccessLevel;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Role {

    Integer roleId;
    String roleCode;
    String roleName;
    Integer priority;

    Integer isActive;

}

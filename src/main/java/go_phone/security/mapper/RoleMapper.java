package go_phone.security.mapper;

import go_phone.security.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoleMapper {

    List<Role> findByUserId(@Param("userId") Integer userId);

    Role findByCode(@Param("roleCode") String roleCode);

    int insertUserRole(@Param("userId") Integer userId, @Param("roleId") Integer roleId);

    int deleteUserRoles(@Param("userId") Integer userId);

}

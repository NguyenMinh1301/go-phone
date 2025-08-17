package go_phone.security.mapper;

import go_phone.common.mapper.BaseMapper;
import go_phone.security.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    User findByUsername(@Param("username") String username);

    User findByEmail(@Param("email") String email);

    User findByUsernameOrEmail(@Param("ue") String usernameOrEmail);

    int existsByUsername(@Param("username") String username);

    int existsByEmail(@Param("email") String email);

}

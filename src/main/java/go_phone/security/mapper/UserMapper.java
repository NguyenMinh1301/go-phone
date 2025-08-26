package go_phone.security.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import go_phone.common.mapper.BaseMapper;
import go_phone.security.entity.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    User findByUsername(@Param("username") String username);

    User findByEmail(@Param("email") String email);

    int existsByUsername(@Param("username") String username);

    int existsByEmail(@Param("email") String email);

    int updatePasswordById(@Param("userId") Integer userId, @Param("password") String hashed);
}

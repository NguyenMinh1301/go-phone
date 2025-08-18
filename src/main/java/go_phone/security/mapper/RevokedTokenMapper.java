package go_phone.security.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;

@Mapper
public interface RevokedTokenMapper {

    int isRevoked(@Param("token") String token);

    int deleteExpired();

    int insert(@Param("token") String token, @Param("expiresAt") LocalDateTime expiresAt);

}

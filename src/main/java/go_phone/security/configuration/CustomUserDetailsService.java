package go_phone.security.configuration;

import go_phone.common.exception.AppException;
import go_phone.common.exception.ErrorCode;
import go_phone.security.entity.User;
import go_phone.security.mapper.UserMapper;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserMapper userMapper;

    public CustomUserDetailsService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.findByUsername(username);
        if (user == null || (user.getIsDeleted() != null && user.getIsDeleted() == 1)) {
            throw new AppException(ErrorCode.NOT_FOUND);
        }
        return new CustomUserDetails(user);
    }
}

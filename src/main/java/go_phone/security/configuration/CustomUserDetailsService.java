package go_phone.security.configuration;

import go_phone.common.exception.AppException;
import go_phone.common.exception.ErrorCode;
import go_phone.security.entity.Role;
import go_phone.security.entity.User;
import go_phone.security.mapper.RoleMapper;
import go_phone.security.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserMapper userMapper;
    private final RoleMapper roleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.findByUsername(username);
        if (user == null || (user.getIsDeleted() != null && user.getIsDeleted() == 1)) {
            throw new AppException(ErrorCode.NOT_FOUND);
        }

        List<Role> roles = roleMapper.findByUserId(user.getUserId());
        user.setRoles(roles);

        return new CustomUserDetails(user);
    }
}

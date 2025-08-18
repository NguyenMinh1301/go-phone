package go_phone.security.config;

import go_phone.security.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class CustomUserDetailsService implements UserDetailsService {

    private final go_phone.security.mapper.UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        User u = userMapper.findByUsernameOrEmail(usernameOrEmail);
        if (u == null) throw new UsernameNotFoundException("User not found");
        return new UserDetailsImpl(u);
    }

}

package go_phone.security.config;

import go_phone.security.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class CustomUserDetails implements UserDetails {

    private final User user;
    public CustomUserDetails(User u) { this.user = u; }

    @Override public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList(); // Sẽ có role trong update...
    }
    @Override public String getPassword() { return user.getPassword(); }
    @Override public String getUsername() { return user.getUsername(); }
    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() {
        return Boolean.TRUE.equals(user.getIsActive()) && !Boolean.TRUE.equals(user.getIsDeleted());
    }

}



package com.phantom.security.model;

import com.phantom.security.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * TODO
 *
 * @author lei.tan
 * @version 1.0
 * @date 2023/1/11 17:45
 */
@Getter
@Setter
@NoArgsConstructor
public class CustomUserDetails extends User implements UserDetails {

    public CustomUserDetails(User user) {
        super(user.getId(), user.getUsername(), user.getPassword(), user.getNickname(), user.getEmail(), user.getPhone(), user.getCreateTime(), user.getUpdateTime());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return super.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

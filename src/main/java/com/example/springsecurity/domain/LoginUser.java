package com.example.springsecurity.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.example.springsecurity.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
public class LoginUser implements UserDetails {

    private User user;
    private List<String> permissions;

    @JSONField
    private List<SimpleGrantedAuthority> grantedAuthorities;

    public LoginUser(User user, List<String> permissions) {
        this.user = user;
        this.permissions = permissions;
    }

    @Override
//    @JSONField
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.grantedAuthorities != null) {
            return this.grantedAuthorities;
        }

       this.grantedAuthorities = new ArrayList<>();
        // 把permissions中的全信息封装到SimpleGrantedAuthority
        for (String permission : permissions) {
            grantedAuthorities.add(new SimpleGrantedAuthority(permission));
        }

        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
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

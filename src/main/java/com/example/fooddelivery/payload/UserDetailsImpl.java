package com.example.fooddelivery.payload;

import com.example.fooddelivery.model.Customer;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;


@Getter
@Setter
public class UserDetailsImpl implements UserDetails {
    private String userId;
    private String email;
    private String password;
    private GrantedAuthority authorities;
    public UserDetailsImpl(String userId, String email, String password, GrantedAuthority authorities) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }
    public static UserDetailsImpl build(Customer customer){
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + customer.getRole().name());
        return new UserDetailsImpl(
                customer.getId(),
                customer.getEmail(),
                customer.getPassword(),
                authority
        );
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(authorities);
    }
    @Override
    public String getPassword() {
        return password;
    }
    @Override
    public String getUsername() {
        return email;
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

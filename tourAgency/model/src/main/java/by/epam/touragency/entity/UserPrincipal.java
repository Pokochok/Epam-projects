package by.epam.touragency.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Principal;
import java.util.Collection;
import java.util.Collections;

public class UserPrincipal implements UserDetails, Principal {

    private User user;

    public UserPrincipal(User user) {
        super();
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_" + user.getRole().toString()));
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getLogin();
    }

    public String getRealUserName() {
        return user.getName();
    }

    public String getUserEmail() {
        return user.getEmail();
    }

    public String getUserPhoneNumber() {
        return user.getPhoneNumber();
    }

    public String getUserSurname() {
        return user.getSurname();
    }

    public long getUserId() {
        return user.getId();
    }

    public Role getUserRole(){
        return user.getRole();
    }

    public String getUserStatus(){
        return user.getStatus();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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


    @Override
    public String getName() {
        return user.getName();
    }
}

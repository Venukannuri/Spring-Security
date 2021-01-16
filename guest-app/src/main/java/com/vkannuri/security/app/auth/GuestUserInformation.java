package com.vkannuri.security.app.auth;

import com.vkannuri.security.app.domain.AuthGroup;
import com.vkannuri.security.app.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/** @author Venu Kannuri . */
public class GuestUserInformation implements UserDetails {

    private User user;
    private List<AuthGroup> authGroups;

    public GuestUserInformation(User user, List<AuthGroup> authGroups) {
        this.user = user;
        this.authGroups = authGroups;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (authGroups == null) {
            return Collections.emptySet();
        }
        Set<SimpleGrantedAuthority> grantedAuthorities = new HashSet<>();
        authGroups.forEach(
                authGroup -> {
                    grantedAuthorities.add(new SimpleGrantedAuthority(authGroup.getAuthGroup()));
                });
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getUserName();
    }

    /*
     * Just For Testing purpose making all those return values true
     * In real time application all these vales get loaded from database
     *   * */

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

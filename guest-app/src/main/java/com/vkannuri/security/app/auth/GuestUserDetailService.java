package com.vkannuri.security.app.auth;

import com.vkannuri.security.app.domain.AuthGroup;
import com.vkannuri.security.app.domain.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/** @author Venu Kannuri . */
@Service
public class GuestUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;
    private final AuthGroupRepository authGroupRepository;

    public GuestUserDetailService(
            UserRepository userRepository, AuthGroupRepository authGroupRepository) {
        super();
        this.userRepository = userRepository;
        this.authGroupRepository = authGroupRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findAllByUserName(userName);
        if (user == null) {
            throw new UsernameNotFoundException("cannot find username: " + userName);
        }
        List<AuthGroup> authGroups = authGroupRepository.findAllByUsername(userName);
        return new GuestUserInformation(user,authGroups);
    }
}

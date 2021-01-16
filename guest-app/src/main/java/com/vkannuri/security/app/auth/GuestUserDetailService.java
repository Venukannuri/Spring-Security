package com.vkannuri.security.app.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/** @author Venu Kannuri . */
@Service
public class GuestUserDetailService implements UserDetailsService {

  private final UserRepository userRepository;

  public GuestUserDetailService(UserRepository userRepository) {
    super();
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
    User user = userRepository.findAllByUserName(userName);
    if (user == null) {
      throw new UsernameNotFoundException("User not found...!!!!");
    }
    return new GuestUserInformation(user);
  }
}

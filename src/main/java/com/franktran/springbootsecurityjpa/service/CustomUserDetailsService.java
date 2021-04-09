package com.franktran.springbootsecurityjpa.service;

import com.franktran.springbootsecurityjpa.model.CustomUserDetails;
import com.franktran.springbootsecurityjpa.model.User;
import com.franktran.springbootsecurityjpa.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CustomUserDetailsService implements UserDetailsService {

  private final UserRepository userRepository;

  public CustomUserDetailsService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByUsername(username);
    if (Objects.isNull(user)) {
      throw new UsernameNotFoundException(String.format("User with username %s does not exists"));
    }
    return new CustomUserDetails(user);
  }
}

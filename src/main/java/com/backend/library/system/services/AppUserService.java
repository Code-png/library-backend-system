package com.backend.library.system.services;

import com.backend.library.system.entities.AppUser;
import com.backend.library.system.repositories.AppUserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class AppUserService implements UserDetailsService {
    @Autowired
    AppUserRepository appUserRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = appUserRepository.getUserByUsername(username);
        if(user == null)
            throw new UsernameNotFoundException("User with username: " + username + " not found");
        return user;
    }
}

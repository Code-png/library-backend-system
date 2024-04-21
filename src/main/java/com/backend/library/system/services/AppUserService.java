package com.backend.library.system.services;

import com.backend.library.system.entities.AppUser;
import com.backend.library.system.repositories.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
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

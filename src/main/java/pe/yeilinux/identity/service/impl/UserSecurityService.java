package pe.yeilinux.identity.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import pe.yeilinux.identity.service.UserService;

import java.util.ArrayList;

@Service
public class UserSecurityService implements UserDetailsService {
    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        pe.yeilinux.identity.domain.User user = this.userService.findByUserName(username);

        if(user == null){
            throw new UsernameNotFoundException("Error");
        }
        return new User(user.getUserName(),user.getPassword(), new ArrayList<>());
    }
}

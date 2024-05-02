package net.yassine.nurseryapp.security;

import net.yassine.nurseryapp.entities.UserApp;
import net.yassine.nurseryapp.repositories.UserappRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;


@Service
public class UserService implements UserDetailsService {


    @Autowired
    private UserappRepository userappRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserApp userApp =userappRepository.findByUsername(username).orElseThrow(()-> {
            return new UsernameNotFoundException("User not found with username: " + username);
        });

        String [] roles=userApp.getRoles().stream().map(r->r.getRoleName()).toArray(String[]::new);

        UserDetails userDetails= User.builder()
                .username(userApp.getUsername())
                .password(userApp.getPassword())
                .roles(roles)
                .build();
        return userDetails;

    }













}

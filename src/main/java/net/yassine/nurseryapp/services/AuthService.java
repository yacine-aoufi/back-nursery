package net.yassine.nurseryapp.services;


import net.yassine.nurseryapp.Dto.UserDto;
import net.yassine.nurseryapp.security.JwtService;
import net.yassine.nurseryapp.Dto.LoginDto;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AuthService {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthService(JwtService jwtService,AuthenticationManager authenticationManager) {
        this.jwtService = jwtService;
        this.authenticationManager=authenticationManager;
    }


public UserDto Login(LoginDto loginDto){

        Authentication authentication=authenticationManager.authenticate(
               new UsernamePasswordAuthenticationToken(loginDto.getUsername(),loginDto.getPassword())
        );
      return  jwtService.generateJwt(authentication);
}














}

package net.yassine.nurseryapp.security;


import net.yassine.nurseryapp.Dto.UserDto;
import net.yassine.nurseryapp.entities.Role;
import net.yassine.nurseryapp.entities.UserApp;
import net.yassine.nurseryapp.repositories.UserappRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class JwtService {
    private JwtEncoder jwtEncoder;
private UserappRepository userappRepository;

    public JwtService(JwtEncoder jwtEncoder,UserappRepository userappRepository) {
        this.jwtEncoder = jwtEncoder;
        this.userappRepository=userappRepository;
    }

    public UserDto generateJwt(Authentication auth) {
        // hash map that have key named token and value of token
//        Map<String, Objects> idToken = new HashMap<>();
        ///// create instant of now
        Instant now = Instant.now();

        // scope=auth.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining("
        // "));
        ///////////////// extract rolles
        String scope = auth.getAuthorities().stream().map(a -> a.getAuthority()).collect(Collectors.joining(" "));
        /////////////////////////////////////////////////
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuedAt(now).expiresAt(now.plus(10, ChronoUnit.HOURS))
                .subject(auth.getName())
                .claim("roles", scope)
                .build();
        // claim ajouter les roles





        String tokken = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        UserApp userApp=userappRepository.findByUsername(auth.getName()).orElse(null);

        assert userApp != null;
        UserDto userDto= UserDto.builder()
                .Id(userApp.getId())
                .firstName(userApp.getFirstName())
                .lastName(userApp.getLastName())
                .roles(List.of(userApp.getRoles().toArray(new Role[0]))).Token(tokken)
                .build();
//        idToken.put("user",userDto);
        return userDto;
    }







}

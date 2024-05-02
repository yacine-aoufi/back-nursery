package net.yassine.nurseryapp.Dto;


import lombok.*;
import net.yassine.nurseryapp.entities.Role;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserDto {
    private Integer Id;
    private String firstName;
    private String lastName;
    private List<Role> roles;
    private String Token;
}

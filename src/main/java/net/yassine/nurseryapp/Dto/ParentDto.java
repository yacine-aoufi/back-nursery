package net.yassine.nurseryapp.Dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.yassine.nurseryapp.entities.*;
import net.yassine.nurseryapp.entities.enums.Gender;
import net.yassine.nurseryapp.entities.enums.Status;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ParentDto {

    private Integer id;
    private String firstName;
    private String lastName;
    private Date birthdate;
    private Gender gender;
    private String phoneNumber;
    private String address;
    private String email;
    private String username;
    private String password;
    private List<Role> roles;
    private Status status;
    private List<Child> children;
    private List<ChildRegistration> childRegistrations;
    private List<PreRegistration> preRegistrations;


}


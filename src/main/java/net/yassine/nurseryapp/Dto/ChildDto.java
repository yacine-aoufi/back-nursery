package net.yassine.nurseryapp.Dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.yassine.nurseryapp.entities.*;
import net.yassine.nurseryapp.entities.enums.Gender;
import net.yassine.nurseryapp.entities.enums.Status;

import java.util.Date;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ChildDto {

    private Integer Id;
    private String firstName;
    private String lastName;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date Birthdate;
    private Gender gender;
    private Status status;
    private byte[] Qrcode;
//    private Parent parent;
    private ChildRegistration childRegistration;
//    private List<ActivitiesRegistration> activitiesRegistrations;
//    private PreRegistration preRegistration;
//    private List<NewsRegistration> newsRegistrationList;
    private Section section;

}

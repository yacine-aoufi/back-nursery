package net.yassine.nurseryapp.Dto;



import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Temporal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.yassine.nurseryapp.entities.Child;
import net.yassine.nurseryapp.entities.Parent;
import net.yassine.nurseryapp.entities.enums.Gender;
import org.springframework.format.annotation.DateTimeFormat;


@Setter @Getter @AllArgsConstructor @NoArgsConstructor
public class PreRegisterDto {
    private String firstNameChild;
    private String lastNameChild;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date Birthdate;
    private Gender gender;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private Integer idsec;
}

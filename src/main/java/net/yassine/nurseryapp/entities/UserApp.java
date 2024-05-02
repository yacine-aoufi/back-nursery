package net.yassine.nurseryapp.entities;

import jakarta.persistence.*;
import lombok.*;
import net.yassine.nurseryapp.entities.enums.Gender;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;


@Entity
@Setter @Getter @AllArgsConstructor @NoArgsConstructor @Builder
//with Default
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(name = "dtype",length = 4)
public class UserApp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    private String firstName;
    private String lastName;
     @Temporal(TemporalType.DATE)
//     @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date birthdate;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String phoneNumber;
    private String Address;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String username;
    private String Password;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = {@JoinColumn(name = "user_id")}
            ,inverseJoinColumns = {@JoinColumn(name = "role_id")})

    private List<Role> roles;




}

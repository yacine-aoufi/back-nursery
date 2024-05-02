package net.yassine.nurseryapp.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import net.yassine.nurseryapp.entities.enums.Gender;
import net.yassine.nurseryapp.entities.enums.Status;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.util.Date;
import java.util.List;

@Entity
@Setter @Getter @AllArgsConstructor @NoArgsConstructor @ToString
@Builder
public class Child {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    private String firstName;

    private String lastName;

    @Temporal(TemporalType.DATE)
    private Date Birthdate;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private Status status;

    private String chargilyId;
    @JsonBackReference
    @ManyToOne
    private Parent parent;

    @JsonManagedReference
@OneToOne(mappedBy = "child")
    private ChildRegistration childRegistration;
    @JsonManagedReference
@OneToMany(mappedBy = "Child")
    private List<ActivitiesRegistration> activitiesRegistrations;
    @JsonManagedReference
@OneToOne(mappedBy = "child")
    private PreRegistration preRegistration;
    @JsonManagedReference
    @OneToMany(mappedBy = "child")
    private List<NewsRegistration> newsRegistrationList;

    @JsonBackReference
@ManyToOne
    private Section section;

}

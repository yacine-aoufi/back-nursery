package net.yassine.nurseryapp.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import net.yassine.nurseryapp.entities.enums.Gender;
import net.yassine.nurseryapp.entities.enums.Status;

import java.util.Date;
import java.util.List;

@Entity
@Setter
@Getter
//@DiscriminatorValue("pare")
public class Parent extends  UserApp{
    public Parent(Integer Id, String firstName, String lastName, Date birthdate, Gender gender, String phoneNumber, String Address, String email,String username, String Password, List<Role> roles,String Checkout, Status status
            ,List<Child> children, List<ChildRegistration> childRegistration,List<PreRegistration> preRegistrations) {
        super(Id, firstName, lastName, birthdate, gender, phoneNumber, Address, email,username, Password, roles);
        this.status = status;
        this.children=children;
        this.childRegistration=childRegistration;
        this.preRegistrations=preRegistrations;
        this.Checkout=Checkout;
    }
    public Parent() {

    }


    @Column(unique = true)
    private String Checkout;


    @Enumerated(EnumType.STRING)
private Status status;
    @JsonManagedReference
    @JsonIgnore
@OneToMany(mappedBy = "parent")
    private List<Child> children;

    @JsonManagedReference
@OneToMany(mappedBy = "parent")
    private List<ChildRegistration> childRegistration;

    @JsonManagedReference
@OneToMany(mappedBy = "parent")
    private List<PreRegistration> preRegistrations;


}

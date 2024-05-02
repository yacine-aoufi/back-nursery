package net.yassine.nurseryapp.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import net.yassine.nurseryapp.entities.enums.PayTime;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class ChildRegistration {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Temporal(TemporalType.DATE)
    private Date lastPaymentDate;

    private Double Amount;
    private boolean isPayed;

    private String PaymentMethode;

    @Enumerated(EnumType.STRING)
    private PayTime payTime;

    @JsonBackReference
    @ManyToOne
    private Parent parent;
    @JsonIgnore
    @OneToOne
    private Child child;













}

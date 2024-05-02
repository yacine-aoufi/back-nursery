package net.yassine.nurseryapp.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.yassine.nurseryapp.entities.enums.PayTime;

import java.util.Date;
import java.util.List;

@Entity
@Setter @Getter @AllArgsConstructor @NoArgsConstructor
public class ActivitiesRegistration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Temporal(TemporalType.DATE)
    private Date RegistrationDate;

    @Temporal(TemporalType.DATE)
    private Date lastPaymentDate;

    @Enumerated(EnumType.STRING)
    private PayTime payTime;

    private Boolean isPayed;

    @ManyToOne
    private ScolaireActivity scolaireActivity;
    
    @ManyToOne
    private Child Child;






}

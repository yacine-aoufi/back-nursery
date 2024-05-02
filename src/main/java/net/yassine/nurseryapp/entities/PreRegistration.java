package net.yassine.nurseryapp.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jdk.jfr.BooleanFlag;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Entity
@Setter @Getter @AllArgsConstructor @NoArgsConstructor @Builder
public class PreRegistration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Temporal(TemporalType.DATE)
    private Date preRegistraionDate;

    private Double preAmount;

    private Boolean isPayed;

@OneToOne
    private Child child;
    @JsonBackReference
@ManyToOne
    private Parent parent;



























}

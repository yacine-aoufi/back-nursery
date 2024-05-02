package net.yassine.nurseryapp.entities;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.yassine.nurseryapp.entities.enums.ActivityType;
import net.yassine.nurseryapp.entities.enums.PayTime;

import java.util.List;


@Entity
@Setter @Getter @AllArgsConstructor @NoArgsConstructor
public class ScolaireActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

private String Description;
private Double Price;
private Integer Capacity;


@OneToMany(mappedBy = "scolaireActivity")
    private List<ActivitiesRegistration> activitiesRegistration;




}

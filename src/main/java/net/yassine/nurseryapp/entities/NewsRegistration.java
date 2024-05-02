package net.yassine.nurseryapp.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Setter @Getter @AllArgsConstructor @NoArgsConstructor @Builder
public class NewsRegistration {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    private Boolean isPayed;
    @Temporal(TemporalType.DATE)
    private Date registrationDate;
  @ManyToOne
    private Child child;
@ManyToOne
    private News news;







}

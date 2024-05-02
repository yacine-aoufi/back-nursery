package net.yassine.nurseryapp.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Setter @Getter @AllArgsConstructor @NoArgsConstructor @Builder
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    private String sectionName;
    private String Ages;
private  Integer SectionPlaces;
    @JsonManagedReference
    @JsonIgnore
    @OneToMany(mappedBy = "section")
private List<Child> children;




}

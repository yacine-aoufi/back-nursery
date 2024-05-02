package net.yassine.nurseryapp.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Setter @Getter @AllArgsConstructor @NoArgsConstructor
public class Role {
@Id
private String roleName;



}

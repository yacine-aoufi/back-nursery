package net.yassine.nurseryapp.entities;


import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.yassine.nurseryapp.entities.enums.NewsType;

import java.util.Date;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer Id;
private String Title;
@Temporal(TemporalType.DATE)
private Date newsDate;
private String Description;
private NewsType newsType;
private Integer placeNumber;
private  Double Price;


@OneToMany(mappedBy = "news")
    private List<NewsRegistration> newsRegistrations;




}

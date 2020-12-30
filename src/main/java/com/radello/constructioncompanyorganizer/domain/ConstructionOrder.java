package com.radello.constructioncompanyorganizer.domain;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class ConstructionOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;
    private String title;
    private String addres;
    private Date startDate;
    private Date scheduledEndDate;

    @OneToOne (cascade = CascadeType.ALL)
    private Income income;

    @OneToMany(mappedBy = "constructionOrder", cascade = CascadeType.ALL)
    private Set<IndicativeCost> indicateCostSet = new HashSet<>();
    //private Set<Income> incomeSet;
}





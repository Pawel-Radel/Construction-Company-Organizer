package com.radello.constructioncompanyorganizer.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class ConstructionOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    private String title;
    private String addres;
    private LocalDate startDate;
    private LocalDate scheduledEndDate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "constructionOrder")
    private Set<Income> incomes = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "constructionOrder")
    private Set<IndicativeCost> indicativeCosts = new HashSet<>();

    public ConstructionOrder addIncome(Income income) {

        income.setConstructionOrder(this);
        this.incomes.add(income);
        return this;
    }

    public ConstructionOrder addIndicativeCost(IndicativeCost indcCost) {
        indcCost.setConstructionOrder(this);
        this.indicativeCosts.add(indcCost);
        return this;
    }

}





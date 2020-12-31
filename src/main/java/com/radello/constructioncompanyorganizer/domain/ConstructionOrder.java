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
    private int ID;
    private String title;
    private String addres;
    private LocalDate startDate;
    private LocalDate scheduledEndDate;

    @OneToOne(cascade = CascadeType.ALL)
    private Income income;

    @OneToMany
    @JoinColumn(name = "Construction_Order_ID")
    private Set<IndicativeCost> indicateCosts = new HashSet<>();

    public void setIncome(Income income) {
        if (income != null) {
            this.income = income;
            income.setConstructionOrder(this);
        }
    }

    public ConstructionOrder addIndicativeCost(IndicativeCost indcCost) {
        indcCost.setConstructionOrder(this);
        this.indicateCosts.add(indcCost);
        return this;
    }

}





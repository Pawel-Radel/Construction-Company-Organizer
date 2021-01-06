package com.radello.constructioncompanyorganizer.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

// class representing costs that will only be used to estimate profits from the construction order (will not be accounted in budget)

@Getter
@Setter
@NoArgsConstructor
@Entity
public class IndicativeCost{

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long ID;
    //Indicate for what will be this Indicate Cost ( Not for accounting for cash resources)
    private String ForWhat;

    private int amount;

    @ManyToOne
    private ConstructionOrder constructionOrder;


}

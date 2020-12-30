package com.radello.constructioncompanyorganizer.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

// class representing costs that will only be used to estimate profits from the construction order (will not be accounted)
@Entity
@Getter
@Setter
@NoArgsConstructor
public class IndicativeCost{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;
    //Indicate for what will be this Indicate Cost ( Not for accounting for cash resources)
    private String ForWhat;
    @ManyToOne
    private ConstructionOrder constructionOrder;


}

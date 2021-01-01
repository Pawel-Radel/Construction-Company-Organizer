package com.radello.constructioncompanyorganizer.commands;

import com.radello.constructioncompanyorganizer.domain.ConstructionOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
public class IndicativeCostCommand{

    private int ID;
    //Indicate for what will be this Indicate Cost ( Not for accounting for cash resources)
    private String ForWhat;
    private ConstructionOrderCommand constructionOrderCommand;
    //@ManyToOne
    //  private ConstructionOrder constructionOrder;


}

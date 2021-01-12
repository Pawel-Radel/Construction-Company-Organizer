package com.radello.constructioncompanyorganizer.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class IndicativeCostCommand{

    private Long ID;
    //Indicate for what will be this Indicate Cost ( Not for accounting for cash resources)
    private String forWhat;

    private int amount;
    private ConstructionOrderCommand constructionOrderCommand;
    //@ManyToOne
    //  private ConstructionOrder constructionOrder;


}

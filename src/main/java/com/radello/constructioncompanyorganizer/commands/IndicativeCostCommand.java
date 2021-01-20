package com.radello.constructioncompanyorganizer.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class IndicativeCostCommand{

    private Long ID;
    private String forWhat;

    private int amount;
    private ConstructionOrderCommand constructionOrderCommand;



}

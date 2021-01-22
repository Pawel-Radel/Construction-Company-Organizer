package com.radello.constructioncompanyorganizer.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class IndicativeCostCommand{

    private Long ID;

    @NotBlank
    private String forWhat;

    @Min(value = 1)
    @Max(value = 2147483647)
    private int amount;
    private ConstructionOrderCommand constructionOrderCommand;



}

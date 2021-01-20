package com.radello.constructioncompanyorganizer.commands;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//Class represents Budget of customer
@Getter
@Setter
@NoArgsConstructor
public class BudgetCommand {


    private Long ID;

    private int amount;
}

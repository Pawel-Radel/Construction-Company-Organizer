package com.radello.constructioncompanyorganizer.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class ConstructionOrderCommand {


    private Long ID;

    //@NotBlank(message = "Title is mandatory")
    //@Size(min = 3, max = 255)
    private String title;

    //@NotBlank(message = "Addres is mandatory")
    //@Size(min = 3, max = 255)
    private String addres;

    //@Min(value = 2021 - 1 - 1)
    //@Max(value = 2100 - 1 - 1)
    private LocalDate startDate;

    //@Min(value = 2021 - 1 - 1)
    //@Max(value = 2100 - 1 - 1)
    private LocalDate scheduledEndDate;

    private Set <IncomeCommand> incomeCommands = new HashSet<>();
    private Set<IndicativeCostCommand> indicativeCostCommands = new HashSet<>();

    public ConstructionOrderCommand addIncomes (IncomeCommand incomeCommand){
        incomeCommand.setConstructionOrderCommand(this);
        this.getIncomeCommands().add(incomeCommand);
        return this;
    }

    public ConstructionOrderCommand addIndicativeCost(IndicativeCostCommand indcCost) {
        indcCost.setConstructionOrderCommand(this);
        this.getIndicativeCostCommands().add(indcCost);
        return this;
    }
}

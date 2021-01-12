package com.radello.constructioncompanyorganizer.converter;

import com.radello.constructioncompanyorganizer.commands.ConstructionOrderCommand;
import com.radello.constructioncompanyorganizer.domain.ConstructionOrder;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class ConstructionOrdertoConstructionOrderCommand implements Converter<ConstructionOrder, ConstructionOrderCommand> {

    private final IncomeToIncomeCommand incomeToIncomeCommand;
    private final IndicativeCostToIndicativeCostCommand indicativeCostToIndicativeCostCommand;

    public ConstructionOrdertoConstructionOrderCommand
            (IncomeToIncomeCommand incomeToIncomeCommand, IndicativeCostToIndicativeCostCommand indicativeCostToIndicativeCostCommand) {
        this.incomeToIncomeCommand = incomeToIncomeCommand;
        this.indicativeCostToIndicativeCostCommand = indicativeCostToIndicativeCostCommand;
    }

    @Synchronized
    @Nullable
    @Override
    public ConstructionOrderCommand convert(ConstructionOrder constructionOrder) {

        if (constructionOrder != null) {

            final ConstructionOrderCommand constructionOrderCommand = new ConstructionOrderCommand();

            constructionOrderCommand.setID(constructionOrder.getID());
            constructionOrderCommand.setAddres(constructionOrder.getAddres());
            constructionOrderCommand.setScheduledEndDate(constructionOrder.getScheduledEndDate());
            constructionOrderCommand.setStartDate(constructionOrder.getStartDate());
            constructionOrderCommand.setTitle(constructionOrder.getTitle());

            if (constructionOrder.getIncomes() !=null && constructionOrder.getIncomes().size() > 0) {
                constructionOrder.getIncomes()
                        .forEach(income -> constructionOrderCommand.addIncomes(incomeToIncomeCommand.convert(income)));
            }

           if (constructionOrder.getIndicativeCosts() != null && constructionOrder.getIndicativeCosts().size() > 0) {
                constructionOrder.getIndicativeCosts()
                        .forEach(indicativeCost -> constructionOrderCommand.addIndicativeCost(indicativeCostToIndicativeCostCommand.convert(indicativeCost)));
            }
            return constructionOrderCommand;
        }
        return null;
    }

}

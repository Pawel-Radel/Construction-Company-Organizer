package com.radello.constructioncompanyorganizer.converter;


import com.radello.constructioncompanyorganizer.commands.ConstructionOrderCommand;
import com.radello.constructioncompanyorganizer.domain.ConstructionOrder;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class ConstructionOrderCommandtoConstructionOrder implements Converter<ConstructionOrderCommand, ConstructionOrder> {


  /*  public ConstructionOrderCommandtoConstructionOrder(IncomeCommandToIncome incomeCommandToIncome,
                                                       IndicativeCostCommandToIndicativeCost indicativeCostCommandToIndicativeCost) {
        this.incomeCommandToIncome = incomeCommandToIncome;
        this.indicativeCostCommandToIndicativeCost = indicativeCostCommandToIndicativeCost;
    }

    private final IncomeCommandToIncome incomeCommandToIncome;
    private final IndicativeCostCommandToIndicativeCost indicativeCostCommandToIndicativeCost;*/

    @Synchronized
    @Nullable
    @Override
    public ConstructionOrder convert(ConstructionOrderCommand constructionOrderCommand) {

        if (constructionOrderCommand == null) return null;

        final ConstructionOrder constructionOrder = new ConstructionOrder();
        constructionOrder.setID(constructionOrderCommand.getID());
        constructionOrder.setTitle(constructionOrderCommand.getTitle());
        constructionOrder.setStartDate(constructionOrderCommand.getStartDate());
        constructionOrder.setScheduledEndDate(constructionOrderCommand.getScheduledEndDate());
        constructionOrder.setAddres(constructionOrderCommand.getAddres());
     //   constructionOrder.setIncome(incomeCommandToIncome.convert(constructionOrderCommand.getIncomeCommand()));

       // if (constructionOrderCommand.getIndicateCosts() != null && constructionOrderCommand.getIndicateCosts().size() > 0) {
         //   constructionOrderCommand.getIndicateCosts()
           //         .forEach(indicativeCostCommand -> constructionOrder.getIndicateCosts()
             //               .add(indicativeCostCommandToIndicativeCost.convert(indicativeCostCommand)));
        //}

        return constructionOrder;
    }
}

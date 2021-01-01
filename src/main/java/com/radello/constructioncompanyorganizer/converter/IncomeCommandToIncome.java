package com.radello.constructioncompanyorganizer.converter;

import com.radello.constructioncompanyorganizer.commands.IncomeCommand;
import com.radello.constructioncompanyorganizer.domain.Income;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class IncomeCommandToIncome implements Converter<IncomeCommand, Income> {

    private ConstructionOrderCommandtoConstructionOrder constructionOrderCommandtoConstructionOrder;

    public IncomeCommandToIncome(ConstructionOrderCommandtoConstructionOrder constructionOrderCommandtoConstructionOrder) {
        this.constructionOrderCommandtoConstructionOrder = constructionOrderCommandtoConstructionOrder;
    }

    @Synchronized
    @Nullable
    @Override
    public Income convert(IncomeCommand incomeCommand) {

        if (incomeCommand == null) return null;

        final Income income = new Income();

       // income.setConstructionOrder(constructionOrderCommandtoConstructionOrder
         //       .convert(incomeCommand.getConstructionOrderCommand()));
        income.setID(incomeCommand.getID());
        income.setForWhat(incomeCommand.getForWhat());
        income.setScheduledTimeToGet(incomeCommand.getScheduledTimeToGet());
        income.setAmount(incomeCommand.getAmount());
        return income;
    }
}

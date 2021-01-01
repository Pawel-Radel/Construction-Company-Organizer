package com.radello.constructioncompanyorganizer.converter;

import com.radello.constructioncompanyorganizer.commands.IncomeCommand;
import com.radello.constructioncompanyorganizer.domain.Income;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Null;

@Component
public class IncomeToIncomeCommand implements Converter<Income, IncomeCommand> {


    @Synchronized
    @Nullable
    @Override
    public IncomeCommand convert(Income income) {
        if (income != null) {
            final IncomeCommand incomeCommand = new IncomeCommand();

            incomeCommand.setID(income.getID());
            incomeCommand.setAmount(income.getAmount());
            incomeCommand.setForWhat(income.getForWhat());
            incomeCommand.setScheduledTimeToGet(income.getScheduledTimeToGet());
            //incomeCommand.setConstructionOrderCommand(constructionOrdertoConstructionOrderCommand
              //      .convert(income.getConstructionOrder()));
            return incomeCommand;
        }
        return null;
    }
}

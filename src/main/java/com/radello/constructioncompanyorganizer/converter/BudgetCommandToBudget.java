package com.radello.constructioncompanyorganizer.converter;

import com.radello.constructioncompanyorganizer.commands.BudgetCommand;
import com.radello.constructioncompanyorganizer.domain.Budget;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class BudgetCommandToBudget implements Converter<BudgetCommand, Budget> {

    @Synchronized
    @Nullable
    @Override
    public Budget convert (BudgetCommand source){
        if (source == null){
            return null;
        }

        final Budget budget = new Budget();
        budget.setID(source.getID());
        budget.setAmount(source.getAmount());
        return budget;
    }
}

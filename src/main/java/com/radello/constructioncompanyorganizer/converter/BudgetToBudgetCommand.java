package com.radello.constructioncompanyorganizer.converter;

import com.radello.constructioncompanyorganizer.commands.BudgetCommand;
import com.radello.constructioncompanyorganizer.domain.Budget;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class BudgetToBudgetCommand implements Converter<Budget, BudgetCommand> {

    @Synchronized
    @Nullable
    @Override
    public BudgetCommand convert(Budget budget) {
        if (budget != null) {
            final BudgetCommand budgetCommand = new BudgetCommand();
            budgetCommand.setAmount(budget.getAmount());
            budgetCommand.setID(budget.getID());
            return budgetCommand;
        }
        return null;
    }
}

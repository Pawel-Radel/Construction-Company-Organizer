package com.radello.constructioncompanyorganizer.services.incomesServices;

import com.radello.constructioncompanyorganizer.commands.CostCommand;
import com.radello.constructioncompanyorganizer.commands.IncomeCommand;
import com.radello.constructioncompanyorganizer.domain.Income;

import java.util.Set;

public interface IncomesDependsOnTime {

    Set<IncomeCommand> getIncomes();

    Set<IncomeCommand> getOutstandingIncomes();

    Set <IncomeCommand> getIncomesNextMonth();

    Set <IncomeCommand> getIncomesAnotherMonth();

    Set <IncomeCommand> getFurtherIncomes();

}

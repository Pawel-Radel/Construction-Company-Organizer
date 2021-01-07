package com.radello.constructioncompanyorganizer.services.incomesServices;

import com.radello.constructioncompanyorganizer.commands.CostCommand;
import com.radello.constructioncompanyorganizer.commands.IncomeCommand;
import com.radello.constructioncompanyorganizer.domain.Income;

import java.util.List;
import java.util.Set;

public interface IncomesDependsOnTime {

    List<IncomeCommand> getIncomes();

    List<IncomeCommand> getOutstandingIncomes();

    List <IncomeCommand> getIncomesNextMonth();

    List <IncomeCommand> getIncomesAnotherMonth();

    List <IncomeCommand> getFurtherIncomes();

}

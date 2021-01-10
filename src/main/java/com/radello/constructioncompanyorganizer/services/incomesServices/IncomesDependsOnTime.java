package com.radello.constructioncompanyorganizer.services.incomesServices;

import com.radello.constructioncompanyorganizer.commands.IncomeCommand;

import java.util.List;

public interface IncomesDependsOnTime {

    List<IncomeCommand> getIncomes();

    List<IncomeCommand> getOutstandingIncomes();

    List <IncomeCommand> getIncomesNextMonth();

    List <IncomeCommand> getIncomesAnotherMonth();

    List <IncomeCommand> getFurtherIncomes();

}

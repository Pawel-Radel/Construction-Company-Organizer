package com.radello.constructioncompanyorganizer.services.incomesServices;

import com.radello.constructioncompanyorganizer.commands.IncomeCommand;
import com.radello.constructioncompanyorganizer.domain.Income;

import java.util.Set;

public interface IncomeService {


    Set<Income> getIncomes();

    Income findByID(Long l);

    IncomeCommand findCommandByID(Long l);

    IncomeCommand saveIncomeCommand (IncomeCommand command);

    void deleteById(Long l);

    void realizeAndPostponeToNewDate(String id, Long value);

}

package com.radello.constructioncompanyorganizer.services.incomesServices;

import com.radello.constructioncompanyorganizer.converter.IncomeToIncomeCommand;
import com.radello.constructioncompanyorganizer.repositories.IncomeRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary
@Service
public class IncomeSumServiceImplementation extends IncomesDependsOnTimeImpl implements IncomeSumService {

    public IncomeSumServiceImplementation(IncomeRepository incomeRepository, IncomeToIncomeCommand incomeToIncomeCommand) {
        super(incomeRepository, incomeToIncomeCommand);
    }

    public int getSumOfIncomes(){

        Integer integer = getIncomes()
                .stream()
                .map(incomeCommand -> incomeCommand.getAmount())
                .reduce(0, Integer::sum);

        return integer;
    }

    @Override
    public int getSumOfOutstandIncomes() {

        Integer integer = getOutstandingIncomes()
                .stream()
                .map(incomeCommand -> incomeCommand.getAmount())
                .reduce(0, Integer::sum);

        return integer;
    }

    @Override
    public int getSumOfNextIncomes() {

        Integer integer = getIncomesNextMonth()
                .stream()
                .map(incomeCommand -> incomeCommand.getAmount())
                .reduce(0, Integer::sum);

        return integer;
    }

    @Override
    public int getSumOfAnotherIncomes() {

        Integer integer = getIncomesAnotherMonth()
                .stream()
                .map(incomeCommand -> incomeCommand.getAmount())
                .reduce(0, Integer::sum);

        return integer;
    }

    @Override
    public int getSumOfFurtherIncomes() {

        Integer integer = getFurtherIncomes()
                .stream()
                .map(incomeCommand -> incomeCommand.getAmount())
                .reduce(0, Integer::sum);

        return integer;
    }
}

package com.radello.constructioncompanyorganizer.services.incomesServices;

import com.radello.constructioncompanyorganizer.commands.IncomeCommand;
import com.radello.constructioncompanyorganizer.converter.IncomeToIncomeCommand;
import com.radello.constructioncompanyorganizer.repositories.IncomeRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class IncomesDependsOnTimeImpl implements IncomesDependsOnTime {

    private final LocalDate TODAY_DATE = LocalDate.now();
    private final LocalDate ONE_MONTH_LATER = LocalDate.now().plusMonths(1);
    private final LocalDate TWO_MONTH_LATER = LocalDate.now().plusMonths(2);

    IncomeRepository incomeRepository;
    IncomeToIncomeCommand incomeToIncomeCommand;

    public IncomesDependsOnTimeImpl(IncomeRepository incomeRepository, IncomeToIncomeCommand incomeToIncomeCommand) {
        this.incomeRepository = incomeRepository;
        this.incomeToIncomeCommand = incomeToIncomeCommand;
    }

    @Override
    public List<IncomeCommand> getIncomes() {

        List<IncomeCommand> incomeSet = new ArrayList<>();
        incomeRepository.findAll(Sort.by(Sort.Direction.ASC, "ID"))
                .stream()
                .map(income -> incomeToIncomeCommand.convert(income))
                .iterator().forEachRemaining(incomeSet::add);
        return incomeSet;
    }

    @Override
    public List<IncomeCommand> getOutstandingIncomes() {

        List<IncomeCommand> listOfDates = new ArrayList<>();

        getIncomes().forEach(incomeCommand -> System.out.println(incomeCommand.getScheduledTimeToGet()));

        getIncomes()
                .stream()
                .filter(incomeCommand -> incomeCommand.getScheduledTimeToGet().isBefore(TODAY_DATE))
                .forEach(listOfDates::add);


        return listOfDates;
    }

    @Override
    public List<IncomeCommand> getIncomesNextMonth() {

        List<IncomeCommand> listOfDates = new ArrayList<>();

        getIncomes()
                .stream()
                .filter(incomeCommand -> incomeCommand.getScheduledTimeToGet().isAfter(TODAY_DATE.minusDays(1)))
                .filter(incomeCommand -> incomeCommand.getScheduledTimeToGet().isBefore(ONE_MONTH_LATER))
                .forEach(listOfDates::add);


        return listOfDates;
    }

    @Override
    public List<IncomeCommand> getIncomesAnotherMonth() {

        List<IncomeCommand> listOfDates = new ArrayList<>();

        getIncomes()
                .stream()
                .filter(incomeCommand -> incomeCommand.getScheduledTimeToGet().isAfter(ONE_MONTH_LATER.minusDays(1)))
                .filter(incomeCommand -> incomeCommand.getScheduledTimeToGet().isBefore(TWO_MONTH_LATER.plusDays(1)))
                .forEach(listOfDates::add);


        return listOfDates;
    }

    @Override
    public List<IncomeCommand> getFurtherIncomes() {

        List<IncomeCommand> listOfDates = new ArrayList<>();

        getIncomes()
                .stream()
                .filter(incomeCommand -> incomeCommand.getScheduledTimeToGet().isAfter(TWO_MONTH_LATER))
                .forEach(listOfDates::add);

        return listOfDates;
    }
}

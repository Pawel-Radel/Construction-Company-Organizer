package com.radello.constructioncompanyorganizer.services.incomesServices;

import com.radello.constructioncompanyorganizer.commands.IncomeCommand;
import com.radello.constructioncompanyorganizer.converter.IncomeToIncomeCommand;
import com.radello.constructioncompanyorganizer.repositories.IncomeRepository;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
                .map(cost -> incomeToIncomeCommand.convert(cost))
                .iterator().forEachRemaining(incomeSet::add);
        return incomeSet;
    }

    @Override
    public List<IncomeCommand> getOutstandingIncomes() {

        List<IncomeCommand> listOfDates = new ArrayList<>();

        getIncomes()
                .stream()
                .filter(incomeCommand -> incomeCommand.getScheduledTimeToGet().before(Date.valueOf(TODAY_DATE)))
                .forEach(listOfDates::add);

        System.out.println("Od " + TODAY_DATE.getDayOfMonth() + TODAY_DATE.getMonthValue() + "do" + ONE_MONTH_LATER.getDayOfMonth() + " " + ONE_MONTH_LATER.getMonthValue());

        return listOfDates;
    }

    @Override
    public List<IncomeCommand> getIncomesNextMonth() {

        List<IncomeCommand> listOfDates = new ArrayList<>();

        getIncomes()
                .stream()
                .filter(incomeCommand -> incomeCommand.getScheduledTimeToGet().after(Date.valueOf(TODAY_DATE.minusDays(1))))
                .filter(incomeCommand -> incomeCommand.getScheduledTimeToGet().before(Date.valueOf(ONE_MONTH_LATER.plusDays(1))))
                .forEach(listOfDates::add);

        System.out.println("Od " + TODAY_DATE.getDayOfMonth() + TODAY_DATE.getMonthValue() + "do" + ONE_MONTH_LATER.getDayOfMonth() + " " + ONE_MONTH_LATER.getMonthValue());

        return listOfDates;
    }

    @Override
    public List<IncomeCommand> getIncomesAnotherMonth() {

        List<IncomeCommand> listOfDates = new ArrayList<>();

        getIncomes()
                .stream()
                .filter(incomeCommand -> incomeCommand.getScheduledTimeToGet().after(Date.valueOf(ONE_MONTH_LATER.minusDays(1))))
                .filter(incomeCommand -> incomeCommand.getScheduledTimeToGet().before(Date.valueOf(TWO_MONTH_LATER.plusDays(1))))
                .forEach(listOfDates::add);

        System.out.println("Od " + String.format("%02d" , ONE_MONTH_LATER.getDayOfMonth()) + ONE_MONTH_LATER.getMonthValue() + "do" + TWO_MONTH_LATER.getDayOfMonth() + " " + TWO_MONTH_LATER.getMonthValue());

        return listOfDates;
    }

    @Override
    public List<IncomeCommand> getFurtherIncomes() {

        List<IncomeCommand> listOfDates = new ArrayList<>();

        getIncomes()
                .stream()
                .filter(incomeCommand -> incomeCommand.getScheduledTimeToGet().after(Date.valueOf(TWO_MONTH_LATER)))
                .forEach(listOfDates::add);

        System.out.println("Od " + ONE_MONTH_LATER.getDayOfMonth() + ONE_MONTH_LATER.getMonthValue() + "do" + TWO_MONTH_LATER.getDayOfMonth() + " " + TWO_MONTH_LATER.getMonthValue());

        return listOfDates;
    }
}

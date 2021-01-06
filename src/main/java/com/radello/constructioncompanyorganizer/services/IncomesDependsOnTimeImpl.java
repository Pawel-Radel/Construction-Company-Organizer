package com.radello.constructioncompanyorganizer.services;

import com.radello.constructioncompanyorganizer.domain.Income;
import com.radello.constructioncompanyorganizer.repositories.IncomeRepository;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Service
public class IncomesDependsOnTimeImpl implements IncomesDependsOnTime {

    private final LocalDate TODAY_DATE = LocalDate.now();

    IncomeRepository incomeRepository;

    public IncomesDependsOnTimeImpl(IncomeRepository incomeRepository) {
        this.incomeRepository = incomeRepository;
    }

    @Override
    public Set<Income> getIncomes() {

        Set<Income> incomeSet = new HashSet<>();
        incomeRepository.findAll().iterator().forEachRemaining(incomeSet::add);
        return incomeSet;
    }

    @Override
    public Set<Income> getOutstandingIncomes() {

        Set <Income> listOfDates = new HashSet<>();

        getIncomes()
                .stream()
                .filter(income -> income.getScheduledTimeToGet().after(Date.valueOf(TODAY_DATE)))
                .forEach(listOfDates::add);

        return  listOfDates;
    }
}

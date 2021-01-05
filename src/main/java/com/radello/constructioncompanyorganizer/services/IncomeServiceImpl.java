package com.radello.constructioncompanyorganizer.services;

import com.radello.constructioncompanyorganizer.commands.IncomeCommand;
import com.radello.constructioncompanyorganizer.converter.IncomeCommandToIncome;
import com.radello.constructioncompanyorganizer.converter.IncomeToIncomeCommand;
import com.radello.constructioncompanyorganizer.domain.Income;
import com.radello.constructioncompanyorganizer.repositories.IncomeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class IncomeServiceImpl implements IncomeService {

    private final IncomeRepository incomeRepository;
    private final IncomeToIncomeCommand incomeToIncomeCommand;
    private final IncomeCommandToIncome incomeCommandToIncome;

    public IncomeServiceImpl(IncomeRepository incomeRepository,
                             IncomeToIncomeCommand incomeToIncomeCommand,
                             IncomeCommandToIncome incomeCommandToIncome) {

        this.incomeRepository = incomeRepository;
        this.incomeToIncomeCommand = incomeToIncomeCommand;
        this.incomeCommandToIncome = incomeCommandToIncome;
    }

    @Override
    public Set<Income> getIncomes() {

        Set<Income> incomeSet = new HashSet<>();
        incomeRepository.findAll().iterator().forEachRemaining(incomeSet::add);
        return incomeSet;

    }

    @Override
    public Income findByID(Long l) {

        Optional<Income> incomeOptional = incomeRepository.findById(l);

        return incomeOptional.orElse(null);


    }

    @Override
    @Transactional
    public IncomeCommand findCommandByID(Long l) {

        return incomeToIncomeCommand.convert(findByID(l));

    }

    @Override
    @Transactional
    public IncomeCommand saveIncomeCommand(IncomeCommand command) {

        Income income = incomeCommandToIncome.convert(command);
        Income incomeSaved = incomeRepository.save(income);

        return incomeToIncomeCommand.convert(incomeSaved);
    }

    @Override
    public void deleteById(Long l) {

        incomeRepository.deleteById(l);
    }

}

package com.radello.constructioncompanyorganizer.services.incomesServices;

import com.radello.constructioncompanyorganizer.commands.IncomeCommand;
import com.radello.constructioncompanyorganizer.converter.IncomeCommandToIncome;
import com.radello.constructioncompanyorganizer.converter.IncomeToIncomeCommand;
import com.radello.constructioncompanyorganizer.domain.Income;
import com.radello.constructioncompanyorganizer.repositories.IncomeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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
    public Income saveIncome(Income income) {

        return incomeRepository.save(income);
    }

    @Override
    public void deleteById(Long l) {

        incomeRepository.deleteById(l);
    }

    @Override
    @Transactional
    public void realizeAndPostponeToNewDate(String id, Long value) {

        IncomeCommand incomeCommand = findCommandByID(Long.valueOf(id));
        IncomeCommand incomeCommand1 = new IncomeCommand();
        incomeCommand1.setAmount(incomeCommand.getAmount());
        incomeCommand1.setForWhat(incomeCommand.getForWhat());
        incomeCommand1.setScheduledTimeToGet(incomeCommand.getScheduledTimeToGet().plusDays(value));
        saveIncomeCommand(incomeCommand1);
        deleteById(incomeCommand.getID());
    }

    @Override
    public List<IncomeCommand> sortSet(Set<IncomeCommand> set) {

        List list = new ArrayList<>(set);

        Comparator<IncomeCommand> comparator = Comparator.comparingInt(left -> left.getID().intValue());
        list.sort(comparator);

        return list;
    }

    public int sumValues(List<IncomeCommand> list) {

        return list
                .stream()
                .map(IncomeCommand::getAmount)
                .reduce(0, Integer::sum);

    }

}

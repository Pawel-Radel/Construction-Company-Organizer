package com.radello.constructioncompanyorganizer.services.costsServices;

import com.radello.constructioncompanyorganizer.commands.CostCommand;
import com.radello.constructioncompanyorganizer.converter.CostToCostCommand;
import com.radello.constructioncompanyorganizer.repositories.CostRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Service
public class CostsDependsOnTimeServiceImpl implements CostsDependsOnTimeService {

    private final LocalDate TODAY_DATE = LocalDate.now();
    private final LocalDate ONE_MONTH_LATER = LocalDate.now().plusMonths(1);
    private final LocalDate TWO_MONTH_LATER = LocalDate.now().plusMonths(2);

    CostRepository costRepository;
    private final CostToCostCommand costToCostCommand;

    public CostsDependsOnTimeServiceImpl(CostRepository costRepository,
                                         CostToCostCommand costToCostCommand) {
        this.costRepository = costRepository;
        this.costToCostCommand = costToCostCommand;
    }

    @Override
    public List<CostCommand> getCosts() {

        List<CostCommand> costSet = new ArrayList<>();
        costRepository.findAll(Sort.by(Sort.Direction.ASC, "ID"))
                .stream()
                .map(cost -> costToCostCommand.convert(cost))
                .iterator().forEachRemaining(costSet::add);
        return costSet;
    }

    @Override
    public List<CostCommand> getOutstandingCosts() {

        List<CostCommand> listOfDates = new ArrayList<>();

        getCosts()
                .stream()
                .filter(costCommand -> costCommand.getScheduledtime().isBefore(TODAY_DATE))
                .forEach(cost -> listOfDates.add(cost));


        return listOfDates;
    }

    @Override
    public List<CostCommand> getCostsNextMonth() {

        List<CostCommand> listOfDates = new ArrayList<>();

        getCosts()
                .stream()
                .filter(costCommand -> costCommand.getScheduledtime().isAfter(TODAY_DATE.minusDays(1)))
                .filter(costCommand -> costCommand.getScheduledtime().isBefore(ONE_MONTH_LATER))
                .forEach(cost -> listOfDates.add(cost));


        return listOfDates;
    }

    @Override
    public List<CostCommand> getCostsAnotherMonth() {

        List<CostCommand> listOfDates = new ArrayList<>();

        getCosts()
                .stream()
                .filter(costCommand -> costCommand.getScheduledtime().isAfter(ONE_MONTH_LATER.minusDays(1)))
                .filter(costCommand -> costCommand.getScheduledtime().isBefore(TWO_MONTH_LATER.plusDays(1)))
                .forEach(cost -> listOfDates.add(cost));


        return listOfDates;
    }

    @Override
    public List<CostCommand> getFurtherCosts() {

        List<CostCommand> listOfDates = new ArrayList<>();

        getCosts()
                .stream()
                .filter(costCommand -> costCommand.getScheduledtime().isAfter(TWO_MONTH_LATER))
                .forEach(cost -> listOfDates.add(cost));


        return listOfDates;
    }
}

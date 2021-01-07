package com.radello.constructioncompanyorganizer.services.costsServices;

import com.radello.constructioncompanyorganizer.commands.CostCommand;
import com.radello.constructioncompanyorganizer.converter.CostToCostCommand;
import com.radello.constructioncompanyorganizer.domain.Cost;
import com.radello.constructioncompanyorganizer.repositories.CostRepository;
import com.radello.constructioncompanyorganizer.services.costsServices.CostsDependsOnTimeService;
import org.springframework.stereotype.Service;

import javax.persistence.OneToMany;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


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
    public Set<CostCommand> getCosts() {

        Set<CostCommand> costSet = new HashSet<>();
        costRepository.findAll()
                .stream()
                .map(cost -> costToCostCommand.convert(cost))
                .iterator().forEachRemaining(costSet::add);
        return costSet;
    }

    @Override
    public Set<CostCommand> getOutstandingCosts() {

        Set<CostCommand> listOfDates = new HashSet<>();

        getCosts()
                .stream()
                .filter(costCommand -> costCommand.getScheduledtime().before(Date.valueOf(TODAY_DATE)))
                .forEach(cost -> listOfDates.add(cost));

        System.out.println("Od " + TODAY_DATE.getDayOfMonth() + TODAY_DATE.getMonthValue() + "do" + ONE_MONTH_LATER.getDayOfMonth() + " " + ONE_MONTH_LATER.getMonthValue());

        return listOfDates;
    }

    @Override
    public Set<CostCommand> getCostsNextMonth() {

        Set<CostCommand> listOfDates = new HashSet<>();

        getCosts()
                .stream()
                .filter(costCommand -> costCommand.getScheduledtime().after(Date.valueOf(TODAY_DATE.minusDays(1))))
                .filter(costCommand -> costCommand.getScheduledtime().before(Date.valueOf(ONE_MONTH_LATER.plusDays(1))))
                .forEach(cost -> listOfDates.add(cost));

        System.out.println("Od " + TODAY_DATE.getDayOfMonth() + TODAY_DATE.getMonthValue() + "do" + ONE_MONTH_LATER.getDayOfMonth() + " " + ONE_MONTH_LATER.getMonthValue());

        return listOfDates;
    }

    @Override
    public Set<CostCommand> getCostsAnotherMonth() {

        Set<CostCommand> listOfDates = new HashSet<>();

        getCosts()
                .stream()
                .filter(costCommand -> costCommand.getScheduledtime().after(Date.valueOf(ONE_MONTH_LATER.minusDays(1))))
                .filter(costCommand -> costCommand.getScheduledtime().before(Date.valueOf(TWO_MONTH_LATER.plusDays(1))))
                .forEach(cost -> listOfDates.add(cost));

        System.out.println("Od " + ONE_MONTH_LATER.getDayOfMonth() + ONE_MONTH_LATER.getMonthValue() + "do" + TWO_MONTH_LATER.getDayOfMonth() + " " + TWO_MONTH_LATER.getMonthValue());

        return listOfDates;
    }

    @Override
    public Set<CostCommand> furtherCosts() {

        Set<CostCommand> listOfDates = new HashSet<>();

        getCosts()
                .stream()
                .filter(costCommand -> costCommand.getScheduledtime().after(Date.valueOf(TWO_MONTH_LATER)))
                .forEach(cost -> listOfDates.add(cost));

        System.out.println("Od " + ONE_MONTH_LATER.getDayOfMonth() + ONE_MONTH_LATER.getMonthValue() + "do" + TWO_MONTH_LATER.getDayOfMonth() + " " + TWO_MONTH_LATER.getMonthValue());

        return listOfDates;
    }
}

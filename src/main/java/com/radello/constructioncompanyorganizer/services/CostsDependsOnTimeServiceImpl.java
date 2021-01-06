package com.radello.constructioncompanyorganizer.services;

import com.radello.constructioncompanyorganizer.domain.Cost;
import com.radello.constructioncompanyorganizer.repositories.CostRepository;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;



@Service
public class CostsDependsOnTimeServiceImpl implements CostsDependsOnTimeService {

    private final LocalDate TODAY_DATE = LocalDate.now();
    private final LocalDate ONE_MONTH_LATER = LocalDate.now().plusMonths(1);
    private final LocalDate TWO_MONTH_LATER = LocalDate.now().plusMonths(2);

    CostRepository costRepository;

    public CostsDependsOnTimeServiceImpl(CostRepository costRepository) {
        this.costRepository = costRepository;
    }

    @Override
    public Set<Cost> getCosts() {

        Set<Cost> costSet = new HashSet<>();
        costRepository.findAll().iterator().forEachRemaining(costSet::add);
        return costSet;
    }

    @Override
    public Set<Cost> getOutstandingCosts() {

        Set <Cost> listOfDates = new HashSet<>();

        getCosts()
                .stream()
                .filter(cost -> cost.getScheduledtime().after(Date.valueOf(TODAY_DATE)))
                .forEach(cost -> listOfDates.add(cost));

        System.out.println("Od " + TODAY_DATE.getDayOfMonth() + TODAY_DATE.getMonthValue() + "do" + ONE_MONTH_LATER.getDayOfMonth() + " " + ONE_MONTH_LATER.getMonthValue());

        return  listOfDates;
    }

}

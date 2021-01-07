package com.radello.constructioncompanyorganizer.services.costsServices;


import com.radello.constructioncompanyorganizer.commands.CostCommand;
import com.radello.constructioncompanyorganizer.converter.CostToCostCommand;
import com.radello.constructioncompanyorganizer.repositories.CostRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class CostSumServiceImpl extends CostsDependsOnTimeServiceImpl implements CostSumService {

    public CostSumServiceImpl(CostRepository costRepository, CostToCostCommand costToCostCommand) {
        super(costRepository, costToCostCommand);
    }

    @Override
    public int getSumOfCosts() {

        return getCosts()
                .stream()
                .map(CostCommand::getAmount)
                .reduce(0, Integer::sum);
    }

    @Override
    public int getSumOfOutstandCosts() {

        return getOutstandingCosts()
                .stream()
                .map(CostCommand::getAmount)
                .reduce(0, Integer::sum);
    }

    @Override
    public int getSumOfNextCosts() {

        return getCostsNextMonth()
                .stream()
                .map(CostCommand::getAmount)
                .reduce(0, Integer::sum);

    }

    @Override
    public int getSumOfAnotherCosts() {

        return getCostsAnotherMonth()
                .stream()
                .map(CostCommand::getAmount)
                .reduce(0, Integer::sum);
    }

    @Override
    public int getSumOfFurtherCosts() {

        return getFurtherCosts()
                .stream()
                .map(CostCommand::getAmount)
                .reduce(0, Integer::sum);
    }
}

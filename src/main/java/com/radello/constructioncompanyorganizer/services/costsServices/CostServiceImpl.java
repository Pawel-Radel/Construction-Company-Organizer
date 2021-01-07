package com.radello.constructioncompanyorganizer.services.costsServices;

import com.radello.constructioncompanyorganizer.commands.CostCommand;
import com.radello.constructioncompanyorganizer.converter.CostCommandToCost;
import com.radello.constructioncompanyorganizer.converter.CostToCostCommand;
import com.radello.constructioncompanyorganizer.domain.Cost;
import com.radello.constructioncompanyorganizer.repositories.CostRepository;
import com.radello.constructioncompanyorganizer.services.costsServices.CostService;
import com.radello.constructioncompanyorganizer.services.costsServices.CostsDependsOnTimeServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class CostServiceImpl implements CostService {



    private final CostRepository costRepository;
    private final CostToCostCommand costToCostCommand;
    private final CostCommandToCost costCommandToCost;

    public CostServiceImpl(CostRepository costRepository, CostToCostCommand costToCostCommand, CostCommandToCost costCommandToCost, CostsDependsOnTimeServiceImpl costsDependsonTimeService) {
        this.costRepository = costRepository;
        this.costToCostCommand = costToCostCommand;
        this.costCommandToCost = costCommandToCost;
    }

    @Override
    public Set<Cost> getCosts() {
        Set<Cost> costSet = new HashSet<>();
        costRepository.findAll().iterator().forEachRemaining(costSet::add);
        return costSet;
    }

    @Override
    public Cost findByID(Long l) {
        Optional<Cost> indCostOptional = costRepository.findById(l);

        return indCostOptional.orElse(null);
    }

    @Override
    @Transactional
    public CostCommand findCommandByID(Long l) {

        return costToCostCommand.convert(findByID(l));
    }

    @Override
    @Transactional
    public CostCommand saveCostCommand(CostCommand command) {

        Cost cost = costCommandToCost.convert(command);

        Cost costSaved = costRepository.save(cost);


        return costToCostCommand.convert(costSaved);
    }

    @Override
    public void deleteById(Long l) {

        costRepository.deleteById(l);
    }


    }




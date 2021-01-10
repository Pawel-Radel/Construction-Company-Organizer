package com.radello.constructioncompanyorganizer.services.costsServices;

import com.radello.constructioncompanyorganizer.commands.CostCommand;
import com.radello.constructioncompanyorganizer.domain.Cost;

import java.util.Set;

public interface CostService {

    Set<Cost> getCosts();

    Cost findByID(Long l);

    CostCommand findCommandByID(Long l);

    CostCommand saveCostCommand (CostCommand costCommand);

    void deleteById(Long l);

    void realizeAndPostponeToNewDate(String id, Long value);
}

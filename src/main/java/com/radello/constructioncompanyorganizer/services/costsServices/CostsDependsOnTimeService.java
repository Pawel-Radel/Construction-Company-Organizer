package com.radello.constructioncompanyorganizer.services.costsServices;

import com.radello.constructioncompanyorganizer.commands.CostCommand;
import com.radello.constructioncompanyorganizer.domain.Cost;

import java.util.Set;

public interface CostsDependsOnTimeService {

    Set<CostCommand> getCosts();

    Set<CostCommand> getOutstandingCosts();

    Set <CostCommand> getCostsNextMonth();

    Set <CostCommand> getCostsAnotherMonth();

    Set <CostCommand> furtherCosts();


}

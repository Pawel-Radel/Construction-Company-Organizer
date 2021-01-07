package com.radello.constructioncompanyorganizer.services.costsServices;

import com.radello.constructioncompanyorganizer.commands.CostCommand;

import java.util.List;
import java.util.Set;

public interface CostsDependsOnTimeService {

    List<CostCommand> getCosts();

    List<CostCommand> getOutstandingCosts();

    List <CostCommand> getCostsNextMonth();

    List <CostCommand> getCostsAnotherMonth();

    List <CostCommand> getFurtherCosts();


}

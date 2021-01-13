package com.radello.constructioncompanyorganizer.services.constructionOrderServices;

import com.radello.constructioncompanyorganizer.commands.ConstructionOrderCommand;
import com.radello.constructioncompanyorganizer.domain.ConstructionOrder;

import java.util.List;
import java.util.Set;

public interface ConstructionOrderService {

    List<ConstructionOrderCommand> getConstructionOrders();

    ConstructionOrder findByID (Long l);

    ConstructionOrderCommand findCommandByID(Long l);

    ConstructionOrderCommand saveConstructionOrderCommand (ConstructionOrderCommand command);

    void deleteById (Long l);

    Integer sumIncomes(ConstructionOrderCommand command);
}

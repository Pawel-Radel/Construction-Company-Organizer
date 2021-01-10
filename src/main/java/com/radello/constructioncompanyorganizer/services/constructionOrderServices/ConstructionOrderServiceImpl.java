package com.radello.constructioncompanyorganizer.services.constructionOrderServices;

import com.radello.constructioncompanyorganizer.commands.ConstructionOrderCommand;
import com.radello.constructioncompanyorganizer.converter.ConstructionOrderCommandtoConstructionOrder;
import com.radello.constructioncompanyorganizer.converter.ConstructionOrdertoConstructionOrderCommand;
import com.radello.constructioncompanyorganizer.domain.ConstructionOrder;
import com.radello.constructioncompanyorganizer.repositories.ConstructionOrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@Service
public class ConstructionOrderServiceImpl implements ConstructionOrderService {


    private final ConstructionOrderRepository constructionOrderRepository;
    private final ConstructionOrdertoConstructionOrderCommand consOrdToConsOrdCommand;
    private final ConstructionOrderCommandtoConstructionOrder commandToConstructionOrder;

    public ConstructionOrderServiceImpl(ConstructionOrderRepository constOrdRepository,
                                        ConstructionOrdertoConstructionOrderCommand consOrdToConsOrdCommand,
                                        ConstructionOrderCommandtoConstructionOrder commandToConstructionOrder) {

        this.constructionOrderRepository = constOrdRepository;
        this.consOrdToConsOrdCommand = consOrdToConsOrdCommand;
        this.commandToConstructionOrder = commandToConstructionOrder;
    }

    @Override
    public Set<ConstructionOrder> getConstructionOrders() {
        Set<ConstructionOrder> constructionOrderSetSet = new HashSet<>();
        constructionOrderRepository.findAll().iterator().forEachRemaining(constructionOrderSetSet::add);
        return constructionOrderSetSet;
    }

    @Override
    public ConstructionOrder findByID(Long l) {
        Optional<ConstructionOrder> consOrderOptional = constructionOrderRepository.findById(l);

        return consOrderOptional.orElse(null);
    }

    @Override
    @Transactional
    public ConstructionOrderCommand findCommandByID(Long l) {

        return consOrdToConsOrdCommand.convert(findByID(l));
    }

    @Override
    @Transactional
    public ConstructionOrderCommand saveConstructionOrderCommand(ConstructionOrderCommand command) {

        ConstructionOrder constructionOrder = commandToConstructionOrder.convert(command);

        ConstructionOrder constructionOrderSaved = constructionOrderRepository.save(constructionOrder);


        return consOrdToConsOrdCommand.convert(constructionOrderSaved);
    }

    @Override
    public void deleteById(Long l) {

        constructionOrderRepository.deleteById(l);
    }
}

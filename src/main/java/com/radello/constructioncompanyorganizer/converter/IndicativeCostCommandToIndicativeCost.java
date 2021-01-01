package com.radello.constructioncompanyorganizer.converter;


import com.radello.constructioncompanyorganizer.commands.IndicativeCostCommand;
import com.radello.constructioncompanyorganizer.domain.IndicativeCost;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class IndicativeCostCommandToIndicativeCost implements Converter<IndicativeCostCommand, IndicativeCost> {

    private ConstructionOrderCommandtoConstructionOrder constructionOrderCommandtoConstructionOrder;

    public IndicativeCostCommandToIndicativeCost(ConstructionOrderCommandtoConstructionOrder ConsOrdComToConsOrd) {
        this.constructionOrderCommandtoConstructionOrder = ConsOrdComToConsOrd;
    }

    @Synchronized
    @Nullable
    @Override
    public IndicativeCost convert(IndicativeCostCommand indicativeCostCommand) {

        if (indicativeCostCommand == null) return null;

        final IndicativeCost indicativeCost = new IndicativeCost();

        indicativeCost.setConstructionOrder(constructionOrderCommandtoConstructionOrder
                .conver(indicativeCostCommand.getConstructionOrderCommand()));
        indicativeCost.setID(indicativeCostCommand.getID());
        indicativeCost.setForWhat(indicativeCostCommand.getForWhat());


        return indicativeCost;
    }
}

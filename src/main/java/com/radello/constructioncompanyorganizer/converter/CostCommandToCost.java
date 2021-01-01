package com.radello.constructioncompanyorganizer.converter;

import com.radello.constructioncompanyorganizer.commands.CostCommand;
import com.radello.constructioncompanyorganizer.domain.Cost;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CostCommandToCost implements Converter<CostCommand, Cost> {

    @Synchronized
    @Nullable
    @Override
    public Cost convert(CostCommand costCommand) {

        if (costCommand == null) return null;

        final Cost cost = new Cost();
        cost.setID(costCommand.getID());
        cost.setForWhat(costCommand.getForWhat());
        cost.setScheduledtime(costCommand.getScheduledtime());
        cost.setAmount(costCommand.getAmount());
        return cost;

    }
}

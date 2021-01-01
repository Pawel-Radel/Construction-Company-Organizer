package com.radello.constructioncompanyorganizer.converter;

import com.radello.constructioncompanyorganizer.commands.CostCommand;
import com.radello.constructioncompanyorganizer.domain.Cost;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CostToCostCommand implements Converter<Cost, CostCommand> {

    @Synchronized
    @Nullable
    @Override
    public CostCommand convert(Cost cost) {
        if (cost != null) {

            final CostCommand costCommand = new CostCommand();
            costCommand.setID(cost.getID());
            costCommand.setAmount(cost.getAmount());
            costCommand.setForWhat(cost.getForWhat());
            costCommand.setScheduledtime(cost.getScheduledtime());
            return costCommand;
        }
        return null;
    }
}

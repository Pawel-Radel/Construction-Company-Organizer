package com.radello.constructioncompanyorganizer.converter;

import com.radello.constructioncompanyorganizer.commands.IndicativeCostCommand;
import com.radello.constructioncompanyorganizer.domain.IndicativeCost;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class IndicativeCostToIndicativeCostCommand implements Converter<IndicativeCost, IndicativeCostCommand> {


    @Synchronized
    @Nullable
    @Override
    public IndicativeCostCommand convert(IndicativeCost indicativeCost) {
        if (indicativeCost != null) {
            final IndicativeCostCommand indicativeCostCommand = new IndicativeCostCommand();

            indicativeCostCommand.setForWhat(indicativeCost.getForWhat());
            indicativeCostCommand.setID(indicativeCost.getID());

            return indicativeCostCommand;
        }
        return null;
    }
}

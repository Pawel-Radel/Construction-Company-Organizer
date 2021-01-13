package com.radello.constructioncompanyorganizer.services.indicativeCostServices;

import com.radello.constructioncompanyorganizer.commands.IndicativeCostCommand;
import com.radello.constructioncompanyorganizer.converter.IndicativeCostCommandToIndicativeCost;
import com.radello.constructioncompanyorganizer.converter.IndicativeCostToIndicativeCostCommand;
import com.radello.constructioncompanyorganizer.domain.IndicativeCost;
import com.radello.constructioncompanyorganizer.repositories.IndicativeCostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class IndicativeCostServiceImpl implements IndicativeCostService {

    private final IndicativeCostRepository indicativeCostRepository;
    private final IndicativeCostToIndicativeCostCommand indicativeCostToIndicativeCostCommand;
    private final IndicativeCostCommandToIndicativeCost indicativeCostCommandToIndicativeCost;

    public IndicativeCostServiceImpl(IndicativeCostRepository indicativeCostRepository,
                                     IndicativeCostToIndicativeCostCommand indCostToCommand,
                                     IndicativeCostCommandToIndicativeCost CommandToIndCost) {

        this.indicativeCostRepository = indicativeCostRepository;
        this.indicativeCostToIndicativeCostCommand = indCostToCommand;
        this.indicativeCostCommandToIndicativeCost = CommandToIndCost;
    }

    @Override
    public Set<IndicativeCost> getIndicativeCosts() {
        Set<IndicativeCost> indicativeCostSet = new HashSet<>();
        indicativeCostRepository.findAll().iterator().forEachRemaining(indicativeCostSet::add);
        return indicativeCostSet;
    }

    @Override
    public IndicativeCost findByID(Long l) {
        Optional<IndicativeCost> indCostOptional = indicativeCostRepository.findById(l);

        return indCostOptional.orElse(null);
    }

    @Override
    @Transactional
    public IndicativeCostCommand findCommandByID(Long l) {

        return indicativeCostToIndicativeCostCommand.convert(findByID(l));
    }

    @Override
    @Transactional
    public IndicativeCostCommand saveCostCommand(IndicativeCostCommand command) {

        IndicativeCost indicativeCost = indicativeCostCommandToIndicativeCost.convert(command);

        IndicativeCost indicativeCostSaved = indicativeCostRepository.save(indicativeCost);

        return indicativeCostToIndicativeCostCommand.convert(indicativeCostSaved);
    }

    @Override
    public IndicativeCost saveCost(IndicativeCost indicativeCost) {

        return indicativeCostRepository.save(indicativeCost);
    }

    @Override
    public void deleteById(Long l) {

        indicativeCostRepository.deleteById(l);
    }

    @Override
    public List<IndicativeCostCommand> sortSet(Set<IndicativeCostCommand> set) {

        List list = new ArrayList<>(set);

        Comparator<IndicativeCostCommand> comparator = Comparator.comparingInt(left -> left.getID().intValue());
        list.sort(comparator);

        return list;
    }

    public int sumValues(List<IndicativeCostCommand> list) {

        return list
                .stream()
                .map(indicativeCostCommand -> indicativeCostCommand.getAmount())
                .reduce(0, Integer::sum);

    }


}

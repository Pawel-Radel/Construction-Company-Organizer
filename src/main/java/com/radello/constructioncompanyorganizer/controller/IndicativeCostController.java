package com.radello.constructioncompanyorganizer.controller;

import com.radello.constructioncompanyorganizer.commands.ConstructionOrderCommand;
import com.radello.constructioncompanyorganizer.commands.IndicativeCostCommand;
import com.radello.constructioncompanyorganizer.services.constructionOrderServices.ConstructionOrderService;
import com.radello.constructioncompanyorganizer.services.indicativeCostServices.IndicativeCostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class IndicativeCostController {

    ConstructionOrderService constructionOrderService;
    IndicativeCostService indicativeCostService;

    public IndicativeCostController(ConstructionOrderService constructionOrderService,
                                    IndicativeCostService indicativeCostService) {

        this.constructionOrderService = constructionOrderService;
        this.indicativeCostService = indicativeCostService;
    }

    @GetMapping("/newCostToConsOrder/{id}")
    public String addCostsToConsOrd(@PathVariable String id, Model model) {

        ConstructionOrderCommand command1 = constructionOrderService.findCommandByID(Long.valueOf(id));
        List list = indicativeCostService.sortSet(command1.getIndicativeCostCommands());

        model.addAttribute("cost", new IndicativeCostCommand());
        model.addAttribute("ConsOrd", command1);
        model.addAttribute("ConsOrd2", list);
        model.addAttribute("SumAmount", indicativeCostService.sumValues(list));

        return "newIndicativeCost";
    }

    @PostMapping("/newCostToConsOrder/{id}/create/")
    public String addingCostToConstructionOrder(@ModelAttribute("cost") IndicativeCostCommand indCostCommand,
                                                @PathVariable String id, Model model) {

        ConstructionOrderCommand command = constructionOrderService.findCommandByID(Long.valueOf(id));
        command.addIndicativeCost(indCostCommand);

        constructionOrderService.saveConstructionOrderCommand(command);

        return "redirect:/newCostToConsOrder/" + id;
    }
}

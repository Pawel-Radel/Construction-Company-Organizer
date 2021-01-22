package com.radello.constructioncompanyorganizer.controller.IndicativeCostsControllers;

import com.radello.constructioncompanyorganizer.commands.ConstructionOrderCommand;
import com.radello.constructioncompanyorganizer.commands.IndicativeCostCommand;
import com.radello.constructioncompanyorganizer.domain.IndicativeCost;
import com.radello.constructioncompanyorganizer.services.constructionOrderServices.ConstructionOrderService;
import com.radello.constructioncompanyorganizer.services.indicativeCostServices.IndicativeCostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class IndicativeCostControllerConstOrderEdit {

    ConstructionOrderService constructionOrderService;
    IndicativeCostService indicativeCostService;

    public IndicativeCostControllerConstOrderEdit(ConstructionOrderService constructionOrderService,
                                    IndicativeCostService indicativeCostService) {

        this.constructionOrderService = constructionOrderService;
        this.indicativeCostService = indicativeCostService;
    }

    @GetMapping("/consOrderEdit/newCostToConsOrder/{id}")
    public String addCostsToConsOrd(@PathVariable String id, Model model) {

        ConstructionOrderCommand command1 = constructionOrderService.findCommandByID(Long.valueOf(id));
        List list = indicativeCostService.sortSet(command1.getIndicativeCostCommands());

        model.addAttribute("cost", new IndicativeCostCommand());
        model.addAttribute("ConsOrd", command1);
        model.addAttribute("ConsOrd2", list);
        model.addAttribute("SumAmount", indicativeCostService.sumValues(list));

        return "IndicativeCostsTemplates/NewIndicativeFormWithLinkToConsOrder";
    }

    @PostMapping("/consOrderEdit/newCostToConsOrder/{id}/create/")
    public String addingCostToConstructionOrder(@Valid @ModelAttribute("cost") IndicativeCostCommand indCostCommand,
                                                BindingResult result,
                                                @PathVariable String id, Model model) {

        ConstructionOrderCommand command1 = constructionOrderService.findCommandByID(Long.valueOf(id));
        List list = indicativeCostService.sortSet(command1.getIndicativeCostCommands());
        model.addAttribute("cost", indCostCommand);
        model.addAttribute("ConsOrd", command1);
        model.addAttribute("ConsOrd2", list);
        model.addAttribute("SumAmount", indicativeCostService.sumValues(list));

        if (result.hasErrors()){
            return "IndicativeCostsTemplates/NewIndicativeFormWithLinkToConsOrder";
        }

        ConstructionOrderCommand command = constructionOrderService.findCommandByID(Long.valueOf(id));
        command.addIndicativeCost(indCostCommand);

        constructionOrderService.saveConstructionOrderCommand(command);

        return "redirect:/consOrderEdit/newCostToConsOrder/" + id;
    }

    @GetMapping("/consOrderEdit/indicativeCost/{id}/delete")
    public String deleteIndicativeCostById(@PathVariable String id) {

        Long ID = indicativeCostService.findByID(Long.valueOf(id)).getConstructionOrder().getID();

        String string = String.valueOf(ID);
        indicativeCostService.deleteById(Long.valueOf(id));

        return "redirect:/consOrderEdit/newCostToConsOrder/" + string;
    }

    @GetMapping("/consOrderEdit/constructionOrder/{id1}/indicativeCost/{id2}/edit")
    public String editIndicativeCostById(@PathVariable String id1,
                                         @PathVariable String id2,
                                         Model model) {

        ConstructionOrderCommand command1 = constructionOrderService.findCommandByID(Long.valueOf(id1));
        List list = indicativeCostService.sortSet(command1.getIndicativeCostCommands());

        model.addAttribute("cost", indicativeCostService.findCommandByID(Long.valueOf(id2)));
        model.addAttribute("ConsOrd", command1);
        model.addAttribute("ConsOrd2", list);
        model.addAttribute("SumAmount", indicativeCostService.sumValues(list));

        return "IndicativeCostsTemplates/editIndicativeCostWithLinkToConsOrderForm";
    }

    @PostMapping("/consOrderEdit/indicativecost/{id}/edit")
    public String editIndicativeCost(@PathVariable String id,
                                     @Valid @ModelAttribute("cost") IndicativeCostCommand indCostCommand,
                                     BindingResult result,
                                     Model model) {

        ConstructionOrderCommand command1 = constructionOrderService.findCommandByID(Long.valueOf(id));
        List list = indicativeCostService.sortSet(command1.getIndicativeCostCommands());

        model.addAttribute("cost", indCostCommand);
        model.addAttribute("ConsOrd", command1);
        model.addAttribute("ConsOrd2", list);
        model.addAttribute("SumAmount", indicativeCostService.sumValues(list));

        if (result.hasErrors()){
            return "IndicativeCostsTemplates/editIndicativeCostWithLinkToConsOrderForm";
        }

        IndicativeCost cost = indicativeCostService.findByID(indCostCommand.getID());
        cost.setAmount(indCostCommand.getAmount());
        cost.setForWhat(indCostCommand.getForWhat());
        indicativeCostService.saveCost(cost);

        return "redirect:/consOrderEdit/newCostToConsOrder/" + id;
    }
}

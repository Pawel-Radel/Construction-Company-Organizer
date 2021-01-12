package com.radello.constructioncompanyorganizer.controller;

import com.radello.constructioncompanyorganizer.commands.ConstructionOrderCommand;
import com.radello.constructioncompanyorganizer.commands.IncomeCommand;
import com.radello.constructioncompanyorganizer.services.constructionOrderServices.ConstructionOrderService;
import com.radello.constructioncompanyorganizer.services.incomesServices.IncomeService;
import com.radello.constructioncompanyorganizer.services.indicativeCostServices.IndicativeCostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ConstructionOrderController {

    ConstructionOrderService constructionOrderService;
    IncomeService incomeService;


    public ConstructionOrderController(ConstructionOrderService constructionOrderService, IncomeService incomeService) {
        this.constructionOrderService = constructionOrderService;
        this.incomeService = incomeService;
    }

    @GetMapping("/listConstructionOrder")
    public String showConsOrdList(Model model) {

        model.addAttribute("ConsOrder", constructionOrderService.getConstructionOrders());


        return "ConstructionOrderList";
    }

    @GetMapping("/newConstructionOrder")
    public String newConstructionOrderform(Model model) {

        model.addAttribute("ConsOrder", new ConstructionOrderCommand());

        return "newConsOrderP1";
    }

    @PostMapping("/createCostOrder")
    public String createConsOrder(@ModelAttribute("ConsOrder") ConstructionOrderCommand command) {
        ConstructionOrderCommand command1 = constructionOrderService.saveConstructionOrderCommand(command);

        Long ID = command1.getID();

        return "redirect:/newCostToConsOrder/" + ID;
    }



    @GetMapping("/newIncomeToConsOrder/{id}")
    public String addingIncomestoConstructionOrder (@PathVariable String id, Model model){

        ConstructionOrderCommand command1 = constructionOrderService.findCommandByID(Long.valueOf(id));
        List list = incomeService.sortSet(command1.getIncomeCommands());
        incomeService.sortSet(command1.getIncomeCommands());

        model.addAttribute("income", new IncomeCommand());
        model.addAttribute("ConsOrd", command1);
        model.addAttribute("ConsOrd2", list);
        model.addAttribute("SumAmount", incomeService.sumValues(list));

        return "newIncomesToConsOrder";

    }

    @PostMapping("/newIncomeToConsOrder/{id}/create/")
    public String addingCostToConstructionOrder(@ModelAttribute("income") IncomeCommand incomeCommand,
                                                @PathVariable String id, Model model) {

        ConstructionOrderCommand command = constructionOrderService.findCommandByID(Long.valueOf(id));
        command.addIncomes(incomeCommand);

        constructionOrderService.saveConstructionOrderCommand(command);

        return "redirect:/newIncomeToConsOrder/" + id;
    }

    }



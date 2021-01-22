package com.radello.constructioncompanyorganizer.controller;

import com.radello.constructioncompanyorganizer.commands.ConstructionOrderCommand;
import com.radello.constructioncompanyorganizer.domain.DateFormatter;
import com.radello.constructioncompanyorganizer.services.budgetServices.BudgetService;
import com.radello.constructioncompanyorganizer.services.constructionOrderServices.ConstructionOrderService;
import com.radello.constructioncompanyorganizer.services.incomesServices.IncomeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.format.DateTimeParseException;

@Slf4j
@Controller
public class ConstructionOrderController {

    ConstructionOrderService constructionOrderService;
    IncomeService incomeService;
    BudgetService budgetService;

    public ConstructionOrderController(ConstructionOrderService constructionOrderService,
                                       IncomeService incomeService,
                                       BudgetService budgetService) {

        this.constructionOrderService = constructionOrderService;
        this.incomeService = incomeService;
        this.budgetService = budgetService;
    }

    @GetMapping("/listConstructionOrder")
    public String showConsOrdList(Model model) {

        model.addAttribute("ConsOrder", constructionOrderService.getConstructionOrders());
        model.addAttribute("Budget", budgetService.find());
        return "ConstructionOrderTemplates/ConstructionOrderList";
    }

    @GetMapping("/newConstructionOrder")
    public String newConstructionOrderform(Model model) {

        model.addAttribute("ConsOrder", new ConstructionOrderCommand());


        return "ConstructionOrderTemplates/newConsOrderP1";
    }

    @PostMapping("/createCostOrder")
    public String createConsOrder(@Valid @ModelAttribute("ConsOrder") ConstructionOrderCommand command, BindingResult result,
                                  @RequestParam(value = "date1") String date1,
                                  @RequestParam(value = "date2") String date2,
                                  Model model) {

       model.addAttribute("startDate", date1);
       model.addAttribute("endDate", date2);


        if (result.hasErrors()) {
            return "ConstructionOrderTemplates/newConsOrderP1";
        }
        try {
            command.setDatesByStrings(date1, date2);
        } catch (DateTimeParseException exc) {
            //return "redirect:/";
            return "ConstructionOrderTemplates/newConsOrderP1WithParseDateError";
        }
        ConstructionOrderCommand command1 = constructionOrderService.saveConstructionOrderCommand(command);
        Long ID = command1.getID();

        return "redirect:/newCostToConsOrder/" + ID;

    }

    @GetMapping("constructionOrder/{id}/delete")
    public String deleteIncomefromConstructionOrderById(@PathVariable String id) {

        constructionOrderService.deleteById(Long.valueOf(id));

        return "redirect:/listConstructionOrder";
    }

    @GetMapping("constructionOrder/{id}/settlementAndDelete")
    public String settlementAndDeleteIncomefromConstructionOrderById(@PathVariable String id) {

        budgetService
                .increaseBudget(constructionOrderService
                        .sumIncomes(constructionOrderService.findCommandByID(Long.valueOf(id))));

        constructionOrderService.deleteById(Long.valueOf(id));

        return "redirect:/listConstructionOrder";
    }

    @GetMapping("constructionOrder/{id}/edit")
    public String editConstructionOrder(@PathVariable String id, Model model) {

        ConstructionOrderCommand command = constructionOrderService.findCommandByID(Long.valueOf(id));
        model.addAttribute("ConsOrder", command);
        model.addAttribute("startDate", DateFormatter.formatDateToProperlyString(command.getStartDate()));
        model.addAttribute("endDate", DateFormatter.formatDateToProperlyString(command.getScheduledEndDate()));

        return "ConstructionOrderTemplates/ConstructionOrderform";
    }

    @PostMapping("constructionOrder/{id}/ConfrimEdit")
    public String confrimEditConstructionORder(@Valid @ModelAttribute("ConsOrder") ConstructionOrderCommand command,
                                               BindingResult result,
                                               @RequestParam(value = "date1") String date1,
                                               @RequestParam(value = "date2") String date2,
                                               Model model) {

        model.addAttribute("startDate", date1);
        model.addAttribute("endDate", date2);
        if (result.hasErrors()){

            return "ConstructionOrderTemplates/ConstructionOrderform";
        }
        try {
            command.setDatesByStrings(date1, date2);
        }
        catch (DateTimeParseException exception) {
            return "ConstructionOrderTemplates/ConstructionOrderFormWithErrors";
        }

        constructionOrderService.saveConstructionOrderCommand(command);

        return "redirect:/listConstructionOrder";
    }


}






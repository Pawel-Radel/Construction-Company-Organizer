package com.radello.constructioncompanyorganizer.controller.IncomeControllers;

import com.radello.constructioncompanyorganizer.commands.ConstructionOrderCommand;
import com.radello.constructioncompanyorganizer.commands.IncomeCommand;
import com.radello.constructioncompanyorganizer.domain.DateFormatter;
import com.radello.constructioncompanyorganizer.domain.Income;
import com.radello.constructioncompanyorganizer.services.constructionOrderServices.ConstructionOrderService;
import com.radello.constructioncompanyorganizer.services.incomesServices.IncomeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.format.DateTimeParseException;
import java.util.List;

//Controller that handles inquiries revenue incomes during editing  a construction order
@Controller
public class IncomeControllerToConstructionOrderFormEdit {

    IncomeService incomeService;
    ConstructionOrderService constructionOrderService;

    public IncomeControllerToConstructionOrderFormEdit(IncomeService incomeService,
                                                         ConstructionOrderService constructionOrderService) {

        this.incomeService = incomeService;
        this.constructionOrderService = constructionOrderService;
    }

    @GetMapping("/constructionOrderEdit/newIncomeToConsOrder/{id}")
    public String addingIncomestoConstructionOrder(@PathVariable String id, Model model) {

        ConstructionOrderCommand command1 = constructionOrderService.findCommandByID(Long.valueOf(id));
        List list = incomeService.sortSet(command1.getIncomeCommands());

        model.addAttribute("income", new IncomeCommand());
        model.addAttribute("ConsOrd", command1);
        model.addAttribute("ConsOrd2", list);
        model.addAttribute("SumAmount", incomeService.sumValues(list));

        return "IncomeTemplates/newIncomesToConsOrderwithLinkToConsOrder";
    }

    @PostMapping("/constructionOrderEdit/newIncomeToConsOrder/{id}/create/")
    public String addingCostToConstructionOrder(@Valid @ModelAttribute("income") IncomeCommand incomeCommand,
                                                BindingResult result,
                                                @PathVariable String id,
                                                @RequestParam("date") String date,
                                                Model model) {

        ConstructionOrderCommand command1 = constructionOrderService.findCommandByID(Long.valueOf(id));
        List list = incomeService.sortSet(command1.getIncomeCommands());

        model.addAttribute("ConsOrd", command1);
        model.addAttribute("ConsOrd2", list);
        model.addAttribute("SumAmount", incomeService.sumValues(list));
        model.addAttribute("date", date);

        if (result.hasErrors()){
            return "IncomeTemplates/newIncomesToConsOrderwithLinkToConsOrder";
        }
        try {
            incomeCommand.setDatesByString(date);
        }
        catch (DateTimeParseException exception){
            return "IncomeTemplates/newIncomesToConsOrderwithLinkToConsOrderAndError";
        }

        IncomeCommand savedIncomeCommand = incomeService.saveIncomeCommand(incomeCommand);
        command1.addIncomes(savedIncomeCommand);

        constructionOrderService.saveConstructionOrderCommand(command1);

        return "redirect:/constructionOrderEdit/newIncomeToConsOrder/" + id;
    }

    @GetMapping("/constructionOrderEdit/incomefromConsOrd/{id}/delete")
    public String deleteIncomefromConstructionOrderById(@PathVariable String id) {

        Long ID = incomeService.findByID(Long.valueOf(id)).getConstructionOrder().getID();
        String string = String.valueOf(ID);
        incomeService.deleteById(Long.valueOf(id));

        return "redirect:/constructionOrderEdit/newIncomeToConsOrder/" + string;
    }

    @GetMapping("/constructionOrderEdit/constructionOrder/{id1}/income/{id2}/edit/")
    public String editIndcomeById(@PathVariable String id1,
                                  @PathVariable String id2,
                                  Model model) {

        ConstructionOrderCommand command1 = constructionOrderService.findCommandByID(Long.valueOf(id1));
        List list = incomeService.sortSet(command1.getIncomeCommands());
        IncomeCommand incomeCommand = incomeService.findCommandByID(Long.valueOf(id2));
        String date = DateFormatter.formatDateToProperlyString(incomeCommand.getScheduledTimeToGet());

        model.addAttribute("income", incomeCommand);
        model.addAttribute("ConsOrd", command1);
        model.addAttribute("ConsOrd2", list);
        model.addAttribute("SumAmount", incomeService.sumValues(list));
        model.addAttribute("date", date);

        return "IncomeTemplates/editIncomesWithLinkToConsOrder";
    }

    @PostMapping("/constructionOrderEdit/income/{id}/edit")
    public String editIndicativeCost(@PathVariable String id,
                                     @Valid @ModelAttribute("income") IncomeCommand incomeCommand,
                                     BindingResult result,
                                     @RequestParam(value = "date2") String date,
                                     Model model) {

        ConstructionOrderCommand command1 = constructionOrderService.findCommandByID(Long.valueOf(id));
        List list = incomeService.sortSet(command1.getIncomeCommands());

        model.addAttribute("ConsOrd", command1);
        model.addAttribute("ConsOrd2", list);
        model.addAttribute("SumAmount", incomeService.sumValues(list));
        model.addAttribute("date", date);

        if (result.hasErrors()){
            return "IncomeTemplates/editIncomesWithLinkToConsOrder";
        }
        try {
            incomeCommand.setDatesByString(date);
        }
        catch (DateTimeParseException exception){
            return "IncomeTemplates/editIncomesWithLinkToConsOrderAndErrors";
        }
        Income income = incomeService.findByID(incomeCommand.getID());
        income.setAmount(incomeCommand.getAmount());
        income.setForWhat(incomeCommand.getForWhat());
        income.setScheduledTimeToGet(incomeCommand.getScheduledTimeToGet());
        incomeService.saveIncome(income);

        return "redirect:/constructionOrderEdit/newIncomeToConsOrder/" + id;
    }
}

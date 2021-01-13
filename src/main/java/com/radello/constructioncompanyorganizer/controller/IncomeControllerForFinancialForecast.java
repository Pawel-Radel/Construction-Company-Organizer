package com.radello.constructioncompanyorganizer.controller;

import com.radello.constructioncompanyorganizer.commands.ConstructionOrderCommand;
import com.radello.constructioncompanyorganizer.commands.IncomeCommand;
import com.radello.constructioncompanyorganizer.commands.IndicativeCostCommand;
import com.radello.constructioncompanyorganizer.domain.IndicativeCost;
import com.radello.constructioncompanyorganizer.services.constructionOrderServices.ConstructionOrderService;
import com.radello.constructioncompanyorganizer.services.incomesServices.IncomeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
public class IncomeControllerForFinancialForecast {

    IncomeService incomeService;

    public IncomeControllerForFinancialForecast(IncomeService incomeService) {
        this.incomeService = incomeService;
    }

    @GetMapping("/newIncome")
    public String shownewIncomeTemplate(Model model) {

        model.addAttribute("income", new IncomeCommand());

        return "newIncome";
    }

    @PostMapping("income")
    public String saveOrUpdate(@Valid @ModelAttribute("income") IncomeCommand command,
                               @RequestParam("date") String localDate,
                               BindingResult bindingResult) {

        command.setDatesByString(localDate);

        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors()
                    .forEach(objectError -> System.out.println((objectError.toString())));
            return "index";
        }

        IncomeCommand savedCommand = incomeService.saveIncomeCommand(command);

        return "redirect:/financialForecast";
    }

    @GetMapping("income/{id}/delete")
    public String deleteById(@PathVariable String id) {

        log.debug("Deleting id: " + id);

        incomeService.deleteById(Long.valueOf(id));
        return "redirect:/financialForecast";
    }



}

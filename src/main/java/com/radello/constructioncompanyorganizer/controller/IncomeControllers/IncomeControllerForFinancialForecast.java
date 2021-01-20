package com.radello.constructioncompanyorganizer.controller.IncomeControllers;

import com.radello.constructioncompanyorganizer.commands.IncomeCommand;
import com.radello.constructioncompanyorganizer.services.incomesServices.IncomeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


//Controller that handles inquiries revenue incomes without creating a construction order
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

        return "IncomeTemplates/newIncome";
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

        incomeService.saveIncomeCommand(command);

        return "redirect:/financialForecast";
    }

    @GetMapping("income/{id}/delete")
    public String deleteById(@PathVariable String id) {

        incomeService.deleteById(Long.valueOf(id));
        return "redirect:/financialForecast";
    }

}

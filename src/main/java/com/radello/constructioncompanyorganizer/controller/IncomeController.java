package com.radello.constructioncompanyorganizer.controller;

import com.radello.constructioncompanyorganizer.commands.IncomeCommand;
import com.radello.constructioncompanyorganizer.services.IncomeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;


@Slf4j
@Controller
public class IncomeController {

    IncomeService incomeService;

    public IncomeController(IncomeService incomeService) {
        this.incomeService = incomeService;
    }

    @GetMapping("/newIncome")
    public String shownewIncomeTemplate(Model model){

        model.addAttribute("income", new IncomeCommand());

        return "newIncome";
    }

    @PostMapping("income")
    public String saveOrUpdate(@Valid @ModelAttribute("income") IncomeCommand command,
                               BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {

            bindingResult.getAllErrors()
        .forEach(objectError -> log.debug(objectError.toString()));

            return "index";
        }
        IncomeCommand savedCommand = incomeService.saveIncomeCommand(command);

        return "redirect:/financialForecast";
    }
}

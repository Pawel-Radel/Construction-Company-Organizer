package com.radello.constructioncompanyorganizer.controller;

import com.radello.constructioncompanyorganizer.commands.CostCommand;
import com.radello.constructioncompanyorganizer.services.CostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.time.LocalDate;

@Slf4j
@Controller
public class CostController {

    CostService costService;

    public CostController(CostService costService) {
        this.costService = costService;
    }

    @PostMapping("cost")
    public String saveOrUpdate(@Valid @ModelAttribute("cost") CostCommand command, BindingResult bindingResult) {

          if(bindingResult.hasErrors()){

            bindingResult.getAllErrors().forEach(objectError -> {
        System.out.println(objectError.toString());
        });
return "index";
    }

        CostCommand savedCommand = costService.saveCostCommand(command);

        System.out.println(savedCommand.getScheduledtime());

        return "redirect:/financialForecast";
    }

}

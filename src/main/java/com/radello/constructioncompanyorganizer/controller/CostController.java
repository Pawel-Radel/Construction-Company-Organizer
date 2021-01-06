package com.radello.constructioncompanyorganizer.controller;

import com.radello.constructioncompanyorganizer.commands.CostCommand;
import com.radello.constructioncompanyorganizer.services.CostService;
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
public class CostController {

    CostService costService;

    public CostController(CostService costService) {
        this.costService = costService;

    }


    @GetMapping("/newCost")
    public String shownewCostTemplate(Model model) {

        model.addAttribute("cost", new CostCommand());

        return "newCost";
    }

    @PostMapping("cost")
    public String saveOrUpdate(@Valid @ModelAttribute("cost") CostCommand command,
                               BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {

            bindingResult.getAllErrors().forEach(objectError -> log.debug(objectError.toString()));
            return "index";
        }

        costService.saveCostCommand(command);
        
        return "redirect:/financialForecast";
    }

}

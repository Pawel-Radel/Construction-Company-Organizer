package com.radello.constructioncompanyorganizer.controller;

import com.radello.constructioncompanyorganizer.commands.CostCommand;
import com.radello.constructioncompanyorganizer.services.costsServices.CostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.format.DateTimeParseException;

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
                               BindingResult bindingResult,
                               @RequestParam("date2") String localDate,
                               Model model) {

        model.addAttribute("date", localDate);

        if (bindingResult.hasErrors()) {
            return "newCost";
        }
        try {
            command.setDatesByString(localDate);
        }
        catch (DateTimeParseException exception){
            model.addAttribute("error", "Pole daty nie może być puste i musi mieć format \"mm/dd/yyyy\"");
            return "newCost";
        }

        command.setDatesByString(localDate);

        costService.saveCostCommand(command);

        return "redirect:/financialForecast";
    }

    @GetMapping("cost/{id}/delete")
    public String deleteById(@PathVariable String id) {

        log.debug("Deleting id: " + id);

        costService.deleteById(Long.valueOf(id));
        return "redirect:/financialForecast";
    }

}

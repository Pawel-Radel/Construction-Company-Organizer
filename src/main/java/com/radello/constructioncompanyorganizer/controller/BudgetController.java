package com.radello.constructioncompanyorganizer.controller;

import com.radello.constructioncompanyorganizer.domain.Cost;
import com.radello.constructioncompanyorganizer.domain.Income;
import com.radello.constructioncompanyorganizer.services.budgetServices.BudgetService;
import com.radello.constructioncompanyorganizer.services.costsServices.CostService;
import com.radello.constructioncompanyorganizer.services.incomesServices.IncomeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BudgetController {

    BudgetService budgetService;
    CostService costService;
    IncomeService incomeService;

    public BudgetController(BudgetService budgetService,
                            CostService costService,
                            IncomeService incomeService) {

        this.budgetService = budgetService;
        this.costService = costService;
        this.incomeService = incomeService;
    }

    @GetMapping("budget/{id}/decrease")
    public String decreaseBudget(@PathVariable String id) {

        Cost cost = costService.findByID(Long.valueOf(id));

        budgetService.decreaseBudget(cost.getAmount());
        return "redirect:/cost/" + id + "/delete";
    }

    @GetMapping("budget/{id}/increase")
    public String increaseBudget(@PathVariable String id) {

        Income income = incomeService.findByID(Long.valueOf(id));

        budgetService.increaseBudget(income.getAmount());

        return "redirect:/income/" + id + "/delete";
    }

    @PostMapping("postpone/{id}/income")
    public String increaseBudget2(@RequestParam(value = "test", required = false) Long value,
                                  @PathVariable String id) {
        budgetService.increaseBudget(incomeService.findCommandByID(Long.valueOf(id)).getAmount());
        incomeService.realizeAndPostponeToNewDate(id, value);

        return "redirect:/financialForecast";
    }

    @PostMapping("postpone/{id}/cost")
    public String decreaseBudget2(@RequestParam(value = "test2", required = false) Long value,
                                  @PathVariable String id) {
        budgetService.decreaseBudget(costService.findCommandByID(Long.valueOf(id)).getAmount());
        costService.realizeAndPostponeToNewDate(id, value);

        return "redirect:/financialForecast";
    }

}

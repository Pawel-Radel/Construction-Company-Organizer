package com.radello.constructioncompanyorganizer.controller;

import com.radello.constructioncompanyorganizer.commands.IncomeCommand;
import com.radello.constructioncompanyorganizer.domain.Cost;
import com.radello.constructioncompanyorganizer.domain.Income;
import com.radello.constructioncompanyorganizer.services.budgetServices.BudgetService;
import com.radello.constructioncompanyorganizer.services.costsServices.CostService;
import com.radello.constructioncompanyorganizer.services.incomesServices.IncomeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
    public String IncreaseBudget(@PathVariable String id) {

        Income income = incomeService.findByID(Long.valueOf(id));

        budgetService.increaseBudget(income.getAmount());

        return "redirect:/income/" + id + "/delete";
    }

    @GetMapping("logout")
    public String IncreaseBudget2(@ModelAttribute("Integer") String string) {

        System.out.println("efffffffffff");
        System.out.println(string);

        return "index";
    }
}

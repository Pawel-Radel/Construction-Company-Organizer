package com.radello.constructioncompanyorganizer.controller;

import com.radello.constructioncompanyorganizer.services.budgetServices.BudgetService;
import com.radello.constructioncompanyorganizer.services.costsServices.CostSumService;
import com.radello.constructioncompanyorganizer.services.costsServices.CostsDependsOnTimeService;
import com.radello.constructioncompanyorganizer.services.incomesServices.IncomesDependsOnTime;
import com.radello.constructioncompanyorganizer.services.incomesServices.IncomeSumService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class FinancialForecastController {

    CostsDependsOnTimeService costsDependsOnTimeService;
    IncomesDependsOnTime incomesDependsOnTime;
    IncomeSumService incomeSumService;
    CostSumService costSumService;
    BudgetService budgetService;

    public FinancialForecastController(CostsDependsOnTimeService costsDependsOnTimeService,
                                       IncomesDependsOnTime incomesDependsOnTime,
                                       IncomeSumService incomeSumService,
                                       CostSumService costSumService,
                                       BudgetService budgetService) {

        this.costsDependsOnTimeService = costsDependsOnTimeService;
        this.incomesDependsOnTime = incomesDependsOnTime;
        this.incomeSumService = incomeSumService;
        this.costSumService = costSumService;
        this.budgetService = budgetService;
    }


    @GetMapping("/financialForecast")
    public String showFinancialForecast(Model model) {

        model.addAttribute("costs", costsDependsOnTimeService.getCosts());
        model.addAttribute("outsCosts", costsDependsOnTimeService.getOutstandingCosts());
        model.addAttribute("nextCosts", costsDependsOnTimeService.getCostsNextMonth());
        model.addAttribute("anotherCosts", costsDependsOnTimeService.getCostsAnotherMonth());
        model.addAttribute("furtherCosts", costsDependsOnTimeService.getFurtherCosts());
        model.addAttribute("incomes", incomesDependsOnTime.getIncomes());
        model.addAttribute("outsIncomes", incomesDependsOnTime.getOutstandingIncomes());
        model.addAttribute("nextIncomes", incomesDependsOnTime.getIncomesNextMonth());
        model.addAttribute("anotherIncomes", incomesDependsOnTime.getIncomesAnotherMonth());
        model.addAttribute("furtherIncomes", incomesDependsOnTime.getFurtherIncomes());
        model.addAttribute("sumOfIncomes", incomeSumService.getSumOfIncomes());
        model.addAttribute("sumOfOutsIncomes", incomeSumService.getSumOfOutstandIncomes());
        model.addAttribute("sumOfNextIncomes", incomeSumService.getSumOfNextIncomes());
        model.addAttribute("sumOfAnotherIncomes", incomeSumService.getSumOfAnotherIncomes());
        model.addAttribute("sumOfFurtherIncomes", incomeSumService.getSumOfFurtherIncomes());
        model.addAttribute("sumOfCosts", costSumService.getSumOfCosts());
        model.addAttribute("sumOfOutsCosts", costSumService.getSumOfOutstandCosts());
        model.addAttribute("sumOfNextCosts", costSumService.getSumOfNextCosts());
        model.addAttribute("sumOfAnotherCosts", costSumService.getSumOfAnotherCosts());
        model.addAttribute("sumOfFurtherCosts", costSumService.getSumOfFurtherCosts());
        model.addAttribute("Budget", budgetService.find());

        return "FinancialForecast";
    }

}


package com.radello.constructioncompanyorganizer.controller;

import com.radello.constructioncompanyorganizer.services.costsServices.CostsDependsOnTimeService;
import com.radello.constructioncompanyorganizer.services.incomesServices.IncomesDependsOnTime;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class FinancialForecastController {

    CostsDependsOnTimeService costsDependsOnTimeService;
    IncomesDependsOnTime incomesDependsOnTime;

    public FinancialForecastController(CostsDependsOnTimeService costsDependsOnTimeService,
                                       IncomesDependsOnTime incomesDependsOnTime) {
        this.costsDependsOnTimeService = costsDependsOnTimeService;
        this.incomesDependsOnTime = incomesDependsOnTime;
    }

    @GetMapping("/financialForecast")
    public String showFinancialForecast(Model model){

        model.addAttribute("costs", costsDependsOnTimeService.getCosts());
        model.addAttribute("outsCosts", costsDependsOnTimeService.getOutstandingCosts());
        model.addAttribute("nextCosts", costsDependsOnTimeService.getCostsNextMonth());
        model.addAttribute("anotherCosts", costsDependsOnTimeService.getCostsAnotherMonth());
        model.addAttribute("furtherCosts", costsDependsOnTimeService.furtherCosts());
        model.addAttribute("incomes", incomesDependsOnTime.getIncomes());
        model.addAttribute("outsIncomes", incomesDependsOnTime.getOutstandingIncomes());
        model.addAttribute("nextIncomes", incomesDependsOnTime.getIncomesNextMonth());
        model.addAttribute("anotherIncomes", incomesDependsOnTime.getIncomesAnotherMonth());
        model.addAttribute("furtherIncomes", incomesDependsOnTime.getFurtherIncomes());

        return "FinancialForecast";
    }
}

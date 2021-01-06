package com.radello.constructioncompanyorganizer.controller;

import com.radello.constructioncompanyorganizer.services.*;
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
        model.addAttribute("incomes", incomesDependsOnTime.getIncomes());
        model.addAttribute("outsCosts", costsDependsOnTimeService.getOutstandingCosts());
        model.addAttribute("outsIncomes", incomesDependsOnTime.getOutstandingIncomes());

        return "FinancialForecast";
    }
}

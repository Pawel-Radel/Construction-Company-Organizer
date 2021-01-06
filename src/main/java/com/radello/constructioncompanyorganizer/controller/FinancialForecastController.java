package com.radello.constructioncompanyorganizer.controller;

import com.radello.constructioncompanyorganizer.services.CostService;
import com.radello.constructioncompanyorganizer.services.CostServiceImpl;
import com.radello.constructioncompanyorganizer.services.IncomeService;
import com.radello.constructioncompanyorganizer.services.IncomeServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FinancialForecastController {

    IncomeService incomeService;
    CostService costService;

    public FinancialForecastController(IncomeService incomeService, CostService costService) {
        this.incomeService = incomeService;
        this.costService = costService;
    }

    @GetMapping("/financialForecast")
    public String showFinancialForecast(Model model){

        model.addAttribute("costs", costService.getCosts());
        return "FinancialForecast";
    }
}

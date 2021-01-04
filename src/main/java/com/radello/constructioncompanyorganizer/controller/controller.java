package com.radello.constructioncompanyorganizer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class controller {

    @GetMapping
    public String showIndex(){
        return "index";
    }

    @GetMapping ("/listConstructionOrder")
    public String showConsOrdList(){
        return "ConstructionOrderList";
    }

    @GetMapping("/newConstructionOrder")
    public String shownewConstructionOrderform() {
        return "ConstructionOrderform";
    }

    @GetMapping("/newIncome")
    public String shownewIncomeTemplate(){

        return "newIncome";
    }

    @GetMapping("/ConstructionOrderView")
    public String showConstructionOrder(){

        return "ConstructionOrderFormView";
    }

    @GetMapping("/financialForecast")
    public String showFinancialForecast(){
        return "FinancialForecast";
    }

    @GetMapping("/newCost")
    public String shownewCostTemplate(){

        return "newCost";
    }
}

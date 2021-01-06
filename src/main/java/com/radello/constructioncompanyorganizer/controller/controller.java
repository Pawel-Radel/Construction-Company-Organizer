package com.radello.constructioncompanyorganizer.controller;

import com.radello.constructioncompanyorganizer.commands.CostCommand;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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


    @GetMapping("/newCost")
    public String shownewCostTemplate(Model model){

        model.addAttribute("cost", new CostCommand());

        return "newCost";
    }
}

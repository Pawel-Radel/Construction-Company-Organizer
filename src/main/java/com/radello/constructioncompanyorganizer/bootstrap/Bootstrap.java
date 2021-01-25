package com.radello.constructioncompanyorganizer.bootstrap;

import com.radello.constructioncompanyorganizer.domain.Budget;
import com.radello.constructioncompanyorganizer.domain.Cost;
import com.radello.constructioncompanyorganizer.domain.Income;
import com.radello.constructioncompanyorganizer.repositories.BudgetRepository;
import com.radello.constructioncompanyorganizer.repositories.CostRepository;
import com.radello.constructioncompanyorganizer.repositories.IncomeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;



// Class to load data before Application Start
@Slf4j
@Component
@Profile(value = "default")
public class Bootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final LocalDate TODAY_DATE = LocalDate.now();
    private final LocalDate ONE_MONTH_LATER = LocalDate.now().plusMonths(1);
    private final LocalDate TWO_MONTH_LATER = LocalDate.now().plusMonths(2);

    BudgetRepository budgetRepository;
    IncomeRepository incomeRepository;
    CostRepository costRepository;

    public Bootstrap(BudgetRepository budgetRepository,
                     IncomeRepository incomeRepository,
                     CostRepository costRepository) {

        this.budgetRepository = budgetRepository;
        this.incomeRepository = incomeRepository;
        this.costRepository = costRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
       log.info("Loading Bootstrap Data");

        createBudget();
        createIncomes();
        createCosts();
    }

    private void createCosts() {
        Cost cost1 = new Cost("Payment", TODAY_DATE.minusDays(1));
        cost1.setAmount(1000);
        costRepository.save(cost1);
        Cost cost2 = new Cost("Payment", TODAY_DATE.minusDays(2));
        cost2.setAmount(2000);
        costRepository.save(cost2);
        Cost cost3 = new Cost("Payment", TODAY_DATE);
        cost3.setAmount(3000);
        costRepository.save(cost3);
        Cost cost4 = new Cost("Payment", TODAY_DATE.plusDays(1));
        cost4.setAmount(4000);
        costRepository.save(cost4);
        Cost cost5 = new Cost("Payment", ONE_MONTH_LATER.minusDays(1));
        cost5.setAmount(5000);
        costRepository.save(cost5);
        Cost cost6 = new Cost("Payment", ONE_MONTH_LATER);
        cost6.setAmount(1100);
        costRepository.save(cost6);
        Cost cost7 = new Cost("Payment", ONE_MONTH_LATER.plusDays(1));
        cost7.setAmount(1200);
        costRepository.save(cost7);
        Cost cost8 = new Cost("Payment", ONE_MONTH_LATER.minusDays(1));
        cost8.setAmount(1300);
        costRepository.save(cost8);
        Cost cost9 = new Cost("Payment", TWO_MONTH_LATER.minusDays(1));
        cost9.setAmount(1400);
        costRepository.save(cost9);
        Cost cost10 = new Cost("Payment", TWO_MONTH_LATER);
        cost10.setAmount(1500);
        costRepository.save(cost10);
        Cost cost11 = new Cost("Payment", TWO_MONTH_LATER.plusDays(1));
        cost11.setAmount(1600);
        costRepository.save(cost11);
    }

   private void createIncomes() {
        Income income1 = new Income("Flat", TODAY_DATE.minusDays(1),null );
        income1.setAmount(1000);
        incomeRepository.save(income1);
        Income income2 = new Income("Flat1", TODAY_DATE.minusDays(2),null );
        income2.setAmount(2000);
        incomeRepository.save(income2);
        Income income3 = new Income("Flat2", TODAY_DATE.plusDays(1),null );
        income3.setAmount(3000);
        incomeRepository.save(income3);
        Income income4 = new Income("Flat3", TODAY_DATE.plusDays(1),null );
        income4.setAmount(4000);
        incomeRepository.save(income4);
        Income income5 = new Income("Flat4", ONE_MONTH_LATER.minusDays(1),null );
        income5.setAmount(5000);
        incomeRepository.save(income5);
        Income income6 = new Income("Flat5", ONE_MONTH_LATER,null );
        income6.setAmount(1100);
        incomeRepository.save(income6);
        Income income7 = new Income("Flat6", ONE_MONTH_LATER.plusDays(3),null );
        income7.setAmount(1200);
        incomeRepository.save(income7);
        Income income8 = new Income("Flat7", TWO_MONTH_LATER,null );
        income8.setAmount(1400);
        incomeRepository.save(income8);
        Income income9 = new Income("Flat8", TWO_MONTH_LATER.minusDays(1),null );
        income9.setAmount(1500);
        incomeRepository.save(income9);
        Income income10 = new Income("Flat9", TWO_MONTH_LATER.plusDays(1),null );
        income10.setAmount(1600);
        incomeRepository.save(income10);
        Income income11 = new Income("Flat10",TWO_MONTH_LATER.plusDays(2),null );
        income11.setAmount(1000);
        incomeRepository.save(income11);
    }

    private void createBudget() {
        Budget budget = new Budget();
        budget.setAmount(20000);
        budgetRepository.save(budget);
    }
}

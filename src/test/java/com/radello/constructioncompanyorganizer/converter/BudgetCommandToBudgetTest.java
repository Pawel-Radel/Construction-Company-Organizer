package com.radello.constructioncompanyorganizer.converter;

import com.radello.constructioncompanyorganizer.commands.BudgetCommand;
import com.radello.constructioncompanyorganizer.domain.Budget;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BudgetCommandToBudgetTest {

    private static final int ID_VALUE = 1;
    private static final int AMOUNT_VALUE = 12;

    BudgetCommandToBudget budgetCommandToBudget;
    @BeforeEach
    void setUp() {
        budgetCommandToBudget = new BudgetCommandToBudget();
    }

    @Test
    public void testNullParameter(){
        assertNull(budgetCommandToBudget.convert(null));
    }

    @Test
    public void testEmptyObject(){
        assertNotNull(budgetCommandToBudget.convert(new BudgetCommand()));
    }

    @Test
    void convert() {
        BudgetCommand budgetCommand = new BudgetCommand();
        budgetCommand.setID(ID_VALUE);
        budgetCommand.setAmount(AMOUNT_VALUE);

        Budget budget = budgetCommandToBudget.convert(budgetCommand);

        assertNotNull(budget);
        assertEquals(ID_VALUE, budget.getID());
        assertEquals(AMOUNT_VALUE, budget.getAmount());

    }
}
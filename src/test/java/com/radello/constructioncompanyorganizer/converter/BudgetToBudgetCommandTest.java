package com.radello.constructioncompanyorganizer.converter;

import com.radello.constructioncompanyorganizer.commands.BudgetCommand;
import com.radello.constructioncompanyorganizer.domain.Budget;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BudgetToBudgetCommandTest {

    private static final int ID_VALUE = 1;
    private static final int AMOUNT_VALUE = 12;

    BudgetToBudgetCommand budgetToBudgetCommand;

    @BeforeEach
    void setUp() {
        budgetToBudgetCommand = new BudgetToBudgetCommand();
    }

    @Test
    void testNullObject(){
        assertNull(budgetToBudgetCommand.convert(null));
    }

    @Test
    void testEmptyObject(){
        assertNotNull(budgetToBudgetCommand.convert(new Budget()));
    }
    @Test
    void convert() {
        Budget budget = new Budget();
        budget.setID(ID_VALUE);
        budget.setAmount(AMOUNT_VALUE);

        BudgetCommand budgetCommand = budgetToBudgetCommand.convert(budget);

        assertNotNull(budgetCommand);
        assertEquals(ID_VALUE, budgetCommand.getID());
        assertEquals(AMOUNT_VALUE, budgetCommand.getAmount());
    }
}
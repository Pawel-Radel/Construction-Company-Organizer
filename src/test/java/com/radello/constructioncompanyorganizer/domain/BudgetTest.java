package com.radello.constructioncompanyorganizer.domain;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BudgetTest {

    Budget budget;
    @BeforeEach
    void setUp() {
        budget = new Budget();
    }

    @Test
    void getID() {
        Long testID = 1L;

        budget.setID(testID);

        assertEquals(testID, budget.getID());
    }

    @Test
    void getAmount() {
        int testAmount = 100;

        budget.setAmount(testAmount);

        assertEquals(testAmount, budget.getAmount());
    }
}
package com.radello.constructioncompanyorganizer.converter;

import com.radello.constructioncompanyorganizer.commands.BudgetCommand;
import com.radello.constructioncompanyorganizer.commands.CostCommand;
import com.radello.constructioncompanyorganizer.domain.Cost;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CostCommandToCostTest {

    private final Long ID_VALUE = 1L;
    private final int AMOUNT_VALUE = 123;
    private final String FOR_WHAT_VALUE = "Flat";
    private final LocalDate LOCALE_DATE_VALUE = LocalDate.of(2020, 11, 11);


    CostCommandToCost costCommandToCost;

    @BeforeEach
    void setUp() {
        costCommandToCost = new CostCommandToCost();
    }

    @Test
    public void testNullParameter() {
        assertNull(costCommandToCost.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(costCommandToCost.convert(new CostCommand()));
    }

    @Test
    void convert() {
        CostCommand costCommand = new CostCommand();

        costCommand.setID(ID_VALUE);
        costCommand.setScheduledtime(LOCALE_DATE_VALUE);
        costCommand.setForWhat(FOR_WHAT_VALUE);
        costCommand.setAmount(AMOUNT_VALUE);

        Cost cost = costCommandToCost.convert(costCommand);

        assertNotNull(cost);
        assertEquals(ID_VALUE, cost.getID());
        assertEquals(LOCALE_DATE_VALUE, cost.getScheduledtime());
        assertEquals(FOR_WHAT_VALUE, cost.getForWhat());
        assertEquals(AMOUNT_VALUE, cost.getAmount());
    }
}
package com.radello.constructioncompanyorganizer.converter;

import com.radello.constructioncompanyorganizer.commands.BudgetCommand;
import com.radello.constructioncompanyorganizer.commands.IncomeCommand;
import com.radello.constructioncompanyorganizer.domain.Income;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class IncomeCommandToIncomeTest {

    private final Long ID_VALUE = 1L;
    private final int AMOUNT_VALUE = 123;
    private final String FOR_WHAT_VALUE = "Flat";
    private final LocalDate LOCALE_DATE_VALUE = LocalDate.of(2020, 11, 11);

    IncomeCommandToIncome incomeCommandToIncome;

    @BeforeEach
    void setUp() {
        incomeCommandToIncome = new IncomeCommandToIncome();
    }

    @Test
    public void testNullParameter() {
        assertNull(incomeCommandToIncome.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(incomeCommandToIncome.convert(new IncomeCommand()));
    }


    @Test
    void convert() {

        IncomeCommand incomeCommand = new IncomeCommand();
        incomeCommand.setID(ID_VALUE);
        incomeCommand.setForWhat(FOR_WHAT_VALUE);
        incomeCommand.setScheduledTimeToGet(LOCALE_DATE_VALUE);
        incomeCommand.setAmount(AMOUNT_VALUE);

        Income income = incomeCommandToIncome.convert(incomeCommand);

        assertNotNull(income);
        assertEquals(ID_VALUE, income.getID());
        assertEquals(FOR_WHAT_VALUE, income.getForWhat());
        assertEquals(LOCALE_DATE_VALUE, income.getScheduledTimeToGet());
        assertEquals(AMOUNT_VALUE, income.getAmount());
    }
}
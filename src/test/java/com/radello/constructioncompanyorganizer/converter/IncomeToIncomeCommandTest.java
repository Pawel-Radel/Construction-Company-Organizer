package com.radello.constructioncompanyorganizer.converter;

import com.radello.constructioncompanyorganizer.commands.IncomeCommand;
import com.radello.constructioncompanyorganizer.domain.Income;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class IncomeToIncomeCommandTest {

    private final Long ID_VALUE = 1L;
    private final int AMOUNT_VALUE = 123;
    private final String FOR_WHAT_VALUE = "Flat";
    private final Date LOCALE_DATE_VALUE = new Date (2020 - 11 -  11);

    IncomeToIncomeCommand incomeToIncomeCommand;

    @BeforeEach
    void setUp() {
        incomeToIncomeCommand = new IncomeToIncomeCommand();
    }

    @Test
    public void testNullParameter() {
        assertNull(incomeToIncomeCommand.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(incomeToIncomeCommand.convert(new Income()));
    }


    @Test
    void convert() {

        Income income = new Income();
        income.setID(ID_VALUE);
        income.setForWhat(FOR_WHAT_VALUE);
        income.setScheduledTimeToGet(LOCALE_DATE_VALUE);
        income.setAmount(AMOUNT_VALUE);

        IncomeCommand incomeCommand = incomeToIncomeCommand.convert(income);

        assertNotNull(incomeCommand);
        assertEquals(ID_VALUE, incomeCommand.getID());
        assertEquals(FOR_WHAT_VALUE, incomeCommand.getForWhat());
        assertEquals(LOCALE_DATE_VALUE, incomeCommand.getScheduledTimeToGet());
        assertEquals(AMOUNT_VALUE, incomeCommand.getAmount());
    }

}
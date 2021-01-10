package com.radello.constructioncompanyorganizer.converter;

import com.radello.constructioncompanyorganizer.commands.CostCommand;
import com.radello.constructioncompanyorganizer.domain.Cost;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

class CostToCostCommandTest {

    private final Long ID_VALUE = 1L;
    private final int AMOUNT_VALUE = 123;
    private final String FOR_WHAT_VALUE = "Flat";
    private final LocalDate LOCALE_DATE_VALUE = LocalDate.now();

    CostToCostCommand costToCostCommand;

    @BeforeEach
    void setUp() {
        costToCostCommand = new CostToCostCommand();
    }

    @Test
    public void testNullParameter(){
        assertNull(costToCostCommand.convert(null));
    }

    @Test
    public void testEmptyObject(){
        assertNotNull(costToCostCommand.convert(new Cost()));
    }

    @Test
    void convert() {
        Cost cost = new Cost();

        cost.setID(ID_VALUE);
        cost.setScheduledtime(LOCALE_DATE_VALUE);
        cost.setForWhat(FOR_WHAT_VALUE);
        cost.setAmount(AMOUNT_VALUE);

        CostCommand costCommand = costToCostCommand.convert(cost);

        assertNotNull(costCommand);
        assertEquals(ID_VALUE, costCommand.getID());
        assertEquals(LOCALE_DATE_VALUE, costCommand.getScheduledtime());
        assertEquals(FOR_WHAT_VALUE, costCommand.getForWhat());
        assertEquals(AMOUNT_VALUE, costCommand.getAmount());
    }

}
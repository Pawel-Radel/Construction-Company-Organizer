package com.radello.constructioncompanyorganizer.converter;

import com.radello.constructioncompanyorganizer.commands.IndicativeCostCommand;
import com.radello.constructioncompanyorganizer.domain.IndicativeCost;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IndicativeCostToIndicativeCostCommandTest {

    private final String FOR_WHAT_VALUE = "Flat";
    private final Long ID_VALUE = 1L;
    private final int AMOUNT_VALUE = 123;


    IndicativeCostToIndicativeCostCommand indicativeCostToIndicativeCostCommand;


    @BeforeEach
    void setUp() {
        indicativeCostToIndicativeCostCommand = new IndicativeCostToIndicativeCostCommand();
    }

    @Test
    public void testNullParameter() {
        assertNull(indicativeCostToIndicativeCostCommand.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(indicativeCostToIndicativeCostCommand.convert(new IndicativeCost()));
    }

    @Test
    void convert() {

        IndicativeCost indicativeCost = new IndicativeCost();

        indicativeCost.setForWhat(FOR_WHAT_VALUE);
        indicativeCost.setID(ID_VALUE);
        indicativeCost.setAmount(AMOUNT_VALUE);

        IndicativeCostCommand indicativeCostCommand = indicativeCostToIndicativeCostCommand.convert(indicativeCost);

        assertNotNull(indicativeCostCommand);
        assertEquals(ID_VALUE,indicativeCostCommand.getID());
        assertEquals(FOR_WHAT_VALUE, indicativeCostCommand.getForWhat());
        assertEquals(AMOUNT_VALUE, indicativeCostCommand.getAmount());

    }
}
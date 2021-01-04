package com.radello.constructioncompanyorganizer.converter;

import com.radello.constructioncompanyorganizer.commands.CostCommand;
import com.radello.constructioncompanyorganizer.commands.IndicativeCostCommand;
import com.radello.constructioncompanyorganizer.domain.IndicativeCost;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IndicativeCostCommandToIndicativeCostTest {

    private final String FOR_WHAT_VALUE = "Flat";
    private final Long ID_VALUE = 1L;

    IndicativeCostCommandToIndicativeCost indicativeCostCommandToIndicativeCost;


    @BeforeEach
    void setUp() {
        indicativeCostCommandToIndicativeCost = new IndicativeCostCommandToIndicativeCost();
    }


    @Test
    public void testNullParameter() {
        assertNull(indicativeCostCommandToIndicativeCost.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(indicativeCostCommandToIndicativeCost.convert(new IndicativeCostCommand()));
    }

    @Test
    void convert() {

        IndicativeCostCommand indicativeCostCommand = new IndicativeCostCommand();

        indicativeCostCommand.setForWhat(FOR_WHAT_VALUE);
        indicativeCostCommand.setID(ID_VALUE);

        IndicativeCost indicativeCost = indicativeCostCommandToIndicativeCost.convert(indicativeCostCommand);

        assertNotNull(indicativeCost);
        assertEquals(ID_VALUE,indicativeCost.getID());
        assertEquals(FOR_WHAT_VALUE, indicativeCost.getForWhat());

    }
}
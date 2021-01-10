package com.radello.constructioncompanyorganizer.converter;

import com.radello.constructioncompanyorganizer.commands.ConstructionOrderCommand;
import com.radello.constructioncompanyorganizer.commands.IncomeCommand;
import com.radello.constructioncompanyorganizer.commands.IndicativeCostCommand;
import com.radello.constructioncompanyorganizer.domain.ConstructionOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ConstructionOrderCommandtoConstructionOrderTest {

    private final Long ID_VALUE_1 = 1L;
    private final Long ID_VALUE_2 = 2L;
    private final String FOR_WHAT_VALUE_1 = "flat";
    private final String FOR_WHAT_VALUE_3 = "flat2";
    private final String FOR_WHAT_VALUE_2 = "flat1";
    private final LocalDate LOCAL_DATE_VALUE = LocalDate.now();
    private final LocalDate LOCAL_DATE_VALUE2 = LocalDate.now().plusDays(2L);
    private final LocalDate LOCAL_DATE_VALUE3 = LocalDate.now().minusDays(2L);
    private final int AMOUNT_VALUE = 123;
    private final String TITLE_VALUE = "Title";
    private final String ADDRES_VALUE = "Addres Value";

    ConstructionOrderCommandtoConstructionOrder constructionOrderCommandtoConstructionOrder;

    @BeforeEach
    void setUp() {
        constructionOrderCommandtoConstructionOrder = new ConstructionOrderCommandtoConstructionOrder
                (new IncomeCommandToIncome(), new IndicativeCostCommandToIndicativeCost());
    }

    @Test
    void testNullObject() {
        assertNull(constructionOrderCommandtoConstructionOrder.convert(null));
    }

    @Test
    void testEmptyObject() {
        assertNotNull(constructionOrderCommandtoConstructionOrder.convert(new ConstructionOrderCommand()));
    }

    @Test
    void convert() {
        ConstructionOrderCommand constructionOrderCommand = new ConstructionOrderCommand();

        constructionOrderCommand.setID(ID_VALUE_1);
        constructionOrderCommand.setTitle(TITLE_VALUE);
        constructionOrderCommand.setStartDate(LOCAL_DATE_VALUE3);
        constructionOrderCommand.setScheduledEndDate(LOCAL_DATE_VALUE2);
        constructionOrderCommand.setAddres(ADDRES_VALUE);

        IncomeCommand incomeCommand = new IncomeCommand();
        incomeCommand.setID(ID_VALUE_1);
        incomeCommand.setScheduledTimeToGet(LOCAL_DATE_VALUE);
        incomeCommand.setForWhat(FOR_WHAT_VALUE_3);
        incomeCommand.setAmount(AMOUNT_VALUE);

        constructionOrderCommand.setIncomeCommand(incomeCommand);

        IndicativeCostCommand indicativeCost1 = new IndicativeCostCommand();
        indicativeCost1.setID(ID_VALUE_1);
        indicativeCost1.setForWhat(FOR_WHAT_VALUE_1);

        IndicativeCostCommand indicativeCost2 = new IndicativeCostCommand();
        indicativeCost2.setID(ID_VALUE_2);
        indicativeCost2.setForWhat(FOR_WHAT_VALUE_2);

        constructionOrderCommand.getIndicateCosts().add(indicativeCost1);
        constructionOrderCommand.getIndicateCosts().add(indicativeCost2);

        ConstructionOrder constructionOrder = constructionOrderCommandtoConstructionOrder
                .convert(constructionOrderCommand);

        assertNotNull(constructionOrder);
        assertEquals(ID_VALUE_1, constructionOrder.getID());
        assertEquals(TITLE_VALUE, constructionOrder.getTitle());
        assertEquals(LOCAL_DATE_VALUE3, constructionOrder.getStartDate());
        assertEquals(LOCAL_DATE_VALUE2, constructionOrder.getScheduledEndDate());
        assertEquals(ADDRES_VALUE, constructionOrder.getAddres());
        assertEquals(ID_VALUE_1, constructionOrder.getIncome().getID());
        assertEquals(FOR_WHAT_VALUE_3, constructionOrder.getIncome().getForWhat());
        assertEquals(LOCAL_DATE_VALUE, constructionOrder.getIncome().getScheduledTimeToGet());
        assertEquals(AMOUNT_VALUE, constructionOrder.getIncome().getAmount());
        assertEquals(2, constructionOrder.getIndicateCosts().size());
    }
}
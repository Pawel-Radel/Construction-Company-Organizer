package com.radello.constructioncompanyorganizer.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class IncomeTest {

    Income income;

    @BeforeEach
    void setUp() {
        income = new Income();
    }

    @Test
    void getForWhat() {

        String forWhatTest = "For Building a flat";
        income.setForWhat(forWhatTest);

        assertEquals(forWhatTest, income.getForWhat());
    }

    @Test
    void getScheduledTimeToGet() {

        LocalDate scheduledTimeToGetTest = LocalDate.of(2020,11,11);

        income.setScheduledTimeToGet(scheduledTimeToGetTest);
        assertEquals(scheduledTimeToGetTest, income.getScheduledTimeToGet());
    }

    /*@Test
    void getConstructionOrder() {

        int idTest = 1;
        ConstructionOrder constructionOrder = new ConstructionOrder();
        constructionOrder.setID(idTest);
        constructionOrder.setIncome(income);
        income.setConstructionOrder(constructionOrder);
        assertEquals(idTest, income.getConstructionOrder().getID());


    } */
}
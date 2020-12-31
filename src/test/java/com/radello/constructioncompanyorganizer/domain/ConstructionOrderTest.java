package com.radello.constructioncompanyorganizer.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ConstructionOrderTest {


    ConstructionOrder consOrd;

    @BeforeEach
    void setUp() {
        consOrd = new ConstructionOrder();
    }

    @Test
    void getID() {
        int idTest = 1;

        consOrd.setID(idTest);

        assertEquals(idTest, consOrd.getID());
    }

    @Test
    void getTitle() {

        String titleTest = "Title Example";

        consOrd.setTitle(titleTest);

        assertEquals(titleTest, consOrd.getTitle());
    }

    @Test
    void getAddres() {

        String addresTest = "Example Addres";

        consOrd.setAddres(addresTest);

        assertEquals(addresTest, consOrd.getAddres());

    }

    @Test
    void getStartDate() {

        LocalDate dateTest = LocalDate.of(2020, 11, 11);
        consOrd.setStartDate(dateTest);

        assertEquals(dateTest, consOrd.getStartDate());


    }

    @Test
    void getScheduledEndDate() {
        LocalDate dateTest = LocalDate.of(2020,11,11);
        consOrd.setScheduledEndDate(dateTest);
        assertEquals(dateTest, consOrd.getScheduledEndDate());

    }

    @Test
    void getIncome() {
        int testint = 1;
        Income income = new Income();
        income.setID(1);

        consOrd.setIncome(income);
        assertEquals(testint, consOrd.getIncome().getID());
    }

    @Test
    void getIndicateCosts() {


        IndicativeCost indc1 = new IndicativeCost();
        indc1.setID(1);
        IndicativeCost indc2 = new IndicativeCost();
        indc2.setID(2);
        Set<IndicativeCost> costsTest = new HashSet<>();
        costsTest.add(indc1);
        costsTest.add(indc2);
        consOrd.setIndicateCosts(costsTest);
        assertEquals(2, consOrd.getIndicateCosts().size());
    }
}
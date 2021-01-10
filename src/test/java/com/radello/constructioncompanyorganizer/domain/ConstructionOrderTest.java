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
        Long idTest = 1L;

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

        LocalDate dateTest = LocalDate.now();
        consOrd.setStartDate(dateTest);

        assertEquals(dateTest, consOrd.getStartDate());


    }

    @Test
    void getScheduledEndDate() {
        LocalDate dateTest = LocalDate.now();
        consOrd.setScheduledEndDate(dateTest);
        assertEquals(dateTest, consOrd.getScheduledEndDate());

    }

    @Test
    void getIncome() {
        int testint = 1;
        Income income = new Income();
        income.setID(1L);

        consOrd.setIncome(income);
        assertEquals(testint, consOrd.getIncome().getID());
    }

    @Test
    void getIndicateCosts() {


        IndicativeCost indc1 = new IndicativeCost();
        indc1.setID(1L);
        IndicativeCost indc2 = new IndicativeCost();
        indc2.setID(2L);
        Set<IndicativeCost> costsTest = new HashSet<>();
        costsTest.add(indc1);
        costsTest.add(indc2);
        consOrd.setIndicateCosts(costsTest);
        assertEquals(2, consOrd.getIndicateCosts().size());
    }
}
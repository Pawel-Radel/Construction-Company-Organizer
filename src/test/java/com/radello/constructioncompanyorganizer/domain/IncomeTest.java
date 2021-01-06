package com.radello.constructioncompanyorganizer.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Date;

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

        Date scheduledTimeToGetTest = new Date (2011-11-11);

        income.setScheduledTimeToGet(scheduledTimeToGetTest);
        assertEquals(scheduledTimeToGetTest, income.getScheduledTimeToGet());
    }

}
package com.radello.constructioncompanyorganizer.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

        LocalDate scheduledTimeToGetTest = LocalDate.now();

        income.setScheduledTimeToGet(scheduledTimeToGetTest);
        assertEquals(scheduledTimeToGetTest, income.getScheduledTimeToGet());
    }

}
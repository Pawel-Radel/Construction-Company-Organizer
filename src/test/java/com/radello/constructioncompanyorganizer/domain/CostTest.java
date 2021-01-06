package com.radello.constructioncompanyorganizer.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CostTest {

    Cost cost;
    @BeforeEach
    void setUp() {
        cost = new Cost();
    }

    @Test
    void getForWhat() {

        String getforWhatTest = "For building a house";

        cost.setForWhat(getforWhatTest);

        assertEquals(getforWhatTest, cost.getForWhat());
    }

    @Test
    void getScheduledtime() {

        Date scheduledTimeTest = new Date (2020-11-11);

        cost.setScheduledtime(scheduledTimeTest);

        assertEquals(scheduledTimeTest, cost.getScheduledtime());
    }
}
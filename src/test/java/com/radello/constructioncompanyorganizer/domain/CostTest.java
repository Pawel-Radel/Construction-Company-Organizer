package com.radello.constructioncompanyorganizer.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;

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

        Date ScheduledTimeTest = new Date(2020-11-11);

        cost.setScheduledtime(ScheduledTimeTest);

        assertEquals(ScheduledTimeTest, cost.getScheduledtime());
    }
}
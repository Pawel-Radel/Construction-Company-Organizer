package com.radello.constructioncompanyorganizer.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IndicativeCostTest {

    IndicativeCost indicativeCost;
    @BeforeEach
    void setUp() {

        indicativeCost = new IndicativeCost();
    }

    @Test
    void getID() {

        int testId = 1;

        indicativeCost.setID(testId);

        assertEquals(testId, indicativeCost.getID());
    }

    @Test
    void getForWhat() {

        String forWhatTest = "For Building a Flat";
        indicativeCost.setForWhat(forWhatTest);

        assertEquals(forWhatTest, indicativeCost.getForWhat());
    }

}
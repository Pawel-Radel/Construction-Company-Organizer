package com.radello.constructioncompanyorganizer.services;

import com.radello.constructioncompanyorganizer.domain.Income;

import java.util.Set;

public interface IncomesDependsOnTime {


    Set<Income> getIncomes();

    Set<Income> getOutstandingIncomes();
}

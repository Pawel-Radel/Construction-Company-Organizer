package com.radello.constructioncompanyorganizer.domain;

import java.time.LocalDate;


// This class is supposed to help me format Date to Properly String,
public class DateFormatter {

    public static String formatDateToProperlyString(LocalDate date){

        String day = String.format("%02d", date.getDayOfMonth());
        String month = String.format("%02d", date.getMonthValue());
        int year = date.getYear();

        return new StringBuilder().append(month).append("/").append(day).append("/").append(year).toString();

    }
}

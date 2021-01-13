package com.radello.constructioncompanyorganizer.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@NoArgsConstructor
public class IncomeCommand {

    private Long ID;
    private int amount;

    private String forWhat;

    @DateTimeFormat(pattern = "mm/dd/yyyy")
    private LocalDate scheduledTimeToGet;

    ConstructionOrderCommand constructionOrderCommand;

    public void setDatesByString(String string1) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

        LocalDate date = LocalDate.parse(string1, formatter);
        this.setScheduledTimeToGet(date);
    }


}

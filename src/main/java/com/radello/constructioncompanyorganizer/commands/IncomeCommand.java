package com.radello.constructioncompanyorganizer.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Getter
@Setter
@NoArgsConstructor
public class IncomeCommand {

    private Long ID;

    @Min(value = 1)
    @Max(value = 2147483647)
    private int amount;

    @NotBlank
    private String forWhat;

    @DateTimeFormat(pattern = "mm/dd/yyyy")
    private LocalDate scheduledTimeToGet;

    ConstructionOrderCommand constructionOrderCommand;

    public void setDatesByString(String string1) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

        LocalDate date = LocalDate.parse(string1, formatter);
        this.setScheduledTimeToGet(date);
    }


}

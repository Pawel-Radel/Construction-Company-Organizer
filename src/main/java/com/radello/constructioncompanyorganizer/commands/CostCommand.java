package com.radello.constructioncompanyorganizer.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@NoArgsConstructor
public class CostCommand {

    private Long ID;


    @Min(value = 1)
    @Max(value = 2147483647)
    private int amount;

    @NotBlank
    private String forWhat;

    private LocalDate scheduledtime;

    public void setDatesByString(String string1) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

        LocalDate date = LocalDate.parse(string1, formatter);
        this.setScheduledtime(date);

    }
}

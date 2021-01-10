package com.radello.constructioncompanyorganizer.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class CostCommand {

    private Long ID;
    private int amount;
    private String forWhat;

    @DateTimeFormat(pattern = "yyyy/mm/dddd")
    private LocalDate scheduledtime;


}

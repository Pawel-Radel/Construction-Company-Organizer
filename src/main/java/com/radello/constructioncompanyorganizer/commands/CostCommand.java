package com.radello.constructioncompanyorganizer.commands;

import com.radello.constructioncompanyorganizer.domain.MoneyTransfer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class CostCommand {

    private int ID;
    private int amount;
    private String forWhat;
    private LocalDate scheduledtime;


}

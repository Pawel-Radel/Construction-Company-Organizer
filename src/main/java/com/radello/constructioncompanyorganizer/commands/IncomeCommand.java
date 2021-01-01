package com.radello.constructioncompanyorganizer.commands;

import com.radello.constructioncompanyorganizer.domain.ConstructionOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class IncomeCommand {

    private int ID;
    private int amount;
    private String forWhat;
    private LocalDate scheduledTimeToGet;
    private ConstructionOrderCommand constructionOrderCommand;

}

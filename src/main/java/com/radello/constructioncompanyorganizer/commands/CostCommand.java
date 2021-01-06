package com.radello.constructioncompanyorganizer.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class CostCommand {

    private Long ID;
    private int amount;
    private String forWhat;


    private Date scheduledtime;


}

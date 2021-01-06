package com.radello.constructioncompanyorganizer.commands;

import com.radello.constructioncompanyorganizer.domain.MoneyTransfer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.TemporalType;
import java.time.LocalDate;
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

package com.radello.constructioncompanyorganizer.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

// class representing cost to accounting
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cost extends MoneyTransfer{


    private String forWhat;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date scheduledtime;


}

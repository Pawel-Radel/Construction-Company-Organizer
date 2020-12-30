package com.radello.constructioncompanyorganizer.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

// class representing cost to accounting
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Cost extends MoneyTransfer{


    private String forWhat;
    private Date scheduledtime;


}

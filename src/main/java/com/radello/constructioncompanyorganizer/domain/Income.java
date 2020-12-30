package com.radello.constructioncompanyorganizer.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "Income")
public class Income extends MoneyTransfer
{


    private String forWhat;
    private Date scheduledTimeToGet;
    @OneToOne
    private ConstructionOrder constructionOrder;

}

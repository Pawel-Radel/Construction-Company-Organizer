package com.radello.constructioncompanyorganizer.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Income extends MoneyTransfer {


    private String forWhat;
    private LocalDate scheduledTimeToGet;

    @OneToOne
    private ConstructionOrder constructionOrder;

}

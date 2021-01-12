package com.radello.constructioncompanyorganizer.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Income extends MoneyTransfer {


    private String forWhat;

    @DateTimeFormat(pattern = "yyyy/mm/dd")
    private LocalDate scheduledTimeToGet;

    @ManyToOne
    private ConstructionOrder constructionOrder;

}

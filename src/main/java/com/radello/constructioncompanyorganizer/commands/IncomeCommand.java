package com.radello.constructioncompanyorganizer.commands;

import com.radello.constructioncompanyorganizer.domain.ConstructionOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;


import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class IncomeCommand {

    private Long ID;
    @NotBlank
    private int amount;

    @NotBlank
    private String forWhat;
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate scheduledTimeToGet;


}

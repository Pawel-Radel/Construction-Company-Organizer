package com.radello.constructioncompanyorganizer.commands;

import com.radello.constructioncompanyorganizer.domain.Income;
import com.radello.constructioncompanyorganizer.domain.IndicativeCost;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class ConstructionOrderCommand {


    private Long ID;

    @NotBlank(message = "Title is mandatory")
    @Size(min = 3, max = 255)
    private String title;

    @NotBlank(message = "Addres is mandatory")
    @Size(min = 3, max = 255)
    private String addres;

    @Min(value = 2021 - 1 - 1)
    @Max(value = 2100 - 1 - 1)
    private Date startDate;

    @Min(value = 2021 - 1 - 1)
    @Max(value = 2100 - 1 - 1)
    private Date scheduledEndDate;

    private IncomeCommand incomeCommand;
    private Set<IndicativeCostCommand> indicateCosts = new HashSet<>();

}

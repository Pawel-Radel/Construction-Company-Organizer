package com.radello.constructioncompanyorganizer.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashSet;
import java.util.Set;


@NoArgsConstructor
@Getter
@Setter
public class ConstructionOrderCommand {

    private Long ID;

    //@Size(min = 3, max = 255, message = "Title is mandatory")
    @NotBlank(message = "pole nie może być puste")
    private String title;

    //@Size(min = 3, max = 255, message = "Addres is mandatory")
    @NotBlank(message = "pole nie może być puste")
    private String addres;


    private LocalDate startDate;


    private LocalDate scheduledEndDate;

    private Set<IncomeCommand> incomeCommands = new HashSet<>();
    private Set<IndicativeCostCommand> indicativeCostCommands = new HashSet<>();

    public void setDatesByStrings(String string1, String string2) throws DateTimeParseException {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

        this.setStartDate(LocalDate.parse(string1, formatter));
        this.setScheduledEndDate(LocalDate.parse(string2, formatter));
    }

    public ConstructionOrderCommand addIncomes(IncomeCommand incomeCommand) {
        incomeCommand.setConstructionOrderCommand(this);
        this.getIncomeCommands().add(incomeCommand);
        return this;
    }

    public void addIndicativeCost(IndicativeCostCommand indcCost) {
        indcCost.setConstructionOrderCommand(this);
        this.getIndicativeCostCommands().add(indcCost);
    }

    public int profitEstimation() {
        Integer sumOfCosts = this.getIndicativeCostCommands()
                .stream()
                .map(IndicativeCostCommand::getAmount)
                .reduce(0, Integer::sum);

        Integer sumOfIncomes = this.getIncomeCommands()
                .stream()
                .map(IncomeCommand::getAmount)
                .reduce(0, Integer::sum);

        return sumOfIncomes - sumOfCosts;
    }
}

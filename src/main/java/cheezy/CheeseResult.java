package cheezy;

import java.util.ArrayList;
import java.util.List;

public class CheeseResult {

    String calculationResult = "";
    List<Cheese> filteredCheese = new ArrayList<>();

    public CheeseResult(String calculationResult, List<Cheese> filteredCheese) {
        this.calculationResult = calculationResult;
        this.filteredCheese = filteredCheese;
    }

    public String getCalculationResult(){
        return calculationResult;
    }

    public List<Cheese> getFilteredCheese(){
        return filteredCheese;
    }

}

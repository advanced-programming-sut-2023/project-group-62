package org.group62.Controller;

import org.group62.Model.Food;
import org.group62.Model.Governance;
import org.group62.Model.Play;

public class GovernanceController {
    public String showPopularityFactors() {
        Governance g = Play.getCurrentGovernance();
        return String.format("popularity factors:\nFood: %d\nTax: %d\nFear: %d\nReligion: %d", g.getFoodPopularity(),
                g.getTaxPopularity(), g.getFear(), g.getReligionPopularity());
    }

    public String showPopularity() {
        return "popularity: " + Play.getCurrentGovernance().getPopularity();
    }

    public String showFoodList() {
        String output = "foods:\n";
        for (Food food : Play.getCurrentGovernance().getFoods().keySet()) {
            output = output + String.format("%s: %d", food, Play.getCurrentGovernance().getFoods().get(food));
        }
        return output;
    }

    public String setFoodRate(int foodRate) {
        if (foodRate > 2 || foodRate < -2)
            return "invalid food rate";
        int foodsNumber = 0;
        for (Food food : Play.getCurrentGovernance().getFoods().keySet())
            foodsNumber = foodsNumber + Play.getCurrentGovernance().getFoods().get(food);
        if (foodsNumber < (int) (Food.GetAmountOfFoodPerPerson(foodRate) * Play.getCurrentGovernance().getPeoples().size()))
            return "you don't have enough food for this food rate";
        Play.getCurrentGovernance().setFoodRate(foodRate);
        return "set food rate was successful";
    }

    public String showFoodRate() {
        return "food rate: " + Play.getCurrentGovernance().getFoodRate();
    }

    public String setTaxRate(int taxRate) {
        if (taxRate > 8 || taxRate < -3)
            return "invalid tax rate";
        if (Play.getCurrentGovernance().getGold() + (int) (Play.getCurrentGovernance().getPeoples().size() * Play.getCurrentGovernance().getTaxOfPerPerson(taxRate)) < 0)
            return "you don't have enough gold for this tax rate";
        Play.getCurrentGovernance().setTax(taxRate);
        return "set tax rate was successful";
    }

    public String showTaxRate() {
        return "tax rate: " + Play.getCurrentGovernance().getTax();
    }

    public String setFearRate(int fearRate) {
        if (fearRate > 5 || fearRate < -5)
            return "invalid fear rate";
        Play.getCurrentGovernance().setFear(fearRate);
        return "set fear rate was successful";
    }
}

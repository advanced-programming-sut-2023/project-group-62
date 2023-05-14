package org.group62.controller;

import org.group62.model.*;

import java.util.ArrayList;

public class TurnsController {
    public void nextRound() {
        collectTax();
        decreasedFoodIntake();
        setPopularity();
        checkForEndKing();
        for (Ground[] grounds : StrongHold.getCurrentPlay().getMap()) {
            for (Ground ground : grounds) {
                if (!ground.getBuildings().isEmpty()) {
                    collectFoodProcessing(ground.getBuildings().get(0));
                    collectFoodFarm(ground.getBuildings().get(0));
                    collectResource(ground.getBuildings().get(0));
                    collectWeapon(ground.getBuildings().get(0));
                }
            }
        }
        Governance winner;
        if ((winner = checkForEndGame()) != null)
            endGame(winner.getOwner());
    }

    private void endGame(User user) {
    }

    private void collectResource(Building building) {
        Resource resource;
        switch (building.getName()) {
            case "Iron mine":
                resource = Resource.IRON;
                break;
            case "Pitch rig":
                resource = Resource.PITCH;
                break;
            case "Quarry":
                resource = Resource.STONE;
                break;
            case "Woodcutter":
                resource = Resource.WOOD;
                break;
            default:
                return;
        }
        if (building.getOwner().getCapacityOfResource() >= building.getOwner().getAmountOfResource() + building.getConstantsInteger(Constant.RATE))
            building.getOwner().addResource(resource, building.getConstantsInteger(Constant.RATE));
    }

    private void collectFoodFarm(Building building) {
        Food food = null;
        Resource resource = null;
        switch (building.getName()) {
            case "Apple garden":
                food = Food.APPLE;
                break;
            case "Dairy products":
                food = Food.CHEESE;
                if (building.getOwner().getCapacityOfWeapons() >= building.getOwner().getAmountOfWeapons() + 1)
                    building.getOwner().addWeapons(Weapons.LEATHER_ARMOR, 1);
                break;
            case "Barley farm":
                resource = Resource.BARLEY;
                break;
            case "Hunting post":
                food = Food.MEAT;
                break;
            case "Wheat farm":
                resource = Resource.WHEAT;
                break;
            default:
                return;
        }
        if (food != null)
            if (building.getOwner().getCapacityOfFood() >= building.getOwner().getAmountOfFood() + building.getConstantsInteger(Constant.RATE))
                building.getOwner().addFood(food, building.getConstantsInteger(Constant.RATE));
        if (resource != null)
            if (building.getOwner().getCapacityOfResource() >= building.getOwner().getAmountOfResource() + building.getConstantsInteger(Constant.RATE))
                building.getOwner().addResource(resource, building.getConstantsInteger(Constant.RATE));
    }

    private void collectFoodProcessing(Building building) {
        Resource resource;
        Resource resourceCost;
        switch (building.getName()) {
            case "Inn":
                if (building.getOwner().getResources().containsKey(Resource.BEER)) {
                    building.getOwner().setPopularity(building.getOwner().getPopularity() +
                            building.getConstantsInteger(Constant.POPULARITY));
                    building.getOwner().decreaseResource(Resource.BEER, 1);
                }
                return;
            case "Mill":
                resourceCost = Resource.WHEAT;
                resource = Resource.FLOUR;
                break;
            case "Bakery":
                if (building.getOwner().getResources().containsKey(Resource.FLOUR)) {
                    if (building.getOwner().getCapacityOfFood() >= building.getOwner().getAmountOfFood() + building.getConstantsInteger(Constant.RATE)) {
                        building.getOwner().decreaseResource(Resource.BEER, building.getConstantsInteger(Constant.WINE_USAGE));
                        building.getOwner().addFood(Food.BREAD, building.getConstantsInteger(Constant.RATE));
                    }
                }
                return;
            case "Brewery":
                resourceCost = Resource.BARLEY;
                resource = Resource.BEER;
                break;
            default:
                return;
        }
        if (building.getOwner().getResources().containsKey(resourceCost)) {
            if (building.getOwner().getCapacityOfResource() >= building.getOwner().getAmountOfWeapons() + building.getConstantsInteger(Constant.RATE)) {
                building.getOwner().decreaseResource(resourceCost, 1);
                building.getOwner().addResource(resource, building.getConstantsInteger(Constant.RATE));
            }
        }
    }

    private void collectWeapon(Building building) {
        Resource resourceCost;
        Weapons weapon1;
        Weapons weapon2;
        switch (building.getName()) {
            case "Armourer":
                resourceCost = Resource.IRON;
                if (building.getOwner().getResources().get(resourceCost) > building.getConstantsInteger(Constant.CONSUMING_MATERIALS)) {
                    if (building.getOwner().getCapacityOfWeapons() >= building.getOwner().getAmountOfWeapons() + building.getConstantsInteger(Constant.PRODUCTION_RATE)) {
                        building.getOwner().decreaseResource(resourceCost, building.getConstantsInteger(Constant.CONSUMING_MATERIALS));
                        building.getOwner().addWeapons(Weapons.METAL_ARMOR, 1);
                    }
                }
                return;
            case "Blacksmith":
                resourceCost = Resource.IRON;
                weapon1 = Weapons.SWORD;
                weapon2 = Weapons.MACE;
                break;
            case "Fletcher":
                resourceCost = Resource.WOOD;
                weapon1 = Weapons.BOW;
                weapon2 = Weapons.CROSSBOW;
                break;
            case "Poleturner":
                resourceCost = Resource.WOOD;
                weapon1 = Weapons.PIK;
                weapon2 = Weapons.SPEAR;
                break;
            default:
                return;
        }
        if (building.getOwner().getResources().get(resourceCost) > building.getConstantsInteger(Constant.CONSUMING_MATERIALS)) {
            if (building.getOwner().getCapacityOfWeapons() >= building.getOwner().getAmountOfWeapons() + 2 * building.getConstantsInteger(Constant.PRODUCTION_RATE)) {
                building.getOwner().decreaseResource(resourceCost, building.getConstantsInteger(Constant.CONSUMING_MATERIALS));
                building.getOwner().addWeapons(weapon1, building.getConstantsInteger(Constant.PRODUCTION_RATE));
                building.getOwner().addWeapons(weapon2, building.getConstantsInteger(Constant.PRODUCTION_RATE));
            }
        }
    }

    private void collectTax() {
        checkTaxRate();
        for (Governance governance : StrongHold.getCurrentPlay().getGovernances()) {
            governance.setGold((governance.getGold() + (int) (governance.getTaxOfPerPerson(governance.getTax()) * governance.getPeoples().size())));
        }
    }

    private void checkTaxRate() {
        for (Governance governance : StrongHold.getCurrentPlay().getGovernances()) {
            if (governance.getGold() + (int) (governance.getPeoples().size() * governance.getTaxOfPerPerson(governance.getTax())) < 0)
                governance.setTax(0);
        }
    }

    private void decreasedFoodIntake() {
        checkFoodRate();
        for (Governance governance : StrongHold.getCurrentPlay().getGovernances()) {
            int i = 0;
            while (i < (int) (Food.GetAmountOfFoodPerPerson(governance.getFoodRate()) * governance.getPeoples().size())) {
                for (Food food : governance.getFoods().keySet()) {
                    governance.decreaseFood(food, 1);
                    i++;
                    if (i < (int) (Food.GetAmountOfFoodPerPerson(governance.getFoodRate()) * governance.getPeoples().size()))
                        break;
                }

            }

        }
    }

    private void checkFoodRate() {
        for (Governance governance : StrongHold.getCurrentPlay().getGovernances()) {
            int foodsNumber = 0;
            for (Food food : governance.getFoods().keySet())
                foodsNumber = foodsNumber + governance.getFoods().get(food);
            while (foodsNumber < (int) (Food.GetAmountOfFoodPerPerson(governance.getFoodRate()) * governance.getPeoples().size()))
                governance.setFoodRate(governance.getFoodRate() - 1);
        }
    }

    private void setPopularity() {
        for (Governance governance : StrongHold.getCurrentPlay().getGovernances()) {
            int popularity = governance.getPopularity() + governance.getFoodPopularity() +
                    governance.getTaxPopularity() + governance.getFear() + governance.getReligionPopularity();
            if (popularity > 100)
                popularity = 100;
            governance.setPopularity(popularity);
        }
    }

    public String nextTurn() {
        Governance currentGovernance = Play.getCurrentGovernance();
        ArrayList<Governance> allGovernance = StrongHold.getCurrentPlay().getGovernances();
        for(int i = 0;i < allGovernance.size();i++){
            if(allGovernance.get(i).equals(currentGovernance)) {
                if(i == allGovernance.size() - 1)
                    Play.setCurrentGovernance(allGovernance.get(0));
                else
                    Play.setCurrentGovernance(allGovernance.get(i++));
            }
        }
        return "Now is next governance turn!";
    }

    private void checkForEndKing() {
        for (Ground[] grounds : StrongHold.getCurrentPlay().getMap()) {
            for (Ground ground : grounds) {
                if (!ground.getPeople().isEmpty()) {
                    for (People person : ground.getPeople()) {
                        if (person instanceof King)
                            if (((King) person).getHP() < 1)
                                destroyGovernance(person.getOwner());
                    }
                }
            }
        }
    }

    private void destroyGovernance(Governance owner) {
        for (Ground[] grounds : StrongHold.getCurrentPlay().getMap()) {
            for (Ground ground : grounds) {
                for (int i = ground.getBuildings().size(); i >= 0; i--)
                    if (ground.getBuildings().get(i).getOwner().equals(owner))
                        ground.getBuildings().remove(i);
                for (int i = ground.getPeople().size(); i >= 0; i--)
                    if (ground.getPeople().get(i).getOwner().equals(owner))
                        ground.getPeople().remove(i);
            }
        }
    }
    private Governance checkForEndGame(){
        int i = 0;
        Governance winner = null;
        for (Ground[] grounds : StrongHold.getCurrentPlay().getMap()) {
            for (Ground ground : grounds) {
                for (People person : ground.getPeople()) {
                    if (person instanceof King){
                        winner = person.getOwner();
                        i++;
                    }

                }
            }
        }
        if(i<2)
            return winner;
        else
            return null;
    }
}

package org.group62.model;

import java.util.ArrayList;
import java.util.HashMap;

public class Governance {
    private final User owner;
    private int popularity;
    private int foodRate = 0;
    private GovernanceColor color;
    private ArrayList<People> peoples = new ArrayList<>();
    private final HashMap<Food, Integer> foods = new HashMap<>();
    private final HashMap<Resource, Integer> resources = new HashMap<>();
    private final HashMap<Weapons, Integer> weapons = new HashMap<>();
    private ArrayList<Building> buildings = new ArrayList<>();
    private int tax = 0;
    private int fear = 0;
    private Building currentBuilding;
    private ArrayList<Troop> currentTroops;
    private final ArrayList<Trade> trades = new ArrayList<>();
    private int gold;

    public Governance(User owner) {
        this.owner = owner;
    }

    public int getReligionPopularity() {
        return StrongHold.getCurrentPlay().howManyOfThisBuildingExistByName("Church", Play.getCurrentGovernance()) *
                TownEnum.getTown(TownEnum.CHURCH).getConstantsInteger(Constant.POPULARITY) +
                StrongHold.getCurrentPlay().howManyOfThisBuildingExistByName("Cathedral", Play.getCurrentGovernance()) *
                        TownEnum.getTown(TownEnum.CATHEDRAL).getConstantsInteger(Constant.POPULARITY);
    }

    public int getFoodPopularity() {
        int foodPopularity = Play.getCurrentGovernance().getFoodRate() * 4;
        if (Play.getCurrentGovernance().getFoods().size() > 0)
            foodPopularity = foodPopularity + Play.getCurrentGovernance().getFoods().size() - 1;
        return foodPopularity;
    }

    public int getTaxPopularity() {
        switch (getTax()) {
            case -3:
                return 7;
            case -2:
                return 5;
            case -1:
                return 3;
            case 0:
                return -1;
            case 1:
                return -2;
            case 2:
                return -4;
            case 3:
                return -6;
            case 4:
                return -8;
            case 5:
                return -12;
            case 6:
                return -16;
            case 7:
                return -20;
            case 8:
                return -24;
            default:
                return 0;
        }
    }

    public double getTaxOfPerPerson(int taxRate) {
        switch (getTax()) {
            case -3:
                return -1;
            case -2:
                return -0.8;
            case -1:
                return -0.6;
            case 0:
                return 0;
            case 1:
                return 0.6;
            case 2:
                return 0.8;
            case 3:
                return 1;
            case 4:
                return 1.2;
            case 5:
                return 1.4;
            case 6:
                return 1.6;
            case 7:
                return 1.8;
            case 8:
                return 2;
            default:
                return -2;
        }
    }

    public GovernanceColor getColor() {
        return color;
    }

    public void setColor(GovernanceColor color) {
        this.color = color;
    }

    public void decreaseFood(Food food, int count) {
        foods.replace(food, foods.get(food) - count);
        if (foods.get(food)< 1)
            foods.remove(food);
    }

    public void decreaseResource(Resource resource, int count) {
        resources.replace(resource, resources.get(resource) - count);
        if (resources.get(resource)<1)
            resources.remove(resource);
    }

    public void decreaseWeapons(Weapons weapon, int count) {
        weapons.replace(weapon, weapons.get(weapon) - count);
        if (weapons.get(weapon)<1)
            weapons.remove(weapon);
    }

    public void removePeople(People people) {
        peoples.remove(people);
    }

    public void removeBuilding(Building building) {
        buildings.remove(building);
    }

    public void addFood(Food food, int count) {
        foods.replace(food, foods.get(food) + count);
    }

    public void addResource(Resource resource, int count) {
        resources.replace(resource, resources.get(resource) + count);
    }

    public void addWeapons(Weapons weapon, int count) {
        weapons.replace(weapon, weapons.get(weapon) + count);
    }

    public void addPeople(People people) {
        peoples.add(people);
    }

    public void addBuilding(Building building) {
        buildings.add(building);
    }

    public void addTrade(Trade trade) {
        trades.add(trade);
    }


    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public void setFoodRate(int foodRate) {
        this.foodRate = foodRate;
    }

    public void setTax(int tax) {
        this.tax = tax;
    }


    public void setFear(int fear) {
        this.fear = fear;
    }

    public void setCurrentBuilding(Building currentBuilding) {
        this.currentBuilding = currentBuilding;
    }

    public void setCurrentTroops(ArrayList<Troop> currentTroops) {
        this.currentTroops = currentTroops;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public User getOwner() {
        return owner;
    }

    public int getPopularity() {
        return popularity;
    }

    public int getFoodRate() {
        return foodRate;
    }

    public ArrayList<People> getPeoples() {
        return peoples;
    }

    public HashMap<Food, Integer> getFoods() {
        return foods;
    }

    public HashMap<Resource, Integer> getResources() {
        return resources;
    }

    public HashMap<Weapons, Integer> getWeapons() {
        return weapons;
    }

    public ArrayList<Building> getBuildings() {
        return buildings;
    }

    public int getTax() {
        return tax;
    }

    public int getFear() {
        return fear;
    }

    public Building getCurrentBuilding() {
        return currentBuilding;
    }

    public ArrayList<Troop> getCurrentTroops() {
        return currentTroops;
    }

    public ArrayList<Trade> getTrades() {
        return trades;
    }

    public int getGold() {
        return gold;
    }
}

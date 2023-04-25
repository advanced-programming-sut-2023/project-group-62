package org.group62.Model;

import java.util.ArrayList;

public class Governance {
    private final User owner;
    private int population = 0;
    private int popularity;
    private int foodRate = 0;
    private ArrayList<People> peoples = new ArrayList<>();
    private final ArrayList<Food> foods = new ArrayList<>();
    private ArrayList<Building> buildings = new ArrayList<>();
    private int tax = 0;
    private int believers = 0;
    private int fear = 0;
    private Building currentBuilding;
    private ArrayList<Troop> currentTroops;
    private final ArrayList<Trade> trades = new ArrayList<>();
    private int gold;

    public Governance(User owner) {
        this.owner = owner;
        // TODO: 4/22/2023 anbar add beshe
    }

    public void removeFood(Food food) {
        foods.remove(food);
    }

    public void removePeople(People people) {
        peoples.remove(people);
    }

    public void removeBuilding(Building building) {
        buildings.remove(building);
    }

    public void addFood(Food food) {
        foods.add(food);
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

    public void setPopulation(int population) {
        this.population = population;
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

    public void setBelievers(int believers) {
        this.believers = believers;
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

    public int getPopulation() {
        return population;
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

    public ArrayList<Food> getFoods() {
        return foods;
    }

    public ArrayList<Building> getBuildings() {
        return buildings;
    }

    public int getTax() {
        return tax;
    }

    public int getBelievers() {
        return believers;
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

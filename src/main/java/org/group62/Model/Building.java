package org.group62.Model;

import java.util.HashMap;

public class Building {
    private final Governance owner;
    private final String name;
    private final int goldCost;
    private final HashMap<Resource, Integer> resourcesCost;
    private int workersNumber;
    private HashMap<Constant, Integer> constants;


    public Building(Governance owner, String name, int goldCost, HashMap<Resource, Integer> resourcesCost, int workersNumber, HashMap<Constant, Integer> constants) {
        this.owner = owner;
        this.name = name;
        this.goldCost = goldCost;
        this.resourcesCost = resourcesCost;
        this.workersNumber = workersNumber;
        this.constants = constants;
    }

    public void setConstants(Constant constant, int keyInteger) {
        constants.replace(constant, keyInteger);
    }

    public int getConstantsInteger(String key) {
        return constants.get(key);
    }

    public Governance getOwner() {
        return owner;
    }

    public String getName() {
        return name;
    }

    public int getGoldCost() {
        return goldCost;
    }

    public HashMap<Resource, Integer> getResourcesCost() {
        return resourcesCost;
    }

    public int getWorkersNumber() {
        return workersNumber;
    }

    public HashMap<Constant, Integer> getConstants() {
        return constants;
    }
}

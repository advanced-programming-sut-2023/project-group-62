package org.group62.model;

import java.util.ArrayList;

public class Ground {
    private final int x;
    private final int y;
    private GroundType groundType = GroundType.LAND;
    private GroundTreeType groundTreeType = null;
    private ArrayList<Building> buildings = new ArrayList<>();
    private ArrayList<People> people = new ArrayList<>();

    public Ground(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void removePeople(People people) {
        this.people.remove(people);
    }

    public void removeBuilding(Building building) {
        buildings.remove(building);
    }

    public void removeTroops() {
        people.clear();
    }

    public void removeBuildings() {
        buildings.clear();
    }

    public void addBuilding(Building building) {
        buildings.add(building);
    }

    public void addPeople(People people) {
        this.people.add(people);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public ArrayList<Building> getBuildings() {
        return buildings;
    }

    public ArrayList<People> getPeople() {
        return people;
    }

    public GroundType getGroundType() {
        return groundType;
    }

    public void setGroundType(GroundType groundType) {
        this.groundType = groundType;
    }

    public GroundTreeType getGroundTreeType() {
        return groundTreeType;
    }

    public void setGroundTreeType(GroundTreeType groundTreeType) {
        this.groundTreeType = groundTreeType;
    }
}

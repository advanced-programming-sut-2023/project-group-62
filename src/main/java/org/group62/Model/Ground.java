package org.group62.Model;

import java.util.ArrayList;

public class Ground {
    private final int x;
    private final int y;
    private GroundType groundType = GroundType.LAND;
    private GroundTreeType groundTreeType = null;
    private ArrayList<Building> buildings = new ArrayList<>();
    private ArrayList<Troop> troops = new ArrayList<>();

    public Ground(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void removeTroop(Troop troop) {
        troops.remove(troop);
    }

    public void removeBuilding(Building building) {
        buildings.remove(building);
    }

    public void removeTroops() {
        troops.clear();
    }

    public void removeBuildings() {
        buildings.clear();
    }

    public void addBuilding(Building building) {
        buildings.add(building);
    }

    public void addTroop(Troop troop) {
        troops.add(troop);
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

    public ArrayList<Troop> getTroops() {
        return troops;
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

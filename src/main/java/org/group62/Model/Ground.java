package org.group62.model;

import java.util.ArrayList;

public class Ground {
    private GroundLandType groundLandType = GroundLandType.LAND;
    private GroundTreeType groundTreeType = null;
    private GroundWaterType groundWaterType = null;
    private ArrayList<Building> buildings = new ArrayList<>();
    private ArrayList<Troop> troops = new ArrayList<>();

    public void removeTroop(Troop troop) {
        troops.remove(troop);
    }

    public void removeBuilding(Building building) {
        buildings.remove(building);
    }

    public void addBuilding(Building building) {
        buildings.add(building);
    }

    public void addTroop(Troop troop) {
        troops.add(troop);
    }


    public ArrayList<Building> getBuildings() {
        return buildings;
    }

    public ArrayList<Troop> getTroops() {
        return troops;
    }

    public GroundLandType getGroundLandType() {
        return groundLandType;
    }

    public void setGroundLandType(GroundLandType groundLandType) {
        this.groundLandType = groundLandType;
    }

    public GroundTreeType getGroundTreeType() {
        return groundTreeType;
    }

    public void setGroundTreeType(GroundTreeType groundTreeType) {
        this.groundTreeType = groundTreeType;
    }

    public GroundWaterType getGroundWaterType() {
        return groundWaterType;
    }

    public void setGroundWaterType(GroundWaterType groundWaterType) {
        this.groundWaterType = groundWaterType;
    }
}

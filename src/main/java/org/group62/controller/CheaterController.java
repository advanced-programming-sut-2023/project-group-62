package org.group62.Controller;

import org.group62.Model.GroundTreeType;
import org.group62.Model.GroundType;
import org.group62.Model.Map;
import org.group62.Model.StrongHold;

public class CheaterController {
    public String setTexture(int x, int y, String groundType1) {
        GroundType groundType;
        if ((groundType = getGroundType(groundType1)) == null)
            return "invalid ground type";
        else if (x < 0 || x > 400 || y < 0 || y > 400)
            return "invalid x,y";
        else if (!StrongHold.getCurrentPlay().getMap()[x - 1][y - 1].getBuildings().isEmpty())
            return "there is building";
        else {
            Map.setGround(StrongHold.getCurrentPlay().getMap(), groundType, x - 1, y - 1);
            return "change texture was successful";
        }
    }

    public String setTexture(int x1, int y1, int x2, int y2, String groundType1) {
        GroundType groundType;
        boolean isThereBuilding = false;
        for (int i = x1 - 1; i < x2 - 1; i++)
            for (int j = y1 - 1; j < y2 - 1; y++)
                if (!StrongHold.getCurrentPlay().getMap()[i][j].getBuildings().isEmpty())
                    isThereBuilding = true;
        if ((groundType = getGroundType(groundType1)) == null)
            return "invalid ground type";
        else if (x1 < 0 || x1 > 400 || y1 < 0 || y1 > 400 || x2 < 0 || x2 > 400 || y2 < 0 || y2 > 400)
            return "invalid x,y";
        else if (isThereBuilding)
            return "there is building";
        else {
            Map.setGround(StrongHold.getCurrentPlay().getMap(), groundType, x1 - 1, y1 - 1, x2 - 1, y2 - 1);
            return "change texture was successful";
        }

    }

    public String clear(int x, int y) {
        StrongHold.getCurrentPlay().getMap()[x][y].setGroundType(GroundType.LAND);
        StrongHold.getCurrentPlay().getMap()[x][y].setGroundTreeType(null);
        StrongHold.getCurrentPlay().getMap()[x][y].removeBuildings();
        StrongHold.getCurrentPlay().getMap()[x][y].removeTroops();
        return "clear was successful";
    }

    public String dropRock(int x, int y, String direction) {
        switch (direction) {
            case "n":
            case "e":
            case "w":
            case "s":
            case "r":
            default:
                return "invalid direction";
        }
        return "drop rock was successful";
    }

    public String dropTree(int x, int y, String groundTreeType1) {
        GroundTreeType groundTreeType;
        if ((groundTreeType = getGroundTreeType(groundTreeType1)) == null)
            return "invalid tree type";
        else if (x < 0 || x > 400 || y < 0 || y > 400)
            return "invalid x,y";
        else if (!StrongHold.getCurrentPlay().getMap()[x - 1][y - 1].getBuildings().isEmpty())
            return "there is building";
        else {
            // TODO: 5/5/2023  
            return "drop tree was successful";
        }
        
    }

    public String dropBuilding(int x, int y, String buildingName) {
        return null;
    }

    public String dropUnit(int x, int y, String unitName, int count) {
        return null;
    }
    private GroundTreeType getGroundTreeType(String groundTreeType){
        switch (groundTreeType) {
            case "desert shrub":
                return GroundTreeType.DESERT_SHRUB;
                break;
            case "cherry":
                return GroundTreeType.CHERRY;
                break;
            case "olive":
                return GroundTreeType.OLIVE;
                break;
            case "coconut":
                return GroundTreeType.COCONUT;
                break;
            case "date":
                return GroundTreeType.DATE;
                break;
            default:
                return null;
        }
    }
    private GroundType getGroundType(String groundType) {
        GroundType output;
        switch (groundType) {
            case "land":
                output = GroundType.LAND;
                break;
            case "land with pebbles":
                output = GroundType.LAND_WITH_PEBBLES;
                break;
            case "stone":
                output = GroundType.STONE;
                break;
            case "rock":
                output = GroundType.ROCK;
                break;
            case "iron":
                output = GroundType.IRON;
                break;
            case "grass":
                output = GroundType.GRASS;
                break;
            case "meadow":
                output = GroundType.MEADOW;
                break;
            case "dense grassland":
                output = GroundType.DENSE_GRASSLAND;
                break;
            case "oil":
                output = GroundType.OIL;
                break;
            case "plain":
                output = GroundType.PLAIN;
                break;
            case "shallow water":
                output = GroundType.SHALLOW_WATER;
                break;
            case "river":
                output = GroundType.RIVER;
                break;
            case "small pond":
                output = GroundType.SMALL_POND;
                break;
            case "big pond":
                output = GroundType.BIG_POND;
                break;
            case "beach":
                output = GroundType.BEACH;
                break;
            case "sea":
                output = GroundType.SEA;
                break;
            default:
                return null;

        }
        return output;
    }
}

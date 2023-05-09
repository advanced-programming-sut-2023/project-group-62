package org.group62.Controller;

import org.group62.Model.*;

import java.util.HashMap;

public class MapMenuController {
    private int currentX;
    private int currentY;

    public String showMap(int x, int y) {
        x = x - 1;
        y = y - 1;
        String output = "";
        String part;
        if (x < 0 || x > 399 || y < 0 || y > 399)
            return "invalid x,y";
        for (Ground[] grounds : StrongHold.getCurrentPlay().getMap()) {
            for (Ground ground : grounds) {
                if (ground.getX() < x + 10 && ground.getX() > x - 10 && ground.getY() < y + 10 && ground.getY() > y - 10){
                    part = GroundType.getAbbreviationGroundType(ground.getGroundType());
                    if (ground.getGroundTreeType() != null)
                        part = "T";
                    if (!ground.getBuildings().isEmpty()){
                        if (ground.getBuildings().get(0) instanceof Castles)
                            part = "W";
                        else
                            part = "B";
                    }
                    if (!ground.getTroops().isEmpty()) {
                        for (Troop troop : ground.getTroops()) {
                            if (troop.isMoving()) {
                                part = "S";
                                break;
                            }
                        }
                    }
                    output = output + String.format("|%3s|",part);
                }
            }
            output = output + "\n";
        }

        return output;
    }

    public String moveMap(String upDown, String rightLeft, int count) {
        int x, y;
        switch (upDown) {
            case "up":
                if (getCurrentX() - count < 0)
                    return "map doesn't big enough";
                x = getCurrentX() - count + 1;
                break;
            case "down":
                if (getCurrentX() + count > 399)
                    return "map doesn't big enough";
                x = getCurrentX() + count + 1;
                break;
            default:
                return "write up or down";
        }
        switch (rightLeft) {
            case "right":
                if (getCurrentY() + count > 399)
                    return "map doesn't big enough";
                y = getCurrentY() + count + 1;
                break;
            case "left":
                if (getCurrentY() - count < 0)
                    return "map doesn't big enough";
                y = getCurrentY() - count + 1;
                break;
            default:
                return "write right or left";
        }
        return showMap(x, y);
    }

    public static String showMapDetails(int x, int y) {
        x = x - 1;
        y = y - 1;
        String output;
        HashMap<String, Integer> troopsHashMap = new HashMap<>();
        if (x < 0 || x > 399 || y < 0 || y > 399)
            return "invalid x,y";
        Ground ground = StrongHold.getCurrentPlay().getMap()[x][y];
        output = String.format("ground type: %s\n", ground.getGroundType());
        if (ground.getGroundTreeType() != null)
            output = output + String.format("ground tree type: %s\n", ground.getGroundTreeType());
        output = output + "Buildings:\n";
        if (!ground.getBuildings().isEmpty())
            for (Building building : ground.getBuildings())
                output = output + building.getName() + "\n";
        output = output + "Troops:\n";
        if (!ground.getTroops().isEmpty()) {
            for (Troop troop : ground.getTroops()) {
                if (troopsHashMap.containsKey(troop.getName()))
                    troopsHashMap.replace(troop.getName(), troopsHashMap.get(troop.getName()) + 1);
                else
                    troopsHashMap.put(troop.getName(), 1);
            }
            for (String key : troopsHashMap.keySet()) {
                output = output + key + ": " + troopsHashMap.get(key) + "\n";
            }
        }
        return output;
    }

    private int getCurrentX() {
        return currentX;
    }

    private void setCurrentX(int currentX) {
        this.currentX = currentX;
    }

    private int getCurrentY() {
        return currentY;
    }

    private void setCurrentY(int currentY) {
        this.currentY = currentY;
    }
}

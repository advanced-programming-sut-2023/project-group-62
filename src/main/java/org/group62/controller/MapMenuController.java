package org.group62.controller;

import org.group62.model.*;

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
                if (ground.getX() - 10 < x && ground.getX() + 10 > x && ground.getY() - 10 < y && ground.getY() + 10 > y) {
                    part = GroundType.getAbbreviationGroundType(ground.getGroundType());
                    if (ground.getGroundTreeType() != null)
                        part = "T";
                    if (!ground.getBuildings().isEmpty()) {
                        if (ground.getBuildings().get(0) instanceof Castles)
                            part = "W";
                        else
                            part = "B";
                    }
                    if (!ground.getPeople().isEmpty()) {
                        for (People people : ground.getPeople()) {
                            if (people instanceof Troop) {
                                if (((Troop) people).isMoving()) {
                                    part = "S";
                                    break;
                                }
                            }
                        }
                    }
                    output = output + String.format(" |%3s| ", part);

                }
            }
            output = output + "\n";
        }

        return " " + output.trim();
    }

    public String moveMap(String direction, int count) {
        int x = getCurrentX(), y = getCurrentY();
        switch (direction) {
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
                return "write right or left or down or up";
        }
        return showMap(x, y);
    }

    public String moveMap(String upDown, String rightLeft, int count) {
        int x = getCurrentX(), y = getCurrentY();
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
        if (!ground.getPeople().isEmpty()) {
            for (People people : ground.getPeople()) {
                if (people instanceof Troop) {
                    if (troopsHashMap.containsKey(((Troop) people).getName()))
                        troopsHashMap.replace(((Troop) people).getName(), troopsHashMap.get(((Troop) people).getName()) + 1);
                    else
                        troopsHashMap.put(((Troop) people).getName(), 1);
                }
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

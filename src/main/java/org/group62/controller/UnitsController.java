package org.group62.controller;

import org.group62.model.*;

import java.util.ArrayList;

public class UnitsController {
    private ArrayList<Troop> currentTroops;
    public String selectUnit(int x, int y){
        x = x - 1;
        y = y - 1;
        if (x < 0 || x > 399 || y < 0 || y > 399)
            return "invalid x,y";
        Ground ground = StrongHold.getCurrentPlay().getMap()[x][y];
        if (ground.getPeople().isEmpty())
            return "there isn't Troop";
        for (People person : ground.getPeople()) {
            if (person instanceof Troop && person.getOwner().equals(Play.getCurrentGovernance()))
                currentTroops.add((Troop) person);
        }
        return "select was successful";
    }

    public ArrayList<Troop> getCurrentTroops() {
        return currentTroops;
    }

    public void setCurrentTroops(ArrayList<Troop> currentTroops) {
        this.currentTroops = currentTroops;
    }
}

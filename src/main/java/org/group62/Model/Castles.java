package org.group62.Model;

import java.util.HashMap;

public class Castles extends Building {
    public Castles(Governance owner, String name, int goldCost, HashMap<Resource, Integer> resourcesCost, int workersNumber, HashMap<Constant, Integer> constants) {
        super(owner, name, goldCost, resourcesCost, workersNumber, constants);
    }
}

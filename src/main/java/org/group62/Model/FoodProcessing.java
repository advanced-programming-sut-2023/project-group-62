package org.group62.Model;

import java.util.HashMap;

public class FoodProcessing extends Building {
    public FoodProcessing(User owner, String name, int goldCost, HashMap<Resource, Integer> resourcesCost, int workersNumber, HashMap<Constant, Integer> constants) {
        super(owner, name, goldCost, resourcesCost, workersNumber, constants);
    }
}

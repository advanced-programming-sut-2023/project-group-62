package org.group62.model;

import java.util.HashMap;

public enum FarmEnum {
    APPLE_GARDEN("Apple garden", 0, new HashMap<Resource, Integer>() {{
        put(Resource.WOOD, 5);
    }}, 1, new HashMap<Constant, Integer>() {{
        put(Constant.HP, 2000);
        put(Constant.RATE, 10);
    }}),
    DAIRY_PRODUCTS("Dairy products", 0, new HashMap<Resource, Integer>() {{
        put(Resource.WOOD, 15);
    }}, 1, new HashMap<Constant, Integer>() {{
        put(Constant.HP, 2000);
        put(Constant.RATE, 8);
    }}),
    BARLEY_FARM("Barley farm", 0, new HashMap<Resource, Integer>() {{
        put(Resource.WOOD, 15);
    }}, 1, new HashMap<Constant, Integer>() {{
        put(Constant.HP, 2000);
        put(Constant.RATE, 6);
    }}),
    HUNTING_POST("Hunting post", 0, new HashMap<Resource, Integer>() {{
        put(Resource.WOOD, 5);
    }}, 1, new HashMap<Constant, Integer>() {{
        put(Constant.HP, 1000);
        put(Constant.RATE, 4);
    }}),
    WHEAT_FARM("Wheat farm", 0, new HashMap<Resource, Integer>() {{
        put(Resource.WOOD, 15);
    }}, 1, new HashMap<Constant, Integer>() {{
        put(Constant.HP, 2000);
        put(Constant.RATE, 6);
    }});
    private final String name;
    private final int goldCost;
    private HashMap<Resource, Integer> resourcesCost = new HashMap<>();
    private int workersNumber;
    private HashMap<Constant, Integer> constants = new HashMap<>();

    private FarmEnum(String name, int goldCost, HashMap<Resource, Integer> resourcesCost, int workersNumber, HashMap<Constant, Integer> constants) {
        this.name = name;
        this.goldCost = goldCost;
        this.resourcesCost = resourcesCost;
        this.workersNumber = workersNumber;
        this.constants = constants;
    }


    public static Farm getFarm(FarmEnum farmEnum) {
        return new Farm(Play.getCurrentUser(), farmEnum.name, farmEnum.goldCost, farmEnum.resourcesCost, farmEnum.workersNumber, farmEnum.constants);
    }
}

package org.group62.model;

import java.util.HashMap;

public enum FoodProcessingEnum {
    INN("Inn", 100, new HashMap<Resource, Integer>() {{
        put(Resource.WOOD, 20);
    }}, 1, new HashMap<Constant, Integer>() {{
        put(Constant.HP, 2000);
        put(Constant.MAX_HP, 2000);
        put(Constant.POPULARITY, 4);
        put(Constant.WINE_USAGE, 1);
    }}),
    MILL("Mill", 0, new HashMap<Resource, Integer>() {{
        put(Resource.WOOD, 20);
    }}, 3, new HashMap<Constant, Integer>() {{
        put(Constant.HP, 2000);
        put(Constant.MAX_HP, 2000);
        put(Constant.RATE, 2);
    }}),
    BAKERY("Bakery", 0, new HashMap<Resource, Integer>() {{
        put(Resource.WOOD, 10);
    }}, 1, new HashMap<Constant, Integer>() {{
        put(Constant.HP, 2000);
        put(Constant.MAX_HP, 2000);
        put(Constant.RATE, 4);
    }}),
    BREWERY("Brewery", 0, new HashMap<Resource, Integer>() {{
        put(Resource.WOOD, 10);
    }}, 1, new HashMap<Constant, Integer>() {{
        put(Constant.HP, 2000);
        put(Constant.MAX_HP, 2000);
        put(Constant.RATE, 4);
    }}),
    FOOD_WAREHOUSE("Food Warehouse", 0, new HashMap<Resource, Integer>() {{
        put(Resource.WOOD, 5);
    }}, 0, new HashMap<Constant, Integer>() {{
        put(Constant.HP, 2000);
        put(Constant.MAX_HP, 2000);
        put(Constant.CAPACITY, 50);
    }});
    private final String name;
    private final int goldCost;
    private HashMap<Resource, Integer> resourcesCost = new HashMap<>();
    private final int workersNumber;
    private HashMap<Constant, Integer> constants = new HashMap<>();

    FoodProcessingEnum(String name, int goldCost, HashMap<Resource, Integer> resourcesCost, int workersNumber, HashMap<Constant, Integer> constants) {
        this.name = name;
        this.goldCost = goldCost;
        this.resourcesCost = resourcesCost;
        this.workersNumber = workersNumber;
        this.constants = constants;
    }

    public static FoodProcessing getFoodProcessing(FoodProcessingEnum foodProcessingEnum) {
        return new FoodProcessing(Play.getCurrentGovernance(), foodProcessingEnum.name, foodProcessingEnum.goldCost, foodProcessingEnum.resourcesCost, foodProcessingEnum.workersNumber, foodProcessingEnum.constants);
    }

    public static FoodProcessing getFoodProcessing(FoodProcessingEnum foodProcessingEnum, Governance governance) {
        return new FoodProcessing(governance, foodProcessingEnum.name, foodProcessingEnum.goldCost, foodProcessingEnum.resourcesCost, foodProcessingEnum.workersNumber, foodProcessingEnum.constants);
    }

    public static FoodProcessingEnum getFoodProcessingEnumByName(String foodProcessingEnum) {
        switch (foodProcessingEnum) {
            case "inn":
                return INN;
            case "mill":
                return MILL;
            case "bakery":
                return BAKERY;
            case "brewery":
                return BREWERY;
            case "food warehouse":
                return FOOD_WAREHOUSE;
            default:
                return null;

        }
    }

}

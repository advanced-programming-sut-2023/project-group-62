package org.group62.Model;

import java.util.HashMap;

public enum TownEnum {
    HOVEL("Hovel", 0, new HashMap<Resource, Integer>() {{
        put(Resource.WOOD, 6);
    }}, 0, new HashMap<Constant, Integer>() {{
        put(Constant.HP, 2000);
        put(Constant.MAX_HP, 2000);
    }}),
    CHURCH("Church", 250, new HashMap<Resource, Integer>()
            , 0, new HashMap<Constant, Integer>() {{
        put(Constant.HP, 2000);
        put(Constant.MAX_HP, 2000);
        put(Constant.POPULARITY, 2);
    }}),
    CATHEDRAL("Cathedral", 1000, new HashMap<Resource, Integer>()
            , 0, new HashMap<Constant, Integer>() {{
        put(Constant.HP, 4000);
        put(Constant.MAX_HP, 4000);
        put(Constant.POPULARITY, 2);
    }});
    private final String name;
    private final int goldCost;
    private HashMap<Resource, Integer> resourcesCost = new HashMap<>();
    private final int workersNumber;
    private HashMap<Constant, Integer> constants = new HashMap<>();

    TownEnum(String name, int goldCost, HashMap<Resource, Integer> resourcesCost, int workersNumber, HashMap<Constant, Integer> constants) {
        this.name = name;
        this.goldCost = goldCost;
        this.resourcesCost = resourcesCost;
        this.workersNumber = workersNumber;
        this.constants = constants;
    }


    public static Town getTown(TownEnum townEnum) {
        return new Town(Play.getCurrentGovernance(), townEnum.name, townEnum.goldCost, townEnum.resourcesCost, townEnum.workersNumber, townEnum.constants);
    }

    public static Town getTown(TownEnum townEnum, Governance governance) {
        return new Town(governance, townEnum.name, townEnum.goldCost, townEnum.resourcesCost, townEnum.workersNumber, townEnum.constants);
    }

    public static TownEnum getTownEnumByName(String townEnum) {
        switch (townEnum) {
            case "hovel":
                return HOVEL;
            case "church":
                return CHURCH;
            case "cathedral":
                return CATHEDRAL;
            default:
                return null;
        }
    }
}

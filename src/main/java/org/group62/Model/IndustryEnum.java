package org.group62.Model;

import java.util.HashMap;

public enum IndustryEnum {
    IRON_MINE("Iron mine", 0, new HashMap<Resource, Integer>() {{
        put(Resource.WOOD, 20);
    }}, 2, new HashMap<Constant, Integer>() {{
        put(Constant.HP, 2000);
        put(Constant.RATE, 2);
    }}),
    MARKET("Market", 0, new HashMap<Resource, Integer>() {{
        put(Resource.WOOD, 5);
    }}, 1, new HashMap<Constant, Integer>() {{
        put(Constant.HP, 2000);
    }}),
    OX_TETHER("Ox tether", 0, new HashMap<Resource, Integer>() {{
        put(Resource.WOOD, 5);
    }}, 1, new HashMap<Constant, Integer>() {{
        put(Constant.HP, 2000);
        put(Constant.RATE, 12);
    }}),
    PITCH_RIG("Pitch rig", 0, new HashMap<Resource, Integer>() {{
        put(Resource.WOOD, 20);
    }}, 1, new HashMap<Constant, Integer>() {{
        put(Constant.HP, 2000);
        put(Constant.RATE, 4);
    }}),
    QUARRY("Quarry", 0, new HashMap<Resource, Integer>() {{
        put(Resource.WOOD, 20);
    }}, 3, new HashMap<Constant, Integer>() {{
        put(Constant.HP, 2000);
        put(Constant.RATE, 4);
    }}),
    STOCKPILE("Stockpile", 0, new HashMap<Resource, Integer>()
            , 0, new HashMap<Constant, Integer>() {{
        put(Constant.HP, 2000);
        put(Constant.CAPACITY, 50);
    }}),
    WOODCUTTER("Woodcutter", 0, new HashMap<Resource, Integer>() {{
        put(Resource.WOOD, 3);
    }}, 1, new HashMap<Constant, Integer>() {{
        put(Constant.HP, 1000);
        put(Constant.RATE, 8);
    }});
    private final String name;
    private final int goldCost;
    private HashMap<Resource, Integer> resourcesCost = new HashMap<>();
    private int workersNumber;
    private HashMap<Constant, Integer> constants = new HashMap<>();

    IndustryEnum(String name, int goldCost, HashMap<Resource, Integer> resourcesCost, int workersNumber, HashMap<Constant, Integer> constants) {
        this.name = name;
        this.goldCost = goldCost;
        this.resourcesCost = resourcesCost;
        this.workersNumber = workersNumber;
        this.constants = constants;
    }

    public static Industry getIndustry(IndustryEnum industryEnum) {
        return new Industry(Play.getCurrentGovernance(), industryEnum.name, industryEnum.goldCost, industryEnum.resourcesCost, industryEnum.workersNumber, industryEnum.constants);
    }

    public static Industry getIndustry(IndustryEnum industryEnum, Governance governance) {
        return new Industry(governance, industryEnum.name, industryEnum.goldCost, industryEnum.resourcesCost, industryEnum.workersNumber, industryEnum.constants);
    }

    public static IndustryEnum getIndustryEnumByName(String industryEnum) {
        switch (industryEnum) {
            case "iron mine":
                return IRON_MINE;
            case "market":
                return MARKET;
            case "ox tether":
                return OX_TETHER;
            case "pitch rig":
                return PITCH_RIG;
            case "quarry":
                return QUARRY;
            case "stockpile":
                return STOCKPILE;
            case "woodcutter":
                return WOODCUTTER;
            default:
                return null;
        }
    }
}

package org.group62.model;

import java.util.HashMap;

public enum WeaponsEnum {
    ARMOURER("Armourer", 100, new HashMap<Resource, Integer>() {{
        put(Resource.WOOD, 20);
    }}, 1, new HashMap<Constant, Integer>() {{
        put(Constant.HP, 2000);
        put(Constant.CONSUMING_MATERIALS, 1);
        put(Constant.PRODUCTION_RATE, 2);
    }}),
    BLACKSMITH("Blacksmith", 100, new HashMap<Resource, Integer>() {{
        put(Resource.WOOD, 20);
    }}, 1, new HashMap<Constant, Integer>() {{
        put(Constant.HP, 2000);
        put(Constant.CONSUMING_MATERIALS, 1);
        put(Constant.PRODUCTION_RATE, 2);
    }}),
    FLETCHER("Fletcher", 100, new HashMap<Resource, Integer>() {{
        put(Resource.WOOD, 20);
    }}, 1, new HashMap<Constant, Integer>() {{
        put(Constant.HP, 2000);
        put(Constant.CONSUMING_MATERIALS, 1);
        put(Constant.PRODUCTION_RATE, 1);
    }}),
    POLETURNER("Poleturner", 100, new HashMap<Resource, Integer>() {{
        put(Resource.WOOD, 20);
    }}, 1, new HashMap<Constant, Integer>() {{
        put(Constant.HP, 2000);
        put(Constant.CONSUMING_MATERIALS, 1);
        put(Constant.PRODUCTION_RATE, 1);
    }});

    private final String name;
    private final int goldCost;
    private HashMap<Resource, Integer> resourcesCost = new HashMap<>();
    private int workersNumber;
    private HashMap<Constant, Integer> constants = new HashMap<>();

    WeaponsEnum(String name, int goldCost, HashMap<Resource, Integer> resourcesCost, int workersNumber, HashMap<Constant, Integer> constants) {
        this.name = name;
        this.goldCost = goldCost;
        this.resourcesCost = resourcesCost;
        this.workersNumber = workersNumber;
        this.constants = constants;
    }

    public static Weapons getWeapons(WeaponsEnum weaponsEnum) {
        return new Weapons(Play.getCurrentUser(), weaponsEnum.name, weaponsEnum.goldCost, weaponsEnum.resourcesCost, weaponsEnum.workersNumber, weaponsEnum.constants);
    }

}

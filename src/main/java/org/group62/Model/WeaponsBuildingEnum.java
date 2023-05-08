package org.group62.Model;

import java.util.HashMap;

public enum WeaponsBuildingEnum {
    ARMOURER("Armourer", 100, new HashMap<Resource, Integer>() {{
        put(Resource.WOOD, 20);
    }}, 1, new HashMap<Constant, Integer>() {{
        put(Constant.HP, 2000);
        put(Constant.MAX_HP, 2000);
        put(Constant.CONSUMING_MATERIALS, 1);
        put(Constant.PRODUCTION_RATE, 2);
    }}),
    BLACKSMITH("Blacksmith", 100, new HashMap<Resource, Integer>() {{
        put(Resource.WOOD, 20);
    }}, 1, new HashMap<Constant, Integer>() {{
        put(Constant.HP, 2000);
        put(Constant.MAX_HP, 2000);
        put(Constant.CONSUMING_MATERIALS, 1);
        put(Constant.PRODUCTION_RATE, 2);
    }}),
    FLETCHER("Fletcher", 100, new HashMap<Resource, Integer>() {{
        put(Resource.WOOD, 20);
    }}, 1, new HashMap<Constant, Integer>() {{
        put(Constant.HP, 2000);
        put(Constant.MAX_HP, 2000);
        put(Constant.CONSUMING_MATERIALS, 1);
        put(Constant.PRODUCTION_RATE, 1);
    }}),
    POLETURNER("Poleturner", 100, new HashMap<Resource, Integer>() {{
        put(Resource.WOOD, 20);
    }}, 1, new HashMap<Constant, Integer>() {{
        put(Constant.HP, 2000);
        put(Constant.MAX_HP, 2000);
        put(Constant.CONSUMING_MATERIALS, 1);
        put(Constant.PRODUCTION_RATE, 1);
    }});

    private final String name;
    private final int goldCost;
    private HashMap<Resource, Integer> resourcesCost = new HashMap<>();
    private final int workersNumber;
    private HashMap<Constant, Integer> constants = new HashMap<>();

    WeaponsBuildingEnum(String name, int goldCost, HashMap<Resource, Integer> resourcesCost, int workersNumber, HashMap<Constant, Integer> constants) {
        this.name = name;
        this.goldCost = goldCost;
        this.resourcesCost = resourcesCost;
        this.workersNumber = workersNumber;
        this.constants = constants;
    }

    public static WeaponsBuilding getWeapons(WeaponsBuildingEnum weaponsBuildingEnum) {
        return new WeaponsBuilding(Play.getCurrentGovernance(), weaponsBuildingEnum.name, weaponsBuildingEnum.goldCost, weaponsBuildingEnum.resourcesCost, weaponsBuildingEnum.workersNumber, weaponsBuildingEnum.constants);
    }

    public static WeaponsBuilding getWeapons(WeaponsBuildingEnum weaponsBuildingEnum, Governance governance) {
        return new WeaponsBuilding(governance, weaponsBuildingEnum.name, weaponsBuildingEnum.goldCost, weaponsBuildingEnum.resourcesCost, weaponsBuildingEnum.workersNumber, weaponsBuildingEnum.constants);
    }

    public static WeaponsBuildingEnum getWeaponsEnumByName(String weaponsEnum) {
        switch (weaponsEnum) {
            case "armourer":
                return ARMOURER;
            case "blacksmith":
                return BLACKSMITH;
            case "fletcher":
                return FLETCHER;
            case "poleturner":
                return POLETURNER;
            default:
                return null;
        }
    }


}

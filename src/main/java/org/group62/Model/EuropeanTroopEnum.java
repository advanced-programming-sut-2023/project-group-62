package org.group62.model;

import java.util.HashMap;

public enum EuropeanTroopEnum {
    ARCHER("Archer", 1000, new HashMap<Weapons, Integer>() {{
        put(Weapons.BOW, 1);
    }}, 6, 400, 100, 4, 10),
    CROSSBOWMEN("Crossbowmen", 1000, new HashMap<Weapons, Integer>() {{
        put(Weapons.CROSSBOW, 1);
        put(Weapons.LEATHER_ARMOR, 1);
    }}, 8, 400, 150, 2, 8),
    SPEARMEN("Spearmen", 1000, new HashMap<Weapons, Integer>() {{
        put(Weapons.SPEAR, 1);
    }}, 5, 600, 50, 3, 0),
    PIKEMEN("Pikemen", 1000, new HashMap<Weapons, Integer>() {{
        put(Weapons.PIK, 1);
        put(Weapons.METAL_ARMOR, 1);
    }}, 10, 600, 200, 2, 0),
    MACEMEN("Macemen", 1000, new HashMap<Weapons, Integer>() {{
        put(Weapons.MACE, 1);
        put(Weapons.LEATHER_ARMOR, 1);
    }}, 10, 800, 150, 3, 0),
    SWORDSMEN("Swordsmen", 1000, new HashMap<Weapons, Integer>() {{
        put(Weapons.SWORD, 1);
        put(Weapons.METAL_ARMOR, 1);
    }}, 12, 1000, 250, 1, 0),
    KNIGHT("Knight", 1000, new HashMap<Weapons, Integer>() {{
        put(Weapons.SWORD, 1);
        put(Weapons.METAL_ARMOR, 1);
        put(Weapons.HORSE, 1);
    }}, 15, 1000, 200, 5, 0),
    BLACK_MONK("BlackMonk", 1000, new HashMap<Weapons, Integer>() {{
    }}, 10, 600, 150, 2, 0);

    private final String name;
    private final int HP;
    private final HashMap<Weapons, Integer> weaponsCost;
    private final  int goldCost;
    private final int attackPower;
    private final int defencePower;
    private final int speed;
    private final int range;

    private EuropeanTroopEnum(String name, int HP, HashMap<Weapons, Integer> weaponsCost, int goldCost, int attackPower, int defencePower, int speed, int range) {
        this.name = name;
        this.HP = HP;
        this.weaponsCost = weaponsCost;
        this.goldCost = goldCost;
        this.attackPower = attackPower;
        this.defencePower = defencePower;
        this.speed = speed;
        this.range = range;
    }


    public static EuropeanTroop getEuropeanTroop(EuropeanTroopEnum europeanTroopEnum) {
        return new EuropeanTroop(Play.getCurrentGovernance(), europeanTroopEnum.name, europeanTroopEnum.HP, europeanTroopEnum.weaponsCost, europeanTroopEnum.goldCost, europeanTroopEnum.attackPower, europeanTroopEnum.defencePower, europeanTroopEnum.speed, europeanTroopEnum.range);
    }

    public static EuropeanTroop getEuropeanTroop(EuropeanTroopEnum europeanTroopEnum, Governance owner) {
        return new EuropeanTroop(owner, europeanTroopEnum.name, europeanTroopEnum.HP, europeanTroopEnum.weaponsCost, europeanTroopEnum.goldCost, europeanTroopEnum.attackPower, europeanTroopEnum.defencePower, europeanTroopEnum.speed, europeanTroopEnum.range);
    }

    public static EuropeanTroopEnum getEuropeanTroopEnumByName(String europeanTroopEnum) {
        switch (europeanTroopEnum) {
            case "archer":
                return ARCHER;
            case "crossbowmen":
                return CROSSBOWMEN;
            case "spearmen":
                return SPEARMEN;
            case "pikemen":
                return PIKEMEN;
            case "macemen":
                return MACEMEN;
            case "swordsmen":
                return SWORDSMEN;
            case "knight":
                return KNIGHT;
            case "black monk":
                return BLACK_MONK;
            default:
                return null;
        }
    }
}

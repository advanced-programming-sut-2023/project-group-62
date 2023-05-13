package org.group62.model;

import java.util.HashMap;

public enum ArabianTroopEnum {
    ARCHERBOW("Archerbow", 1000, 4, 400, 100, 4, 10),
    SLAVES("Slaves", 1000, 1, 200, 0, 4, 0),
    SLINGERS("Slingers", 1000, 3, 400, 50, 4, 6),
    ASSASSINS("Assassins", 1000, 6, 600, 150, 3, 0),
    HORSE_ARCHERS("Horse archers", 1000, 6, 400, 150, 5, 10),
    ARABIAN_SWORDSMEN("Arabian swordsmen", 1000, 7, 800, 200, 2, 0),
    FIRE_THROWERS("Fire throwers", 1000, 4, 800, 100, 5, 6);


    private final String name;
    private final int HP;
    private  final HashMap<Weapons, Integer> weaponsCost = new HashMap<>();
    private final  int goldCost;
    private final int attackPower;
    private final int defencePower;
    private final int speed;
    private final int range;

    private ArabianTroopEnum(String name, int HP, int goldCost, int attackPower, int defencePower, int speed, int range) {
        this.name = name;
        this.HP = HP;
        this.goldCost = goldCost;
        this.attackPower = attackPower;
        this.defencePower = defencePower;
        this.speed = speed;
        this.range = range;
    }

    public static ArabianTroop getArabianTroop(ArabianTroopEnum arabianTroopEnum) {
        return new ArabianTroop(Play.getCurrentGovernance(), arabianTroopEnum.name, arabianTroopEnum.HP, arabianTroopEnum.weaponsCost, arabianTroopEnum.goldCost, arabianTroopEnum.attackPower, arabianTroopEnum.defencePower, arabianTroopEnum.speed, arabianTroopEnum.range);
    }

    public static ArabianTroop getArabianTroop(ArabianTroopEnum arabianTroopEnum, Governance owner) {
        return new ArabianTroop(owner, arabianTroopEnum.name, arabianTroopEnum.HP, arabianTroopEnum.weaponsCost, arabianTroopEnum.goldCost, arabianTroopEnum.attackPower, arabianTroopEnum.defencePower, arabianTroopEnum.speed, arabianTroopEnum.range);
    }

    public static ArabianTroopEnum getArabianTroopEnumByName(String arabianTroopEnum) {
        switch (arabianTroopEnum) {
            case "archerbow":
                return ARCHERBOW;
            case "slaves":
                return SLAVES;
            case "slingers":
                return SLINGERS;
            case "assassins":
                return ASSASSINS;
            case "horse archer":
                return HORSE_ARCHERS;
            case "arabian swordsmen":
                return ARABIAN_SWORDSMEN;
            case "fire throwers":
                return FIRE_THROWERS;
            default:
                return null;
        }
    }
}

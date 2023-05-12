package org.group62.model;

public enum EuropeanTroopEnum {
    ARCHER("Archer", 1000, 400, 100, 4, 10),
    CROSSBOWMEN("Crossbowmen", 1000, 400, 150, 2, 8),
    SPEARMEN("Spearmen", 1000, 600, 50, 3, 0),
    PIKEMEN("Pikemen", 1000, 600, 200, 2, 0),
    MACEMEN("Macemen", 1000, 800, 150, 3, 0),
    SWORDSMEN("Swordsmen", 1000, 1000, 250, 1, 0),
    KNIGHT("Knight", 1000, 1000, 200, 5, 0),
    BLACK_MONK("BlackMonk", 1000, 600, 150, 2, 0);

    private final String name;
    private final int HP;
    private final int attackPower;
    private final int defencePower;
    private final int speed;
    private final int range;

    private EuropeanTroopEnum(String name, int HP, int attackPower, int defencePower, int speed, int range) {
        this.name = name;
        this.HP = HP;
        this.attackPower = attackPower;
        this.defencePower = defencePower;
        this.speed = speed;
        this.range = range;
    }


    public static EuropeanTroop getEuropeanTroop(EuropeanTroopEnum europeanTroopEnum) {
        return new EuropeanTroop(Play.getCurrentUser(), europeanTroopEnum.name, europeanTroopEnum.HP, europeanTroopEnum.attackPower, europeanTroopEnum.defencePower, europeanTroopEnum.speed, europeanTroopEnum.range);
    }
}

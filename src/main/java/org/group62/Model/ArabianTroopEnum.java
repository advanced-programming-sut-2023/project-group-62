package org.group62.model;

public enum ArabianTroopEnum {
    ARCHERBOW("Archerbow", 1000, 400, 100, 4, 10),
    SLAVES("Slaves", 1000, 200, 0, 4, 0),
    SLINGERS("Slingers", 1000, 400, 50, 4, 6),
    ASSASSINS("Assassins", 1000, 600, 150, 3, 0),
    HORSE_ARCHERS("Horse archers", 1000, 400, 150, 5, 10),
    ARABIAN_SWORDSMEN("Arabian swordsmen", 1000, 800, 200, 2, 0),
    FIRE_THROWERS("Fire throwers", 1000, 800, 100, 5, 6);


    private final String name;
    private final int HP;
    private final int attackPower;
    private final int defencePower;
    private final int speed;
    private final int range;

    private ArabianTroopEnum(String name, int HP, int attackPower, int defencePower, int speed, int range) {
        this.name = name;
        this.HP = HP;
        this.attackPower = attackPower;
        this.defencePower = defencePower;
        this.speed = speed;
        this.range = range;
    }

    public static ArabianTroop getArabianTroop(ArabianTroopEnum arabianTroopEnum) {
        return new ArabianTroop(Play.getCurrentUser(), arabianTroopEnum.name, arabianTroopEnum.HP, arabianTroopEnum.attackPower, arabianTroopEnum.defencePower, arabianTroopEnum.speed, arabianTroopEnum.range);
    }
}

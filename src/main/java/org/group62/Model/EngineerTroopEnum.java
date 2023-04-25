package org.group62.Model;

public enum EngineerTroopEnum {
    TUNNELER("Tunneler", 1000, 600, 0, 4, 0),
    LADDERMEN("Laddermen", 1000, 0, 50, 4, 0),
    ENGINEER("Engineer", 1000, 0, 50, 3, 0);


    private final String name;
    private final int HP;
    private final int attackPower;
    private final int defencePower;
    private final int speed;
    private final int range;
    private EngineerTroopEnum(String name, int HP, int attackPower, int defencePower, int speed, int range) {
        this.name = name;
        this.HP = HP;
        this.attackPower = attackPower;
        this.defencePower = defencePower;
        this.speed = speed;
        this.range = range;
    }

    public static EngineerTroop getEngineerTroop(EngineerTroopEnum engineerTroopEnum) {
        return new EngineerTroop(Play.getCurrentUser(), engineerTroopEnum.name, engineerTroopEnum.HP, engineerTroopEnum.attackPower, engineerTroopEnum.defencePower, engineerTroopEnum.speed, engineerTroopEnum.range);
    }
}

package org.group62.Model;

import javax.jws.soap.SOAPBinding;
import java.util.HashMap;

public enum EngineerTroopEnum {
    TUNNELER("Tunneler", 1000, 10, 600, 0, 4, 0),
    LADDERMEN("Laddermen", 1000, 10, 0, 50, 4, 0),
    ENGINEER("Engineer", 1000, 10, 0, 50, 3, 0);


    private final String name;
    private final int HP;
    private final HashMap<Weapons, Integer> weaponsCost = new HashMap<>();
    private final  int goldCost;
    private final int attackPower;
    private final int defencePower;
    private final int speed;
    private final int range;

    private EngineerTroopEnum(String name, int HP, int goldCost, int attackPower, int defencePower, int speed, int range) {
        this.name = name;
        this.HP = HP;
        this.goldCost = goldCost;
        this.attackPower = attackPower;
        this.defencePower = defencePower;
        this.speed = speed;
        this.range = range;
    }

    public static EngineerTroop getEngineerTroop(EngineerTroopEnum engineerTroopEnum) {
        return new EngineerTroop(Play.getCurrentGovernance(), engineerTroopEnum.name, engineerTroopEnum.HP, engineerTroopEnum.weaponsCost, engineerTroopEnum.goldCost, engineerTroopEnum.attackPower, engineerTroopEnum.defencePower, engineerTroopEnum.speed, engineerTroopEnum.range);
    }

    public static EngineerTroop getEngineerTroop(EngineerTroopEnum engineerTroopEnum, Governance owner) {
        return new EngineerTroop(owner, engineerTroopEnum.name, engineerTroopEnum.HP, engineerTroopEnum.weaponsCost, engineerTroopEnum.goldCost, engineerTroopEnum.attackPower, engineerTroopEnum.defencePower, engineerTroopEnum.speed, engineerTroopEnum.range);
    }

    public static EngineerTroopEnum getEngineerTroopEnumByName(String engineerTroopEnum) {
        switch (engineerTroopEnum) {
            case "tunneler":
                return TUNNELER;
            case "laddermen":
                return LADDERMEN;
            case "engineer":
                return ENGINEER;
            default:
                return null;

        }
    }
}

package org.group62.model;

import java.util.HashMap;

public class King extends Troop{
    public King(Governance owner, String name, int HP, HashMap<Weapons, Integer> weaponsCost, int goldCost, int attackPower, int defencePower, int speed, int range) {
        super(owner, name, HP, weaponsCost, goldCost, attackPower, defencePower, speed, range);
    }
    public static King getKing(Governance governance){
        return new King(governance,"King",10000,null,0,2000,500,4,0);
    }
}

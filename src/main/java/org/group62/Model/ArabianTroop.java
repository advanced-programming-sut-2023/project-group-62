package org.group62.Model;

import java.util.HashMap;

public class ArabianTroop extends Troop {
    public ArabianTroop(Governance owner, String name, int HP, HashMap<Weapons, Integer> weaponsCost, int goldCost, int attackPower, int defencePower, int speed, int range) {
        super(owner, name, HP, weaponsCost, goldCost, attackPower, defencePower, speed, range);
    }
}

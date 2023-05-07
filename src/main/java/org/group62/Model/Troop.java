package org.group62.Model;

import java.util.HashMap;

public class Troop extends People {
    private final Governance owner;
    private final String name;
    private int HP; // Hitpoint
    private HashMap<Weapons, Integer> weaponsCost;
    private int goldCost;
    private final int attackPower; // Damage
    private final int defencePower; // Armor
    private final int speed; // Number of blocks in each turn
    private final int range; // Number of blocks

    public Troop(Governance owner, String name, int HP, HashMap<Weapons, Integer> weaponsCost, int goldCost, int attackPower, int defencePower, int speed, int range) {
        this.owner = owner;
        this.name = name;
        this.HP = HP;
        this.weaponsCost = weaponsCost;
        this.goldCost = goldCost;
        this.attackPower = attackPower;
        this.defencePower = defencePower;
        this.speed = speed;
        this.range = range;
    }

    public static boolean canDropTroopThere(int x, int y) {
        GroundType groundType = StrongHold.getCurrentPlay().getMap()[x][y].getGroundType();
        switch (groundType) {
            case ROCK:
            case OIL:
            case PLAIN:
            case RIVER:
            case SMALL_POND:
            case BIG_POND:
            case BEACH:
            case SEA:
                return false;
            default:
                return true;
        }
    }

    public int getGoldCost() {
        return goldCost;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public Governance getOwner() {
        return owner;
    }

    public String getName() {
        return name;
    }

    public int getHP() {
        return HP;
    }

    public HashMap<Weapons, Integer> getWeaponsCost() {
        return weaponsCost;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public int getDefencePower() {
        return defencePower;
    }

    public int getSpeed() {
        return speed;
    }

    public int getRange() {
        return range;
    }
    /*
    private final static int insignificantAttackPower = 0;
    private final static int soLowAttackPower = 200;
    private final static int lowAttackPower = 400;
    private final static int normalAttackPower = 600;
    private final static int muchAttackPower = 800;
    private final static int soMuchAttackPower = 1000;

    private final static int insignificantDefencePower = 0;
    private final static int soLowDefencePower = 50;
    private final static int lowDefencePower = 100;
    private final static int normalDefencePower = 150;
    private final static int muchDefencePower = 200;
    private final static int soMuchDefencePower = 250;

    private final static int soLowSpeed = 1;
    private final static int lowSpeed = 2;
    private final static int normalSpeed = 3;
    private final static int muchSpeed = 4;
    private final static int soMuchSpeed = 5;

    private final static int noRange = 0;
    private final static int lowRange = 6;
    private final static int normalRange = 8;
    private final static int muchRange = 10;
    */
}

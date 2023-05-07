package org.group62.Model;

import java.util.HashMap;

public class Building {
    private final Governance owner;
    private final String name;
    private final int goldCost;
    private final HashMap<Resource, Integer> resourcesCost;
    private int workersNumber;
    private HashMap<Constant, Integer> constants;


    public Building(Governance owner, String name, int goldCost, HashMap<Resource, Integer> resourcesCost, int workersNumber, HashMap<Constant, Integer> constants) {
        this.owner = owner;
        this.name = name;
        this.goldCost = goldCost;
        this.resourcesCost = resourcesCost;
        this.workersNumber = workersNumber;
        this.constants = constants;
    }
    public static boolean canDropBuildingThere(int x, int y) {
        GroundType groundType = StrongHold.getCurrentPlay().getMap()[x][y].getGroundType();
        switch (groundType) {
            case ROCK:
            case STONE:
            case IRON:
            case OIL:
            case PLAIN:
            case SHALLOW_WATER:
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
    public static Building getBuilding(String buildingName, Governance governance) {
        CastlesEnum castlesEnum = CastlesEnum.getCastlesEnumByName(buildingName);
        FarmEnum farmEnum = FarmEnum.getFarmEnumByName(buildingName);
        FoodProcessingEnum foodProcessingEnum = FoodProcessingEnum.getFoodProcessingEnumByName(buildingName);
        IndustryEnum industryEnum = IndustryEnum.getIndustryEnumByName(buildingName);
        TownEnum townEnum = TownEnum.getTownEnumByName(buildingName);
        WeaponsBuildingEnum weaponsBuildingEnum = WeaponsBuildingEnum.getWeaponsEnumByName(buildingName);
        if (castlesEnum != null)
            return CastlesEnum.getCastles(castlesEnum, governance);
        else if (farmEnum != null)
            return FarmEnum.getFarm(farmEnum, governance);
        else if (foodProcessingEnum != null)
            return FoodProcessingEnum.getFoodProcessing(foodProcessingEnum, governance);
        else if (industryEnum != null)
            return IndustryEnum.getIndustry(industryEnum, governance);
        else if (townEnum != null)
            return TownEnum.getTown(townEnum, governance);
        else if (weaponsBuildingEnum != null)
            return WeaponsBuildingEnum.getWeapons(weaponsBuildingEnum, governance);
        else
            return null;
    }
    public void setConstants(Constant constant, int keyInteger) {
        constants.replace(constant, keyInteger);
    }

    public int getConstantsInteger(String key) {
        return constants.get(key);
    }

    public Governance getOwner() {
        return owner;
    }

    public String getName() {
        return name;
    }

    public int getGoldCost() {
        return goldCost;
    }

    public HashMap<Resource, Integer> getResourcesCost() {
        return resourcesCost;
    }

    public int getWorkersNumber() {
        return workersNumber;
    }

    public HashMap<Constant, Integer> getConstants() {
        return constants;
    }
}

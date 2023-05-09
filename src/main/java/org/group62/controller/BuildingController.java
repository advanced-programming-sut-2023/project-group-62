package org.group62.Controller;

import org.group62.Model.*;

import static org.group62.Model.Building.canDropBuildingThere;
import static org.group62.Model.Building.getBuilding;


public class BuildingController {
    private Building currentBuilding = null;

    public String dropBuilding(int x, int y, String buildingName) {
        x = x - 1;
        y = y - 1;
        Building building;
        Governance governance = Play.getCurrentGovernance();
        if ((building = getBuilding(buildingName, governance)) == null)
            return "invalid building name";
        else if (x < 0 || x > 399 || y < 0 || y > 399)
            return "invalid x,y";
        Ground ground = StrongHold.getCurrentPlay().getMap()[x][y];
        if (!ground.getBuildings().isEmpty())
            return "there is building";
        else if ((building instanceof Farm && !(ground.getGroundType().equals(GroundType.GRASS) ||
                ground.getGroundType().equals(GroundType.DENSE_GRASSLAND))) ||
                !canDropBuildingThere(x, y) || ground.getGroundTreeType() == null)
            return "can't drop building there";
        else if (governance.getGold() < building.getGoldCost())
            return "you don't have enough money";
        else {
            for (Resource resource : building.getResourcesCost().keySet()) {
                if (governance.getResources().get(resource) < building.getResourcesCost().get(resource))
                    return "you don't have enough resource";
            }
            for (Resource resource : building.getResourcesCost().keySet()) {
                governance.decreaseResource(resource, building.getResourcesCost().get(resource));
            }
            governance.setGold(governance.getGold() - building.getGoldCost());
            ground.addBuilding(building);
            governance.addBuilding(building);
            return "drop building was successful";
        }
    }

    public String selectBuilding(int x, int y) {
        x = x - 1;
        y = y - 1;
        if (x < 0 || x > 399 || y < 0 || y > 399)
            return "invalid x,y";
        Ground ground = StrongHold.getCurrentPlay().getMap()[x][y];
        if (ground.getBuildings().isEmpty())
            return "there isn't building";
        if (!ground.getBuildings().get(0).getOwner().equals(Play.getCurrentGovernance()))
            return "you not the owner of this building";
        this.setCurrentBuilding(ground.getBuildings().get(0));
        return String.format("select %s was successful", this.getCurrentBuilding().getName());
    }

    public String createUnit(String type, int count) {
        int[] location = StrongHold.getCurrentPlay().getKeepLocationOfGovernance(Play.getCurrentGovernance());
        Ground ground = StrongHold.getCurrentPlay().getMap()[location[0]][location[1]];
        Governance governance = Play.getCurrentGovernance();
        Troop troop;
        EuropeanTroopEnum europeanTroopEnum = null;
        ArabianTroopEnum arabianTroopEnum = null;
        EngineerTroopEnum engineerTroopEnum = null;
        if (currentBuilding == null)
            return "you don't select building";
        else if (count < 1)
            return "invalid count";
        else {
            switch (currentBuilding.getName()) {
                case "Barrack":
                    europeanTroopEnum = EuropeanTroopEnum.getEuropeanTroopEnumByName(type);
                    if (europeanTroopEnum == null)
                        return "you can't create this unit there";
                    else
                        troop = EuropeanTroopEnum.getEuropeanTroop(europeanTroopEnum);

                    break;
                case "Engineer guild":
                    arabianTroopEnum = ArabianTroopEnum.getArabianTroopEnumByName(type);
                    if (arabianTroopEnum == null)
                        return "you can't create this unit there";
                    else
                        troop = ArabianTroopEnum.getArabianTroop(arabianTroopEnum);
                    break;
                case "Mercenary post":
                    engineerTroopEnum = EngineerTroopEnum.getEngineerTroopEnumByName(type);
                    if (engineerTroopEnum == null)
                        return "you can't create this unit there";
                    else
                        troop = EngineerTroopEnum.getEngineerTroop(engineerTroopEnum);
                    break;
                default:
                    return "you can't create unit with this building";
            }
            if (troop.getGoldCost() * count > governance.getGold())
                return "you haven't enough gold";
            else if (governance.getPeoples().size() < count)
                return "you don't have enough people";
            else if (!troop.getWeaponsCost().isEmpty()) {
                for (Weapons weapons : troop.getWeaponsCost().keySet()) {
                    if (troop.getWeaponsCost().get(weapons) > governance.getWeapons().get(weapons))
                        return "you don't have enough weapons";
                }
            } else {
                governance.setGold(governance.getGold() - count * troop.getGoldCost());
                if (!troop.getWeaponsCost().isEmpty())
                    for (Weapons weapons1 : troop.getWeaponsCost().keySet())
                        governance.getWeapons().replace(weapons1, governance.getWeapons().get(weapons1) - troop.getWeaponsCost().get(weapons1));
                if (europeanTroopEnum != null)
                    for (int i = 0; i < count; i++)
                        ground.addTroop(EuropeanTroopEnum.getEuropeanTroop(europeanTroopEnum));
                if (arabianTroopEnum != null)
                    for (int i = 0; i < count; i++)
                        ground.addTroop(ArabianTroopEnum.getArabianTroop(arabianTroopEnum));
                if (engineerTroopEnum != null)
                    for (int i = 0; i < count; i++)
                        ground.addTroop(EngineerTroopEnum.getEngineerTroop(engineerTroopEnum));
            }
            return "create unit was successful";
        }
    }

    public String repair() {
        int[] location = StrongHold.getCurrentPlay().getLocationOfBuilding(this.getCurrentBuilding());
        Ground[][] map = StrongHold.getCurrentPlay().getMap();
        Governance governance = Play.getCurrentGovernance();
        int x = location[0], y = location[1];
        if (getCurrentBuilding() == null)
            return "you don't select building";
        else if (!(getCurrentBuilding() instanceof Castles))
            return "This building is not a castle";
        else if (getCurrentBuilding().getResourcesCost().containsKey(Resource.STONE))
            return "This is not a stone building";
        else if (getCurrentBuilding().getConstants().get(Constant.HP) >= getCurrentBuilding().getConstants().get(Constant.MAX_HP))
            return "HP is full";
        int stoneCost = 5;
        if (governance.getResources().get(Resource.STONE) < stoneCost)
            return "not enough stone to repair";
        for (Ground[] grounds : map) {
            for (Ground ground : grounds) {
                if (ground.getX() < x + 10 && ground.getX() > x - 10 && ground.getY() < y + 10 && ground.getY() > y - 10)
                    if (!ground.getTroops().isEmpty())
                        for (Troop troop : ground.getTroops())
                            if (!troop.getOwner().equals(getCurrentBuilding().getOwner()))
                                return "there is enemy";
            }
        }
        getCurrentBuilding().getConstants().replace(Constant.HP, getCurrentBuilding().getConstants().get(Constant.MAX_HP));
        governance.getResources().replace(Resource.STONE, governance.getResources().get(Resource.STONE) - stoneCost);
        return "repair was successful";
    }

    private Building getCurrentBuilding() {
        return currentBuilding;
    }

    private void setCurrentBuilding(Building currentBuilding) {
        this.currentBuilding = currentBuilding;
    }
}

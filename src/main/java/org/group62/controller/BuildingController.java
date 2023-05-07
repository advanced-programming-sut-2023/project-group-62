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
        else if (x < 0 || x > 400 || y < 0 || y > 400)
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
        if (x < 0 || x > 400 || y < 0 || y > 400)
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
        if (currentBuilding == null)
            return "you don't select building";
        else if (count < 1)
            return "invalid count";
        else {
            Troop troop;
            switch (currentBuilding.getName()) {
                case "Barrack":
                    EuropeanTroopEnum europeanTroopEnum = EuropeanTroopEnum.getEuropeanTroopEnumByName(type);
                    if (europeanTroopEnum == null)
                        return "you can't create this unit there";
                    else{
                        if ((troop = EuropeanTroopEnum.getEuropeanTroop(europeanTroopEnum)).getGoldCost()*count> governance.getGold())
                            return "you haven't enough gold";
                        else
                            for (int i = 0; i< count; i++)
                                ground.addTroop(EuropeanTroopEnum.getEuropeanTroop(europeanTroopEnum));

                    }
                    break;
                case "Engineer guild":
                    ArabianTroopEnum arabianTroopEnum = ArabianTroopEnum.getArabianTroopEnumByName(type);
                    if (arabianTroopEnum == null)
                        return "you can't create this unit there";
                    else{
                        if ((troop = ArabianTroopEnum.getArabianTroop(arabianTroopEnum)).getGoldCost()*count> governance.getGold())
                            return "you haven't enough gold";
                        else
                            for (int i = 0; i< count; i++)
                                ground.addTroop(ArabianTroopEnum.getArabianTroop(arabianTroopEnum));
                    }
                    break;
                case "Mercenary post":
                    EngineerTroopEnum engineerTroopEnum = EngineerTroopEnum.getEngineerTroopEnumByName(type);
                    if (engineerTroopEnum == null)
                        return "you can't create this unit there";
                    else{
                        if ((troop = EngineerTroopEnum.getEngineerTroop(engineerTroopEnum)).getGoldCost()*count> governance.getGold())
                            return "you haven't enough gold";
                        else
                            for (int i = 0; i< count; i++)
                                ground.addTroop(EngineerTroopEnum.getEngineerTroop(engineerTroopEnum));
                    }
                    break;
                default:
                    return "you can't create unit with this building";
            }
            governance.setGold(governance.getGold() - count * troop.getGoldCost());
            return "create unit was successful";

        }

    }

    public String repair() {
        return null;
    }

    public Building getCurrentBuilding() {
        return currentBuilding;
    }

    public void setCurrentBuilding(Building currentBuilding) {
        this.currentBuilding = currentBuilding;
    }
}

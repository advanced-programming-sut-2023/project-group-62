package org.group62.controller;

import org.group62.model.*;

import static org.group62.model.Building.canDropBuildingThere;
import static org.group62.model.Building.getBuilding;
import static org.group62.model.GroundTreeType.canDropTreeThere;
import static org.group62.model.Troop.canDropTroopThere;

public class CheaterController {
    public String setTexture(int x, int y, String groundType1) {
        x = x - 1;
        y = y - 1;
        GroundType groundType;
        if ((groundType = GroundType.getGroundType(groundType1)) == null)
            return "invalid ground type";
        else if (x < 0 || x > 399 || y < 0 || y > 399)
            return "invalid x,y";
        else if (!StrongHold.getCurrentPlay().getMap()[x][y].getBuildings().isEmpty())
            return "there is building";
        else {
            Map.setGround(StrongHold.getCurrentPlay().getMap(), groundType, x, y);
            return "change texture was successful";
        }
    }

    public String setTexture(int x1, int y1, int x2, int y2, String groundType1) {
        x1 = x1 - 1;
        y1 = y1 - 1;
        x2 = x2 - 1;
        y2 = y2 - 1;
        if (x1 < 0 || x1 > 399 || y1 < 0 || y1 > 399 || x2 < 0 || x2 > 399 || y2 < 0 || y2 > 399)
            return "invalid x,y";
        GroundType groundType;
        boolean isThereBuilding = false;
        for (int i = x1; i < x2; i++)
            for (int j = y1; j < y2; j++)
                if (!StrongHold.getCurrentPlay().getMap()[i][j].getBuildings().isEmpty())
                    isThereBuilding = true;
        if ((groundType = GroundType.getGroundType(groundType1)) == null)
            return "invalid ground type";
        else if (isThereBuilding)
            return "there is building";
        else {
            Map.setGround(StrongHold.getCurrentPlay().getMap(), groundType, x1, y1, x2, y2);
            return "change texture was successful";
        }

    }

    public String clear(int x, int y) {
        x = x - 1;
        y = y - 1;
        StrongHold.getCurrentPlay().getMap()[x][y].setGroundType(GroundType.LAND);
        StrongHold.getCurrentPlay().getMap()[x][y].setGroundTreeType(null);
        StrongHold.getCurrentPlay().getMap()[x][y].removeBuildings();
        StrongHold.getCurrentPlay().getMap()[x][y].removeTroops();
        return "clear was successful";
    }

    public String dropRock(int x, int y, String direction) {
        x = x - 1;
        y = y - 1;
        if (!(direction.equals("r") || direction.equals("w") || direction.equals("s") ||
                direction.equals("e") || direction.equals("n")))
            return "invalid direction";
        else if (x < 0 || x > 399 || y < 0 || y > 399)
            return "invalid x,y";
        else {
            StrongHold.getCurrentPlay().getMap()[x][y].setGroundType(GroundType.ROCK);
            return "drop rock was successful";
        }


    }

    public String dropTree(int x, int y, String groundTreeType1) {
        x = x - 1;
        y = y - 1;
        GroundTreeType groundTreeType;
        if ((groundTreeType = GroundTreeType.getGroundTreeType(groundTreeType1)) == null)
            return "invalid tree type";
        else if (x < 0 || x > 399 || y < 0 || y > 399)
            return "invalid x,y";
        else if (!StrongHold.getCurrentPlay().getMap()[x][y].getBuildings().isEmpty())
            return "there is building";
        else if (!canDropTreeThere(x, y))
            return "can't drop tree there";
        else {
            StrongHold.getCurrentPlay().getMap()[x][y].setGroundTreeType(groundTreeType);
            return "drop tree was successful";
        }

    }

    public String dropBuilding(int x, int y, String buildingName, Governance governance) {
        x = x - 1;
        y = y - 1;
        Building building;
        if ((building = getBuilding(buildingName, governance)) == null)
            return "invalid building name";
        else if (x < 0 || x > 399 || y < 0 || y > 399)
            return "invalid x,y";
        Ground ground = StrongHold.getCurrentPlay().getMap()[x][y];
        if (!ground.getBuildings().isEmpty())
            return "there is building";
        else if ((building instanceof Farm && !(ground.getGroundType().equals(GroundType.GRASS) ||
                ground.getGroundType().equals(GroundType.DENSE_GRASSLAND))) ||
                !canDropBuildingThere(x, y) || ground.getGroundTreeType() == null ||
                (ground.getGroundType().equals(GroundType.STONE) && !building.getName().equals("Quarry")) ||
                (ground.getGroundType().equals(GroundType.IRON) && !building.getName().equals("Iron mine")) ||
                (ground.getGroundType().equals(GroundType.PLAIN) && !building.getName().equals("Pitch rig")))
            return "can't drop building there";
        else {
            ground.addBuilding(building);
            governance.addBuilding(building);
            if (building.getName().equals("Hovel"))
                for (int i = 0; i < 8; i++) {
                    People people = new People(governance);
                    governance.addPeople(people);
                    int[] location = StrongHold.getCurrentPlay().getKeepLocationOfGovernance(governance);
                    StrongHold.getCurrentPlay().getMap()[location[0]][location[1]].addPeople(people);
                }
            int i = 0;
            while (i < building.getWorkersNumber()) {
                Worker worker = new Worker(governance);
                governance.addPeople(worker);
                ground.addPeople(worker);
                i++;
            }
            switch (building.getName()){
                case "Stockpile":
                    governance.setCapacityOfResource(governance.getCapacityOfResource() + 50);
                    break;
                case "Armoury":
                    governance.setCapacityOfWeapons(governance.getCapacityOfWeapons() + 50);
                    break;
                case "Food Warehouse":
                    governance.setCapacityOfFood(governance.getCapacityOfFood() + 50);
                    break;
                default:
                    break;
            }
            return "drop building was successful";
        }

    }

    public String dropUnit(int x, int y, String unitName, int count, Governance governance) {
        x = x - 1;
        y = y - 1;
        EuropeanTroopEnum europeanTroopEnum = EuropeanTroopEnum.getEuropeanTroopEnumByName(unitName);
        ArabianTroopEnum arabianTroopEnum = ArabianTroopEnum.getArabianTroopEnumByName(unitName);
        EngineerTroopEnum engineerTroopEnum = EngineerTroopEnum.getEngineerTroopEnumByName(unitName);
        if (x < 0 || x > 399 || y < 0 || y > 399)
            return "invalid x,y";
        else if (count < 1)
            return "invalid count";
        else if (engineerTroopEnum == null && arabianTroopEnum == null && europeanTroopEnum == null)
            return "invalid unit name";
        else if (!canDropTroopThere(x, y))
            return "can't drop unit there";
        else {
            if (europeanTroopEnum != null)
                for (int i = 0; i < count; i++)
                    StrongHold.getCurrentPlay().getMap()[x][y].addPeople(EuropeanTroopEnum.getEuropeanTroop(europeanTroopEnum, governance));
            if (arabianTroopEnum != null)
                for (int i = 0; i < count; i++)
                    StrongHold.getCurrentPlay().getMap()[x][y].addPeople(ArabianTroopEnum.getArabianTroop(arabianTroopEnum, governance));
            if (engineerTroopEnum != null)
                for (int i = 0; i < count; i++)
                    StrongHold.getCurrentPlay().getMap()[x][y].addPeople(EngineerTroopEnum.getEngineerTroop(engineerTroopEnum, governance));
            return "drop unit was successful";
        }
    }
}

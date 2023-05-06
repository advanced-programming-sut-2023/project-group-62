package org.group62.Controller;

import org.group62.Model.*;

public class CheaterController {
    public String setTexture(int x, int y, String groundType1) {
        x = x - 1;
        y = y - 1;
        GroundType groundType;
        if ((groundType = GroundType.getGroundType(groundType1)) == null)
            return "invalid ground type";
        else if (x < 0 || x > 400 || y < 0 || y > 400)
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
        if (x1 < 0 || x1 > 400 || y1 < 0 || y1 > 400 || x2 < 0 || x2 > 400 || y2 < 0 || y2 > 400)
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
        else if (x < 0 || x > 400 || y < 0 || y > 400)
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
        else if (x < 0 || x > 400 || y < 0 || y > 400)
            return "invalid x,y";
        else if (!StrongHold.getCurrentPlay().getMap()[x][y].getBuildings().isEmpty())
            return "there is building";
        else if (!canDropTreeOrBuildingThere(x, y))
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
        GovernanceColor governanceColor;
        Governance owner;
        if ((building = getBuilding(buildingName, governance)) == null)
            return "invalid building name";
        else if (x < 0 || x > 400 || y < 0 || y > 400)
            return "invalid x,y";
        else if (!StrongHold.getCurrentPlay().getMap()[x][y].getBuildings().isEmpty())
            return "there is building";
        Ground ground = StrongHold.getCurrentPlay().getMap()[x][y];
        if ((building instanceof Farm && !(ground.getGroundType().equals(GroundType.GRASS) ||
                ground.getGroundType().equals(GroundType.DENSE_GRASSLAND))) ||
                !canDropTreeOrBuildingThere(x, y) || ground.getGroundTreeType() == null)
            return "can't drop building there";
        else {
            ground.addBuilding(building);
            governance.addBuilding(building);
            return "drop building was successful";
        }

    }

    public String dropUnit(int x, int y, String unitName, int count, Governance governance) {
        x = x - 1;
        y = y - 1;
        EuropeanTroopEnum europeanTroopEnum = EuropeanTroopEnum.getEuropeanTroopEnumByName(unitName);
        ArabianTroopEnum arabianTroopEnum = ArabianTroopEnum.getArabianTroopEnumByName(unitName);
        EngineerTroopEnum engineerTroopEnum = EngineerTroopEnum.getEngineerTroopEnumByName(unitName);
        if (x < 0 || x > 400 || y < 0 || y > 400)
            return "invalid x,y";
        else if (count < 1)
            return "invalid count";
        else if (engineerTroopEnum == null && arabianTroopEnum == null && europeanTroopEnum == null)
            return "invalid unit name";
        else if (!canDropUnitThere(x, y))
            return "can't drop unit there";
        else {
            if (europeanTroopEnum != null)
                for (int i = 0; i < count; i++)
                    StrongHold.getCurrentPlay().getMap()[x][y].addTroop(EuropeanTroopEnum.getEuropeanTroop(europeanTroopEnum, governance));
            if (arabianTroopEnum != null)
                for (int i = 0; i < count; i++)
                    StrongHold.getCurrentPlay().getMap()[x][y].addTroop(ArabianTroopEnum.getArabianTroop(arabianTroopEnum, governance));
            if (engineerTroopEnum != null)
                for (int i = 0; i < count; i++)
                    StrongHold.getCurrentPlay().getMap()[x][y].addTroop(EngineerTroopEnum.getEngineerTroop(engineerTroopEnum, governance));
            return "drop unit was successful";
        }
    }

    private boolean canDropUnitThere(int x, int y) {
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

    private boolean canDropTreeOrBuildingThere(int x, int y) {
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


    private Building getBuilding(String buildingName, Governance governance) {
        CastlesEnum castlesEnum = CastlesEnum.getCastlesEnumByName(buildingName);
        FarmEnum farmEnum = FarmEnum.getFarmEnumByName(buildingName);
        FoodProcessingEnum foodProcessingEnum = FoodProcessingEnum.getFoodProcessingEnumByName(buildingName);
        IndustryEnum industryEnum = IndustryEnum.getIndustryEnumByName(buildingName);
        TownEnum townEnum = TownEnum.getTownEnumByName(buildingName);
        WeaponsEnum weaponsEnum = WeaponsEnum.getWeaponsEnumByName(buildingName);
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
        else if (weaponsEnum != null)
            return WeaponsEnum.getWeapons(weaponsEnum, governance);
        else
            return null;
    }
}

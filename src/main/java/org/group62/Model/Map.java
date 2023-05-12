package org.group62.model;

public class Map {
    private Ground[][] map = new Ground[400][400];

    public Map() {
        for (Ground[] grounds : map)
            for (Ground ground : grounds)
                ground = new Ground();
    }

    public Ground[][] getMap() {
        return map;
    }

    public static Ground[][] getMap1(Governance g1, Governance g2, Governance g3, Governance g4) {
        Ground[][] map = new Ground[400][400];
        for (Ground[] grounds : map)
            for (Ground ground : grounds)
                ground = new Ground();
        setKeep(map, g1, 0, 0);
        setStockpile(map, g1, 0, 1);
        setKeep(map, g2, 0, 399);
        setStockpile(map, g2, 0, 398);
        setKeep(map, g3, 399, 0);
        setStockpile(map, g3, 399, 1);
        setKeep(map, g4, 399, 399);
        setStockpile(map, g4, 399, 398);
        setGroundWater(map, GroundWaterType.SHALLOW_WATER, 190, 0, 210, 399);
        setGroundWater(map, GroundWaterType.RIVER, 190, 0, 210, 10);
        setGroundWater(map, GroundWaterType.RIVER, 190, 389, 210, 399);
        setGroundLand(map, GroundLandType.ROCK, 0, 200, 10, 210);
        setGroundLand(map, GroundLandType.ROCK, 389, 200, 399, 210);
        setGroundLand(map, GroundLandType.GRASS, 0, 0, 40, 40);
        setGroundLand(map, GroundLandType.GRASS, 0, 359, 40, 399);
        setGroundLand(map, GroundLandType.GRASS, 359, 0, 399, 40);
        setGroundLand(map, GroundLandType.GRASS, 359, 359, 399, 399);
        setGroundLand(map, GroundLandType.STONE, 41, 0, 50, 10);
        setGroundLand(map, GroundLandType.IRON, 41, 11, 50, 20);
        setGroundLand(map, GroundLandType.STONE, 41, 389, 50, 399);
        setGroundLand(map, GroundLandType.IRON, 41, 379, 50, 388);
        setGroundLand(map, GroundLandType.STONE,349, 0, 358, 10);
        setGroundLand(map, GroundLandType.IRON, 349, 11, 358, 20);
        setGroundLand(map, GroundLandType.STONE, 349, 389, 358, 399);
        setGroundLand(map, GroundLandType.IRON, 349, 379, 358, 388);
        setGroundTree(map, GroundTreeType.CHERRY,0,10,10,20);
        setGroundTree(map, GroundTreeType.CHERRY,0,379,10,389);
        setGroundTree(map, GroundTreeType.CHERRY,379,10,389,20);
        setGroundTree(map, GroundTreeType.CHERRY,379,379,389,389);
        return map;
    }

    private static void setGroundWater(Ground[][] map, GroundWaterType groundWaterType, int x1, int y1, int x2, int y2) {
        for (int i = x1; i < x2; i++)
            for (int j = y1; j < y2; j++)
                map[i][j].setGroundWaterType(groundWaterType);
    }

    private static void setGroundTree(Ground[][] map, GroundTreeType groundTreeType, int x1, int y1, int x2, int y2) {
        for (int i = x1; i < x2; i++)
            for (int j = y1; j < y2; j++)
                map[i][j].setGroundTreeType(groundTreeType);
    }

    private static void setGroundLand(Ground[][] map, GroundLandType groundLandType, int x1, int y1, int x2, int y2) {
        for (int i = x1; i < x2; i++)
            for (int j = y1; j < y2; j++)
                map[i][j].setGroundLandType(groundLandType);
    }

    private static void setKeep(Ground[][] map, Governance governance, int x, int y) {
        Building building = CastlesEnum.getCastles(CastlesEnum.KEEP);
        governance.addBuilding(building);
        map[x][y].addBuilding(building);
    }

    private static void setStockpile(Ground[][] map, Governance governance, int x, int y) {
        Building building = IndustryEnum.getIndustry(IndustryEnum.STOCKPILE);
        governance.addBuilding(building);
        map[x][y].addBuilding(building);
    }
}

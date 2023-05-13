package org.group62.model;

public class Map {
    public static Ground[][] getMap1(Governance g1, Governance g2, Governance g3, Governance g4) {
        Ground[][] map = new Ground[400][400];
        for (int i = 0; i < 400; i++) {
            for (int j = 0; j < 400; j++) {
                map[i][j] = new Ground(i,j);
            }
        }
        setKeep(map, g1, 0, 0);
        setStockpile(map, g1, 0, 1);
        setKeep(map, g2, 0, 399);
        setStockpile(map, g2, 0, 398);
        setKeep(map, g3, 399, 0);
        setStockpile(map, g3, 399, 1);
        setKeep(map, g4, 399, 399);
        setStockpile(map, g4, 399, 398);
        setGround(map, GroundType.SHALLOW_WATER, 190, 0, 210, 399);
        setGround(map, GroundType.RIVER, 190, 0, 210, 10);
        setGround(map, GroundType.RIVER, 190, 389, 210, 399);
        setGround(map, GroundType.ROCK, 0, 200, 10, 210);
        setGround(map, GroundType.ROCK, 389, 200, 399, 210);
        setGround(map, GroundType.GRASS, 0, 0, 40, 40);
        setGround(map, GroundType.GRASS, 0, 359, 40, 399);
        setGround(map, GroundType.GRASS, 359, 0, 399, 40);
        setGround(map, GroundType.GRASS, 359, 359, 399, 399);
        setGround(map, GroundType.STONE, 41, 0, 50, 10);
        setGround(map, GroundType.IRON, 41, 11, 50, 20);
        setGround(map, GroundType.STONE, 41, 389, 50, 399);
        setGround(map, GroundType.IRON, 41, 379, 50, 388);
        setGround(map, GroundType.STONE, 349, 0, 358, 10);
        setGround(map, GroundType.IRON, 349, 11, 358, 20);
        setGround(map, GroundType.STONE, 349, 389, 358, 399);
        setGround(map, GroundType.IRON, 349, 379, 358, 388);
        setGroundTree(map, GroundTreeType.CHERRY, 0, 10, 10, 20);
        setGroundTree(map, GroundTreeType.CHERRY, 0, 379, 10, 389);
        setGroundTree(map, GroundTreeType.CHERRY, 379, 10, 389, 20);
        setGroundTree(map, GroundTreeType.CHERRY, 379, 379, 389, 389);
        return map;
    }

    public static void setGround(Ground[][] map, GroundType groundType, int x1, int y1, int x2, int y2) {
        for (int i = x1; i < x2; i++)
            for (int j = y1; j < y2; j++)
                map[i][j].setGroundType(groundType);
    }

    public static void setGround(Ground[][] map, GroundType groundType, int x, int y) {
        map[x][y].setGroundType(groundType);
    }

    public static void setGroundTree(Ground[][] map, GroundTreeType groundTreeType, int x1, int y1, int x2, int y2) {
        for (int i = x1; i < x2; i++)
            for (int j = y1; j < y2; j++)
                map[i][j].setGroundTreeType(groundTreeType);
    }

    public static void setGroundTree(Ground[][] map, GroundTreeType groundTreeType, int x, int y) {
        map[x][y].setGroundTreeType(groundTreeType);
    }

    public static void setKeep(Ground[][] map, Governance governance, int x, int y) {
        Building building = CastlesEnum.getCastles(CastlesEnum.KEEP);
        governance.addBuilding(building);
        map[x][y].addBuilding(building);
    }

    public static void setStockpile(Ground[][] map, Governance governance, int x, int y) {
        Building building = IndustryEnum.getIndustry(IndustryEnum.STOCKPILE);
        governance.addBuilding(building);
        map[x][y].addBuilding(building);
    }
}

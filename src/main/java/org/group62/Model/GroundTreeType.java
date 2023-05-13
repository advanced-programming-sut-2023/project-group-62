package org.group62.model;

public enum GroundTreeType {
    DESERT_SHRUB,
    CHERRY,
    OLIVE,
    COCONUT,
    DATE;

    public static GroundTreeType getGroundTreeType(String groundTreeType) {
        switch (groundTreeType) {
            case "desert shrub":
                return GroundTreeType.DESERT_SHRUB;
            case "cherry":
                return GroundTreeType.CHERRY;
            case "olive":
                return GroundTreeType.OLIVE;
            case "coconut":
                return GroundTreeType.COCONUT;
            case "date":
                return GroundTreeType.DATE;
            default:
                return null;
        }
    }
    public static boolean canDropTreeThere(int x, int y) {
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

}

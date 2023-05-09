package org.group62.Model;

public enum GroundType {
    // dirt
    LAND,
    LAND_WITH_PEBBLES,
    STONE, // it use for wall
    ROCK, // can't pass from that
    IRON,
    GRASS, // can build farm
    MEADOW, // can't build farm
    DENSE_GRASSLAND, // can build farm
    // water
    OIL,
    PLAIN,
    SHALLOW_WATER,
    RIVER,
    SMALL_POND,
    BIG_POND,
    BEACH,
    SEA;

    public static GroundType getGroundType(String groundType) {
        switch (groundType) {
            case "land":
                return GroundType.LAND;
            case "land with pebbles":
                return GroundType.LAND_WITH_PEBBLES;
            case "stone":
                return GroundType.STONE;
            case "rock":
                return GroundType.ROCK;
            case "iron":
                return GroundType.IRON;
            case "grass":
                return GroundType.GRASS;
            case "meadow":
                return GroundType.MEADOW;
            case "dense grassland":
                return GroundType.DENSE_GRASSLAND;
            case "oil":
                return GroundType.OIL;
            case "plain":
                return GroundType.PLAIN;
            case "shallow water":
                return GroundType.SHALLOW_WATER;
            case "river":
                return GroundType.RIVER;
            case "small pond":
                return GroundType.SMALL_POND;
            case "big pond":
                return GroundType.BIG_POND;
            case "beach":
                return GroundType.BEACH;
            case "sea":
                return GroundType.SEA;
            default:
                return null;

        }
    }
    public static String getAbbreviationGroundType(GroundType groundType) {
        switch (groundType) {
            case LAND:
                return "L";
            case LAND_WITH_PEBBLES:
                return "LP";
            case STONE:
                return "ST";
            case ROCK:
                return "R";
            case IRON:
                return "I";
            case GRASS:
                return "G";
            case MEADOW:
                return "M";
            case DENSE_GRASSLAND:
                return "DG";
            case OIL:
                return "O";
            case PLAIN:
                return "P";
            case SHALLOW_WATER:
                return "SW";
            case RIVER:
                return "RI";
            case SMALL_POND:
                return "SP";
            case BIG_POND:
                return "BP";
            case BEACH:
                return "B";
            case SEA:
                return "SE";
            default:
                return null;

        }
    }
}

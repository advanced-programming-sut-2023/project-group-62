package org.group62.model;

public enum Resource {
    WOOD, STONE, IRON, PITCH, BEER, FLOUR, WHEAT, BARLEY;
    public static Resource getResourceByName(String resourceType){
        switch (resourceType){
            case "wood":
                return WOOD;
            case "stone":
                return STONE;
            case "iron":
                return IRON;
            case "pitch":
                return PITCH;
            case "beer":
                return BEER;
            case "flour":
                return FLOUR;
            case "wheat":
                return WHEAT;
            case "barley":
                return BARLEY;
            default:
                return null;
        }
    }
}

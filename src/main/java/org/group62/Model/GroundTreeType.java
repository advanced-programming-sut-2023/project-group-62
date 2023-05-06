package org.group62.Model;

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

}

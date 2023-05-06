package org.group62.Model;

public enum GovernanceColor {
    RED,
    GREEN,
    BLACK,
    YELLOW,
    ORANGE,
    PINK,
    BLUE,
    PURPLE;

    public static GovernanceColor getGovernanceColor(String color) {
        switch (color) {
            case "red":
                return RED;
            case "green":
                return GREEN;
            case "black":
                return BLACK;
            case "yellow":
                return YELLOW;
            case "orange":
                return ORANGE;
            case "pink":
                return PINK;
            case "blue":
                return BLUE;
            case "purple":
                return PURPLE;
            default:
                return null;
        }
    }
}

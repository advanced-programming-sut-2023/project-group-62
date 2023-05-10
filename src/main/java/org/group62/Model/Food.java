package org.group62.Model;

public enum Food {
    APPLE, CHEESE, MEAT, BREAD;
    public static double GetAmountOfFoodPerPerson(int foodRate){
        switch (foodRate){
            case -2:
                return 0;
            case -1:
                return 0.5;
            case 0:
                return 1;
            case 1:
                return 1.5;
            case 2:
                return 2;
            default:
                return -1;
        }
    }
}

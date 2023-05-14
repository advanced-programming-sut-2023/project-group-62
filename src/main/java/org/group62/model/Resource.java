package org.group62.model;

public enum Resource {
    WOOD(25,10),
    STONE(40,20),
    IRON(60,30),
    PITCH(35,15),
    BEER(30,15),
    FLOUR(40,20),
    WHEAT(25,10),
    BARLEY(25,10);
    private int buyPrice;
    private int sellPrice;

    private Resource(int buyPrice, int sellPrice) {
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
    }

    public int getBuyPrice() {
        return buyPrice;
    }

    public int getSellPrice() {
        return sellPrice;
    }
}

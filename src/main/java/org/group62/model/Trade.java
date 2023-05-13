package org.group62.model;

public class Trade {
    private final Resource resourceType;
    private final int resourceAmount;
    private final int price;
    private final String message;

    public Trade(Resource resourceType, int resourceAmount, int price, String message) {
        this.resourceType = resourceType;
        this.resourceAmount = resourceAmount;
        this.price = price;
        this.message = message;
    }

    public Resource getResourceType() {
        return resourceType;
    }

    public int getResourceAmount() {
        return resourceAmount;
    }

    public int getPrice() {
        return price;
    }

    public String getMessage() {
        return message;
    }
}

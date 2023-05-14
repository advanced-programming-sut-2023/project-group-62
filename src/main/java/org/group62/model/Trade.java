package org.group62.model;

public class Trade {
    private final Governance owner;
    private Governance acceptedGovernance;
    private final Resource resourceType;
    private final int resourceAmount;
    private final int price;
    private final String message;
    boolean isThisUsed = false;

    public Trade(Governance governance, Resource resourceType, int resourceAmount, int price, String message) {
        this.owner = governance;
        this.resourceType = resourceType;
        this.resourceAmount = resourceAmount;
        this.price = price;
        this.message = message;
    }

    public boolean isThisUsed() {
        return isThisUsed;
    }

    public void setThisUsed(boolean thisUsed) {
        isThisUsed = thisUsed;
    }

    public Governance getOwner() {
        return owner;
    }

    public Governance getAcceptedGovernance() {
        return acceptedGovernance;
    }

    public void setAcceptedGovernance(Governance acceptedGovernance) {
        this.acceptedGovernance = acceptedGovernance;
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

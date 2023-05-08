package org.group62.Model;

public class People {
    private final Governance owner;

    public People(Governance owner) {
        this.owner = owner;
    }

    public Governance getOwner() {
        return owner;
    }
}

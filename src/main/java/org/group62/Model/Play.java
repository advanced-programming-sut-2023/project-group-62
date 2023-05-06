package org.group62.Model;

import java.util.ArrayList;

public class Play {
    private final ArrayList<ArrayList<Governance>> alies;
    private final ArrayList<Governance> governances;
    private final ArrayList<Trade> trades = new ArrayList<>();
    private final Ground[][] map;
    private static User currentUser = StrongHold.getCurrentUser();
    private static Governance currentGovernance;

    public Play(ArrayList<ArrayList<Governance>> alies, ArrayList<Governance> governances, Ground[][] map) {
        this.alies = alies;
        this.governances = governances;
        this.map = map;
    }

    public Governance getGovernanceByColor(GovernanceColor color) {
        for (Governance governance : governances) {
            if (governance.getColor().equals(color))
                return governance;
        }
        return null;
    }

    public static Governance getCurrentGovernance() {
        return currentGovernance;
    }

    public static void setCurrentGovernance(Governance currentGovernance) {
        Play.currentGovernance = currentGovernance;
    }

    public ArrayList<Governance> getGovernances() {
        return governances;
    }

    public void removeTrade(Trade trade) {
        trades.remove(trade);
    }

    public void addTrade(Trade trade) {
        trades.add(trade);
    }

    public static void setCurrentUser(User currentUser) {
        Play.currentUser = currentUser;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public ArrayList<ArrayList<Governance>> getAlies() {
        return alies;
    }

    public ArrayList<Trade> getTrades() {
        return trades;
    }

    public Ground[][] getMap() {
        return map;
    }
}

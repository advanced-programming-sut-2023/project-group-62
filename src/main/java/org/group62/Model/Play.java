package org.group62.model;

import java.util.ArrayList;

public class Play {
    private final ArrayList<ArrayList<Governance>> alies;
    private final ArrayList<Trade> trades = new ArrayList<>();
    private final Map map;
    private static User currentUser = StrongHold.getCurrentUser();

    public Play(ArrayList<ArrayList<Governance>> alies, Map map) {
        this.alies = alies;
        this.map = map;
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

    public Map getMap() {
        return map;
    }
}

package org.group62.Model;

import java.util.ArrayList;

public class Play {
    private final ArrayList<ArrayList<Governance>> alies;
    private final ArrayList<Governance> governances;
    private final ArrayList<Trade> trades = new ArrayList<>();
    private final Ground[][] map;
    private static Governance currentGovernance;

    public Play(ArrayList<ArrayList<Governance>> alies, ArrayList<Governance> governances, Ground[][] map) {
        this.alies = alies;
        this.governances = governances;
        this.map = map;
    }
    public int[] getLocationOfTroop(Troop troop) {
        int[] output = new int[2];
        for (int i = 0; i < 400; i++) {
            for (int j = 0; j < 400; j++) {
                if (!map[i][j].getTroops().isEmpty())
                    for (Troop troop1 : map[i][j].getTroops()) {
                        if (troop1.equals(troop)){
                            output[0] = i;
                            output[1] = j;
                            return output;
                        }
                    }
            }
        }
        return null;
    }
    public int[] getLocationOfBuilding(Building building) {
        int[] output = new int[2];
        for (int i = 0; i < 400; i++) {
            for (int j = 0; j < 400; j++) {
                if (!map[i][j].getBuildings().isEmpty())
                    if (map[i][j].getBuildings().get(0).equals(building)){
                        output[0] = i;
                        output[1] = j;
                        return output;
                    }
            }
        }
        return null;
    }
    public int[] getKeepLocationOfGovernance(Governance governance) {
        int[] output = new int[2];
        for (int i = 0; i < 400; i++) {
            for (int j = 0; j < 400; j++) {
                if (!map[i][j].getBuildings().isEmpty())
                    if (map[i][j].getBuildings().get(0).getOwner().equals(governance))
                        if (map[i][j].getBuildings().get(0).getName().equals("Keep")) {
                            output[0] = i;
                            output[1] = j;
                            return output;
                        }
            }
        }
        return null;
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

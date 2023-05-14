package org.group62.model;

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

    public Building getBuildingByName(String buildingName, Governance governance){
        for (int i = 0; i < 400; i++) {
            for (int j = 0; j < 400; j++) {
                if (!map[i][j].getPeople().isEmpty())
                    if (!map[i][j].getBuildings().isEmpty())
                        if (map[i][j].getBuildings().get(0).getName().equals(buildingName)) {
                            return map[i][j].getBuildings().get(0);
                        }
            }
        }
        return null;
    }
    public int[] getLocationOfPeople(People people) {
        int[] output = new int[2];
        for (int i = 0; i < 400; i++) {
            for (int j = 0; j < 400; j++) {
                if (!map[i][j].getPeople().isEmpty())
                    for (People people1 : map[i][j].getPeople()) {
                        if (people1.equals(people)) {
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
                    if (map[i][j].getBuildings().get(0).equals(building)) {
                        output[0] = i;
                        output[1] = j;
                        return output;
                    }
            }
        }
        return null;
    }

    public int howManyOfThisBuildingExistByName(String buildingName, Governance governance) {
        int output = 0;
        if (!governance.getBuildings().isEmpty())
            if (governance.getBuildings().get(0).getName().equals(buildingName))
                output++;
        return output;
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

    public void set10PeopleInEveryKeep() {
        for (Governance governance : getGovernances()) {
            int[] location = this.getKeepLocationOfGovernance(governance);
            for (int i = 0;i<10;i++){
                People people = new People(governance);
                map[location[0]][location[1]].addPeople(people);
                governance.addPeople(people);
            }

        }
    }
}

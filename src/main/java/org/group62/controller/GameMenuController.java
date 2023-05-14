package org.group62.controller;

import org.group62.model.Governance;
import org.group62.model.Map;
import org.group62.model.Play;
import org.group62.model.StrongHold;

import java.util.ArrayList;
import java.util.Arrays;

public class GameMenuController {
    public String createNewGame(Governance g1, Governance g2, Governance g3, Governance g4) {
        Play play = new Play(null, new ArrayList<Governance>(Arrays.asList(g1, g2, g3, g4)), Map.getMap1(g1, g2, g3, g4));
        play.set10PeopleInEveryKeep();
        StrongHold.setCurrentPlay(play);
        return "new game create successful";
    }
}

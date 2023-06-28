package org.group62.controller;

import org.group62.model.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class GameMenuController {
    private ArrayList<String> usernames = new ArrayList<>();
    public String newGame(String[] players,User currentUser) {
        userJsonFileParse("checkUsername",null,null,null,null);
        if(!isPlayersIsValid(players))
            return "Player Username not exist. Please set players again!";
        else{
            User player4 = new User();
            User player2 = new User();
            User player3 = new User();
            User player1 = currentUser;
            userJsonFileParse("parseUserInformation",players[0],null,player2,null);
            userJsonFileParse("parseUserInformation",players[1],null,player3,null);
            userJsonFileParse("parseUserInformation",players[2],null,player4,null);
            Governance g1 = new Governance(player1);
            Governance g2 = new Governance(player2);
            Governance g3 = new Governance(player3);
            Governance g4 = new Governance(player4);
            Play.setCurrentGovernance(g4);
            return createNewGame(g1,g2,g3,g4);
        }

    }

    private boolean isPlayersIsValid(String[] players) {
        for(String username : players){
            Boolean flag = isPlayerUsernameExist(username);
            if(!flag)
                return false;
        }
        return true;
    }

    private Boolean isPlayerUsernameExist(String username) {
        for(String user : usernames){
            if(username.equals(user))
                return true;
        }
        return false;
    }

    private void userJsonFileParse(String order, String username, String newPassword, User user, JSONArray newUserList) {
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader("database\\users.json")) {
            Object object = jsonParser.parse(reader);
            JSONArray empList = (JSONArray) object;
            userJsonFileFunctions(empList, order, username, newPassword, user, newUserList);
        } catch (FileNotFoundException e) {
            System.out.println("Database file not found!");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ParseException e) {
            System.out.println("The database file is empty! No data to check!");
        }
    }
    private void userJsonFileFunctions(JSONArray empList, String function, String username, String newPassword,
                                       User user, JSONArray newUserList) {
        switch (function) {
            case "checkUsername":
                usernames.clear();
                empList.forEach(emp -> usernameParse((JSONObject) emp));
                break;
            case "parseUserInformation":
                empList.forEach(emp -> parseUserInformation((JSONObject) emp, username, user));
                break;
        }

    }
    private void usernameParse(JSONObject emp) {
        JSONObject empobj = (JSONObject) emp.get("User");
        String username = (String) empobj.get("username");
        usernames.add(username);
    }
    private void parseUserInformation(JSONObject emp, String usernameIn, User user) {
        JSONObject empobj = (JSONObject) emp.get("User");
        String username = (String) empobj.get("username");
        if (username.equals(usernameIn)) {
            String passwordSecure = (String) empobj.get("passwordSecure");
            String email = (String) empobj.get("Email");
            String nickname = (String) empobj.get("nickname");
            String slogan = (String) empobj.get("slogan");
            String securityQuestionSecure = (String) empobj.get("securityQuestionSecure");
            String securityQuestionAnswerSecure = (String) empobj.get("securityQuestionAnswerSecure");
            String highScore = (String) empobj.get("highScore");
            user.setPassword(passwordSecure);
            user.setUsername(username);
            user.setEmail(email);
            user.setNickname(nickname);
            user.setSlogan(slogan);
            user.setPasswordRecoveryAnswer(securityQuestionAnswerSecure);
            user.setPasswordRecoveryQuestion(securityQuestionSecure);
            user.setHighScore(highScore);
        }
    }
    public String createNewGame(Governance g1, Governance g2, Governance g3, Governance g4) {
        g1.setColor(GovernanceColor.BLACK);
        g2.setColor(GovernanceColor.GREEN);
        g3.setColor(GovernanceColor.BLUE);
        g4.setColor(GovernanceColor.RED);
        Play play = new Play(null, new ArrayList<Governance>(Arrays.asList(g1, g2, g3, g4)), Map.getMap1(g1, g2, g3, g4));
        StrongHold.setCurrentPlay(play);
        play.set10PeopleInEveryKeep();
        return "new game create successful";
    }
}

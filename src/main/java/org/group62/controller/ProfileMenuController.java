package org.group62.Controller;

import org.group62.Model.StrongHold;
import org.group62.Model.User;

import java.util.regex.Matcher;
public class ProfileMenuController {
    public String changePassword(Matcher matcher) {
        return null;
    }

    public String changeUsername(Matcher matcher) {
        return null;
    }

    public String changeNickname(Matcher matcher) {
        return null;
    }

    public String changeEmail(Matcher matcher) {
        return null;
    }

    public String changeSlogan(Matcher matcher) {
        return null;
    }

    public String removeSlogan() {
        return null;
    }

    public String displayHighScore() {
        User user = StrongHold.getCurrentUser();
        return String.format("%s highscore: %d",user.getNickname(),user.getHighScore());
    }

    public String displayRank() {
        User user = StrongHold.getCurrentUser();
        return String.format("%s rank: %d",user.getNickname(),user.getRank());
    }

    public String displaySlogan() {
        User user = StrongHold.getCurrentUser();
        if (user.getSlogan() == null)
            return "Slogan is empty!";
        else
            return String.format("%s slogan: %s",user.getNickname(),user.getSlogan());
    }

    public String displayAllProfile() {
        User user = StrongHold.getCurrentUser();
        return String.format("%s\nhighscore: %d\nrank: %d\nslogan: %s",user.getNickname(),
                user.getHighScore(),user.getRank(),user.getSlogan());
    }
}

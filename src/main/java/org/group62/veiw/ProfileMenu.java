package org.group62.veiw;

import java.util.Scanner;
import java.util.regex.Matcher;

public class ProfileMenu {
    Matcher matcher;
    ProfileMenuControlle profileMenuControlle;
    public ProfileMenu(ProfileMenuController profileMenuController){
        this.profileMenuControlle = profileMenuController;
    }
    public void run(Scanner scanner){
        while (true) {
            String inputCommand = scanner.nextLine();
            if ((matcher = Commands.getMatcher(inputCommand, Commands.PROFILE_PASSWORD_CHANGE)) != null)
                changePassword(matcher);
            else if ((matcher = Commands.getMatcher(inputCommand, Commands.PROFILE_USERNAME_CHANGE)) != null)
                changeUsername(matcher);
            else if ((matcher = Commands.getMatcher(inputCommand, Commands.PROFILE_NICKNAME_CHANGE)) != null)
                changeNickname(matcher);
            else if ((matcher = Commands.getMatcher(inputCommand, Commands.PROFILE_EMAIL_CHANGE)) != null)
                changeEmail(matcher);
            else if ((matcher = Commands.getMatcher(inputCommand, Commands.PROFILE_SLOGAN_CHANGE)) != null)
                changeSlogan(matcher);
            else if ((Commands.getMatcher(inputCommand, Commands.PROFILE_REMOVE_SLOGAN)) != null)
                removeSlogan();
            else if (Commands.getMatcher(inputCommand, Commands.PROFILE_DISPLAY_HIGH_SCORE) != null)
                displayHighScore();
            else if (Commands.getMatcher(inputCommand, Commands.PROFILE_DISPLAY_RANK) != null)
                displayRank();
            else if (Commands.getMatcher(inputCommand, Commands.PROFILE_DISPLAY_SLOGAN) != null)
                displaySlogan();
            else if (Commands.getMatcher(inputCommand, Commands.PROFILE_DISPLAY) != null)
                displayAllProfile();
            else if (Commands.getMatcher(inputCommand, Commands.USER_LOGOUT) != null)
                break;
            else
                System.out.println("Invalid command!");
        }
    }

    private void displayAllProfile() {
    }

    private void displaySlogan() {
    }

    private void displayRank() {
    }

    private void displayHighScore() {
    }

    private void removeSlogan() {
    }

    private void changeSlogan(Matcher matcher) {
    }

    private void changeEmail(Matcher matcher) {
    }

    private void changeNickname(Matcher matcher) {
    }

    private void changeUsername(Matcher matcher) {
    }

    private void changePassword(Matcher matcher) {
    }
}

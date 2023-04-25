package org.group62.veiw;

import org.group62.controller.ProfileMenuController;

import java.util.Scanner;
import java.util.regex.Matcher;

public class ProfileMenu {
    Matcher matcher;
    ProfileMenuController profileMenuControlle;

    public ProfileMenu(ProfileMenuController profileMenuController) {
        this.profileMenuControlle = profileMenuController;
    }

    public void run(Scanner scanner) {
        while (true) {
            String inputCommand = scanner.nextLine();
            if ((matcher = Commands.getMatcher(inputCommand, Commands.PROFILE_PASSWORD_CHANGE)) != null)
                System.out.println(changePassword(matcher));
            else if ((matcher = Commands.getMatcher(inputCommand, Commands.PROFILE_USERNAME_CHANGE)) != null)
                System.out.println(changeUsername(matcher));
            else if ((matcher = Commands.getMatcher(inputCommand, Commands.PROFILE_NICKNAME_CHANGE)) != null)
                System.out.println(changeNickname(matcher));
            else if ((matcher = Commands.getMatcher(inputCommand, Commands.PROFILE_EMAIL_CHANGE)) != null)
                System.out.println(changeEmail(matcher));
            else if ((matcher = Commands.getMatcher(inputCommand, Commands.PROFILE_SLOGAN_CHANGE)) != null)
                System.out.println(changeSlogan(matcher));
            else if ((Commands.getMatcher(inputCommand, Commands.PROFILE_REMOVE_SLOGAN)) != null)
                System.out.println(removeSlogan());
            else if (Commands.getMatcher(inputCommand, Commands.PROFILE_DISPLAY_HIGH_SCORE) != null)
                System.out.println(displayHighScore());
            else if (Commands.getMatcher(inputCommand, Commands.PROFILE_DISPLAY_RANK) != null)
                System.out.println(displayRank());
            else if (Commands.getMatcher(inputCommand, Commands.PROFILE_DISPLAY_SLOGAN) != null)
                System.out.println(displaySlogan());
            else if (Commands.getMatcher(inputCommand, Commands.PROFILE_DISPLAY) != null)
                System.out.println(displayAllProfile());
            else if (Commands.getMatcher(inputCommand, Commands.USER_LOGOUT) != null)
                break;
            else
                System.out.println("Invalid command!");
        }
    }

    private String displayAllProfile() {
        return null;
    }

    private String displaySlogan() {
        return null;
    }

    private String displayRank() {
        return null;
    }

    private String displayHighScore() {
        return null;
    }

    private String removeSlogan() {
        return null;
    }

    private String changeSlogan(Matcher matcher) {
        return null;
    }

    private String changeEmail(Matcher matcher) {
        return null;
    }

    private String changeNickname(Matcher matcher) {
        return null;
    }

    private String changeUsername(Matcher matcher) {
        return null;
    }

    private String changePassword(Matcher matcher) {
        return null;
    }
}

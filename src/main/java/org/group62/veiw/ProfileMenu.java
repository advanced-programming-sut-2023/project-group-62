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
                System.out.println(profileMenuControlle.changePassword(matcher));
            else if ((matcher = Commands.getMatcher(inputCommand, Commands.PROFILE_USERNAME_CHANGE)) != null)
                System.out.println(profileMenuControlle.changeUsername(matcher));
            else if ((matcher = Commands.getMatcher(inputCommand, Commands.PROFILE_NICKNAME_CHANGE)) != null)
                System.out.println(profileMenuControlle.changeNickname(matcher));
            else if ((matcher = Commands.getMatcher(inputCommand, Commands.PROFILE_EMAIL_CHANGE)) != null)
                System.out.println(profileMenuControlle.changeEmail(matcher));
            else if ((matcher = Commands.getMatcher(inputCommand, Commands.PROFILE_SLOGAN_CHANGE)) != null)
                System.out.println(profileMenuControlle.changeSlogan(matcher));
            else if ((Commands.getMatcher(inputCommand, Commands.PROFILE_REMOVE_SLOGAN)) != null)
                System.out.println(profileMenuControlle.removeSlogan());
            else if (Commands.getMatcher(inputCommand, Commands.PROFILE_DISPLAY_HIGH_SCORE) != null)
                System.out.println(profileMenuControlle.displayHighScore());
            else if (Commands.getMatcher(inputCommand, Commands.PROFILE_DISPLAY_RANK) != null)
                System.out.println(profileMenuControlle.displayRank());
            else if (Commands.getMatcher(inputCommand, Commands.PROFILE_DISPLAY_SLOGAN) != null)
                System.out.println(profileMenuControlle.displaySlogan());
            else if (Commands.getMatcher(inputCommand, Commands.PROFILE_DISPLAY) != null)
                System.out.println(profileMenuControlle.displayAllProfile());
            else if (Commands.getMatcher(inputCommand, Commands.USER_LOGOUT) != null)
                break;
            else
                System.out.println("Invalid command!");
        }
    }
}

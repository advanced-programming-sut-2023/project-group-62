package org.group62.veiw;

import org.group62.controller.GameMenuController;
import org.group62.controller.MainMenuController;
import org.group62.controller.ProfileMenuController;
import org.group62.model.User;

import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import java.util.regex.Matcher;

public class MainMenu {
    ProfileMenuController profileMenuController;
    GameMenuController gameMenuController;
    MainMenuController mainMenuController;
    Matcher matcher;
    private User currentUser;

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public MainMenu(MainMenuController mainMenuController) {
        this.mainMenuController = mainMenuController;
        gameMenuController = new GameMenuController();
        profileMenuController = new ProfileMenuController();
    }

    public void run(Scanner scanner) throws NoSuchAlgorithmException {

        while (true) {
            String inputCommand = scanner.nextLine();
            if (Commands.getMatcherMatches(inputCommand, Commands.ENTER_PROFILE_MENU) != null) {
                ProfileMenu profileMenu = new ProfileMenu(profileMenuController);
                profileMenu.setCurrentUser(currentUser);
                System.out.println("You are in the profile menu!");
                profileMenu.run(scanner);
            } else if (Commands.getMatcherMatches(inputCommand, Commands.ENTER_GAME_MENU) != null) {
                GameMenu gameMenu = new GameMenu(gameMenuController);
                gameMenu.setCurrentUser(currentUser);
                System.out.println("You are in the game menu!");
                gameMenu.run(scanner);
            } else if (Commands.getMatcherMatches(inputCommand, Commands.USER_LOGOUT) != null) {
                System.out.println("user logged out successfully!");
                mainMenuController.setLogout(1);
                break;
            }
            else if(Commands.getMatcherMatches(inputCommand,Commands.SHOW_CURRENT_MENU) != null)
                System.out.println("Current menu is Main manu!");
            else
                System.out.println("Invalid command!");
        }
    }
}

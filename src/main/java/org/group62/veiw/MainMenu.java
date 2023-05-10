package org.group62.veiw;

import org.group62.controller.GameMenuController;
import org.group62.controller.MainMenuController;
import org.group62.controller.ProfileMenuController;

import java.util.Scanner;
import java.util.regex.Matcher;

public class MainMenu {
    ProfileMenuController profileMenuController;
    GameMenuController gameMenuController;
    MainMenuController mainMenuController;
    Matcher matcher;

    public MainMenu(MainMenuController mainMenuController) {
        this.mainMenuController = mainMenuController;
        gameMenuController = new GameMenuController();
        profileMenuController = new ProfileMenuController();
    }

    public void run(Scanner scanner) {
        while (true) {
            String inputCommand = scanner.nextLine();
            if (Commands.getMatcherMatches(inputCommand, Commands.ENTER_PROFILE_MENU) != null) {
                ProfileMenu profileMenu = new ProfileMenu(profileMenuController);
                profileMenu.run(scanner);
            } else if (Commands.getMatcherMatches(inputCommand, Commands.ENTER_GAME_MENU) != null) {
                GameMenu gameMenu = new GameMenu(gameMenuController);
                gameMenu.run(scanner);
            } else if (Commands.getMatcherMatches(inputCommand, Commands.BACK) != null)
                break;
            else
                System.out.println("Invalid command!");
        }
    }
}

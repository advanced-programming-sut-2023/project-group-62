package org.group62.veiw;

import java.util.Scanner;
import java.util.regex.Matcher;

public class LoginMenu {
    Matcher matcher;
    LoginMenuController loginMenuController;
    MainMenuController mainMenuController;

    public LoginMenu(LoginMenuController loginMenuController) {
        this.loginMenuController = loginMenuController;
        mainMenuController = new MainMenuController();
    }

    public void run(Scanner scanner) {
        while (true) {
            String inputCommand = scanner.nextLine();
            if ((matcher = Commands.getMatcher(inputCommand, Commands.USER_LOGIN)) != null) {
                loginWithoutStayLoggedIn(matcher, scanner);
                break;
            } else if ((matcher = Commands.getMatcher(inputCommand, Commands.USER_STAY_LOGGED_IN)) != null) {
                loginWithStayLoggedIn(matcher, scanner);
                break;
            } else if ((matcher = Commands.getMatcher(inputCommand, Commands.FORGOT_MY_PASSWORD)) != null)
                System.out.println(forgotPassword(matcher));
            else
                System.out.println("Invalid command!");

        }
    }

    private String forgotPassword(Matcher matcher) {
        return null;
    }

    private void loginWithStayLoggedIn(Matcher matcher, Scanner scanner) {
        //TODO if success then goto to mainMenu;
        MainMenu mainMenu = new MainMenu(mainMenuController);
        mainMenu.run(scanner);
    }

    private void loginWithoutStayLoggedIn(Matcher matcher, Scanner scanner) {
        //TODO if success then go to mainMenu
        MainMenu mainMenu = new MainMenu(mainMenuController);
        mainMenu.run(scanner);
    }

}

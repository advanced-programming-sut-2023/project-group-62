package org.group62.veiw;
import org.group62.controller.LoginMenuController;
import org.group62.controller.MainMenuController;
import org.group62.model.User;

import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import java.util.regex.Matcher;

public class LoginMenu {
    private Matcher matcher;
    private LoginMenuController loginMenuController;
    private MainMenuController mainMenuController;
    private User currentUser;

    private Integer timerFactor = 1;
    public LoginMenu(LoginMenuController loginMenuController) {
        this.loginMenuController = loginMenuController;
        mainMenuController = new MainMenuController();
    }

    public void run(Scanner scanner) {
        timerFactor = 1;
        while (true) {
            String inputCommand = scanner.nextLine();
            if ((matcher = Commands.getMatcherMatches(inputCommand, Commands.USER_LOGIN)) != null) {
                loginWithoutStayLoggedIn(matcher, scanner,timerFactor);
                timerFactor++;
            } else if ((matcher = Commands.getMatcherMatches(inputCommand, Commands.USER_STAY_LOGGED_IN)) != null) {
                loginWithStayLoggedIn(matcher, scanner);

            } else if ((matcher = Commands.getMatcherMatches(inputCommand, Commands.FORGOT_MY_PASSWORD)) != null)
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

    private void loginWithoutStayLoggedIn(Matcher matcher, Scanner scanner,Integer timerFactor) throws NoSuchAlgorithmException, InterruptedException {
        String message = loginMenuController.loginWithoutStayLoggedIn(matcher,timerFactor);
        MainMenu mainMenu = new MainMenu(mainMenuController);
        mainMenu.run(scanner);
    }

}

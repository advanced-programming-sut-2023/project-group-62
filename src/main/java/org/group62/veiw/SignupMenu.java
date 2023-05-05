package org.group62.veiw;

import org.group62.Controller.LoginMenuController;
import org.group62.Controller.SignupMenuController;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import java.util.regex.Matcher;

public class SignupMenu {
    SignupMenuController signupMenuController;
    LoginMenuController loginMenuController;

    public SignupMenu(SignupMenuController signupMenuController) {
        this.signupMenuController = signupMenuController;
        loginMenuController = new LoginMenuController();
    }

    Scanner scanner = new Scanner(System.in);
    Matcher matcher;
    String inputCommand;

    public void run() throws NoSuchAlgorithmException, IOException {
        while (true) {
            inputCommand = scanner.nextLine();
            if ((matcher = Commands.getMatcher(inputCommand, Commands.CREAT_USER_WITH_SLOGAN)) != null)
                System.out.println(signupMenuController.normalCreatUser(matcher));
            else if ((matcher = Commands.getMatcher(inputCommand, Commands.CREAT_USER_WITHOUT_SLOGAN)) != null)
                System.out.println(signupMenuController.creatUserWithoutSlogan(matcher));
            else if ((matcher = Commands.getMatcher(inputCommand, Commands.CREAT_USER_WITH_RANDOM_PASSWORD)) != null)
                System.out.println(signupMenuController.creatUserWithRandomPassword(matcher));
            else if ((matcher = Commands.getMatcher(inputCommand, Commands.CREAT_USER_WITH_RANDOM_SLOGAN)) != null)
                System.out.println(signupMenuController.creatUserWithRandomSlogan(matcher));
            else if ((matcher = Commands.getMatcher(inputCommand, Commands.CREAT_USER_WITH_RANDOM_SLOGAN_AND_RANDOM_PASSWORD)) != null)
                System.out.println(signupMenuController.creatUserWithRandomPasswordAndSlogan(matcher));
            else if ((matcher = Commands.getMatcher(inputCommand, Commands.ENTER_LOGIN_MENU)) != null) {
                LoginMenu loginMenu = new LoginMenu(loginMenuController);
                loginMenu.run(scanner);
            } else if (Commands.getMatcher(inputCommand, Commands.EXIT) != null)
                break;
            else
                System.out.println("Invalid command!");


        }
    }
}

package org.group62.veiw;

import org.group62.controller.LoginMenuController;
import org.group62.controller.SignupMenuController;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import java.util.regex.Matcher;

public class SignupMenu {
    SignupMenuController signupMenuController;
    LoginMenuController loginMenuController;
    Scanner scanner;

    public SignupMenu(SignupMenuController signupMenuController,Scanner scanner) {
        this.scanner = scanner;
        this.signupMenuController = signupMenuController;
        loginMenuController = new LoginMenuController();
    }

    Matcher matcher;
    String inputCommand;

    public void run() throws IOException {
        while (true) {
            inputCommand = scanner.nextLine();
            if ((matcher = Commands.getMatcherMatches(inputCommand, Commands.CREAT_USER_WITH_SLOGAN)) != null)
                createUserPrintResult(true);
            else if ((matcher = Commands.getMatcherMatches(inputCommand, Commands.CREAT_USER_WITHOUT_SLOGAN)) != null)
                createUserPrintResult(false);
            else if ((matcher = Commands.getMatcherMatches(inputCommand, Commands.CREAT_USER_WITH_RANDOM_PASSWORD)) != null)
                System.out.println(signupMenuController.creatUserWithRandomPassword(matcher));
            else if ((matcher = Commands.getMatcherMatches(inputCommand, Commands.CREAT_USER_WITH_RANDOM_SLOGAN)) != null)
                System.out.println(signupMenuController.creatUserWithRandomSlogan(matcher));
            else if ((matcher = Commands.getMatcherMatches(inputCommand, Commands.CREAT_USER_WITH_RANDOM_SLOGAN_AND_RANDOM_PASSWORD)) != null)
                System.out.println(signupMenuController.creatUserWithRandomPasswordAndSlogan(matcher));
            else if ((matcher = Commands.getMatcherMatches(inputCommand, Commands.ENTER_LOGIN_MENU)) != null) {
                LoginMenu loginMenu = new LoginMenu(loginMenuController);
                loginMenu.run(scanner);
            } else if (Commands.getMatcherMatches(inputCommand, Commands.EXIT) != null)
                break;
            else
                System.out.println("Invalid command!");


        }
    }
    private void createUserPrintResult(boolean flag) throws IOException {
        String feedback;
        if(flag)
            System.out.println(feedback = signupMenuController.normalCreatUser(matcher));
        else
            System.out.println(feedback = signupMenuController.creatUserWithoutSlogan(matcher));
        if(feedback.startsWith("Creat user successful!")){
            Matcher pickQuestionMatcher;
            String message = null;
            while (true) {
                inputCommand = scanner.nextLine();
                if((pickQuestionMatcher = Commands.getMatcherMatches(inputCommand,Commands.QUESTION_PICK)) != null) {
                    if(!(message = signupMenuController.pickSecurityQuestion(flag, matcher, pickQuestionMatcher)).equals("Picked successfully!")) {
                        System.out.println(message);
                        continue;
                    }else {
                        System.out.println(message);
                        break;
                    }
                }
                else
                    System.out.println("Pick Question failed: input Command invalid!" +
                            " Please see help line and command again.");
            }

        }
    }
}

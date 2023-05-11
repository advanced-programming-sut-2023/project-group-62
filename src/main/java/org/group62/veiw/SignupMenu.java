package org.group62.veiw;

import org.group62.controller.LoginMenuController;
import org.group62.controller.SignupMenuController;

import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;

public class SignupMenu {
    SignupMenuController signupMenuController;
    LoginMenuController loginMenuController;
    Scanner scanner;

    public SignupMenu(SignupMenuController signupMenuController, Scanner scanner) {
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
                createUserWithRandomPassword(matcher);
            else if ((matcher = Commands.getMatcherMatches(inputCommand, Commands.CREAT_USER_WITH_RANDOM_SLOGAN)) != null)
                createUserWithRandomSlogan(matcher);
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

    private void createUserWithRandomSlogan(Matcher matcher) {
        String feedback;
        String randomSlogan = signupMenuController.randomSloganGenerator();
        System.out.println(feedback = signupMenuController.creatUserWithRandomSlogan(matcher));
        if (feedback.startsWith("Creat user successful!")) {
            Matcher pickQuestionMatcher;
            String message = null;
            while (true) {
                inputCommand = scanner.nextLine();
                if ((pickQuestionMatcher = Commands.getMatcherMatches(inputCommand, Commands.QUESTION_PICK)) != null) {
                    if (!(message = signupMenuController.normalPickSecurityQuestion(false, matcher, pickQuestionMatcher, randomSlogan)).equals("Picked successfully!")) {
                        System.out.println(message);
                        continue;
                    } else {
                        System.out.println(message);
                        break;
                    }
                } else
                    System.out.println("Pick Question failed: input Command invalid!" +
                            " Please see help line and command again.");
            }

        }
    }


    private void createUserWithRandomPassword(Matcher matcher) {
        String message = null;
        System.out.println(message = signupMenuController.creatUserWithRandomPassword(matcher));
        if (message.startsWith("Your random password is:")) {
            String randomPassword = "";
            for (int i = 25; i < 33; i++)
                randomPassword += message.charAt(i);
            String userPassword = scanner.nextLine();
            String errorMessage = null;
            while (true) {
                errorMessage = signupMenuController.isRandomPasswordEqualUserPassword(randomPassword, userPassword);
                if (errorMessage.equals("successful")) {
                    Matcher pickQuestionMatcher;
                    String pickMessage = null;
                    String firstSecurityQuestion = "1.What is your first school's name?";
                    String secondSecurityQuestion = "2.What is your favorite car?";
                    String thirdSecurityQuestion = "3.When is your birthday?";
                    System.out.println("Creat user successful!\n" +
                            "Pick your security question:\n" +
                            firstSecurityQuestion + "\n" +
                            secondSecurityQuestion + "\n" +
                            thirdSecurityQuestion + "\n" +
                            "Help: question pick -q <question-number> -a <answer> -c <answer-confirm>");
                    while (true) {
                        inputCommand = scanner.nextLine();
                        if ((pickQuestionMatcher = Commands.getMatcherMatches(inputCommand, Commands.QUESTION_PICK)) != null) {
                            if (!(pickMessage = signupMenuController.randomPasswordPickSecurityQuestion(matcher,
                                    pickQuestionMatcher, randomPassword)).equals("Picked successfully!")) {
                                System.out.println(pickMessage);
                                continue;
                            } else {
                                System.out.println(pickMessage);
                                break;
                            }
                        } else
                            System.out.println("Pick Question failed: input Command invalid!" +
                                    " Please see help line and command again.");
                    }
                } else if (errorMessage.equals("error"))
                    System.out.println("Password confirmation failed! Please re-enter your password.");
                userPassword = scanner.nextLine();

            }
        }

    }

    private void createUserPrintResult(boolean flag) throws IOException {
        String feedback;
        if (flag)
            System.out.println(feedback = signupMenuController.normalCreatUser(matcher));
        else
            System.out.println(feedback = signupMenuController.creatUserWithoutSlogan(matcher));
        if (feedback.startsWith("Creat user successful!")) {
            Matcher pickQuestionMatcher;
            String message = null;
            while (true) {
                inputCommand = scanner.nextLine();
                if ((pickQuestionMatcher = Commands.getMatcherMatches(inputCommand, Commands.QUESTION_PICK)) != null) {
                    if (!(message = signupMenuController.normalPickSecurityQuestion(flag, matcher, pickQuestionMatcher, "User has no slogan")).equals("Picked successfully!")) {
                        System.out.println(message);
                        continue;
                    } else {
                        System.out.println(message);
                        break;
                    }
                } else
                    System.out.println("Pick Question failed: input Command invalid!" +
                            " Please see help line and command again.");
            }

        }
    }
}

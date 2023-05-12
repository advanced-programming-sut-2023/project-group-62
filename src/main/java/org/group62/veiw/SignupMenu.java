package org.group62.veiw;

import org.group62.controller.LoginMenuController;
import org.group62.controller.SignupMenuController;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
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

    public void run() throws IOException, NoSuchAlgorithmException, InterruptedException {
        while (true) {
            inputCommand = scanner.nextLine();
            if ((matcher = Commands.getMatcherMatches(inputCommand, Commands.CREAT_USER_WITH_SLOGAN)) != null)
                createUserPrintResult(true);
            else if ((matcher = Commands.getMatcherMatches(inputCommand, Commands.CREAT_USER_WITHOUT_SLOGAN)) != null)
                createUserPrintResult(false);
            else if ((matcher = Commands.getMatcherMatches(inputCommand,
                    Commands.CREAT_USER_WITH_RANDOM_PASSWORD_WITH_SLOGAN)) != null)
                createUserWithRandomPasswordAndSlogan(matcher,true,false);
            else if((matcher = Commands.getMatcherMatches(inputCommand,
                    Commands.CREAT_USER_WITH_RANDOM_PASSWORD_WITHOUT_SLOGAN)) !=null)
                createUserWithRandomPasswordAndSlogan(matcher,false,false);
            else if ((matcher = Commands.getMatcherMatches(inputCommand, Commands.CREAT_USER_WITH_RANDOM_SLOGAN)) != null)
                createUserWithRandomSlogan(matcher);
            else if(Commands.getMatcherMatches(inputCommand,Commands.SHOW_CURRENT_MENU) != null)
                System.out.println("You are in the signup menu!");
            else if ((matcher = Commands.getMatcherMatches(inputCommand, Commands.ENTER_LOGIN_MENU)) != null) {
                LoginMenu loginMenu = new LoginMenu(loginMenuController);
                System.out.println("You are in the login menu!");
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
                    if (!(message = signupMenuController.normalPickSecurityQuestion(false, matcher,
                            pickQuestionMatcher, randomSlogan)).equals("Picked successfully!")) {
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


    private void createUserWithRandomPasswordAndSlogan(Matcher matcher, Boolean isSloganExist,Boolean isSloganRandom) {
        if(matcher.group("slogan").equals("random")) {
            isSloganExist = false;
            isSloganRandom = true;
        }
        String randomSlogan = "";
        String message = null;
        String messageCopy = null;
        message = signupMenuController.creatUserWithRandomPassword(matcher,isSloganExist);
        messageCopy = message;
        if(isSloganRandom) {
            randomSlogan = signupMenuController.randomSloganGenerator();
            String firstOfMessageString = "Your slogan is \"" + randomSlogan + " \"\n";
            message = firstOfMessageString + message;
        }
        System.out.println(message);
        if (messageCopy.startsWith("Your random password is:")) {
            String randomPassword = "";
            for (int i = 25; i < 33; i++)
                randomPassword += messageCopy.charAt(i);
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
                                    pickQuestionMatcher, randomPassword,isSloganExist,isSloganRandom,randomSlogan)).equals("Picked successfully!")) {
                                System.out.println(pickMessage);
                                continue;
                            } else {
                                System.out.println(pickMessage);
                                return;
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
                    if (!(message = signupMenuController.normalPickSecurityQuestion(flag, matcher, pickQuestionMatcher,
                            "User has no slogan")).equals("Picked successfully!")) {
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

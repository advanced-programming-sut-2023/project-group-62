package org.group62.veiw;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.group62.controller.LoginMenuController;
import org.group62.controller.MainMenuController;
import org.group62.controller.SHA256;
import org.group62.model.User;

import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import java.util.regex.Matcher;

public class LoginMenu extends Application {
    private Matcher matcher;
    private LoginMenuController loginMenuController;
    private MainMenuController mainMenuController;
    private User currentUser;

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
    private Integer timerFactor = 1;
    public LoginMenu(){

    }
    public LoginMenu(LoginMenuController loginMenuController) {
        this.loginMenuController = loginMenuController;
        mainMenuController = new MainMenuController();
    }

    public void run(Scanner scanner) throws NoSuchAlgorithmException, InterruptedException {
        loginMenuController.setCurrentUser(currentUser);
        mainMenuController.setLogout(10);
        timerFactor = 1;
        while (true) {
            String inputCommand = scanner.nextLine();
            if ((matcher = Commands.getMatcherMatches(inputCommand, Commands.USER_STAY_LOGGED_IN)) != null) {
                loginWithStayLoggedIn(matcher, scanner);
                timerFactor++;
                int flag = mainMenuController.getLogout();
                if(flag == 1){
                    mainMenuController.setLogout(0);
                    break;
                }
            } else if ((matcher = Commands.getMatcherMatches(inputCommand, Commands.USER_LOGIN)) != null) {
                loginWithoutStayLoggedIn(matcher, scanner);
                timerFactor++;
                int flag = mainMenuController.getLogout();
                if(flag == 1){
                    mainMenuController.setLogout(0);
                    break;
                }
            }
            else if ((matcher = Commands.getMatcherMatches(inputCommand, Commands.FORGOT_MY_PASSWORD)) != null)
                forgotPassword(matcher,scanner);
            else if(Commands.getMatcherMatches(inputCommand,Commands.BACK_TO_SIGNUP_MENU) != null){
                System.out.println("You are in signup menu!");
                break;
            }
            else if(Commands.getMatcherMatches(inputCommand,Commands.SHOW_CURRENT_MENU) != null)
                System.out.println("Current menu is login menu!");
            else
                System.out.println("Invalid command!");

        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        URL url = LoginMenu.class.getResource("/fxml/LoginMenu.fxml");
        BorderPane borderPane = FXMLLoader.load(url);
        Scene scene = new Scene(borderPane);
        stage.setScene(scene);
        stage.show();
    }

    private void forgotPassword(Matcher matcher,Scanner scanner) throws NoSuchAlgorithmException {
        String username = matcher.group("username");
        String passwordRecoveryQuestionMessage = loginMenuController.forgotPasswordReturnSecurityQuestion(username);
        if(passwordRecoveryQuestionMessage.equals("Username not found!")) {
            System.out.println(passwordRecoveryQuestionMessage);
        }else {
            String recoveryQuestion = loginMenuController.decodePasswordRecoveryQuestionMessage(passwordRecoveryQuestionMessage);
            System.out.println("To reset your password you must answer security question below: \n" +
                    recoveryQuestion);
            String correctRecoveryQuestionAnswer = loginMenuController.forgotPasswordReturnSecurityQuestionAnswer(username);
            String recoveryQuestionAnswer = scanner.nextLine();
            String userRecoveryPasswordAnswerSecure = SHA256.sha256Security(recoveryQuestionAnswer);
            if (userRecoveryPasswordAnswerSecure.equals(correctRecoveryQuestionAnswer)) {
                System.out.println("Your answer is correct. Set a new password with correct format: ");
                String message = "";
                while (!message.equals("Set a new password successful!")) {
                    String newPassword = scanner.nextLine();
                    message = loginMenuController.forgotPasswordSetNewPassword(username, newPassword);
                    System.out.println(message);
                }
            } else {
                System.out.println("Your answer is not correct. Command again!");
            }
        }
    }

    private void loginWithStayLoggedIn(Matcher matcher, Scanner scanner) throws NoSuchAlgorithmException, InterruptedException {
        String message = "";
        System.out.println(message = loginMenuController.loginWithStayLoggedIn(matcher));
        if(message.equals("Error: Incorrect password!")) {
            printTimer();
            mainMenuController.setLogout(0);
            return;
        }
        else if(message.equals("User logged in successfully")){
            MainMenu mainMenu = new MainMenu(mainMenuController);
            mainMenu.setCurrentUser(currentUser);
            mainMenu.run(scanner);
        }
        timerFactor = 1;
    }

    private void loginWithoutStayLoggedIn(Matcher matcher, Scanner scanner) throws NoSuchAlgorithmException, InterruptedException {
        String message = loginMenuController.loginWithoutStayLoggedIn(matcher);
        System.out.println(message);
        if(message.equals("Username and password didn’t match! --> Password is incorrect!")){
            printTimer();
            mainMenuController.setLogout(0);
            return;
        }
        timerFactor = 1;
        MainMenu mainMenu = new MainMenu(mainMenuController);
        mainMenu.setCurrentUser(currentUser);
        mainMenu.run(scanner);
    }
    private void printTimer() throws InterruptedException {
        int timer = timerFactor * 5;
        while (timer != -1) {
            System.out.println("You can enter password in " + timer + " seconds!");
            timer--;
            Thread.sleep(1000);
            if(timer == 0) {
                System.out.println("Now you can try one more!");
                break;
            }
        }
    }


}

package org.group62.veiw;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.group62.controller.ProfileMenuController;
import org.group62.model.User;

import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import java.util.regex.Matcher;

public class ProfileMenu extends Application {
    Matcher matcher;
    ProfileMenuController profileMenuController;
    private User currentUser;

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public ProfileMenu(){}

    public ProfileMenu(ProfileMenuController profileMenuController) {
        this.profileMenuController = profileMenuController;
    }

    public void run(Scanner scanner) throws NoSuchAlgorithmException {
        profileMenuController.setCurrentUser(currentUser);
        while (true) {
            String inputCommand = scanner.nextLine();
            if ((matcher = Commands.getMatcherMatches(inputCommand, Commands.PROFILE_PASSWORD_CHANGE)) != null)
                changePassword(matcher, scanner);
            else if ((matcher = Commands.getMatcherMatches(inputCommand, Commands.PROFILE_USERNAME_CHANGE)) != null)
                changeUsername(matcher);
            else if ((matcher = Commands.getMatcherMatches(inputCommand, Commands.PROFILE_NICKNAME_CHANGE)) != null)
                changeNickname(matcher);
            else if ((matcher = Commands.getMatcherMatches(inputCommand, Commands.PROFILE_EMAIL_CHANGE)) != null)
                changeEmail(matcher);
            else if ((matcher = Commands.getMatcherMatches(inputCommand, Commands.PROFILE_SLOGAN_CHANGE)) != null)
                changeSlogan(matcher);
            else if ((Commands.getMatcherMatches(inputCommand, Commands.PROFILE_REMOVE_SLOGAN)) != null)
                removeSlogan();
            else if (Commands.getMatcherMatches(inputCommand, Commands.PROFILE_DISPLAY_HIGH_SCORE) != null)
                System.out.println(profileMenuController.displayHighScore());
            else if (Commands.getMatcherMatches(inputCommand, Commands.PROFILE_DISPLAY_RANK) != null)
                System.out.println(profileMenuController.displayRank());
            else if (Commands.getMatcherMatches(inputCommand, Commands.PROFILE_DISPLAY_SLOGAN) != null)
                System.out.println(profileMenuController.displaySlogan());
            else if (Commands.getMatcherMatches(inputCommand, Commands.PROFILE_DISPLAY) != null)
                System.out.println(profileMenuController.displayAllProfile());
            else if (Commands.getMatcherMatches(inputCommand, Commands.SHOW_CURRENT_MENU) != null)
                System.out.println("Current menu is profile menu!");
            else if (Commands.getMatcherMatches(inputCommand, Commands.BACK) != null)
                break;
            else
                System.out.println("Invalid command!");
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        URL url = ProfileMenu.class.getResource("/fxml/ProfileMenu.fxml");
        BorderPane borderPane = FXMLLoader.load(url);
        Background background = new Background(setBackGround());
        borderPane.setBackground(background);
        Scene scene = new Scene(borderPane);
        stage.setScene(scene);
        stage.show();
    }
    private BackgroundImage setBackGround() {
        Image image = new Image(ProfileMenu.class.getResource("/images/10.jpg").toExternalForm(), 1200 ,800, false, false);
        BackgroundImage backgroundImage = new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        return backgroundImage;
    }

    private void removeSlogan() {
        System.out.println(profileMenuController.removeSlogan());
    }

    private void changeSlogan(Matcher matcher) {
        System.out.println(profileMenuController.changeSlogan(matcher));
    }

    private void changeEmail(Matcher matcher) {
        System.out.println(profileMenuController.changeEmail(matcher));
    }

    private void changeNickname(Matcher matcher) {
        System.out.println(profileMenuController.changeNickname(matcher));
    }

    private void changeUsername(Matcher matcher) throws NoSuchAlgorithmException {
        System.out.println(profileMenuController.changeUsername(matcher));
    }

    private void changePassword(Matcher matcher, Scanner scanner) throws NoSuchAlgorithmException {
        String message = profileMenuController.changePassword(matcher);
        String newPasswordConfirmation;
        System.out.println(message);
        if (!message.startsWith("Profile change password failed:") && !message.startsWith("Set a new password failed:")) {
            newPasswordConfirmation = scanner.nextLine();
            Boolean isValid = profileMenuController.checkNewPasswordConfirmation(newPasswordConfirmation,
                    matcher.group("newPassword"));
            if (isValid) {
                profileMenuController.putNewPasswordInCurrentUserPassword(matcher.group("newPassword"));
                profileMenuController.writeDataInJsonFile("putNewPassword", matcher.group("newPassword"));
                System.out.println("Password change successfully!");
            } else
                System.out.println("Password confirmation failed!");

        }
    }


}
package org.group62.veiw;

import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.group62.Main;
import org.group62.controller.LoginMenuController;
import org.group62.model.StrongHold;
import org.group62.model.User;

public class ControllerLoginMenu {
    public TextField username;
    public PasswordField password;
    LoginMenuController loginMenuController = new LoginMenuController();

    public void login(MouseEvent mouseEvent) throws Exception {
        String output;
        if (!(output = loginMenuController.login(username.getText(),password.getText())).equals("User logged in successfully!")){
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Error");
            errorAlert.setHeaderText("login failed");
            errorAlert.setContentText(output);
            errorAlert.showAndWait();
        }else {
            MainMenu mainMenu = new MainMenu();
            mainMenu.start(Main.stage);
        }
    }

    public void back(MouseEvent mouseEvent) throws Exception {
        SignupMenu signupMenu = new SignupMenu();
        signupMenu.start(Main.stage);
    }

    public void reset(MouseEvent mouseEvent) {
        username.setText("");
        password.setText("");
    }

    public void forgotPassword(MouseEvent mouseEvent) throws Exception {
        User user;
        if ((user = StrongHold.getUserByUsername(username.getText()))==null) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Error");
            errorAlert.setHeaderText("change password failed");
            errorAlert.setContentText("Username not found!");
            errorAlert.showAndWait();
        }
        else {
            ForgotPasswordMenu forgotPasswordMenu = new ForgotPasswordMenu(user);
            forgotPasswordMenu.start(Main.stage);
        }
    }
}

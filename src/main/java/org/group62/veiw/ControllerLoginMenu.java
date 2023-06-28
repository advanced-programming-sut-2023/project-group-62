package org.group62.veiw;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.group62.Main;
import org.group62.controller.LoginMenuController;

public class ControllerLoginMenu {
    public TextField username;
    public PasswordField password;
    LoginMenuController loginMenuController = new LoginMenuController();

    public void login(MouseEvent mouseEvent) throws Exception {
        // TODO: 6/27/2023
        MainMenu mainMenu = new MainMenu();
        mainMenu.start(Main.stage);

    }

    public void back(MouseEvent mouseEvent) throws Exception {
        SignupMenu signupMenu = new SignupMenu();
        signupMenu.start(Main.stage);
    }

    public void reset(MouseEvent mouseEvent) {
        username.setText("");
        password.setText("");
    }

    public void forgotPassword(MouseEvent mouseEvent) {
    }
}

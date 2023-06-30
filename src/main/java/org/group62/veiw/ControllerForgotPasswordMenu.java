package org.group62.veiw;

import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import org.group62.Main;
import org.group62.controller.LoginMenuController;


public class ControllerForgotPasswordMenu {
    public Text question;
    public TextField answer;
    public PasswordField newPassword;
    private final LoginMenuController loginMenuController = new LoginMenuController();

    public void initialize() {
        question.setText(loginMenuController.forgotPasswordReturnSecurityQuestion(ForgotPasswordMenu.username));
    }

    public void changePassword(MouseEvent mouseEvent) {
        String output = loginMenuController.forgotPassword(ForgotPasswordMenu.username, newPassword.getText(), answer.getText());
        if (output.equals("change password was successful")) {
            Alert informationAlert = new Alert(Alert.AlertType.INFORMATION);
            informationAlert.setTitle("Susses");
            informationAlert.setHeaderText("password change");
            informationAlert.setContentText("change password was successful");
            informationAlert.showAndWait();
        } else {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Error");
            errorAlert.setHeaderText("change password failed");
            errorAlert.setContentText(output);
            errorAlert.showAndWait();
        }
    }
    public void back(MouseEvent mouseEvent) throws Exception {
        LoginMenu loginMenu = new LoginMenu();
        loginMenu.start(Main.stage);
    }
}

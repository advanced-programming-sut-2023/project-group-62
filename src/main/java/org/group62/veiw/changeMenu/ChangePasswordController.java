package org.group62.veiw.changeMenu;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.group62.Main;
import org.group62.controller.ProfileMenuController;
import org.group62.controller.SignupMenuController;
import org.group62.veiw.ProfileMenu;

import java.security.NoSuchAlgorithmException;

public class ChangePasswordController {
    public PasswordField oldPassword;
    public PasswordField newPassword;
    public TextField text;

    public void passwordChange(MouseEvent mouseEvent) throws NoSuchAlgorithmException {
        ProfileMenuController profileMenuController = new ProfileMenuController();
        String output = profileMenuController.changePassword(newPassword.getText(), oldPassword.getText());
        if (output.equals("change password was successful")){
            Alert informationAlert = new Alert(Alert.AlertType.INFORMATION);
            informationAlert.setTitle("Susses");
            informationAlert.setHeaderText("password change");
            informationAlert.setContentText(output);
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
        ProfileMenu profileMenu = new ProfileMenu();
        profileMenu.start(Main.stage);
    }
    @FXML
    public void initialize(){
        SignupMenuController signupMenuController = new SignupMenuController();
        newPassword.textProperty().addListener((observable, oldText, newText)->{
            String output;
            if (!(output = signupMenuController.checkPassword(newText)).equals("strong password"))
                text.setText(output);
        });
    }
}

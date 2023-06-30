package org.group62.veiw.changeMenu;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.group62.Main;
import org.group62.controller.ProfileMenuController;
import org.group62.controller.SignupMenuController;
import org.group62.veiw.ProfileMenu;

public class ChangeUsernameController {
    public TextField newUsername;
    public TextField text;

    public void usernameChange(MouseEvent mouseEvent) {
        ProfileMenuController profileMenuController = new ProfileMenuController();
        String output = profileMenuController.changeUsername(newUsername.getText());
        if (output.equals("change username was successful")){
            Alert informationAlert = new Alert(Alert.AlertType.INFORMATION);
            informationAlert.setTitle("Susses");
            informationAlert.setHeaderText("username change");
            informationAlert.setContentText(output);
            informationAlert.showAndWait();
        } else {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Error");
            errorAlert.setHeaderText("change username failed");
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
        newUsername.textProperty().addListener((observable, oldText, newText)->{
            String output;
            if (!(output = signupMenuController.checkUsername(newText)).equals("good username")){
                text.setText(output);
                text.setStyle("-fx-text-fill: red;");
            }

        });
    }

}

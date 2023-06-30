package org.group62.veiw.changeMenu;

import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.group62.Main;
import org.group62.controller.ProfileMenuController;
import org.group62.veiw.ProfileMenu;

public class ChangeNicknameController {
    public TextField newNickname;

    public void nicknameChange(MouseEvent mouseEvent) {
        ProfileMenuController profileMenuController = new ProfileMenuController();
        String output = profileMenuController.changeNickname(newNickname.getText());
        if (output.equals("change nickname was successful")){
            Alert informationAlert = new Alert(Alert.AlertType.INFORMATION);
            informationAlert.setTitle("Susses");
            informationAlert.setHeaderText("nickname change");
            informationAlert.setContentText(output);
            informationAlert.showAndWait();
        } else {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Error");
            errorAlert.setHeaderText("change nickname failed");
            errorAlert.setContentText(output);
            errorAlert.showAndWait();
        }
    }

    public void back(MouseEvent mouseEvent) throws Exception {
        ProfileMenu profileMenu = new ProfileMenu();
        profileMenu.start(Main.stage);
    }


}

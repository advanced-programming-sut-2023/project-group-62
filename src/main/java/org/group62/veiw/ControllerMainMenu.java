package org.group62.veiw;

import javafx.scene.input.MouseEvent;
import org.group62.Main;
import org.group62.model.StrongHold;

public class ControllerMainMenu {
    public void enterProfileMenu(MouseEvent mouseEvent) {
    }

    public void enterGameMenu(MouseEvent mouseEvent) {
    }

    public void logout(MouseEvent mouseEvent) throws Exception {
        StrongHold.setCurrentUser(null);
        LoginMenu loginMenu = new LoginMenu();
        loginMenu.start(Main.stage);
    }
}

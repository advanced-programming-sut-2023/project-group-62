package org.group62.veiw;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.group62.controller.LoginMenuController;

import java.net.URL;

public class ForgotPasswordMenu extends Application {
    public Text question;
    public TextField answer;
    public PasswordField newPassword;
    private final String username;
    private final LoginMenuController loginMenuController = new LoginMenuController();

    public ForgotPasswordMenu(String username) {
        this.username = username;
    }

    @Override
    public void start(Stage stage) throws Exception {
        URL url = ForgotPasswordMenu.class.getResource("/fxml/ForgotPasswordMenu.fxml");
        BorderPane borderPane = FXMLLoader.load(url);
        Scene scene = new Scene(borderPane);
        stage.setScene(scene);
        stage.show();
    }

    public void initialize() {
        question.setText(loginMenuController.forgotPasswordReturnSecurityQuestion(username));
    }

    public void changePassword(MouseEvent mouseEvent) {
        String output = loginMenuController.forgotPassword(username, newPassword.getText(), answer.getText());
        if (output.equals("change password was successful")) {
            Alert informationAlert = new Alert(Alert.AlertType.INFORMATION);
            informationAlert.setTitle("Susses");
            informationAlert.setHeaderText("password change");
            informationAlert.setContentText("change password was successful");
        } else {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Error");
            errorAlert.setHeaderText("change password failed");
            errorAlert.setContentText(output);
            errorAlert.showAndWait();
        }
    }

    public void back(MouseEvent mouseEvent) {
        LoginMenu loginMenu = new LoginMenu();
        loginMenu.start();
    }
}

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
import org.group62.controller.SignupMenuController;
import org.group62.model.User;

import java.net.URL;

public class ForgotPasswordMenu extends Application {
    public Text question;
    public TextField answer;
    public PasswordField newPassword;
    private final User user;

    public ForgotPasswordMenu(User user){
        this.user = user;
    }
    @Override
    public void start(Stage stage) throws Exception {
        URL url = ForgotPasswordMenu.class.getResource("/fxml/ForgotPasswordMenu.fxml");
        BorderPane borderPane = FXMLLoader.load(url);
        Scene scene = new Scene(borderPane);
        stage.setScene(scene);
    }
    public void initialize(){
        question.setText(user.getPasswordRecoveryQuestion());
    }

    public void changePassword(MouseEvent mouseEvent) {
        String output;
        SignupMenuController signupMenuController = new SignupMenuController();
        if (answer.getText().equals(""))
            output = "change password failed: please wright answer";
        else if (newPassword.getText().equals(""))
            output = "change password failed: please wright new password";
        else if (!user.getPasswordRecoveryAnswer().equals(answer.getText()))
            output = "change password failed: wrong answer";
        else if (!(output = signupMenuController.checkPassword(newPassword.getText())).equals("strong password"));
        else {
            user.setPassword(newPassword.getText());
            Alert informationAlert = new Alert(Alert.AlertType.INFORMATION);
            informationAlert.setTitle("Susses");
            informationAlert.setHeaderText("password change");
            informationAlert.setContentText("change password was successful");
            return;
        }
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("Error");
        errorAlert.setHeaderText("change password failed");
        errorAlert.setContentText(output);
        errorAlert.showAndWait();
    }
}

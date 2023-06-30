package org.group62.veiw;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.group62.Main;
import org.group62.controller.SignupMenuController;

import java.util.regex.Matcher;


public class ControllerSignupMenu {

    SignupMenuController signupMenuController = new SignupMenuController();

    public TextField username;
    public PasswordField password;
    public TextField nickname;
    public TextField Email;
    public TextField slogan;
    public TextField answer;
    public TextArea textArea;

    public void signup(MouseEvent mouseEvent) {
        String output;
        Matcher matcher;
        String passwordRecoveryQuestion = null;
        Alert informationAlert = new Alert(Alert.AlertType.INFORMATION);
        informationAlert.setTitle("Susses");
        informationAlert.setHeaderText("User created");
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("Error");
        errorAlert.setHeaderText("signup failed");
        if (answer.getText().equals(""))
            output = "wright answer";
        else if ((matcher = Commands.getMatcherMatches(answer.getText(), Commands.FXML_QUESTION_PICK)) == null)
            output = "please write the answer in the form given";
        else {
            switch (matcher.group("questionNumber")) {
                case "1":
                    passwordRecoveryQuestion = "What is your first school's name?";
                    break;
                case "2":
                    passwordRecoveryQuestion = "What is your favorite car?";
                    break;
                case "3":
                    passwordRecoveryQuestion = "When is your birthday?";
                    break;
            }
            output = signupMenuController.createUser(username.getText(), password.getText(),
                    passwordRecoveryQuestion, matcher.group("answer"), nickname.getText(), Email.getText(), slogan.getText());
            if (output.equals("user created successful")) {
                informationAlert.setContentText(output);
                informationAlert.showAndWait();
                return;
            } else {
                errorAlert.setContentText(output);
                errorAlert.showAndWait();
                return;
            }
        }
        errorAlert.setContentText(output);
        errorAlert.showAndWait();
    }

    public void reset(MouseEvent mouseEvent) {
        username.setText("");
        password.setText("");
        nickname.setText("");
        Email.setText("");
        slogan.setText("");
        answer.setText("");
        textArea.setText("");
    }

    public void loginMenu(MouseEvent mouseEvent) throws Exception {
        LoginMenu loginMenu = new LoginMenu();
        loginMenu.start(Main.stage);
    }

    public void randomPassword(MouseEvent mouseEvent) {
        String randomPassword = signupMenuController.randomPasswordGenerator();
        password.setText(randomPassword);
        textArea.setText("your random password : " + randomPassword);
        textArea.setStyle("-fx-text-fill: green;");
    }

    public void randomSlogan(MouseEvent mouseEvent) {
        String randomSlogan = signupMenuController.randomSloganGenerator();
        slogan.setText(randomSlogan);
        textArea.setText("your random slogan : " + randomSlogan);
        textArea.setStyle("-fx-text-fill: green;");
    }

    @FXML
    public void initialize() {
        username.textProperty().addListener((observable, oldText, newText) -> {
            String output;
            if (!(output = signupMenuController.checkUsername(newText)).equals("good username")) {
                textArea.setText(output);
                textArea.setStyle("-fx-text-fill: red;");
            }

        });
        password.textProperty().addListener((observable, oldText, newText) -> {
            String output;
            if (!(output = signupMenuController.checkPassword(newText)).equals("strong password")) {
                textArea.setText(output);
                textArea.setStyle("-fx-text-fill: red;");
            }

        });
    }
}

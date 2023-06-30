package org.group62.veiw;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;

public class ForgotPasswordMenu extends Application {
    public static String username;

    public ForgotPasswordMenu(String username) {
        ForgotPasswordMenu.username = username;
    }

    @Override
    public void start(Stage stage) throws Exception {
        URL url = ForgotPasswordMenu.class.getResource("/fxml/ForgotPasswordMenu.fxml");
        BorderPane borderPane = FXMLLoader.load(url);
        Scene scene = new Scene(borderPane);
        stage.setScene(scene);
        stage.show();
    }
}

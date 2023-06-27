package org.group62;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.group62.controller.SignupMenuController;
import org.group62.veiw.SignupMenu;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import java.net.URL;

public class Main extends Application {
    public static Stage stage;
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException, InterruptedException {
        /*Scanner scanner = new Scanner(System.in);
        SignupMenuController signupMenuController = new SignupMenuController(scanner);
        SignupMenu signupMenu = new SignupMenu(signupMenuController,scanner);
        signupMenu.run();*/
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Main.stage = stage;
        URL url = Main.class.getResource("/fxml/start.fxml");
        BorderPane borderPane = FXMLLoader.load(url);
        Scene scene = new Scene(borderPane);
        stage.setScene(scene);
        stage.show();
    }

    public void signup(MouseEvent mouseEvent) throws Exception {
        Scanner scanner = new Scanner(System.in);
        SignupMenuController signupMenuController = new SignupMenuController(scanner);
        SignupMenu signupMenu = new SignupMenu(signupMenuController,scanner);
        signupMenu.start(Main.stage);
    }

    public void login(MouseEvent mouseEvent) {
    }
}
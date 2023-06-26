package org.group62;

import javafx.application.Application;
import javafx.stage.Stage;
import org.group62.controller.SignupMenuController;
import org.group62.veiw.SignupMenu;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Main extends Application {
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        SignupMenuController signupMenuController = new SignupMenuController(scanner);
        SignupMenu signupMenu = new SignupMenu(signupMenuController,scanner);
        signupMenu.run();
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.show();
    }
}
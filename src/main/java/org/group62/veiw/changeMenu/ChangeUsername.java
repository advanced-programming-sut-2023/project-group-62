package org.group62.veiw.changeMenu;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.net.URL;

public class ChangeUsername extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        URL url = ChangeUsername.class.getResource("/fxml/changeMenu/ChangeUsername.fxml");
        BorderPane borderPane = FXMLLoader.load(url);
        Background background = new Background(setBackGround());
        borderPane.setBackground(background);
        Scene scene = new Scene(borderPane);
        stage.setScene(scene);
        stage.show();
    }
    private BackgroundImage setBackGround() {
        Image image = new Image(ChangeUsername.class.getResource("/images/25.jpg").toExternalForm(), 1200 ,800, false, false);
        BackgroundImage backgroundImage = new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        return backgroundImage;
    }
}

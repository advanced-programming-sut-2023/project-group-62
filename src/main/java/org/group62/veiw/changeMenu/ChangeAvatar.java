package org.group62.veiw.changeMenu;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.group62.veiw.SignupMenu;

import java.net.URL;

public class ChangeAvatar extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        URL url = ChangeAvatar.class.getResource("/fxml/changeMenu/ChangeAvatar.fxml");
        BorderPane borderPane = FXMLLoader.load(url);
        Background background = new Background(setBackGround());
        borderPane.setBackground(background);
        Scene scene = new Scene(borderPane);
        stage.setScene(scene);
        stage.show();
    }
    private BackgroundImage setBackGround() {
        Image image = new Image(ChangeAvatar.class.getResource("/images/01.jpg").toExternalForm(), 1200 ,800, false, false);
        BackgroundImage backgroundImage = new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        return backgroundImage;
    }
}

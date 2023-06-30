package org.group62.veiw;

import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import org.group62.Main;
import org.group62.controller.ProfileMenuController;
import org.group62.veiw.changeMenu.*;

public class ControllerProfileMenu {
    public Text username;
    public Text nickname;
    public Text Email;
    public Text slogan;
    public Text highScore;
    public Text rank;
    ProfileMenuController profileMenuController = new ProfileMenuController();

    public void initialize(){
        username.setText(profileMenuController.showUsername());
        nickname.setText(profileMenuController.showNickname());
        Email.setText(profileMenuController.showEmail());
        slogan.setText(profileMenuController.showSlogan());
        highScore.setText(profileMenuController.showHighScore());
        rank.setText(profileMenuController.showRank());
    }
    public void changeUsername(MouseEvent mouseEvent) throws Exception {
        ChangeUsername changeUsername = new ChangeUsername();
        changeUsername.start(Main.stage);
    }

    public void changePassword(MouseEvent mouseEvent) throws Exception {
        ChangePassword changePassword = new ChangePassword();
        changePassword.start(Main.stage);
    }

    public void changeNickname(MouseEvent mouseEvent) throws Exception {
        ChangeNickname changeNickname = new ChangeNickname();
        changeNickname.start(Main.stage);
    }

    public void changeEmail(MouseEvent mouseEvent) throws Exception {
        ChangeEmail changeEmail = new ChangeEmail();
        changeEmail.start(Main.stage);
    }

    public void changeAvatar(MouseEvent mouseEvent) throws Exception {
        ChangeAvatar changeAvatar = new ChangeAvatar();
        changeAvatar.start(Main.stage);
    }

    public void changeSlogan(MouseEvent mouseEvent) throws Exception {
        ChangeSlogan changeSlogan = new ChangeSlogan();
        changeSlogan.start(Main.stage);
    }
}

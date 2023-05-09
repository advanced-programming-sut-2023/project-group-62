package org.group62;

import org.group62.Controller.SignupMenuController;
import org.group62.veiw.SignupMenu;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class Main {
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
        SignupMenuController signupMenuController = new SignupMenuController();
        SignupMenu signupMenu = new SignupMenu(signupMenuController);
        signupMenu.run();
    }
}
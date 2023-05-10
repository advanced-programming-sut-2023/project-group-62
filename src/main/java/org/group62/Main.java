package org.group62;

import org.group62.controller.SignupMenuController;
import org.group62.veiw.SignupMenu;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class Main {
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException, ParseException {
        SignupMenuController signupMenuController = new SignupMenuController();
        SignupMenu signupMenu = new SignupMenu(signupMenuController);
        signupMenu.run();
    }
}
package org.group62;

import org.group62.controller.SignupMenuController;
import org.group62.veiw.SignupMenu;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException, ParseException {
        Scanner scanner = new Scanner(System.in);
        SignupMenuController signupMenuController = new SignupMenuController(scanner);
        SignupMenu signupMenu = new SignupMenu(signupMenuController,scanner);
        signupMenu.run();
    }
}
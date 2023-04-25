package org.group62;

import org.group62.veiw.SignupMenu;

public class Main {
    public static void main(String[] args) {
        SignupMenuController signupMenuController = new SignupMenuController;
        SignupMenu signupMenu = new SignupMenu(signupMenuController);
        signupMenu.run();
    }
}
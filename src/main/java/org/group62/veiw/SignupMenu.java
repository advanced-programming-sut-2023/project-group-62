package org.group62.veiw;

import java.util.Scanner;

public class SignupMenu {
    SignupMenuController signupMenuController;
    LoginMenuController loginMenuController;
    public SignupMenu(SignupMenuController signupMenuController){
        this.signupMenuController = signupMenuController;
        loginMenuController = new LoginMenuController();
    }

    Scanner scanner = new Scanner(System.in);
    String inputCommand;
    public void run(){
        while (true){
            inputCommand = scanner.nextLine();

        }
    }
}

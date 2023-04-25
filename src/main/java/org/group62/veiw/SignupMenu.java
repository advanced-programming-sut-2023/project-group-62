package org.group62.veiw;

import java.util.Scanner;
import java.util.regex.Matcher;

public class SignupMenu {
    SignupMenuController signupMenuController;
    LoginMenuController loginMenuController;
    public SignupMenu(SignupMenuController signupMenuController){
        this.signupMenuController = signupMenuController;
        loginMenuController = new LoginMenuController();
    }

    Scanner scanner = new Scanner(System.in);
    Matcher matcher;
    String inputCommand;
    public void run(){
        while (true){
            inputCommand = scanner.nextLine();
            if((matcher = Commands.getMatcher(inputCommand,Commands.CREAT_USER_WITH_SLOGAN)) != null)
                normalCreatUser(matcher);
            else if((matcher = Commands.getMatcher(inputCommand,Commands.CREAT_USER_WITHOUT_SLOGAN)) != null)
                creatUserWithoutSlogan(matcher);
            else if((matcher = Commands.getMatcher(inputCommand,Commands.CREAT_USER_WITH_RANDOM_PASSWORD)) != null)
                creatUserWithRandomPassword(matcher);
            else if((matcher = Commands.getMatcher(inputCommand,Commands.CREAT_USER_WITH_RANDOM_SLOGAN)) != null)
                creatUserWithRandomSlogan(matcher);
            else if((matcher = Commands.getMatcher(inputCommand,Commands.CREAT_USER_WITH_RANDOM_SLOGAN_AND_RANDOM_PASSWORD)) != null)
                creatUserWithRandomPasswordAndSlogan(matcher);
            else if((matcher = Commands.getMatcher(inputCommand,Commands.ENTER_LOGIN_MENU)) != null){
                LoginMenu loginMenu = new LoginMenu(loginMenuController);
                loginMenu.run(scanner);
            }
            else if(Commands.getMatcher(inputCommand,Commands.EXIT) != null)
                break;
            else
                System.out.println("Invalid command!");


        }
    }


    private void creatUserWithRandomPasswordAndSlogan(Matcher matcher) {
        //TODO
    }

    private void creatUserWithRandomSlogan(Matcher matcher) {
        //TODO
    }

    private void creatUserWithRandomPassword(Matcher matcher) {
        //TODO
    }

    private void creatUserWithoutSlogan(Matcher matcher) {
        //TODO
    }

    private void normalCreatUser(Matcher matcher) {
        //TODO
    }
}

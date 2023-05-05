package org.group62.Controller;


import org.group62.veiw.Commands;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignupMenuController {

    public String normalCreatUser(Matcher matcher) throws NoSuchAlgorithmException, IOException {
        String username = matcher.group("username");
        String password = matcher.group("password");
        String passwordConfirmation = matcher.group("passwordConfirmation");
        String email = matcher.group("email");
        String nickname = matcher.group("nickname");
        String slogan = matcher.group("slogan");

        if (isBlank(username) || isBlank(passwordConfirmation) || isBlank(password) ||
                isBlank(email) || isBlank(nickname) || isBlank(slogan))
            return "Creat user failed: Some fields is empty!";
        else if (Commands.getMatcher(username, Commands.USERNAME_VALIDATION) == null)
            return "Creat user failed: Invalid username!";
        else if (isUsernameDuplicate(username))
            return "Creat user failed: Your username is duplicate!";
        else if (Commands.getMatcher(password, Commands.STRONG_PASSWORD) == null) {
            if (Commands.getMatcher(password, Commands.PASSWORD_WEAK_LOWERCASE_ALPHABET) == null)
                return "Creat user failed: Password is weak --> lowercase alphabet not involved!";
            else if (Commands.getMatcher(password, Commands.PASSWORD_WEAK_UPPERCASE_ALPHABET) == null)
                return "Creat user failed: Password is weak --> uppercase alphabet not involved!";
            else if (Commands.getMatcher(password, Commands.PASSWORD_WEAK_NUMBER) == null)
                return "Creat user failed: Password is weak --> numbers not involved!";
            else
                return "Creat user failed: Password is weak --> any non number or alphabet not involved!";
        } else if (!password.equals(passwordConfirmation))
            return "Creat user failed: password confirmation incorrect!";
        else if (isEmailDuplicate(email))
            return "Creat user failed: Your email duplicate!";
        else if (Commands.getMatcher(email, Commands.EMAIL_VALIDATION) == null)
            return "Creat user failed: Email address invalid!";
        else {
            usernameDatabase(username);
            emailDatabase(email);
            String passwordSecure = SHA256.sha256Security(password);
            JSONObject userDetails = new JSONObject();
            userDetails.put("username", username);
            userDetails.put("passwordSecure", passwordSecure);
            userDetails.put("Email", email);
            userDetails.put("nickname", nickname);
            userDetails.put("slogan", slogan);

            JSONObject userObject = new JSONObject();
            userObject.put("User", userDetails);

            JSONArray userList = new JSONArray();
            userList.put(userObject);
            try (FileWriter file = new FileWriter("users.json")) {
                file.write(userList.toString());
                file.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "Creat user successful!";
        }
    }

    public String creatUserWithoutSlogan(Matcher matcher) throws IOException, NoSuchAlgorithmException {
        String username = matcher.group("username");
        String password = matcher.group("password");
        String passwordConfirmation = matcher.group("passwordConfirmation");
        String email = matcher.group("email");
        String nickname = matcher.group("nickname");

        if (isBlank(username) || isBlank(passwordConfirmation) || isBlank(password) ||
                isBlank(email) || isBlank(nickname))
            return "Creat user failed: Some fields is empty!";
        else if (Commands.getMatcher(username, Commands.USERNAME_VALIDATION) == null)
            return "Creat user failed: Invalid username!";
        else if (isUsernameDuplicate(username))
            return "Creat user failed: Your username is duplicate!";
        else if (Commands.getMatcher(password, Commands.STRONG_PASSWORD) == null) {
            if (Commands.getMatcher(password, Commands.PASSWORD_WEAK_LOWERCASE_ALPHABET) == null)
                return "Creat user failed: Password is weak --> lowercase alphabet not involved!";
            else if (Commands.getMatcher(password, Commands.PASSWORD_WEAK_UPPERCASE_ALPHABET) == null)
                return "Creat user failed: Password is weak --> uppercase alphabet not involved!";
            else if (Commands.getMatcher(password, Commands.PASSWORD_WEAK_NUMBER) == null)
                return "Creat user failed: Password is weak --> numbers not involved!";
            else
                return "Creat user failed: Password is weak --> any non number or alphabet not involved!";
        } else if (!password.equals(passwordConfirmation))
            return "Creat user failed: password confirmation incorrect!";
        else if (isEmailDuplicate(email))
            return "Creat user failed: Your email duplicate!";
        else if (Commands.getMatcher(email, Commands.EMAIL_VALIDATION) == null)
            return "Creat user failed: Email address invalid!";
        else {
            usernameDatabase(username);
            emailDatabase(email);
            String passwordSecure = SHA256.sha256Security(password);
            JSONObject userDetails = new JSONObject();
            userDetails.put("username", username);
            userDetails.put("passwordSecure", passwordSecure);
            userDetails.put("Email", email);
            userDetails.put("nickname", nickname);

            JSONObject userObject = new JSONObject();
            userObject.put("User", userDetails);

            JSONArray userList = new JSONArray();
            userList.put(userObject);
            try (FileWriter file = new FileWriter("users.json")) {
                file.write(userList.toString());
                file.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "Creat user successful!";
        }
    }

    public String creatUserWithRandomPassword(Matcher matcher) {
        return null;
    }

    public String creatUserWithRandomSlogan(Matcher matcher) {
        return null;
    }

    public String creatUserWithRandomPasswordAndSlogan(Matcher matcher) {
        return null;
    }

    private Boolean isBlank(String input) {
        Matcher whiteSpaceMatcher = Pattern.compile("\s+").matcher(input);
        if (whiteSpaceMatcher.matches())
            return true;
        else
            return false;
    }

    private Boolean isUsernameDuplicate(String username) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("usernames.txt"));
        ArrayList<String> userList = new ArrayList<>();
        while (scanner.hasNext()) {
            userList.add(scanner.next());
        }
        scanner.close();
        for (String name : userList) {
            if (name.equals(username))
                return true;
        }
        return false;
    }

    private Boolean isEmailDuplicate(String email) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("emails.txt"));
        ArrayList<String> emailList = new ArrayList<>();
        while (scanner.hasNext()) {
            emailList.add(scanner.next().toUpperCase());
        }
        scanner.close();
        for (String name : emailList) {
            if (name.equals(email))
                return true;
        }
        return false;
    }

    private void usernameDatabase(String username) throws IOException {
        FileWriter fileWriter = new FileWriter("usernames.txt");
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.print(username);
        printWriter.close();
    }

    private void emailDatabase(String email) throws IOException {
        FileWriter fileWriter = new FileWriter("emails.txt");
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.print(email);
        printWriter.close();
    }
}

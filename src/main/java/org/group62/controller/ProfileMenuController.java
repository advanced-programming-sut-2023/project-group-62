package org.group62.controller;

import org.group62.model.StrongHold;
import org.group62.model.User;

import org.group62.model.User;
import org.group62.veiw.Commands;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.regex.Matcher;
public class ProfileMenuController {
    private User currentUser;
    private ArrayList<String> usernames = new ArrayList<>();
    private ArrayList<String> emails = new ArrayList<>();

    public Boolean checkNewPasswordConfirmation(String newPasswordConfirmation, String newPassword) {
        if (newPassword.equals(newPasswordConfirmation))
            return true;
        else
            return false;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public String changePassword(Matcher matcher) throws NoSuchAlgorithmException {
        String oldPassword = matcher.group("oldPassword");
        String newPassword = matcher.group("newPassword");
        if (!isPasswordCorrect(oldPassword))
            return "Profile change password failed: Current password is incorrect!";
        else if(oldPassword.equals(newPassword))
            return "Profile change password failed: Please enter a new password!";
        else if (Commands.getMatcherMatches(newPassword, Commands.STRONG_PASSWORD) == null) {
            if (newPassword.length() < 6)
                return "Profile change password failed: Password is weak --> password is less than 6 characters!";
            else if (Commands.getMatcherFind(newPassword, Commands.PASSWORD_WEAK_LOWERCASE_ALPHABET) == null)
                return "Profile change password failed: Password is weak --> lowercase alphabet not involved!";
            else if (Commands.getMatcherFind(newPassword, Commands.PASSWORD_WEAK_UPPERCASE_ALPHABET) == null)
                return "Profile change password failed: Password is weak --> uppercase alphabet not involved!";
            else if (Commands.getMatcherFind(newPassword, Commands.PASSWORD_WEAK_NUMBER) == null)
                return "Profile change password failed: Password is weak --> numbers not involved!";
            else
                return "Profile change password failed: Password is weak --> any non number or alphabet not involved!";
        } else
            return "Please enter your new password again:";
    }

    private boolean isPasswordCorrect(String oldPassword) throws NoSuchAlgorithmException {
        String oldPasswordSecure = SHA256.sha256Security(oldPassword);
        if (oldPasswordSecure.equals(currentUser.getPasswordSecure()))
            return true;
        else
            return false;
    }

    public String changeUsername(Matcher matcher) throws NoSuchAlgorithmException {
        String newUsername = matcher.group("username");
        if (isUsernameDuplicate(newUsername))
            return "Profile change username failed: You have entered duplicate username!";
        else if (Commands.getMatcherMatches(newUsername, Commands.USERNAME_VALIDATION) == null)
            return "Profile change username failed: Invalid username format!";
        else {
            writeDataInJsonFile("putNewUsername", newUsername);
            currentUser.setUsername(newUsername);
            return "Profile change username successfully!";
        }

    }


    public String changeNickname(Matcher matcher) {
        String newNickname = matcher.group("nickname");
        writeDataInJsonFile("putNewNickname",newNickname);
        currentUser.setNickname(newNickname);
        return "Profile change nickname successfully!";
    }

    public String changeEmail(Matcher matcher) {
        String newEmail = matcher.group("email");
        if(isEmailDuplicate(newEmail))
            return "Profile change email failed: Duplicate email address!";
        else if(Commands.getMatcherMatches(newEmail,Commands.EMAIL_VALIDATION) == null)
            return "Profile change email failed: Invalid email address format!";
        else{
            writeDataInJsonFile("putNewEmailAddress",newEmail);
            currentUser.setEmail(newEmail);
            return "Profile change email address successfully!";
        }

    }

    public String changeSlogan(Matcher matcher) {
        String newSlogan = matcher.group("slogan");
        writeDataInJsonFile("putNewSlogan",newSlogan);
        currentUser.setSlogan(newSlogan);
        return "Profile change slogan successfully!";
    }

    public String removeSlogan() {
        writeDataInJsonFile("putNewSlogan","User has no slogan!");
        currentUser.setSlogan(null);
        return "Profile remove slogan successfully!";
    }

    public String displayHighScore() {
        String highScore = "";
        highScore += currentUser.getHighScore();
        return "User high score is: " + highScore;
    }

    public String displayRank() {
        String rank = "";
        rank += currentUser.getRank();
        return "User rank is: " + rank;
    }

    public String displaySlogan() {
        return "User slogan is: " + currentUser.getSlogan();
    }

    public String displayAllProfile() {
        String message = "";
        message += "Username is: " + currentUser.getUsername();
        message += "\nNickname is: " + currentUser.getNickname();
        message += "\nEmail address is: " + currentUser.getEmail();
        message += "\nSlogan is: " + currentUser.getSlogan();
        message += "\nRank is: " + currentUser.getRank();
        message += "\nHigh score is: " + currentUser.getHighScore();
        return message;
    }

    private void updateUserJsonFile(JSONObject emp, JSONArray newUserList) {
        JSONObject array = new JSONObject();
        JSONObject empobj = (JSONObject) emp.get("User");
        String username = (String) empobj.get("username");
        String passwordSecure = (String) empobj.get("passwordSecure");
        String email = (String) empobj.get("Email");
        String nickname = (String) empobj.get("nickname");
        String slogan = (String) empobj.get("slogan");
        String securityQuestionSecure = (String) empobj.get("securityQuestionSecure");
        String securityQuestionAnswerSecure = (String) empobj.get("securityQuestionAnswerSecure");
        String highScore = (String) empobj.get("highScore");
        array.put("username", username);
        array.put("passwordSecure", passwordSecure);
        array.put("Email", email);
        array.put("nickname", nickname);
        array.put("slogan", slogan);
        array.put("securityQuestionSecure", securityQuestionSecure);
        array.put("securityQuestionAnswerSecure", securityQuestionAnswerSecure);
        array.put("highScore",highScore);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("User", array);
        newUserList.add(jsonObject);
    }

    private void userJsonFileFunctions(JSONArray empList, String function, String currentUsername, String newData,
                                       User user, JSONArray newUserList) {
        switch (function) {
            case "putNewPassword":
                empList.forEach(emp -> {
                    try {
                        putNewPasswordInJsonFile((JSONObject) emp, currentUsername, newData, newUserList);
                    } catch (NoSuchAlgorithmException e) {
                        throw new RuntimeException(e);
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                });
            case "usernameCheck":
                usernames.clear();
                empList.forEach(emp -> usernameParse((JSONObject) emp));
                break;
            case "putNewUsername":
                empList.forEach(emp -> putNewUserDataInJsonFile((JSONObject) emp, "username", currentUsername, newData, newUserList));
                break;
            case "putNewNickname":
                empList.forEach(emp -> putNewUserDataInJsonFile((JSONObject) emp, "nickname", currentUsername, newData, newUserList));
                break;
            case "emailCheck":
                emails.clear();
                empList.forEach(emp -> emailParse((JSONObject) emp));
                break;
            case "putNewEmailAddress":
                empList.forEach(emp -> putNewUserDataInJsonFile((JSONObject) emp, "Email", currentUsername, newData, newUserList));
                break;
            case "putNewSlogan":
                empList.forEach(emp -> putNewUserDataInJsonFile((JSONObject) emp, "slogan", currentUsername, newData, newUserList));
                break;
        }
    }

    private void emailParse(JSONObject emp) {
        JSONObject empobj = (JSONObject) emp.get("User");
        String email = (String) empobj.get("Email");
        emails.add(email);
    }

    private void putNewUserDataInJsonFile(JSONObject emp, String partName, String oldUsername, String newData, JSONArray newUserList) {
        JSONObject empobj = (JSONObject) emp.get("User");
        String username = (String) empobj.get("username");
        if (username.equals(oldUsername)) {
            empobj.put(partName, newData);
        }
        updateUserJsonFile(emp, newUserList);
    }

    private void putNewPasswordInJsonFile(JSONObject emp, String usernameIn,
                                          String newPassword, JSONArray newUserList) throws NoSuchAlgorithmException, FileNotFoundException {
        JSONObject empobj = (JSONObject) emp.get("User");
        String username = (String) empobj.get("username");
        if (username.equals(usernameIn)) {
            String securePassword = SHA256.sha256Security(newPassword);
            empobj.put("passwordSecure", securePassword);
        }
        updateUserJsonFile(emp, newUserList);
    }

    private void userJsonFileParse(String order, String username, String newData, User user, JSONArray newUserList) {
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader("database\\users.json")) {
            Object object = jsonParser.parse(reader);
            JSONArray empList = (JSONArray) object;
            userJsonFileFunctions(empList, order, username, newData, user, newUserList);
        } catch (FileNotFoundException e) {
            System.out.println("Database file not found!");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ParseException e) {
            System.out.println("The database file is empty! No data to check!");
        }
    }

    public void writeDataInJsonFile(String order, String newData) {
        JSONArray newUserList = new JSONArray();
        userJsonFileParse(order, currentUser.getUsername(), newData, null, newUserList);
        try (FileWriter file = new FileWriter("database\\users.json")) {
            file.write(newUserList.toString());
            file.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void usernameParse(JSONObject emp) {
        JSONObject empobj = (JSONObject) emp.get("User");
        String username = (String) empobj.get("username");
        usernames.add(username);
    }

    private boolean isUsernameDuplicate(String username) {
        userJsonFileParse("usernameCheck", null, null, null, null);
        if (usernames.size() != 0) {
            for (String name : usernames) {
                if (username.equals(name))
                    return true;
            }
            return false;
        }
        return false;
    }

    public void putNewPasswordInCurrentUserPassword(String newPassword) throws NoSuchAlgorithmException {
        String newPasswordSecure = SHA256.sha256Security(newPassword);
        currentUser.setPassword(newPasswordSecure);
    }
    private Boolean isEmailDuplicate(String email){
        userJsonFileParse("emailCheck",null,null,null,null);
        if(emails.size() != 0){
            for (String e : emails){
                if(e.equals(email))
                    return true;
            }
            return false;
        }
        return false;
    }
}

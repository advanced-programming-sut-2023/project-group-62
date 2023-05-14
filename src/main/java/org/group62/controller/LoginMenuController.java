package org.group62.controller;

import org.group62.model.User;
import org.group62.veiw.Commands;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;

public class LoginMenuController {
    private User currentUser;
    private String currentUserPasswordInHashFormat;

    private ArrayList<String> usernames = new ArrayList<>();

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public String loginWithoutStayLoggedIn(Matcher matcher) throws NoSuchAlgorithmException {
        String username = matcher.group("username");
        String password = matcher.group("password");
        userJsonFileParse("usernameCheck", username, null, currentUser, null);
        if (!isUsernameExist(username))
            return "Username and password didn’t match! --> Username not exist!";
        else if (!isPasswordCorrect(password, username))
            return "Username and password didn’t match! --> Password is incorrect!";
        else {
            userJsonFileParse("parseUserInformation", username, null, currentUser, null);
            return "User logged in successfully!";
        }

    }

    private boolean isPasswordCorrect(String password, String username) throws NoSuchAlgorithmException {
        User tempUser = new User();
        userJsonFileParse("parseUserInformation", username, null, tempUser, null);
        String passwordInHashSecurity = SHA256.sha256Security(password);
        if (passwordInHashSecurity.equals(tempUser.getPasswordSecure()))
            return true;
        else
            return false;
    }

    private boolean isUsernameExist(String username) {
        if (usernames.size() != 0) {
            for (String name : usernames) {
                if (name.equals(username))
                    return true;
            }
            return false;
        }
        return false;
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
        String rank = (String) empobj.get("rank");
        array.put("username", username);
        array.put("passwordSecure", passwordSecure);
        array.put("Email", email);
        array.put("nickname", nickname);
        array.put("slogan", slogan);
        array.put("securityQuestionSecure", securityQuestionSecure);
        array.put("securityQuestionAnswerSecure", securityQuestionAnswerSecure);
        array.put("highScore",highScore);
        array.put("rank",rank);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("User", array);
        newUserList.add(jsonObject);
    }

    private void userJsonFileParse(String order, String username, String newPassword, User user, JSONArray newUserList) {
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader("database\\users.json")) {
            Object object = jsonParser.parse(reader);
            JSONArray empList = (JSONArray) object;
            userJsonFileFunctions(empList, order, username, newPassword, user, newUserList);
        } catch (FileNotFoundException e) {
            System.out.println("Database file not found!");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ParseException e) {
            System.out.println("The database file is empty! No data to check!");
        }
    }

    private void userJsonFileFunctions(JSONArray empList, String function, String username, String newPassword,
                                       User user, JSONArray newUserList) {
        switch (function) {
            case "usernameCheck":
                usernames.clear();
                empList.forEach(emp -> usernameParse((JSONObject) emp));
                break;
            case "parseUserInformation":
                empList.forEach(emp -> parseUserInformation((JSONObject) emp, username, user));
                break;
            case "putNewPassword":
                empList.forEach(emp -> {
                    try {
                        putNewPasswordInJsonFile((JSONObject) emp, username, newPassword, newUserList);
                    } catch (NoSuchAlgorithmException e) {
                        throw new RuntimeException(e);
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                });


        }

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

    private void parseUserInformation(JSONObject emp, String usernameIn, User user) {
        JSONObject empobj = (JSONObject) emp.get("User");
        String username = (String) empobj.get("username");
        if (username.equals(usernameIn)) {
            String passwordSecure = (String) empobj.get("passwordSecure");
            currentUserPasswordInHashFormat = passwordSecure;
            String email = (String) empobj.get("Email");
            String nickname = (String) empobj.get("nickname");
            String slogan = (String) empobj.get("slogan");
            String securityQuestionSecure = (String) empobj.get("securityQuestionSecure");
            String securityQuestionAnswerSecure = (String) empobj.get("securityQuestionAnswerSecure");
            String highScore = (String) empobj.get("highScore");
            String rank = (String) empobj.get("rank");
            user.setPassword(passwordSecure);
            user.setUsername(username);
            user.setEmail(email);
            user.setNickname(nickname);
            user.setSlogan(slogan);
            user.setPasswordRecoveryAnswer(securityQuestionAnswerSecure);
            user.setPasswordRecoveryQuestion(securityQuestionSecure);
            user.setHighScore(highScore);
            user.setRank(rank);
        }
    }

    private void usernameParse(JSONObject emp) {
        JSONObject empobj = (JSONObject) emp.get("User");
        String username = (String) empobj.get("username");
        usernames.add(username);
    }

    public String loginWithStayLoggedIn(Matcher matcher) throws NoSuchAlgorithmException {
        String username = matcher.group("username");
        String password = matcher.group("password");
        String passwordInHashFormat = SHA256.sha256Security(password);
        if (!username.equals(currentUser.getUsername()))
            return "Error: The username that you hava entered is not equal to last logged in user!";
        else if (!passwordInHashFormat.equals(currentUserPasswordInHashFormat))
            return "Error: Incorrect password!";
        else
            return "User logged in successfully";
    }

    public String forgotPasswordReturnSecurityQuestion(String username) {
        User forgetfulUser = new User();
        userJsonFileParse("parseUserInformation", username, null, forgetfulUser, null);
        String passwordRecoveryQuestion = forgetfulUser.getPasswordRecoveryQuestion();
        if (passwordRecoveryQuestion != null)
            return passwordRecoveryQuestion;
        else
            return "Username not found!";
    }

    public String forgotPasswordReturnSecurityQuestionAnswer(String username) {
        User forgetfulUser = new User();
        userJsonFileParse("parseUserInformation", username, null, forgetfulUser, null);
        return forgetfulUser.getPasswordRecoveryAnswer();
    }

    public String forgotPasswordSetNewPassword(String username, String password) {
        if (Commands.getMatcherMatches(password, Commands.STRONG_PASSWORD) == null) {
            if (password.length() < 6)
                return "Set a new password failed: Password is weak --> password is less than 6 characters!";
            else if (Commands.getMatcherFind(password, Commands.PASSWORD_WEAK_LOWERCASE_ALPHABET) == null)
                return "Set a new password failed: Password is weak --> lowercase alphabet not involved!";
            else if (Commands.getMatcherFind(password, Commands.PASSWORD_WEAK_UPPERCASE_ALPHABET) == null)
                return "Set a new password failed: Password is weak --> uppercase alphabet not involved!";
            else if (Commands.getMatcherFind(password, Commands.PASSWORD_WEAK_NUMBER) == null)
                return "Set a new password failed: Password is weak --> numbers not involved!";
            else
                return "Set a new password failed: Password is weak --> any non number or alphabet not involved!";
        } else {
            JSONArray newUserList = new JSONArray();
            userJsonFileParse("putNewPassword", username, password, null, newUserList);
            try (FileWriter file = new FileWriter("database\\users.json")) {
                file.write(newUserList.toString());
                file.flush();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            return "Set a new password successful!";
        }
    }

    public String decodePasswordRecoveryQuestionMessage(String passwordRecoveryQuestionMessage) throws NoSuchAlgorithmException {
        String firstSecurityQuestion = "1.What is your first school's name?";
        String secondSecurityQuestion = "2.What is your favorite car?";
        String thirdSecurityQuestion = "3.When is your birthday?";
        ArrayList<String> securityQuestions = new ArrayList<>(Arrays.asList(firstSecurityQuestion,
                secondSecurityQuestion,thirdSecurityQuestion));
        for(String question : securityQuestions){
            String secure = SHA256.sha256Security(question);
            if(secure.equals(passwordRecoveryQuestionMessage))
                return question;
        }
        return "Question not found!";
    }
}

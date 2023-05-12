package org.group62.controller;

import org.group62.model.User;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.AbstractList;
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

    public String loginWithoutStayLoggedIn(Matcher matcher, Integer timerFactor) throws NoSuchAlgorithmException,
            InterruptedException {
        String username = matcher.group("username");
        String password = matcher.group("password");
        userJsonFileParse("usernameCheck",username);
        if(!isUsernameExist(username))
            return "Username and password didn’t match! --> Username not exist!";
        else if(!isPasswordCorrect(password,username))
            return "Username and password didn’t match! --> Password is incorrect!";
        else
            return "User logged in successfully!";

    }

    private boolean isPasswordCorrect(String password,String username) throws NoSuchAlgorithmException {
        userJsonFileParse("parseUserInformation",username);
        String passwordInHashSecurity = SHA256.sha256Security(password);
        if(passwordInHashSecurity.equals(currentUserPasswordInHashFormat))
            return true;
        else
            return false;
    }

    private boolean isUsernameExist(String username) {
        if(usernames.size() != 0){
            for(String name : usernames){
                if(name.equals(username))
                    return true;
            }
            return false;
        }
        return false;
    }
    private void updateParseEmpObject(JSONObject emp, JSONArray userList) {
        JSONObject array = new JSONObject();
        JSONObject empobj = (JSONObject) emp.get("User");
        String username = (String) empobj.get("username");
        String passwordSecure = (String) empobj.get("passwordSecure");
        String email = (String) empobj.get("Email");
        String nickname = (String) empobj.get("nickname");
        String slogan = (String) empobj.get("slogan");
        String securityQuestion = (String) empobj.get("securityQuestion");
        String securityQuestionAnswer = (String) empobj.get("securityQuestionAnswer");
        array.put("username", username);
        array.put("passwordSecure", passwordSecure);
        array.put("Email", email);
        array.put("nickname", nickname);
        array.put("slogan", slogan);
        array.put("securityQuestion",securityQuestion);
        array.put("securityQuestionAnswer",securityQuestionAnswer);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("User",array);
        userList.add(jsonObject);

    }
    private void updateUserJsonFile(JSONArray userList){
        JSONParser jsonParser = new JSONParser();
        try(FileReader reader = new FileReader("database\\users.json")){
            Object object = jsonParser.parse(reader);
            JSONArray empList = (JSONArray) object;
            empList.forEach(emp -> updateParseEmpObject((JSONObject) emp,userList));
        }
        catch (FileNotFoundException e){
            System.out.println("Database file not found!");
        }catch (IOException e){
            System.out.println(e.getMessage());
        }catch (ParseException e){
            System.out.println("The database file is empty! The first user added!");
        }
    }
    private void userJsonFileParse(String order,String username){
        JSONParser jsonParser = new JSONParser();
        try(FileReader reader = new FileReader("database\\users.json")) {
            Object object = jsonParser.parse(reader);
            JSONArray empList = (JSONArray) object;
            if (order.equals("usernameCheck"))
                userJsonFileFunctions(empList, "usernameCheck",username);
            else if (order.equals("parseUserInformation")){
                userJsonFileFunctions(empList,"parseUserInformation",username);
            }
        }
        catch (FileNotFoundException e){
            System.out.println("Database file not found!");
        }catch (IOException e){
            System.out.println(e.getMessage());
        }catch (ParseException e){
            System.out.println("The database file is empty! No data to check!");
        }
    }
    private void putDataInToDatabase(String username,String password,String email,String nickname,String slogan,
                                     String securityQuestion,String securityQuestionAnswer){
        String passwordSecure = null;
        try {
            passwordSecure = SHA256.sha256Security(password);
        }catch (NoSuchAlgorithmException e){
            System.out.println(e.getMessage());
        }
        email = email.toLowerCase();
        JSONObject userDetails = new JSONObject();
        userDetails.put("username", username);
        userDetails.put("passwordSecure", passwordSecure);
        userDetails.put("Email", email);
        userDetails.put("nickname", nickname);
        userDetails.put("slogan", slogan);
        userDetails.put("securityQuestion",securityQuestion);
        userDetails.put("securityQuestionAnswer",securityQuestionAnswer);

        JSONObject userObject = new JSONObject();
        userObject.put("User", userDetails);

        JSONArray userList = new JSONArray();
        updateUserJsonFile(userList);
        userList.add(userObject);
        try (FileWriter file = new FileWriter("database\\users.json")) {
            file.write(userList.toString());
            file.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    private void userJsonFileFunctions(JSONArray empList, String function,String username){
        switch (function){
            case "usernameCheck":
                usernames.clear();
                empList.forEach(emp -> usernameParse((JSONObject) emp));
                break;
            case "parseUserInformation":
                empList.forEach(emp -> parseUserInformation((JSONObject) emp,username));
                break;
        }

    }

    private void parseUserInformation(JSONObject emp,String usernameIn) {
        JSONObject empobj = (JSONObject) emp.get("User");
        String username = (String) empobj.get("username");
        if(username.equals(usernameIn)) {
            String passwordSecure = (String) empobj.get("passwordSecure");
            currentUserPasswordInHashFormat = passwordSecure;
            String email = (String) empobj.get("Email");
            String nickname = (String) empobj.get("nickname");
            String slogan = (String) empobj.get("slogan");
            String securityQuestion = (String) empobj.get("securityQuestion");
            String securityQuestionAnswer = (String) empobj.get("securityQuestionAnswer");
            currentUser.setPassword(passwordSecure);
            currentUser.setUsername(username);
            currentUser.setEmail(email);
            currentUser.setNickname(nickname);
            currentUser.setSlogan(slogan);
            currentUser.setPasswordRecoveryAnswer(securityQuestionAnswer);
            currentUser.setPasswordRecoveryQuestion(securityQuestion);
        }
    }

    private void usernameParse(JSONObject emp) {
        JSONObject empobj = (JSONObject) emp.get("User");
        String username = (String) empobj.get("username");
        usernames.add(username);
    }
}

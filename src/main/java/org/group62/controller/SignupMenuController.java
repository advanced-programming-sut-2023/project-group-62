package org.group62.controller;


import org.group62.veiw.Commands;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignupMenuController {
    public SignupMenuController(){

    }

    public String normalCreatUser(Matcher matcher) throws IOException {
        String username = matcher.group("username");
        String password = matcher.group("password");
        String passwordConfirmation = matcher.group("passwordConfirmation");
        String email = matcher.group("email");
        String nickname = matcher.group("nickname");
        String slogan = matcher.group("slogan");

        if (isBlank(username) || isBlank(passwordConfirmation) || isBlank(password) ||
                isBlank(email) || isBlank(nickname) || isBlank(slogan))
            return "Create user failed: Some fields is empty!";
        else if (Commands.getMatcherMatches(username, Commands.USERNAME_VALIDATION) == null)
            return "Create user failed: Invalid username!";
        else if (isUsernameDuplicate(username))
            return "Create user failed: Your username is duplicate!";
        else if (Commands.getMatcherMatches(password, Commands.STRONG_PASSWORD) == null) {
            if(password.length() < 6)
                return "Create user failed: Password is weak --> password is less than 6 characters!";
            else if (Commands.getMatcherFind(password, Commands.PASSWORD_WEAK_LOWERCASE_ALPHABET) == null)
                return "Create user failed: Password is weak --> lowercase alphabet not involved!";
            else if (Commands.getMatcherFind(password, Commands.PASSWORD_WEAK_UPPERCASE_ALPHABET) == null)
                return "Create user failed: Password is weak --> uppercase alphabet not involved!";
            else if (Commands.getMatcherFind(password, Commands.PASSWORD_WEAK_NUMBER) == null)
                return "Create user failed: Password is weak --> numbers not involved!";
            else
                return "Creat user failed: Password is weak --> any non number or alphabet not involved!";
        } else if (!password.equals(passwordConfirmation))
            return "Creat user failed: password confirmation incorrect!";
        else if (isEmailDuplicate(email))
            return "Creat user failed: Your email duplicate!";
        else if (Commands.getMatcherMatches(email, Commands.EMAIL_VALIDATION) == null)
            return "Creat user failed: Email address invalid!";
        else {
            putDataInToDatabase(username,password,email,nickname,slogan);
            return "Creat user successful!";
        }
    }

    public String creatUserWithoutSlogan(Matcher matcher) throws IOException {
        String username = matcher.group("username");
        String password = matcher.group("password");
        String passwordConfirmation = matcher.group("passwordConfirmation");
        String email = matcher.group("email");
        String nickname = matcher.group("nickname");

        if (isBlank(username) || isBlank(passwordConfirmation) || isBlank(password) ||
                isBlank(email) || isBlank(nickname))
            return "Create user failed: Some fields is empty!";
        else if (Commands.getMatcherMatches(username, Commands.USERNAME_VALIDATION) == null)
            return "Create user failed: Invalid username!";
        else if (isUsernameDuplicate(username))
            return "Create user failed: Your username is duplicate!";
        else if (Commands.getMatcherMatches(password, Commands.STRONG_PASSWORD) == null) {
            if(password.length() < 6)
                return "Create user failed: Password is weak --> password is less than 6 characters!";
            else if (Commands.getMatcherFind(password, Commands.PASSWORD_WEAK_LOWERCASE_ALPHABET) == null)
                return "Create user failed: Password is weak --> lowercase alphabet not involved!";
            else if (Commands.getMatcherFind(password, Commands.PASSWORD_WEAK_UPPERCASE_ALPHABET) == null)
                return "Create user failed: Password is weak --> uppercase alphabet not involved!";
            else if (Commands.getMatcherFind(password, Commands.PASSWORD_WEAK_NUMBER) == null)
                return "Create user failed: Password is weak --> numbers not involved!";
            else
                return "Create user failed: Password is weak --> any non number or alphabet not involved!";
        } else if (!password.equals(passwordConfirmation))
            return "Create user failed: password confirmation incorrect!";
        else if (isEmailDuplicate(email))
            return "Create user failed: Your email duplicate!";
        else if (Commands.getMatcherMatches(email, Commands.EMAIL_VALIDATION) == null)
            return "Create user failed: Email address invalid!";
        else {
            putDataInToDatabase(username,password,email,nickname,"User has no slogan!");
            return "Create user successful!";
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
        //Scanner scanner = new Scanner(new File("usernames.txt"));
        ArrayList<String> userList = new ArrayList<>();
        //while (scanner.hasNext()) {
          //  userList.add(scanner.next());
        //}
        //scanner.close();
        for (String name : userList) {
            if (name.equals(username))
                return true;
        }
        return false;
    }

    private Boolean isEmailDuplicate(String email) throws FileNotFoundException {
        //Scanner scanner = new Scanner(new File("emails.txt"));
        ArrayList<String> emailList = new ArrayList<>();
        //while (scanner.hasNext()) {
        //    emailList.add(scanner.next().toUpperCase());
        //}
        //scanner.close();
        for (String name : emailList) {
            if (name.equals(email))
                return true;
        }
        return false;
    }
    private void parseEmpObj(JSONObject emp,JSONArray userList) {
        JSONObject array = new JSONObject();
        JSONObject empobj = (JSONObject) emp.get("User");
        String username = (String) empobj.get("username");
        String passwordSecure = (String) empobj.get("passwordSecure");
        String email = (String) empobj.get("Email");
        String nickname = (String) empobj.get("nickname");
        String slogan = (String) empobj.get("slogan");
        array.put("username", username);
        array.put("passwordSecure", passwordSecure);
        array.put("Email", email);
        array.put("nickname", nickname);
        array.put("slogan", slogan);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("User",array);
        userList.add(jsonObject);
    }
    private void readUserJsonFile(JSONArray userList){
        JSONParser jsonParser = new JSONParser();
        try(FileReader reader = new FileReader("database\\users.json")){
            Object object = jsonParser.parse(reader);
            JSONArray empList = (JSONArray) object;
            empList.forEach(emp -> parseEmpObj((JSONObject) emp,userList));
        }
        catch (FileNotFoundException e){
            System.out.println("Database file not found!");
        }catch (IOException e){
            System.out.println(e.getMessage());
        }catch (ParseException e){
            System.out.println("The database file is empty! The first user added!");
        }
    }
    private void putDataInToDatabase(String username,String password,String email,String nickname,String slogan){
        String passwordSecure = null;
        try {
            passwordSecure = SHA256.sha256Security(password);
        }catch (NoSuchAlgorithmException e){
            System.out.println(e.getMessage());
        }
        JSONObject userDetails = new JSONObject();
        userDetails.put("username", username);
        userDetails.put("passwordSecure", passwordSecure);
        userDetails.put("Email", email);
        userDetails.put("nickname", nickname);
        userDetails.put("slogan", slogan);

        JSONObject userObject = new JSONObject();
        userObject.put("User", userDetails);

        JSONArray userList = new JSONArray();
        readUserJsonFile(userList);
        userList.add(userObject);
        try (FileWriter file = new FileWriter("database\\users.json")) {
            file.write(userList.toString());
            file.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

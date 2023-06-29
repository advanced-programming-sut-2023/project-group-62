package org.group62.controller;


import org.group62.model.StrongHold;
import org.group62.model.User;
import org.group62.veiw.Commands;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.passay.CharacterData;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;

import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignupMenuController {
    ArrayList<String> usernames = new ArrayList<>();
    ArrayList<String> emails = new ArrayList<>();
    Scanner scanner;

    public SignupMenuController() {

    }

    public SignupMenuController(Scanner scanner) {
        this.scanner = scanner;
    }

    public String checkUsername(String username) {// TODO: 6/29/2023 file read
        if (Commands.getMatcherMatches(username, Commands.USERNAME_VALIDATION) == null)
            return "Invalid username!";
        else if (StrongHold.getUserByUsername(username) != null)//if (isUsernameDuplicate(username))
            return "Your username is duplicate!";
        else
            return "good username";
    }

    public String checkPassword(String password) {
        if (Commands.getMatcherMatches(password, Commands.STRONG_PASSWORD) == null) {
            if (password.length() < 6)
                return "Password is weak --> password is less than 6 characters!";
            else if (Commands.getMatcherFind(password, Commands.PASSWORD_WEAK_LOWERCASE_ALPHABET) == null)
                return "Password is weak --> lowercase alphabet not involved!";
            else if (Commands.getMatcherFind(password, Commands.PASSWORD_WEAK_UPPERCASE_ALPHABET) == null)
                return "Password is weak --> uppercase alphabet not involved!";
            else if (Commands.getMatcherFind(password, Commands.PASSWORD_WEAK_NUMBER) == null)
                return "Password is weak --> numbers not involved!";
            else
                return "Password is weak --> any non number or alphabet not involved!";
        } else
            return "strong password";
    }

    public String createUser(String username, String password, String passwordRecoveryQuestion,
                             String passwordRecoveryAnswer, String nickname, String Email, String slogan) {
        if (username.equals("") || password.equals("") ||
                Email.equals("") || nickname.equals("") || slogan.equals(""))
            return "Create user failed: Some fields is empty!";
        else if (Commands.getMatcherMatches(username, Commands.USERNAME_VALIDATION) == null)
            return "Create user failed: Invalid username!";
        else if (isUsernameDuplicate(username))
            return "Create user failed: Your username is duplicate!";
        else if (Commands.getMatcherMatches(password, Commands.STRONG_PASSWORD) == null) {
            if (password.length() < 6)
                return "Create user failed: Password is weak --> password is less than 6 characters!";
            else if (Commands.getMatcherFind(password, Commands.PASSWORD_WEAK_LOWERCASE_ALPHABET) == null)
                return "Create user failed: Password is weak --> lowercase alphabet not involved!";
            else if (Commands.getMatcherFind(password, Commands.PASSWORD_WEAK_UPPERCASE_ALPHABET) == null)
                return "Create user failed: Password is weak --> uppercase alphabet not involved!";
            else if (Commands.getMatcherFind(password, Commands.PASSWORD_WEAK_NUMBER) == null)
                return "Create user failed: Password is weak --> numbers not involved!";
            else
                return "Create user failed: Password is weak --> any non number or alphabet not involved!";
        } else if (isEmailDuplicate(Email))
            return "Create user failed: Your email is duplicate!";
        else if (Commands.getMatcherMatches(Email, Commands.EMAIL_VALIDATION) == null)
            return "Create user failed: Email address invalid!";
        else if (StrongHold.getUserByUsername(username) != null)
            return "Create user failed: username was used";
        else {
            User user = new User(username, password, nickname, Email, slogan, passwordRecoveryQuestion, passwordRecoveryAnswer);
            StrongHold.addUser(user);
            return "user created successful";
        }

    }

    public String normalCreatUser(Matcher matcher) throws IOException, NoSuchAlgorithmException {
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
            if (password.length() < 6)
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
            return "Create user failed: Your email is duplicate!";
        else if (Commands.getMatcherMatches(email, Commands.EMAIL_VALIDATION) == null)
            return "Create user failed: Email address invalid!";
        else {
            String firstSecurityQuestion = "1.What is your first school's name?";
            String secondSecurityQuestion = "2.What is your favorite car?";
            String thirdSecurityQuestion = "3.When is your birthday?";
            return "Create user successful!\n" +
                    "Pick your security question:\n" +
                    firstSecurityQuestion + "\n" +
                    secondSecurityQuestion + "\n" +
                    thirdSecurityQuestion + "\n" +
                    "Help: question pick -q <question-number> -a <answer> -c <answer-confirm>";
        }
    }

    public String creatUserWithoutSlogan(Matcher matcher) throws NoSuchAlgorithmException {
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
            if (password.length() < 6)
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
            return "Create user failed: Your email is duplicate!";
        else if (Commands.getMatcherMatches(email, Commands.EMAIL_VALIDATION) == null)
            return "Create user failed: Email address invalid!";
        else {
            String firstSecurityQuestion = "1.What is your first school's name?";
            String secondSecurityQuestion = "2.What is your favorite car?";
            String thirdSecurityQuestion = "3.When is your birthday?";
            return "Creat user successful!\n" +
                    "Pick your security question:\n" +
                    firstSecurityQuestion + "\n" +
                    secondSecurityQuestion + "\n" +
                    thirdSecurityQuestion + "\n" +
                    "Help: question pick -q <question-number> -a <answer> -c <answer-confirm>";
        }
    }

    public String normalPickSecurityQuestion(boolean isSloganExist, Matcher matcher,
                                             Matcher pickQuestionMatcher, String sloganIn) throws NoSuchAlgorithmException {
        String slogan = sloganIn;
        String firstSecurityQuestion = "1.What is your first school's name?";
        String secondSecurityQuestion = "2.What is your favorite car?";
        String thirdSecurityQuestion = "3.When is your birthday?";
        String username = matcher.group("username");
        String password = matcher.group("password");
        String email = matcher.group("email");
        String nickname = matcher.group("nickname");
        if (isSloganExist)
            slogan = matcher.group("slogan");
        String securityQuestionNumber = pickQuestionMatcher.group("questionNumber");
        String answer = pickQuestionMatcher.group("answer");
        String answerConfirm = pickQuestionMatcher.group("answerConfirm");
        String securityQuestion = "";
        if (securityQuestionNumber.equals("1"))
            securityQuestion = firstSecurityQuestion;
        else if (securityQuestionNumber.equals("2"))
            securityQuestion = secondSecurityQuestion;
        else if (securityQuestionNumber.equals("3"))
            securityQuestion = thirdSecurityQuestion;
        if (Integer.parseInt(securityQuestionNumber) > 3 || Integer.parseInt(securityQuestionNumber) < 1)
            return "Pick Question failed: You hava entered invalid question number!";
        else if (!answerConfirm.equals(answer))
            return "Pick Question failed: Answer confirm incorrect!";
        else {
            putDataInToDatabase(username, password, email, nickname, slogan, securityQuestion, answer);
            return "Picked successfully!";
        }

    }

    public String creatUserWithRandomPassword(Matcher matcher, boolean isSloganExist) {
        String slogan = "User has no slogan!";
        String username = matcher.group("username");
        String email = matcher.group("email");
        String nickname = matcher.group("nickname");
        if (isSloganExist)
            slogan = matcher.group("slogan");

        if (isBlank(username) || isBlank(email) || isBlank(nickname) || isBlank(slogan))
            return "Create user failed: Some fields is empty!";
        else if (Commands.getMatcherMatches(username, Commands.USERNAME_VALIDATION) == null)
            return "Create user failed: Invalid username!";
        else if (isUsernameDuplicate(username))
            return "Create user failed: Your username is duplicate!";
        else if (isEmailDuplicate(email))
            return "Creat user failed: Your email is duplicate!";
        else if (Commands.getMatcherMatches(email, Commands.EMAIL_VALIDATION) == null)
            return "Creat user failed: Email address invalid!";
        else {
            String randomPassword = randomPasswordGenerator();
            return "Your random password is: " + randomPassword +
                    "\nPlease re-enter your password here: ";
        }
    }

    public String creatUserWithRandomSlogan(Matcher matcher) {
        String username = matcher.group("username");
        String password = matcher.group("password");
        String passwordConfirmation = matcher.group("passwordConfirmation");
        String email = matcher.group("email");
        String nickname = matcher.group("nickname");
        String slogan = randomSloganGenerator();
        if (isBlank(username) || isBlank(passwordConfirmation) || isBlank(password) ||
                isBlank(email) || isBlank(nickname))
            return "Create user failed: Some fields is empty!";
        else if (Commands.getMatcherMatches(username, Commands.USERNAME_VALIDATION) == null)
            return "Create user failed: Invalid username!";
        else if (isUsernameDuplicate(username))
            return "Create user failed: Your username is duplicate!";
        else if (Commands.getMatcherMatches(password, Commands.STRONG_PASSWORD) == null) {
            if (password.length() < 6)
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
            return "Create user failed: Your email is duplicate!";
        else if (Commands.getMatcherMatches(email, Commands.EMAIL_VALIDATION) == null)
            return "Create user failed: Email address invalid!";
        else {
            String firstSecurityQuestion = "1.What is your first school's name?";
            String secondSecurityQuestion = "2.What is your favorite car?";
            String thirdSecurityQuestion = "3.When is your birthday?";
            return "Creat user successful!\n\n" +
                    "Your slogan is \"" + slogan + " \"\n\n" +
                    "Pick your security question:\n" +
                    firstSecurityQuestion + "\n" +
                    secondSecurityQuestion + "\n" +
                    thirdSecurityQuestion + "\n" +
                    "Help: question pick -q <question-number> -a <answer> -c <answer-confirm>";
        }
    }


    private Boolean isBlank(String input) {
        Matcher whiteSpaceMatcher = Pattern.compile("\s+").matcher(input);
        if (whiteSpaceMatcher.matches())
            return true;
        else
            return false;
    }

    private boolean isUsernameDuplicate(String username) {
        userJsonFileParse("checkUsername");
        if (usernames.size() != 0) {
            for (String name : usernames) {
                if (username.equals(name))
                    return true;
            }
            return false;
        }
        return false;
    }

    private Boolean isEmailDuplicate(String email) {
        userJsonFileParse("emailCheck");
        if (emails.size() != 0) {
            for (String e : emails) {
                if (e.equals(email))
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
        String securityQuestionSecure = (String) empobj.get("securityQuestionSecure");
        String securityQuestionAnswerSecure = (String) empobj.get("securityQuestionAnswerSecure");
        String highScore = (String) empobj.get("highScore");
        ;
        array.put("username", username);
        array.put("passwordSecure", passwordSecure);
        array.put("Email", email);
        array.put("nickname", nickname);
        array.put("slogan", slogan);
        array.put("securityQuestionSecure", securityQuestionSecure);
        array.put("securityQuestionAnswerSecure", securityQuestionAnswerSecure);
        array.put("highScore", highScore);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("User", array);
        userList.add(jsonObject);

    }

    private void updateUserJsonFile(JSONArray userList) {
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader("database\\users.json")) {
            Object object = jsonParser.parse(reader);
            JSONArray empList = (JSONArray) object;
            empList.forEach(emp -> updateParseEmpObject((JSONObject) emp, userList));
        } catch (FileNotFoundException e) {
            System.out.println("Database file not found!");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ParseException e) {
            System.out.println("The database file is empty! The first user added!");
        }
    }

    private void userJsonFileParse(String order) {
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader("database\\users.json")) {
            Object object = jsonParser.parse(reader);
            JSONArray empList = (JSONArray) object;
            if (order.equals("checkUsername"))
                userJsonFileFunctions(empList, "checkUsername");
            else if (order.equals("emailCheck")) {
                userJsonFileFunctions(empList, "emailCheck");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Database file not found!");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ParseException e) {
            System.out.println("The database file is empty! No data to check!");
        }
    }

    private void putDataInToDatabase(String username, String password, String email, String nickname, String slogan,
                                     String securityQuestion, String securityQuestionAnswer) {
        String passwordSecure = null;
        String recoveryPasswordAnswerSecure = null;
        String recoveryPasswordQuestionSecure = null;
        try {
            passwordSecure = SHA256.sha256Security(password);
            recoveryPasswordAnswerSecure = SHA256.sha256Security(securityQuestionAnswer);
            recoveryPasswordQuestionSecure = SHA256.sha256Security(securityQuestion);
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
        }
        email = email.toLowerCase();
        JSONObject userDetails = new JSONObject();
        userDetails.put("username", username);
        userDetails.put("passwordSecure", passwordSecure);
        userDetails.put("Email", email);
        userDetails.put("nickname", nickname);
        userDetails.put("slogan", slogan);
        userDetails.put("securityQuestionSecure", recoveryPasswordQuestionSecure);
        userDetails.put("securityQuestionAnswerSecure", recoveryPasswordAnswerSecure);
        userDetails.put("highScore", "empty");

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

    private void userJsonFileFunctions(JSONArray empList, String function) {
        switch (function) {
            case "checkUsername":
                usernames.clear();
                empList.forEach(emp -> usernameParse((JSONObject) emp));
                break;
            case "emailCheck":
                emails.clear();
                empList.forEach(emp -> emailParse((JSONObject) emp));
                break;
        }

    }

    private void usernameParse(JSONObject emp) {
        JSONObject empobj = (JSONObject) emp.get("User");
        String username = (String) empobj.get("username");
        usernames.add(username);
    }

    private void emailParse(JSONObject emp) {
        JSONObject empobj = (JSONObject) emp.get("User");
        String email = (String) empobj.get("Email");
        emails.add(email);
    }

    public String randomPasswordGenerator() {
        CharacterRule lowerCaseRule = new CharacterRule(EnglishCharacterData.LowerCase);
        lowerCaseRule.setNumberOfCharacters(2);
        CharacterRule upperCaseRule = new CharacterRule(EnglishCharacterData.UpperCase);
        upperCaseRule.setNumberOfCharacters(2);
        CharacterRule digitRule = new CharacterRule(EnglishCharacterData.Digit);
        digitRule.setNumberOfCharacters(2);
        CharacterData specialChars = new CharacterData() {
            @Override
            public String getErrorCode() {
                return null;
            }

            @Override
            public String getCharacters() {
                return "!@#$&*%^()_\\-=\\]+}{\\[~`'\\\";:?\\/><.,|";
            }
        };
        CharacterRule specialRule = new CharacterRule(specialChars);
        specialRule.setNumberOfCharacters(2);
        PasswordGenerator passGen = new PasswordGenerator();
        String password = passGen.generatePassword(8, specialRule, lowerCaseRule, upperCaseRule, digitRule);
        return password;
    }

    public String isRandomPasswordEqualUserPassword(String randomPassword, String userPassword) {
        if (randomPassword.equals(userPassword))
            return "successful";
        else
            return "error";
    }

    public String randomPasswordPickSecurityQuestion(Matcher matcher, Matcher pickQuestionMatcher, String randomPassword,
                                                     Boolean isSloganExist, Boolean isSloganRandom, String randomSlogan) {
        String slogan = "User has no slogan!";
        if (isSloganRandom)
            slogan = randomSlogan;
        String username = matcher.group("username");
        String nickname = matcher.group("nickname");
        String email = matcher.group("email");
        if (isSloganExist)
            slogan = matcher.group("slogan");
        String securityQuestionNumber = pickQuestionMatcher.group("questionNumber");
        String answer = pickQuestionMatcher.group("answer");
        String answerConfirm = pickQuestionMatcher.group("answerConfirm");
        String firstSecurityQuestion = "1.What is your first school's name?";
        String secondSecurityQuestion = "2.What is your favorite car?";
        String thirdSecurityQuestion = "3.When is your birthday?";
        String securityQuestion = null;
        if (securityQuestionNumber.equals("1"))
            securityQuestion = firstSecurityQuestion;
        else if (securityQuestionNumber.equals("2"))
            securityQuestion = secondSecurityQuestion;
        else if (securityQuestionNumber.equals("3"))
            securityQuestion = thirdSecurityQuestion;
        if (Integer.parseInt(securityQuestionNumber) > 3 || Integer.parseInt(securityQuestionNumber) < 1)
            return "Pick Question failed: You hava entered invalid question number!";
        else if (!answerConfirm.equals(answer))
            return "Pick Question failed: Answer confirm incorrect!";
        else {
            putDataInToDatabase(username, randomPassword, email, nickname, slogan, securityQuestion, answer);
            return "Picked successfully!";
        }

    }

    public String randomSloganGenerator() {
        ArrayList<String> randomSlogans = new ArrayList<>(Arrays.asList("My men approach, you will trouble me no more",
                "Damn you Boy! I will have revenge!", "I will tear down your castle, stone by stone if i have to! But i will have your head!",
                "Soon you will see what it means to be Real Warfare!", "Is there no one who will rid me of your irritating presence?!",
                "Your time on this earth is limited. Time to say your prayers!", "I will kill you soon! You and all your vermin!",
                "I shall have my revenge, in this life or the next"));
        Random random = new Random();
        int randomIndex = random.nextInt(0, 8);
        return randomSlogans.get(randomIndex);
    }
}

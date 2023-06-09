package org.group62.model;

public class User {
    private String username;
    private String password;
    private String nickname;
    private String email;
    private String slogan;
    private String passwordRecoveryQuestion;
    private String passwordRecoveryAnswer;
    private String highScore = "0";
    private String rank;
    public User(){

    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public User(String username, String password, String nickname, String email) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
    }

    public User(String username, String password, String nickname, String email,
                String slogan, String passwordRecoveryQuestion, String passwordRecoveryAnswer) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.slogan = slogan;
        this.passwordRecoveryQuestion = passwordRecoveryQuestion;
        this.passwordRecoveryAnswer = passwordRecoveryAnswer;
    }

    public String getPasswordSecure() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isPassWordCorrect(String password) {
        return this.password.equals(password);
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public void setPasswordRecoveryQuestion(String passwordRecoveryQuestion) {
        this.passwordRecoveryQuestion = passwordRecoveryQuestion;
    }

    public void setPasswordRecoveryAnswer(String passwordRecoveryAnswer) {
        this.passwordRecoveryAnswer = passwordRecoveryAnswer;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setHighScore(String highScore) {
        this.highScore = highScore;
    }

    public String getUsername() {
        return username;
    }

    public String getNickname() {
        return nickname;
    }

    public String getEmail() {
        return email;
    }

    public String getSlogan() {
        return slogan;
    }

    public String getPasswordRecoveryQuestion() {
        return passwordRecoveryQuestion;
    }

    public String getPasswordRecoveryAnswer() {
        return passwordRecoveryAnswer;
    }

    public String getHighScore() {
        return highScore;
    }
}

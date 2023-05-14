package org.group62.model;

public class User {
    private String username;
    private String password;
    private String nickname;
    private String email;
    private String slogan;
    private String passwordRecoveryQuestion;
    private String passwordRecoveryAnswer;
    private int highScore;
    private int rank;
    public User(){

    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public User(String username, String password, String nickname, String email) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
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

    public void setHighScore(int highScore) {
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

    public int getHighScore() {
        return highScore;
    }
}

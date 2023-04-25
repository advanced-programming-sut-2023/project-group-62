package org.group62.Model;

import java.util.ArrayList;

public class StrongHold {
    private static final ArrayList<User> users = new ArrayList<>();
    private static User currentUser;
    private static Play currentPlay;

    public static void addUser(User user) {
        users.add(user);
    }

    public static int getRankOfUser(User user) {
        int rank = 1;
        for (User anotherUser : users) {
            if (anotherUser.getHighScore() > user.getHighScore())
                rank++;
        }
        return rank;
    }

    public static void setCurrentUser(User currentUser) {
        StrongHold.currentUser = currentUser;
    }

    public static void setCurrentPlay(Play currentPlay) {
        StrongHold.currentPlay = currentPlay;
    }

    public static ArrayList<User> getUsers() {
        return users;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static Play getCurrentPlay() {
        return currentPlay;
    }
}

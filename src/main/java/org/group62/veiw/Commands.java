package org.group62.veiw;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Commands {
    CREAT_USER("^\\s*user\\s+create(?<args>(?=.+(-u|--username)\\s+(?<username>\\S+))" +
            "(?=.+(-p|--password)\\s+(?<password>\\S+)\\s+(?<passwordConfirmation>\\S+))(?=.+(-email)" +
            "\\s+(?<email>\\S+))(?=.+(-n|--nickname)\\s+(\\S+))((?=.+(-s|--slogan)\\s+(?<slogan>\\S+)))?.+)\\s*$"),
    QUESTION_PICK("^\\s*question\\s+pick(?<args>(?=.+(-q)\\s+(?<questionNumber>\\S+))(?=.+(-a)\\s+" +
            "(?<answer>\\S+))(?=.+(-c)\\s+(?<answerConfirm>\\S+)).+)\\s*$"),
    CREAT_USER_WITH_RANDOM_PASSWORD("^\\s*user\\s+creat(?<args>(?=.+(-u|--username)\\s+(?<username>\\S+))" +
            "(?=.+(-n|--nickname)\\s+(?<nickname>\\S+))(?=.+(-p)\\s+random)(?=.+(-e|--email)\\s+(?<email>\\S+)).+)\\s*$"),
    CREAT_USER_WITH_RANDOM_SLOGAN("^\\s*user\\s+creat(?<args>(?=.+(-u|--username)\\s+(?<username>\\S+))" +
            "(?=.+(-n|--nickname)\\s+(?<nickname>\\S+))(?=.+(-p)\\s+random)(?=.+(-s)\\s+random)(?=.+(-e|--email)" +
            "\\s+(?<email>\\S+)).+)\\s*$"),
    USERNAME_VALIDATION("[a-zA-Z0-9_]+"),
    STRONG_PASSWORD("^(?=.*[A-Z])(?=.*[!@#$&*%^()_\\-=\\]+}{[~`'\";:?\\/><.,|])(?=.*[0-9])(?=.*[a-z])(?=\\S+$).{6,}$"),
    EMAIL_VALIDATION("\\S+@\\S+.\\S+"),
    USER_LOGIN("^\\s*user\\s+login(?<args>(?=.+(-u|--username)\\s+(?<username>\\S+))(?=.+(-p|--password)\\s+" +
            "(?<password>\\S+)).+)\\s*$"),
    USER_STAY_LOGGED_IN("^\\s*user\\s+login(?<args>(?=.+(-u|--username)\\s+(?<username>\\S+))(?=.+(-p|--password)" +
            "\\s+(?<password>\\S+))(?=.+(--stay-logged-in)).+)\\s*$"),
    FORGOT_MY_PASSWORD("^\\s*forgot\\s+my\\s+password\\s+(-u|--username)\\s+(?<username>\\S+)\\s*$"),
    USER_LOGOUT("^\\s*user\\s+logout\\s*$"),
    PROFILE_USERNAME_CHANGE("^\\s*profile\\s+change\\s+(-u|--username)\\s+(?<username>\\S+)\\s*$"),
    PROFILE_NICKNAME_CHANGE("^\\s*profile\\s+change\\s+(-n|--nickname)\\s+(?<nickname>\\S+)\\s*$"),
    PROFILE_PASSWORD_CHANGE("^\\s*profile\\s+change(?<args>(?=.+(-o)\\s+(?<oldPassword>\\S+))(?=.+(-n)" +
            "\\s+(?<newPassword>\\S+)).+)\\s*$"),
    PROFILE_EMAIL_CHANGE("^\\s*profile\\s+change\\s+(-e|--email)\\s+(?<email>\\S+)\\s*$"),
    PROFILE_SLOGAN_CHANGE("^\\s*profile\\s+change\\s+slogan\\s+(-s)\\s+(?<slogan>\\S+)\\s*$"),
    PROFILE_REMOVE_SLOGAN("^\\s*profile\\s+remove\\s+slogan\\s*$"),
    PROFILE_DISPLAY_HIGH_SCORE("^\\s*profile\\s+display\\s+highscore\\s*$"),
    PROFILE_DISPLAY_RANK("^\\s*profile\\s+display\\s+rank\\s*$"),
    PROFILE_DISPLAY_SLOGAN("^\\s*profile\\s+display\\s+slogan\\s*$"),
    PROFILE_DISPLAY("^\\s*profile\\s+display\\s*$"),
    SHOW_MAP("^\\s*show\\s+map(?<args>(?=.+(-x)\\s+(?<xCoordinates>\\S+))" +
            "(?=.+(-y)\\s+(?<yCoordinates>\\S+)).+)\\s*$"),
    MAP_MOVE("^\\s*map(?<args>(?=.+((up)\\s+(?<upValue>\\d+)))?(?=.+((down)\\s+(?<downValue>\\d+)))?" +
            "(?=.+((left)\\s+(?<leftValue>\\d+)))?(?=.+((right)\\s+(?<rightValue>\\d+)))?.+)\\s*$"),
    SHOW_DETAILS("^\\s*show\\s+details(?<args>(?=.+(-x)\\s+(?<xCoordinates>\\S+))(?=.+(-y)\\s+" +
            "(?<yCoordinates>\\S+)).+)\\s0*$"),
    EXIT("^\\s*exit\\s*$"),
    SHOW_POPULARITY_FACTORS("^\\s*show\\s+popularity\\s+factors\\s*$"),
    SHOW_POPULARITY("^\\s*show\\s+popularity\\s*$"),
    SHOW_FOOD_LIST("^\\s*show\\s+food\\s+list\\s*$"),
    FOOD_RATE("^\\s*food\\s+rate\\s+-r\\s+(?<rateNumber>\\d+)\\s*$"),
    FOOD_RATE_SHOW("^\\s*food\\s+rate\\s+show\\s*$"),
    TAX_RATE("^\\s*tax\\s+rate\\s+-r(?<rateNumber>\\d+)\\s*$"),
    TAX_RATE_SHOW("^\\s*tax\\s+rate\\s+show\\s*$"),
    FEAR_RATE("^\\s*fear\\s+rate\\s+-r(?<rateNumber>\\d+)\\s*$"),
    DROP_BUILDING("^\\s*drop\\s+building(?<args>(?=.+(-x)\\s+(?<xCoordinates>\\d+))" +
            "(?=.+(-y)\\s+(?<yCoordinates>\\d+))(?=.+(-type)\\s+(?<type>.+)).+)\\s*$"),
    SELECT_BUILDING("^^\\s*select\\s+building(?<args>(?=.+(-x)\\s+(?<xCoordinates>\\d+))" +
            "(?=.+(-y)\\s+(?<yCoordinates>\\d+)).+)\\s*$$"),
    CREATE_UNIT("^\\s*createunit(?<args>(?=.+(-t)\\s+(?<type>.+))(?=.+(-c)\\s+(?<count>\\d+)).+)\\s*$"),
    REPAIR("^\\s*repair\\s*$"),
    SELECT_UNIT("^\\s*select\\s+unit(?<args>(?=.+(-x)\\s+(?<xCoordinates>\\d+))(?=.+(-y)\\s+" +
            "(?<yCoordinates>\\d+)).+)\\s*$"),
    MOVE_UNIT("^\\s*move\\s+unit\\s+to(?<args>(?=.+(-x)\\s+(?<xCoordinates>\\d+))(?=.+(-y)\\s+" +
            "(?<yCoordinates>\\d+)).+)\\s*$"),
    PATROL_UNIT("^\\s*patrol\\s+unit(?<args>(?=.+(-x1)\\s+(?<x1Coordinates>\\d+))(?=.+(-y1)\\s+(?<y1Coordinates>\\d+))" +
            "(?=.+(-x2)\\s+(?<x2Coordinates>\\d+))(?=.+(-y2)\\s+(?<y2Coordinates>\\d+)).+)\\s*$"),
    SET_STATE("^\\s*set(?<args>(?=.+(-x)\\s+(?<xCoordinates>\\d+))(?=.+(-y)\\s+(?<yCoordinates>\\d+))" +
            "(?=.+(-s)\\s+(?<state>.+)).+)\\s*$"),
    ATTACK_COORDINATES("^\\s*attack\\s+-e\\s+(?<enemyx>\\d+)\\d+(?<enemyy>\\d+)\\s*$"),
    ATTACK("^\\s*attack(?<args>(?=.+(-x)\\s+(?<xCoordinates>\\d+))(?=.+(-y)\\s+(?<yCoordinates>\\d+)).+)\\s*$"),
    POUR_OIL("^\\s*pour\\s+oil\\s+-d\\s+(?<direction>.+)\\s*$"),
    DIG_TUNNEL("^\\s*dig\\s+tunnel(?<args>(?=.+(-x)\\s+(?<xCoordinates>\\d+))" +
            "(?=.+(-y)\\s+(?<yCoordinates>\\d+)).+)\\s*$"),
    BUILD("^\\s*build\\s+-q\\s+(?<equipmentName>.+)\\s*$"),
    DISBAND_UNIT("^\\s*disband\\s+unit\\s*$"),
    SET_BLOCK_TEXTURE("^\\s*settexture(?<args>(?=.+(-x)\\s+(?<xCoordinates>\\d+))(?=.+(-y)\\s+(?<yCoordinates>\\d+))" +
            "(?=.+(-t)\\s+(?<type>.+)).+)\\s*$"),
    SET_RECTANGLE_TEXTURE("^\\s*settexture(?<args>(?=.+(-x1)\\s+(?<x1Coordinates>\\d+))(?=.+(-y1)\\s+(?<y1Coordinates>\\d+))" +
            "(?=.+(-x2)\\s+(?<x2Coordinates>\\d+))(?=.+(-y2)\\s+(?<y2Coordinates>\\d+))(?=.+(-t)\\s+(?<type>.+)).+)\\s*$"),
    CLEAR("^\\s*clear(?<args>(?=.+(-x)\\s+(?<xCoordinates>\\d+))(?=.+(-y)\\s+(?<yCoordinates>\\d+)).+)\\s*$"),
    DROP_ROCK("^\\s*droprock(?<args>(?=.+(-x)\\s+(?<xCoordinates>\\d+))(?=.+(-y)\\s+(?<yCoordinates>\\d+))(?=.+(-d)" +
            "\\s+(?<direction>\\w)).+)\\s*$"),
    DROP_TREE("^\\s*droptree(?<args>(?=.+(-x)\\s+(?<xCoordinates>\\d+))(?=.+(-y)\\s+(?<yCoordinates>\\d+))(?=.+(-t)" +
            "\\s+(?<type>.+)).+)\\s*$"),
    DROP_UNIT("^\\s*dropunit(?<args>(?=.+(-x)\\s+(?<xCoordinates>\\d+))(?=.+(-y)\\s+(?<yCoordinates>\\d+))" +
            "(?=.+(-t)\\s+(?<type>.+))(?=.+(-c)\\s+(?<count>\\d+)).+)\\s*$"),
    TRADE("^\\s*trade(?<args>(?=.+(-t)\\s+(?<resourceType>.+))(?=.+(-a)\\s+(?<resourceAmount>.+))" +
            "(?=.+(-p)\\s+(?<price>.+))(?=.+(-m)\\s+(?<message>.+)).+)\\s*$"),
    TRADE_LIST("^\\s*trade\\s+list\\s*$"),
    TRADE_ACCEPT("^\\s*trade\\s+accept(?<args>(?=.+(-i)\\s+(?<id>\\d+))(?=.+(-m)\\s+(?<message>.+)).+)\\s*$"),
    TRADE_HISTORY("^\\s*trade\\s+history\\s*$"),
    SHOW_PRICE_LIST("^\\s*show\\s+price\\s+list\\s*$"),
    BUY("^\\s*buy(?<args>(?=.+(-i)\\s+(?<itemName>.+))(?=.+(-a)\\s+(?<itemAmount>\\d+)).+)\\s*$"),
    SELL("^\\s*sell(?<args>(?=.+(-i)\\s+(?<itemName>.+))(?=.+(-a)\\s+(?<itemAmount>\\d+)).+)\\s*$");
    private String regex;
    private Commands(String regex){
        this.regex = regex;
    }
    public Matcher getMatcher(String input,Commands mainRegex){
        Matcher matcher = Pattern.compile(mainRegex.regex).matcher(input);
        if(matcher.matches())
            return matcher;
        else
            return null;
    }
}

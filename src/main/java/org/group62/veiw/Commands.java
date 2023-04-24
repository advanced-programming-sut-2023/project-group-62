package org.group62.veiw;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Commands {
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

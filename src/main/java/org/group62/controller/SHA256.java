package org.group62.controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256 {
    public static String sha256Security(String inputString) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(inputString.getBytes());
        byte[] digets = messageDigest.digest();
        StringBuffer stringBuffer = new StringBuffer();
        for(byte b : digets){
            stringBuffer.append(String.format("%02x",b & 0xff));
        }
        return stringBuffer.toString();
    }
}

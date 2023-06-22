package com.project.cafe.util.encrypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class OpenCrypt {

    public static String getSHA256(String source, String salt) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(source.getBytes());
        md.update(salt.getBytes());
        byte[] byteData = md.digest();
        String hashMsg = byteArrayToHex(byteData);
        return hashMsg;
    }

    public static String byteArrayToHex(byte[] ba) {
        if (ba == null || ba.length == 0) {
            return null;
        }

        StringBuffer sb = new StringBuffer(ba.length * 2);
        String hexNumber;
        for (int x = 0; x < ba.length; x++) {
            hexNumber = "0" + Integer.toHexString(0xff & ba[x]);
            sb.append(hexNumber.substring(hexNumber.length() - 2));
        }
        return sb.toString();
    }

}

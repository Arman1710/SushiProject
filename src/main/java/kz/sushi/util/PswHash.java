package kz.sushi.util;

import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PswHash {
    private static Logger log = Logger.getLogger(PswHash.class.getName());
    public String md5Hash(String password) {
        byte[] digest = new byte[0];
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.reset();
            md5.update(password.getBytes("UTF-8"));
            digest = md5.digest();

        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            log.error(e);
        }
        BigInteger bigInt = new BigInteger(1, digest);
        StringBuilder md5Hex = new StringBuilder(bigInt.toString(16));

        while (md5Hex.length() < 32) {
            md5Hex.insert(0, "0");

        }
        return md5Hex.toString();
    }
}

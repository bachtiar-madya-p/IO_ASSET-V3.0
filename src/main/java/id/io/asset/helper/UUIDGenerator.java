/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.io.asset.helper;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author permadi
 */
public class UUIDGenerator {

    private static final String APP_KEY = "asset";

    public static void main(String[] args) {

        int x = 10;

        for (int i = 0; i < x; i++) {
            System.out.println("Type 4 : " + generateType4UUID());

        }

        System.out.println("Type 5 : " + generateType5UUID(APP_KEY, "asset1"));
        System.out.println("Type 5 : " + generateType5UUID(APP_KEY, "asset1"));
        System.out.println("Type 5 : " + generateType5UUID(APP_KEY, "asset1"));
        System.out.println("Type 5 : " + generateType5UUID(APP_KEY, "asset4"));
        System.out.println("Type 5 : " + generateType5UUID(APP_KEY, "asset5"));

    }

    public static UUID generateType4UUID() {
        UUID uuid = UUID.randomUUID();
        return uuid;
    }

    public static UUID generateType5UUID(String namespace, String name) {
        String source = namespace + name;
        byte[] bytes;
        UUID uuid = null;
        try {
            bytes = source.getBytes("UTF-8");
            uuid = type5UUIDFromBytes(bytes);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(UUIDGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }

        return uuid;
    }

    public static UUID type5UUIDFromBytes(byte[] name) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException nsae) {
            throw new InternalError("SHA-1 not supported", nsae);
        }
        byte[] bytes = Arrays.copyOfRange(md.digest(name), 0, 16);
        bytes[6] &= 0x0f;
        /* clear version        */
        bytes[6] |= 0x50;
        /* set to version 5     */
        bytes[8] &= 0x3f;
        /* clear variant        */
        bytes[8] |= 0x80;
        /* set to IETF variant  */
        return constructType5UUID(bytes);
    }

    private static UUID constructType5UUID(byte[] data) {
        long msb = 0;
        long lsb = 0;
        assert data.length == 16 : "data must be 16 bytes in length";

        for (int i = 0; i < 8; i++) {
            msb = (msb << 8) | (data[i] & 0xff);
        }

        for (int i = 8; i < 16; i++) {
            lsb = (lsb << 8) | (data[i] & 0xff);
        }
        return new UUID(msb, lsb);
    }

}


package id.io.asset.manager;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import id.io.asset.util.log.AppLogger;

public class EncryptionManager {

    private static final AppLogger logger = new AppLogger(EncryptionManager.class);
    private static final String ALGO = "PBEWithMD5AndDES";
    private static final String KEY = "@#1D%7887F&108!8780FEF*88&%!@d97d0!";
    private static final int ITRCOUNT = 17;

    // 8-byte Salt
    private static final byte[] SALT =
            {(byte) 0xA9, (byte) 0x9B, (byte) 0xC8, (byte) 0x32, (byte) 0x56, (byte) 0x35, (byte) 0xE3, (byte) 0x03};

    private static Cipher ecipher;
    private static Cipher dcipher;

    private static MessageDigest digest;

    public static synchronized void init() {
        final String methodName = "init";
        try {
            // Create the key
            KeySpec keySpec = new PBEKeySpec(KEY.toCharArray(), SALT, ITRCOUNT);
            SecretKey key = SecretKeyFactory.getInstance(ALGO).generateSecret(keySpec);
            ecipher = Cipher.getInstance(key.getAlgorithm());
            dcipher = Cipher.getInstance(key.getAlgorithm());

            // Prepare the parameter to the ciphers
            AlgorithmParameterSpec paramSpec = new PBEParameterSpec(SALT, ITRCOUNT);

            // Create the ciphers
            ecipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
            dcipher.init(Cipher.DECRYPT_MODE, key, paramSpec);

            digest = MessageDigest.getInstance("SHA-256");

        } catch (InvalidKeySpecException ex) {
            logger.error(methodName, ex.getMessage());
        } catch (NoSuchAlgorithmException ex) {
            logger.error(methodName, ex.getMessage());
        } catch (NoSuchPaddingException ex) {
            logger.error(methodName, ex.getMessage());
        } catch (InvalidAlgorithmParameterException ex) {
            logger.error(methodName, ex.getMessage());
        } catch (InvalidKeyException ex) {
            logger.error(methodName, ex.getMessage());
        }
    }

    public static synchronized void shutdown() {
        ecipher = null;
        dcipher = null;
    }

    /**
     * Encrypts the given plaintext and returns an encrypted ciphertext.
     *
     * @param plaintext The text to be encrypted.
     * @return A ciphertext
     */
    public static synchronized String encrypt(String plaintext) {
        final String methodName = "encrypt";

        try {
            // Encode the string into bytes using utf-8
            byte[] utf8 = plaintext.getBytes("UTF8");

            // Encrypt
            byte[] enc = ecipher.doFinal(utf8);

            // Encode bytes to base64 to get a string
            return Base64.getEncoder().encodeToString(enc);
        } catch (UnsupportedEncodingException ex) {
            logger.error(methodName, ex.getMessage());
        } catch (BadPaddingException ex) {
            logger.error(methodName, ex.getMessage());
        } catch (IllegalBlockSizeException ex) {
            logger.error(methodName, ex.getMessage());
        }
        return null;
    }

    /**
     * Decrypts the given ciphertext and returns an decrypted plaintext.
     *
     * @param ciphertext The text to be decrypted.
     * @return A plaintext
     */
    public static synchronized String decrypt(String ciphertext) {
        final String methodName = "decrypt";

        try {
            // Decode base64 to get bytes
            byte[] dec = Base64.getDecoder().decode(ciphertext.getBytes("UTF-8"));

            // Decrypt
            byte[] utf8 = dcipher.doFinal(dec);

            // Decode using utf-8
            return new String(utf8, "UTF8");

        } catch (UnsupportedEncodingException ex) {
            logger.error(methodName, ex.getMessage());
        } catch (BadPaddingException ex) {
            logger.error(methodName, ex.getMessage());
        } catch (IllegalBlockSizeException ex) {
            logger.error(methodName, ex.getMessage());
        }
        return "";
    }

    public static String hash(String plainText) {
        byte[] output = digest.digest(plainText.getBytes());
        return Base64.getEncoder().encodeToString(output);
    }
}

package pl.consdata.security.cryptofail.fails;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import static pl.consdata.security.cryptofail.CryptographicConstants.AES_IV_LENGTH;
import static pl.consdata.security.cryptofail.CryptographicConstants.AES_KEY_LENGTH;

/**
 * https://stackoverflow.com/questions/992019/java-256-bit-aes-password-based-encryptionz
 */
public class FailCBCAES implements Fail {

    @Override
    public byte[] encrypt(byte[] key, byte[] iv, byte[] plaintext) throws Exception {
        SecretKey secret = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        cipher.init(Cipher.ENCRYPT_MODE, secret, ivSpec);
        return cipher.doFinal(plaintext);
    }

    @Override
    public byte[] decrypt(byte[] key, byte[] iv, byte[] ciphertext) throws Exception {
        SecretKey secret = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        cipher.init(Cipher.DECRYPT_MODE, secret, ivSpec);
        return cipher.doFinal(ciphertext);
    }

    @Override
    public int getKeyLength() {
        return AES_KEY_LENGTH;
    }

    @Override
    public int getIVLength() {
        return AES_IV_LENGTH;
    }
}

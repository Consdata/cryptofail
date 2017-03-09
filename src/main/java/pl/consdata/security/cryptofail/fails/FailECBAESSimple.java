package pl.consdata.security.cryptofail.fails;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

import static pl.consdata.security.cryptofail.CryptographicConstants.AES_IV_LENGTH;
import static pl.consdata.security.cryptofail.CryptographicConstants.AES_KEY_LENGTH;

/**
 * https://stackoverflow.com/questions/23561104/how-to-encrypt-and-decrypt-string-with-my-passphrase-in-java-pc-not-mobile-plat/30591269
 */
public class FailECBAESSimple implements Fail {

    public byte[] encrypt(byte[] key, byte[] iv, byte[] plaintext) throws Exception {
        Key aesKey = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, aesKey);
        return cipher.doFinal(plaintext);
    }

    @Override
    public byte[] decrypt(byte[] key, byte[] iv, byte[] ciphertext) {
        return new byte[0];
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

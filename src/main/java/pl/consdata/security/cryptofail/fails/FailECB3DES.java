package pl.consdata.security.cryptofail.fails;


import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import java.security.spec.KeySpec;

import static pl.consdata.security.cryptofail.CryptographicConstants.DESEDE_KEY_LENGTH;
import static pl.consdata.security.cryptofail.CryptographicConstants.DES_IV_LENGTH;

/**
 * https://stackoverflow.com/questions/10303767/encrypt-and-decrypt-in-java
 */
public class FailECB3DES implements Fail {

    @Override
    public byte[] encrypt(byte[] key, byte[] iv, byte[] plaintext) throws Exception {
        KeySpec ks = new DESedeKeySpec(key);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("DESede");
        Cipher cipher = Cipher.getInstance("DESede");
        SecretKey secretKey = skf.generateSecret(ks);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        return cipher.doFinal(plaintext);
    }

    @Override
    public byte[] decrypt(byte[] key, byte[] iv, byte[] ciphertext) {
        return new byte[0];
    }

    @Override
    public int getKeyLength() {
        return DESEDE_KEY_LENGTH;
    }

    @Override
    public int getIVLength() {
        return DES_IV_LENGTH;
    }
}

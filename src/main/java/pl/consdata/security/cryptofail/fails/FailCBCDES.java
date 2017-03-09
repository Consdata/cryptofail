package pl.consdata.security.cryptofail.fails;

import static pl.consdata.security.cryptofail.CryptographicConstants.DES_IV_LENGTH;
import static pl.consdata.security.cryptofail.CryptographicConstants.DES_KEY_LENGTH;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * https://stackoverflow.com/questions/1205135/how-to-encrypt-string-in-java
 */
public class FailCBCDES implements Fail {

    @Override
    public byte[] encrypt(byte[] key, byte[] iv, byte[] plaintext) throws Exception {
        SecretKeySpec keySpec = new SecretKeySpec(key, "DES");
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");

        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
        byte[] encrypted = new byte[cipher.getOutputSize(plaintext.length)];
        int enc_len = cipher.update(plaintext, 0, plaintext.length, encrypted, 0);
        cipher.doFinal(encrypted, enc_len);

        return encrypted;
    }

    @Override
    public byte[] decrypt(byte[] key, byte[] iv, byte[] ciphertext) {
        return new byte[0];
    }

    @Override
    public int getKeyLength() {
        return DES_KEY_LENGTH;
    }

    @Override
    public int getIVLength() {
        return DES_IV_LENGTH;
    }
}

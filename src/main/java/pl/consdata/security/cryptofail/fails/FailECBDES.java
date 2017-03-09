package pl.consdata.security.cryptofail.fails;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import static pl.consdata.security.cryptofail.CryptographicConstants.DES_IV_LENGTH;
import static pl.consdata.security.cryptofail.CryptographicConstants.DES_KEY_LENGTH;

/**
 * https://stackoverflow.com/questions/4487525/encrypt-and-decrypt-a-string-in-java/4487541
 */
public class FailECBDES implements Fail {
    public byte[] encrypt(byte[] key, byte[] iv, byte[] plaintext) throws Exception {
        SecretKey keyDES = KeyGenerator.getInstance("DES").generateKey();

        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.ENCRYPT_MODE, keyDES);
        return cipher.doFinal(plaintext);
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

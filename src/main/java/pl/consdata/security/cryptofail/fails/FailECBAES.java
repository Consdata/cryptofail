package pl.consdata.security.cryptofail.fails;

import static pl.consdata.security.cryptofail.CryptographicConstants.AES_IV_LENGTH;
import static pl.consdata.security.cryptofail.CryptographicConstants.AES_KEY_LENGTH;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * https://stackoverflow.com/questions/7762771/how-do-i-encrypt-decrypt-a-string-with-another-string-as-a-password/7762882
 */
public class FailECBAES implements Fail {

    public byte[] encrypt(byte[] key, byte[] iv, byte[] plaintext) throws Exception {
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);

        byte[] cipherText = new byte[cipher.getOutputSize(plaintext.length)];
        int ctLength = cipher.update(plaintext, 0, plaintext.length, cipherText, 0);
        cipher.doFinal(cipherText, ctLength);
        return cipherText;
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

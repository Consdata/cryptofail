package pl.consdata.security.cryptofail.fails;

/**
 * Interfejs implementowany przez poszczególne, niepoprawne kryptograficznie, przykłady
 */
public interface Fail {

    byte[] encrypt(byte[] key, byte[] iv, byte[] plaintext) throws Exception;

    byte[] decrypt(byte[] key, byte[] iv, byte[] ciphertext) throws Exception;

    int getKeyLength();

    int getIVLength();
}

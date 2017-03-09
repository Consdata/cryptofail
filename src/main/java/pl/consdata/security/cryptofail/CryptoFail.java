package pl.consdata.security.cryptofail;

import pl.consdata.security.cryptofail.fails.*;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.ArrayList;
import java.util.List;

import static pl.consdata.security.cryptofail.CryptographicConstants.*;

public class CryptoFail {

    private static final List<Fail> fails = new ArrayList<Fail>() {{
        add(new FailECBAES());
        add(new FailECBAESSimple());
        add(new FailECBDES());
        add(new FailECB3DES());
        add(new FailCBCDES());
        add(new FailCBCAES());
    }};

    public static void main(String[] args) throws Exception {
        CmdLineConfiguration cmd = new CmdLineConfiguration(args);
        String currentDirectory = System.getProperty("user.dir");

        if (cmd.isEncrypt()) {
            encrypt(cmd.getKey(), cmd.getInput(), currentDirectory);
        } else if (cmd.isDecrypt()) {
            decrypt(cmd.getKey(), cmd.getInput(), currentDirectory);
        }
    }

    private static void encrypt(String userKey, String inputFile, String outputDir) throws Exception {
        for (Fail fail : fails) {
            byte[] input = Files.readAllBytes(Paths.get(inputFile));
            byte[] key = deriveKey(userKey, SALT, fail.getKeyLength());
            // stały, a więc przewidywalny IV - tylko na potrzeby prezentacji!
            byte[] iv = fail.getIVLength() == AES_IV_LENGTH ? IV_128 : IV_64;
            byte[] output = fail.encrypt(key, iv, input);

            // zachowujemy nagłówek BMP32, żeby obraz nie stracił swojej formy - tylko na potrzeby prezentacji!
            System.arraycopy(input, 0, output, 0, BMP_HEADER_LENGTH);

            String failName = String.format("output_%s.bmp", fail.getClass().getSimpleName());
            Path outputFilePath = Paths.get(outputDir, failName);
            Files.write(outputFilePath, output);
        }
    }

    private static void decrypt(String userKey, String inputFile, String outputDir) throws Exception {
        Fail fail = new FailCBCAES();
        byte[] input = Files.readAllBytes(Paths.get(inputFile));
        byte[] key = deriveKey(userKey, SALT, fail.getKeyLength());
        byte[] iv = fail.getIVLength() == AES_IV_LENGTH ? IV_128 : IV_64;
        byte[] output = fail.decrypt(key, iv, input);

        // zachowujemy nagłówek BMP32, żeby obraz nie stracił swojej formy - tylko na potrzeby prezentacji!
        System.arraycopy(input, 0, output, 0, BMP_HEADER_LENGTH);

        Path outputFilePath = Paths.get(outputDir, "output_decrypt.bmp");
        Files.write(outputFilePath, output);
    }

    private static byte[] deriveKey(String password, byte[] salt, int keyLength) throws NoSuchAlgorithmException, InvalidKeySpecException {
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
        KeySpec specs = new PBEKeySpec(password.toCharArray(), salt, 1024, keyLength);
        SecretKey key = keyFactory.generateSecret(specs);
        return key.getEncoded();
    }
}

package pl.consdata.security.cryptofail;

/**
 * Stałe kryptograficzne używane na potrzeby prezentacji pewnych własności szyfrów blokowych.
 */
public class CryptographicConstants {

    /**
     * Rozmiar klucza szyfru AES, w bitach
     */
    public static final int AES_KEY_LENGTH = 128;

    /**
     * Rozmiar klucza szyfru DES, w bitach
     */
    public static final int DES_KEY_LENGTH = 64;

    /**
     * Rozmiar klucza szyfru 3DES, w bitach
     */
    public static final int DESEDE_KEY_LENGTH = 192;

    /**
     * Rozmiar wektora inicjalizujacego dla szyfru AES, w bitach.
     * Jednocześnie jest to rozmiar bloku tego szyfru.
     */
    public static final int AES_IV_LENGTH = 128;

    /**
     * Rozmiar wektora inicjalizujacego dla szyfru DES, w bitach.
     * Jednocześnie jest to rozmiar bloku tego szyfru.
     */
    public static final int DES_IV_LENGTH = 64;

    /**
     * Sól dla funkcji wyznaczającej klucz.
     * Nie musi być tajna, powinna być zmienna.
     * W poniższym przykładzie, i jedynie na potrzeby prezentacji, jest stała.
     */
    public static final byte[] SALT = "salt".getBytes();

    /**
     * wektor inicjalizacyjny dla szyfrow blokowych w trybie strumieniowym.
     * W prawdziwym swiecie MUSI BYC nieprzewidywalny, MOZE byc tajny.
     */
    public static final byte[] IV_64 = "12345678".getBytes();

    /**
     * wektor inicjalizacyjny dla szyfrow blokowych w trybie strumieniowym.
     * W prawdziwym swiecie MUSI BYC nieprzewidywalny, MOZE byc tajny.
     */
    public static final byte[] IV_128 = "1234567812345678".getBytes();

    public static final int BMP_HEADER_LENGTH = 138;
}

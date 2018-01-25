package DataHandling.Encryption;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.security.*;

import static org.apache.commons.codec.CharEncoding.UTF_8;

public class Crypt {

    private static Base64 b64 = new Base64();

    public static String encrypt64(String s){
        return new String(b64.encode(s.getBytes()));
    }

    public static String decrypt64(String s){
        return new String(b64.decode(s.getBytes()));
    }

    public static KeyPair generateKeyPair() throws NoSuchAlgorithmException {

        final int keySize = 4096;
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(keySize, new SecureRandom());

        KeyPair p = kpg.generateKeyPair();

        return p;

    }

    public static byte[] encryptRSA(PublicKey publicKey, String apiKey) throws Exception {

        Cipher c = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        c.init(Cipher.ENCRYPT_MODE, publicKey);

        byte[] cText = apiKey.getBytes(UTF_8);

        return c.doFinal(cText);

    }

    public static byte[] decryptRSA(PrivateKey privateKey, byte[] enc) throws Exception {

        //byte[] bText = Base64.getDecoder().decode(enc);

        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE,privateKey);

        return cipher.doFinal(enc);



    }

}

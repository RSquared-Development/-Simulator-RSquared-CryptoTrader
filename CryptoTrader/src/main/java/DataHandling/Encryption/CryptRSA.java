package DataHandling.Encryption;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.util.ArrayList;
import java.util.Base64;

import static org.apache.commons.codec.CharEncoding.UTF_8;

public class CryptRSA {

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

    public static void main(String[] args) throws Exception{

        KeyPair key = generateKeyPair();
        String test = "test";

        System.out.println(test + "\n__\n\n");

        byte[] test2 = encryptRSA(key.getPublic(), test);

        System.out.println(new String(test2) + "\n__\n\n");

        byte[] test3 = decryptRSA(key.getPrivate(), test2);

        System.out.println(new String(test3) + "\n__\n\n");

    }



}

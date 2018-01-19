import org.apache.commons.codec.binary.Base64;

public class Crypt {
    private static Base64 b64 = new Base64();

    public static String encrypt(String s){
        return new String(b64.encode(s.getBytes()));
    }

    public static String decrypt(String s){
        return new String(b64.decode(s.getBytes()));
    }

}

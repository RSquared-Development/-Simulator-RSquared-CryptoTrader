package DataHandling;

public class CryptTest {
    public static void main(String[] args){
        //Enter string to test here
        String TEST_STRING = "rocketrice";

        String enc = Crypt.encrypt(TEST_STRING);
        System.out.println("Unencoded: "+TEST_STRING +"\n" +
                            "Encoded: " + enc +"\n" +
                            "Decoded: " + Crypt.decrypt(enc));
    }
}

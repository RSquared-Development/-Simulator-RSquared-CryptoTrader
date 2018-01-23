package Exceptions;

public class CryptoTraderException extends Throwable {

    public CryptoTraderException(String s) {
        System.out.println("[EXCEPTION] " + s);
    }

}

package Coins;

import org.knowm.xchange.currency.Currency;

import static java.lang.System.out;

public class Trading {
    private String API_KEY;
    private String API_SECRET;

    public Trading(String API_KEY, String API_SECRET){
        this.API_KEY = API_KEY;
        this.API_SECRET = API_SECRET;
    }

    public static boolean buy(String currency, double quantity){
        //@TODO put in the actual buy commands
        try {
            out.println("[+] I just bought " + quantity + " " + currency);
            return true;
        } catch (Exception e) {
            //failed buy attempt
            out.println(e);
            return false;
        }
    }

    public static boolean sell(String currency, double quantity){
        //@TODO put in the actual buy commands
        try {
            out.println("[+] I just sold " + quantity + " " + currency);
            return true;
        } catch (Exception e) {
            //failed sell attempt
            out.println(e);
            return false;
        }
    }

}

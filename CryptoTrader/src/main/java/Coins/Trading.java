package Coins;

import DataHandling.BinanceAdapters.BinancePriceDataAccessor;
import org.knowm.xchange.currency.Currency;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Writer;

import static java.lang.System.out;

public class Trading {
    private String API_KEY;
    private String API_SECRET;

    public Trading(String API_KEY, String API_SECRET){
        this.API_KEY = API_KEY;
        this.API_SECRET = API_SECRET;
    }

    public static boolean buy(String currency, double quantity, double worth){
        //@TODO put in the actual buy commands
        try {
            Writer fw = new BufferedWriter(new FileWriter("TestingData.log", true));
            String output = "[+] I just bought " + quantity + " " + currency + " for "
                    + worth*quantity + " BTC";
            out.println(output);
            fw.append("\n"+output);
            fw.close();
            return true;
        } catch (Exception e) {
            //failed buy attempt
            out.println(e);
            return false;
        }
    }

    public static boolean sell(String currency, double quantity, double worth){
        //@TODO put in the actual buy commands
        try {
            String output = ("[+] I just sold " + quantity + " " + currency
                    + worth*quantity + " BTC");
            out.println(output);
            Writer fw = new BufferedWriter(new FileWriter("TestingData.log", true));
            fw.append("\n"+output);
            fw.close();
            return true;
        } catch (Exception e) {
            //failed sell attempt
            out.println(e);
            return false;
        }
    }

}

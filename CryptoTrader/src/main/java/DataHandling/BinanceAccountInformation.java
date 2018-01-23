package DataHandling;

import com.google.gson.JsonObject;
import com.webcerebrium.binance.api.BinanceApi;
import com.webcerebrium.binance.api.BinanceApiException;

public class BinanceAccountInformation {

    private static final String API                           = "cC1n5BogRnebbwnCmyxFoHOciDOO4C5vOgvtv3Fs4qN7gMmCFsgqgcsWqNmnKZJ6";
    private static final String SECRET                        = "x3HwmPWGw4cYeQKpwkIRDjvspNG2ZBQt5uE3HjR7hE26A1aR8fZu6gfCevuy6hJy";

    public static void main(String[] args) throws BinanceApiException{
;
        JsonObject account = (new BinanceApi(API, SECRET)).account();
        System.out.println("--\n\n");
        System.out.println(account);
        System.out.println((new BinanceApi(API, SECRET)).balances());
        //comment
        //another comment
    }

}

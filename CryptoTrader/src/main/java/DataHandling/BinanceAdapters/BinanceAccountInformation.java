package DataHandling.BinanceAdapters;

import com.google.gson.JsonObject;
import com.webcerebrium.binance.api.BinanceApi;
import com.webcerebrium.binance.api.BinanceApiException;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

import Exceptions.CryptoTraderException;
import org.knowm.xchange.currency.Currency;

public class BinanceAccountInformation {

    // Important user data. This should never be stored in plainText
    // TODO Include a encode and decode method to encrypt the api keys
    private static String        API              = "cC1n5BogRnebbwnCmyxFoHOciDOO4C5vOgvtv3Fs4qN7gMmCFsgqgcsWqNmnKZJ6";
    private static String        SECRET           = "x3HwmPWGw4cYeQKpwkIRDjvspNG2ZBQt5uE3HjR7hE26A1aR8fZu6gfCevuy6hJy";

    // Variables that are specifically used to interface with the exchange
    private static BinanceApi backbone;
    private static JsonObject account;
    private static TreeMap<String,Integer> currencyIndexNum;

    //Miscellaneous variables
    private static boolean hasBeenRun = false;

    public static void main(String[] args) throws BinanceApiException, CryptoTraderException{

        System.out.println("** BEGIN DIAGNOSTIC OUTPUT **");
        init();
        System.out.println("**  END DIAGNOSTIC OUTPUT  **");
        System.out.println("--\n\n");
        System.out.println("** BEGIN METHOD OUTPUT **");
        getPortfolioValue();
        System.out.println("**  END METHOD OUTPUT  **");


        //comment
        //another comment

    }

    public BinanceAccountInformation(String API_KEY, String API_SECRET){
        API = API_KEY;
        SECRET = API_SECRET;
    }


    public static void init() throws BinanceApiException {

        if(!hasBeenRun) {

            backbone = new BinanceApi(API, SECRET);
            account = backbone.account();

            currencyIndexNum = new TreeMap<>();

            for(String c : getSupportedCurrencies()) {

                currencyIndexNum.put(c,getIndexOfCurrency(c));

            }
            hasBeenRun = true;

        }

        else return;

    }

    public static BigDecimal[] getPortfolioValue() throws BinanceApiException, CryptoTraderException{

        if(hasBeenRun) {

            int index = 0;
            String temp = "";
            String tempCurrencyValue = "";
            BigDecimal value = BigDecimal.ZERO;
            Set<String> currenciesList = currencyIndexNum.keySet();
            BigDecimal[] values = new BigDecimal[currencyIndexNum.size()];
            Iterator<String> currencies = currenciesList.iterator();

            while (currencies.hasNext()) {

                temp              = currencies.next();
                tempCurrencyValue = backbone.balances().get(currencyIndexNum.get(temp)).getAsJsonObject().get("free").toString();
                value             = new BigDecimal(tempCurrencyValue.substring(1, tempCurrencyValue.length() - 1));
                values[index++]   = value;


                System.out.println(temp + ": " + tempCurrencyValue.substring(1, tempCurrencyValue.length() - 1));


            }
            return values;

        }
        else throw new CryptoTraderException("You need to initialize the information first! \n[POTENTIAL SOLUTION] Add BinanceAccountInformation.init() before the conflicting code");

    }

    public static int getIndexOfCurrency(String cSymbol) throws BinanceApiException{

        // Using a Switch/Case Statement to make accession easier
        switch(cSymbol) {

            case "ADA": return 113;
            case "BNB": return 6;
            case "BTC": return 0;
            default   :
                for(int i = 0; i < backbone.balances().size(); i++) {

                    //System.out.println(backbone.balances().get(i).toString().contains(c));

                    if(backbone.balances().get(i).toString().contains(cSymbol)) {

                        System.out.println("*" + i + "*");
                        return i;

                    }
                    else {
                        System.out.println(i);
                    }

                }
                return -1;

        }

    }

    public static String[] getSupportedCurrencies() {

        String[] supported = {"BTC", "BNB", "ADA"};
        return supported;

    }

    public static Currency[] getSupportedCurrenciesInCurrency() {

        Currency[] supported = {Currency.BTC, Currency.BNB, Currency.ADA};
        return supported;

    }

}

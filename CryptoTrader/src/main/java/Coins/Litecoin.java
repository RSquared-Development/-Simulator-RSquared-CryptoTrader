package Coins;

import DataHandling.BinanceAdapters.BinancePriceDataAccessor;
import DataHandling.DataHandler;
import org.knowm.xchange.currency.Currency;

import javax.xml.crypto.Data;
import java.io.IOException;

import static java.lang.System.out;

public class Litecoin {
    boolean trading;
    String stratedgy;
    double amount;

    private String API_KEY;
    private String API_SECRET;

    public Litecoin(String username){
        stratedgy = null;
        trading = false;
        amount = 0;
        API_KEY = DataHandler.openAccountSettings(username)[2];
        API_SECRET = DataHandler.openAccountSettings(username)[3];
    }

    public Litecoin(String username, String stratedgy){
        this.stratedgy = stratedgy;
        trading = false;
        amount = 0;
        API_KEY = DataHandler.openAccountSettings(username)[2];
        API_SECRET = DataHandler.openAccountSettings(username)[3];
    }

    public String getStratedgy() {
        return stratedgy;
    }

    public void setStratedgy(String stratedgy) {
        this.stratedgy = stratedgy;
    }

    public double getAmount() {
        //@TODO this needs to return from binance
        return amount;
    }

    public void startTrading(){
        Trading trader = new Trading(API_KEY, API_SECRET);
        getAmount();
        Boolean potentialBuy = false;
        Boolean potentialSell = false;

        try {
            Double curVal = BinancePriceDataAccessor.getValueInBTC(Currency.LTC);
            while (true){
                Double oldVal = curVal;
                curVal = BinancePriceDataAccessor.getValueInBTC(Currency.LTC);
                while (true) {
                    if (oldVal < curVal) {
                        //worth went up

                        //but it just went down so we are buying
                        if (potentialBuy){
                            potentialBuy = false;
                            trader.buy("LTC", 1);
                        } else{

                        }
                    }
                }
            }
        } catch (IOException e){
            out.println(e);
        }

    }
    public void stopTrading(){
        //@TODO tell it to stop trading
    }
}

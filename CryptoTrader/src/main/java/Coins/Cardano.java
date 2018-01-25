package Coins;

import DataHandling.BinanceAdapters.BinancePriceDataAccessor;
import DataHandling.DataHandler;
import org.knowm.xchange.currency.Currency;

import static java.lang.System.out;

public class Cardano {
    private boolean trading;
    private String stratedgy;
    private double amount;

    private boolean potentialBuy = false;
    private boolean potentialSell = false;
    private double prevWorth;
    private double currWorth;

    private final Currency curr = Currency.ADA;

    private String API_KEY;
    private String API_SECRET;
    Trading trader;

    public Cardano(String username){
        stratedgy = null;
        trading = false;
        amount = 0;
        API_KEY = DataHandler.openAccountSettings(username)[2];
        API_SECRET = DataHandler.openAccountSettings(username)[3];
        trader = new Trading(API_KEY, API_SECRET);
    }

    public Cardano(String username, String stratedgy){
        this.stratedgy = stratedgy;
        trading = false;
        API_KEY = DataHandler.openAccountSettings(username)[2];
        API_SECRET = DataHandler.openAccountSettings(username)[3];
        trader = new Trading(API_KEY, API_SECRET);
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

    public Currency getCurr() {
        return curr;
    }

    public void initTrade(){
        getAmount();
        potentialBuy = false;
        potentialSell = false;

        try {
            currWorth = BinancePriceDataAccessor.getValueInBTC(curr).doubleValue();
        } catch (Exception e){
            out.println(e);
        }
    }

    public void checkTrade(){
        //@TODO fill in the algorithm here
        try {
            prevWorth = currWorth;
            currWorth = BinancePriceDataAccessor.getValueInBTC(curr).doubleValue();

            //potential buy and the price is up
            if (potentialBuy && currWorth > prevWorth){
                trader.buy("ada", .25);
                amount += .25;
                potentialBuy = false;
                return;
            }
            //potential sell and the price is down
            if (potentialSell && currWorth < prevWorth && amount > 0){
                trader.sell("ada", amount);
                amount = 0;
                potentialSell = false;
                return;
            }
            //price is up
            if (currWorth > prevWorth){
                potentialSell = true;
                return;
            }
            //price is down
            if (currWorth < prevWorth){
                potentialBuy = true;
            }
        } catch (Exception e){
            out.println(e);
        }
    }
    public void stopTrading(){
        //@TODO tell it to stop trading
    }
}

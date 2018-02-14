package Coins;

import DataHandling.BinanceAdapters.BinancePriceDataAccessor;
import DataHandling.DataHandler;
import Gui.GUITest;
import org.knowm.xchange.currency.Currency;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import static java.lang.System.out;

public class Cardano {
    private boolean trading;
    private String stratedgy;
    private double amount;

    private boolean potentialBuy = false;
    private double prevWorth;
    private double currWorth;
    private double buyPrice = 0;

    private final Currency curr = Currency.ADA;
    private final double TOP_THRESHHOLD = .01;
    private final double BOTTOM_THRESHHOLD = .5;

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

        try {
            currWorth = BinancePriceDataAccessor.getValueInBTC(curr);
        } catch (Exception e){
            out.println(e);
        }
    }

    public void checkTrade(){
        //@TODO fill in the algorithm here
        try {
            prevWorth = currWorth;
            currWorth = BinancePriceDataAccessor.getValueInBTC(curr);
            out.println("\n\n\n Potential buy = " + potentialBuy);
            double value = currWorth;

            String wat = DataChecker.checkCoin(potentialBuy, prevWorth, currWorth, buyPrice, curr, BinancePriceDataAccessor.getDailyDelta().get("ADA"));

            if (wat.equals("buy")){
                double buy = (GUITest.amountBTC*.25)/(value);
                trader.buy("ada", buy, value);
                amount += buy;
                buyPrice = value;
                GUITest.amountBTC-= GUITest.amountBTC*.25;
                out.println("AmountBTC: "+GUITest.amountBTC);
                Writer fw = new BufferedWriter(new FileWriter("TestingData.log", true));
                fw.append("\nAmountBTC: "+GUITest.amountBTC);
                fw.close();
                potentialBuy = false;
                return;
            } else if (wat.equals("sell")){
                sell(value);
            }
        } catch (Exception e){
            out.println("ERROR");
        }
    }
    public void stopTrading(){
        //@TODO tell it to stop trading
        try {
            sell(BinancePriceDataAccessor.getValueInBTC(Currency.ADA));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sell(double value){
        //don't sell if amount = 0
        if (amount == 0) return;

        trader.sell("ada", amount, value);
        GUITest.amountBTC+=value*amount;
        out.println("AmountBTC: "+GUITest.amountBTC);
        Writer fw = null;
        try {
            fw = new BufferedWriter(new FileWriter("TestingData.log", true));
            fw.append("\nAmountBTC: "+GUITest.amountBTC);
            fw.close();
            amount = 0;
            buyPrice = 0;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

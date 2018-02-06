package DataHandling.BinanceAdapters;

import Coins.CurrencyInformation;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.json.simple.JSONArray;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.binance.BinanceExchange;
import org.knowm.xchange.currency.Currency;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.knowm.xchange.dto.trade.LimitOrder;
import org.knowm.xchange.service.marketdata.MarketDataService;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class BinancePriceDataAccessor {

    private static final CurrencyPair BTC_ADA = new CurrencyPair(Currency.ADA, Currency.BTC);
    private static final CurrencyPair BTC_BNB = new CurrencyPair(Currency.BNB, Currency.BTC);
    private static final CurrencyPair BTC_USD = new CurrencyPair(Currency.BTC, Currency.USDT);

    private static CurrencyPair getCurrencyPair (CurrencyPair look, List<CurrencyPair> currencyPairsOnTheBook) {

        for (CurrencyPair c : currencyPairsOnTheBook) {

            //System.out.println(c);
            if(c.equals(look)) return c;

        }

        return null;

    }
    public static double getValueInBTC(Currency c) throws IOException {

        CurrencyPair pair= new CurrencyPair(c,Currency.BTC);
        // This line creates a way to interface with binance
        // The Exchange factory is kind of like an easy way to instantiate this variable
        Exchange binanceExchange     = ExchangeFactory.INSTANCE.createExchange(BinanceExchange.class.getName());

        // This variable is kind of like the output of the market it will return the information needed
        // To see how the market is doing
        MarketDataService binanceMDS = binanceExchange.getMarketDataService();


        // This will get the prices from the exchange for the given currencyPair
        // Can throw IOException
        OrderBook binanceOrderBook      = binanceMDS.getOrderBook(pair);
        System.out.println("\n\n" + binanceOrderBook.getAsks().get(binanceOrderBook.getAsks().size()-1).getLimitPrice().doubleValue() + "\n\n");


        return binanceOrderBook.getAsks().get(binanceOrderBook.getAsks().size()-1).getLimitPrice().doubleValue();

    }

    public static TreeMap<String,Double> getDailyDelta() throws Exception{

        TreeMap<String, Double> deltas = new TreeMap<>();
        File name = new File("lastPrice.json");

        // Check to see if the file is there, if it is not there, it will report a zero percent change and write current prices (in BTC) into it
        if(!name.exists()) generateJsonFile();
        else {


            JsonParser parser         = new JsonParser();
            Object object             = parser.parse(new FileReader("lastPrice.json"));
            JsonObject jObj           = (JsonObject) object;
            ArrayList<Double> oldVals = new ArrayList();
            ArrayList<Integer> index  = new ArrayList();


            int j = 0;
            for(Currency i : CurrencyInformation.SUPPORTED_CURRENCIES) {

                if(jObj.has(i.getSymbol())) oldVals.add(((JsonArray)jObj.get(i.getSymbol())).get(0).getAsDouble());
                index.add(j);
                j++;

            }
            for (int i = 0; i < oldVals.size(); i++) {

                deltas.put(CurrencyInformation.SUPPORTED_CURRENCIES[index.get(i)].getSymbol(), getDelta(oldVals, CurrencyInformation.SUPPORTED_CURRENCIES[index.get(i)], i));

            }
            name.delete();
            generateJsonFile();

        }


        return deltas;

    }

    private static Double getDelta(ArrayList<Double> oldVals, Currency c, int i) throws Exception {

        return (getValueInBTC(c)-oldVals.get(i))/oldVals.get(i);

    }

    private static void generateJsonFile() throws Exception{

        JsonObject newJObj = new JsonObject();
        JsonArray tempJson;

        for(Currency i : CurrencyInformation.SUPPORTED_CURRENCIES) {

            if(i.getSymbol().equals("BTC")){}
            else {

                tempJson = new JsonArray();
                tempJson.add(getValueInBTC(i));

                newJObj.add(i.getSymbol(), tempJson);

            }

        }
        try {

            FileWriter fw = new FileWriter("lastPrice.json");
            fw.write(newJObj.toString());
            fw.close();
            System.out.println("JSON written to file successfully");


        } catch (Exception e) {
            System.out.println("something went wrong :/\n" + e.toString());
        }

    }

    public static void main(String[] args) throws IOException, InterruptedException {







    }

}

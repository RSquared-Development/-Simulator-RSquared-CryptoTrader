package DataHandling;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.binance.BinanceExchange;
import org.knowm.xchange.currency.Currency;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.knowm.xchange.dto.trade.LimitOrder;
import org.knowm.xchange.service.marketdata.MarketDataService;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

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
    public static BigDecimal getValueInBTC(Currency c) {

        // This line creates a way to interface with binance
        // The Exchange factory is kind of like an easy way to instantiate this variable
        Exchange binanceExchange     = ExchangeFactory.INSTANCE.createExchange(BinanceExchange.class.getName());

        // This variable is kind of like the output of the market it will return the information needed
        // To see how the market is doing
        MarketDataService binanceMDS = binanceExchange.getMarketDataService();


        // This will get the prices from the exchange for the given currencyPair
        // Can throw IOException
        OrderBook binanceOrderBook      = binanceMDS.getOrderBook(getCurrencyPair(BTC_ADA, binanceExchange.getExchangeSymbols()));
        OrderBook binanceOrderBookBNB   = binanceMDS.getOrderBook(getCurrencyPair(BTC_BNB, binanceExchange.getExchangeSymbols()));
        OrderBook binanceOrderBookBTC   = binanceMDS.getOrderBook(getCurrencyPair(BTC_USD, binanceExchange.getExchangeSymbols()));

        String ADAGatheredData = "";
        String BNBGatheredData = "";
        long currTime = 0;

        for(int i = 0; i < 10000; i++) {
            currTime = System.currentTimeMillis();

            //System.out.println("BTC/ADA: " + binanceOrderBookADA.getAsks().get(binanceOrderBookADA.getAsks().size() - 1).getLimitPrice());
            //System.out.println("BTC/BNB: " + binanceOrderBookBNB.getAsks().get(binanceOrderBookBNB.getAsks().size() - 1).getLimitPrice());
            //System.out.println("BTC/USD: $" + binanceOrderBookBTC.getAsks().get(binanceOrderBookBTC.getAsks().size() - 1).getLimitPrice());
            System.out.println("ADA/USD: $" + binanceOrderBookBTC.getAsks().get(binanceOrderBookBTC.getAsks().size() - 1).getLimitPrice().multiply(binanceOrderBookADA.getAsks().get(binanceOrderBookADA.getAsks().size() - 1).getLimitPrice()));
            System.out.println("BNB/USD: $" + binanceOrderBookBTC.getAsks().get(binanceOrderBookBTC.getAsks().size() - 1).getLimitPrice().multiply(binanceOrderBookBNB.getAsks().get(binanceOrderBookBNB.getAsks().size() - 1).getLimitPrice()));

            ADAGatheredData += binanceOrderBookBTC.getAsks().get(binanceOrderBookBTC.getAsks().size() - 1).getLimitPrice().multiply(binanceOrderBookADA.getAsks().get(binanceOrderBookADA.getAsks().size() - 1).getLimitPrice()) + "\n";
            BNBGatheredData += binanceOrderBookBTC.getAsks().get(binanceOrderBookBTC.getAsks().size() - 1).getLimitPrice().multiply(binanceOrderBookBNB.getAsks().get(binanceOrderBookBNB.getAsks().size() - 1).getLimitPrice()) + "\n";

            binanceOrderBookADA   = binanceMDS.getOrderBook(getCurrencyPair(BTC_ADA, binanceExchange.getExchangeSymbols()));
            binanceOrderBookBNB   = binanceMDS.getOrderBook(getCurrencyPair(BTC_BNB, binanceExchange.getExchangeSymbols()));
            binanceOrderBookBTC   = binanceMDS.getOrderBook(getCurrencyPair(BTC_USD, binanceExchange.getExchangeSymbols()));

            if(1000 - (System.currentTimeMillis() - currTime) > 0) Thread.sleep(1000 - (System.currentTimeMillis() - currTime));
            else {}

            System.out.println((i + 1) + "/10000 : " + (System.currentTimeMillis() - currTime));

        }

    }

    public static void main(String[] args) throws IOException, InterruptedException {







    }

}

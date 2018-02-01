package DataHandling.BinanceAdapters;

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

    public static void main(String[] args) throws IOException, InterruptedException {







    }

}

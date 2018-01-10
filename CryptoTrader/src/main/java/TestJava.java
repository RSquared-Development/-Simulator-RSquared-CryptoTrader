import org.knowm.xchange.Exchange;
import org.knowm.xchange.binance.Binance;
import org.knowm.xchange.binance.BinanceExchange;
import org.knowm.xchange.binance.dto.marketdata.BinanceTicker24h;
import org.knowm.xchange.binance.service.BinanceMarketDataService;
import org.knowm.xchange.currency.Currency;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.examples.binance.BinanceDemoUtils;
import org.knowm.xchange.service.marketdata.MarketDataService;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TestJava {

    private static final Currency[] portfolio = {Currency.ADA, Currency.BNB, Currency.BTC};

    public static void main(String[] args) throws IOException{

        // Initialize the exchange
        Exchange exchange = BinanceDemoUtils.createExchange();

        // Create a data exchange service for the exchange
        MarketDataService marketDataService = exchange.getMarketDataService();

        generic(exchange, marketDataService);
        raw((BinanceExchange) exchange, (BinanceMarketDataService) marketDataService);

    }

    public static void generic(Exchange exchange, MarketDataService marketDataService) throws IOException {

    }

    private static boolean isInPortfolio (Currency c) {

        for(Currency curr : portfolio) {

            if(curr == c) return true;

        }

        return false;

    }

    public static void raw(BinanceExchange exchange, BinanceMarketDataService marketDataService) throws IOException {

        List<BinanceTicker24h> tickers = new ArrayList<>();
        double btcPerUsd = 0;
        for (Currency curr : portfolio) {



            for (CurrencyPair cp : exchange.getExchangeMetaData().getCurrencyPairs().keySet()) {

                if (cp.base == Currency.BTC && cp.counter == Currency.USDT) {
                    btcPerUsd = Double.parseDouble("" + marketDataService.ticker24h(cp).getAskPrice());
                }

                else if (cp.base == curr && isInPortfolio(cp.counter)) {

                    tickers.add(marketDataService.ticker24h(cp));

                }

            }

            Collections.sort(tickers, new Comparator<BinanceTicker24h>() {
                @Override
                public int compare(BinanceTicker24h t1, BinanceTicker24h t2) {
                    return t2.getPriceChangePercent().compareTo(t1.getPriceChangePercent());
                }
            });
        }

        System.out.println("Price in Bitcoin: ");
        for (BinanceTicker24h b : tickers) {

            System.out.println(b.getCurrencyPair() + " -> " + b.getAskPrice());

        }
        System.out.println("============================================================");
        System.out.println("Price of Bitcoin in USD: " + btcPerUsd);
        System.out.println("Price in USD: ");
        for (BinanceTicker24h b : tickers) {

            System.out.println(b.getCurrencyPair() + " -> $" + new BigDecimal(btcPerUsd * Double.parseDouble("" + b.getAskPrice())));

        }



    }
}

import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.binance.BinanceAdapters;
import org.knowm.xchange.binance.BinanceExchange;
import org.knowm.xchange.binance.dto.account.BinanceBalance;
import org.knowm.xchange.binance.service.BinanceAccountService;
import org.knowm.xchange.binance.service.BinanceAccountServiceRaw;
import org.knowm.xchange.dto.account.AccountInfo;
import org.knowm.xchange.dto.account.Balance;
import org.knowm.xchange.dto.trade.UserTrade;
import org.knowm.xchange.examples.binance.BinanceDemoUtils;
import org.knowm.xchange.examples.bitcoinde.ExchangeUtils;
import org.knowm.xchange.binance.dto.account.BinanceBalance;
import org.knowm.xchange.service.account.AccountService;

import java.io.IOException;

import static org.junit.Assert.assertNotNull;

public class BinanceAccountConnector {
    private static final String API                           = "cC1n5BogRnebbwnCmyxFoHOciDOO4C5vOgvtv3Fs4qN7gMmCFsgqgcsWqNmnKZJ6";
    private static final String SECRET                        = "x3HwmPWGw4cYeQKpwkIRDjvspNG2ZBQt5uE3HjR7hE26A1aR8fZu6gfCevuy6hJy";
    private static final String USER                          = "";
    private static ExchangeSpecification binanceExchangeSpecs = new BinanceExchange().getDefaultExchangeSpecification();
    private static Exchange binanceExchange;
    private static AccountService binanceAS;
    public static void main(String[] args) throws IOException {

        establishInterface();


        generic((BinanceAccountService) binanceAS);
        //raw((BinanceAccountServiceRaw) binanceDemoAS);

        //This is a demo exchange for testing the rest of the code without endangering any accounts
        //Exchange binanceDemoExchange = BinanceDemoUtils.createExchange();
        //AccountService binanceDemoAS = binanceDemoExchange.getAccountService();

        //generic(binanceDemoAS);
        //raw((BinanceAccountServiceRaw) binanceDemoAS);



    }

    private static void establishInterface() {

        //binanceExchangeSpecs.setUserName("ruben.s.ruiz@outlook.com");
        binanceExchangeSpecs.setApiKey(API);
        binanceExchangeSpecs.setSecretKey(SECRET);
        binanceExchangeSpecs.setExchangeSpecificParametersItem("recvWindow", 60000L);
        binanceExchange = ExchangeFactory.INSTANCE.createExchange(binanceExchangeSpecs);
        binanceAS       = binanceExchange.getAccountService();

    }

    private static void generic(BinanceAccountService b) throws IOException {

        assertNotNull(b);
        AccountInfo binanceAI = b.getAccountInfo();
        //System.out.println("Binance Account Information:\n----------\nUsername: " + binanceAI.getUsername() );



    }

    private static void raw(BinanceAccountServiceRaw br) {

        //BinanceBalance accountBalance = br.

    }

}

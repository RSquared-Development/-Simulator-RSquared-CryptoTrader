import DataHandling.BinanceAdapters.BinancePriceDataAccessor;
import org.knowm.xchange.currency.Currency;

import java.io.IOException;

public class BinancePriceDataAccessorTest {

    public static void main(String[] args) throws IOException {

        System.out.println(BinancePriceDataAccessor.getValueInBTC(Currency.ADA));

    }

}

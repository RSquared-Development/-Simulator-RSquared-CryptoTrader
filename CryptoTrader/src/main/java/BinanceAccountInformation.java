import com.google.gson.JsonObject;
import com.webcerebrium.binance.api.BinanceApi;
import com.webcerebrium.binance.api.BinanceApiException;

public class BinanceAccountInformation {

    public static void main(String[] args) throws BinanceApiException{



        System.getenv();
        System.setProperty("BINANCE_API_KEY","eLChU3yNN2P6I3LNWvyyVpSL8VNWsz0aA0N0TJABgbln3A3KpLRVz0o1MJ84hvhU");
        System.setProperty("BINANCE_SECRET_KEY", "5udJXvtWlgxQWp23F62hTuP4K1untQc3XNZaQz942UQyCPYmKXEWKU6DkxRxpkk");
        System.out.println(System.getProperty("BINANCE_API_KEY"));
        JsonObject account = (new BinanceApi()).account();
        System.out.println(account.get("canTrade").getAsBoolean());
        //System.out.println((new BinanceApi()).balances());
        //System.setProperties();

    }

}

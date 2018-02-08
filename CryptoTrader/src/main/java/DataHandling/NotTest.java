package DataHandling;

import DataHandling.BinanceAdapters.BinancePriceDataAccessor;
import DataHandling.Encryption.Crypt;

import java.util.TreeMap;

public class NotTest {

    public static void main(String[] args) throws Exception {

        //UserInformationHandler.createUserInformation("rubenr", "mypassword", "myapikey", "myprivateapikey");
        //System.out.println(UserInformationHandler.checkUserInformation("rubenr",Crypt.encrypt64("mypassword")));
        TreeMap<String, Double> temp = BinancePriceDataAccessor.getDailyDelta();
        System.out.println("---\n\n" + temp.toString());

    }
}

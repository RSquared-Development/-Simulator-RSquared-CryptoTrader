package DataHandling;


import DataHandling.Encryption.Crypt;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.mindrot.jbcrypt.BCrypt;

import java.io.FileReader;
import java.io.FileWriter;
import java.security.KeyPair;

public class UserInformationHandler {

    public static void createUserInformation(String u, String p, String a, String pa) throws Exception {

        KeyPair    keys = Crypt.generateKeyPair();

        JsonObject jObj = new JsonObject();
        JsonArray  jArr = new JsonArray();

        jArr.add("Username: " + u);
        jArr.add("Password: " + BCrypt.hashpw(p, BCrypt.gensalt(12)));
        jArr.add("Api; "      + new String(Crypt.encryptRSA(keys.getPublic(),a)));
        jArr.add("Secret: "   + new String(Crypt.encryptRSA(keys.getPublic(),pa)));
        jArr.add("pKey: "     + Crypt.encrypt64(keys.getPublic().toString()));
        jArr.add("prKey: "    + Crypt.encrypt64(keys.getPrivate().toString()));

        jObj.add("User", jArr);

        try {

            FileWriter fw = new FileWriter("account.json");
            fw.write(jObj.toString());
            fw.close();
            System.out.println("JSON written to file successfully");


        } catch (Exception e) {
            System.out.println("something went wrong :/\n" + e.toString());
        }


    }
    public static boolean checkUserInformation(String u, String p) {

        JsonParser jp = new JsonParser();

        try {

            Object     o  = jp.parse(new FileReader("account.json"));
            JsonObject jo = jp.parse(new FileReader("account.json"));
            JsonArray  ja = jo.getAsJsonArray("User");

            System.out.println(un);

        } catch (Exception e) {

            System.out.println("something went wrong :/\n" + e.toString());

        }
        return false;

    }

}

package DataHandling;


import DataHandling.Encryption.Crypt;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.mindrot.jbcrypt.BCrypt;

import java.io.File;
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
        jArr.add("Api: "      + new String(Crypt.encryptRSA(keys.getPublic(),a)));
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
    public static boolean checkUserInformation(String u, String p) throws Exception {

        JSONParser parser       = new JSONParser();
        try{

            Object     object   = parser.parse(new FileReader("account.json"));
            JSONObject user     = (JSONObject) object;
            JSONArray  userInfo = (JSONArray) user.get("User");


            // Simplest way to check user information
            // 1.) Check Username and if they don't match return false
            // 2.) Check Password and return if they do or do not match
            if(!(userInfo.get(0).toString().split(": ")[1].equals(u))) return false;
            else return(BCrypt.checkpw(Crypt.decrypt64(p),userInfo.get(1).toString().split(": ")[1]));
        } catch (Exception e) {

            System.out.println("Something went wrong :/\n" + e.toString());

        }

        return false;
    }

    //public static

}

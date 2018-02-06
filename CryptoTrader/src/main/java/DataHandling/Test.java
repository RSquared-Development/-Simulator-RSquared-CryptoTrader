package DataHandling;

import DataHandling.Encryption.Crypt;

public class Test {

    public static void main(String[] args) throws Exception {

        //UserInformationHandler.createUserInformation("rubenr", "mypassword", "myapikey", "myprivateapikey");
        System.out.println(UserInformationHandler.checkUserInformation("rubenr",Crypt.encrypt64("mypassword")));

    }
}

package DataHandling;

public class Test {

    public static void main(String[] args) throws Exception {

        UserInformationHandler.createUserInformation("rubenr", "mypassword", "myapikey", "myprivateapikey");
        UserInformationHandler.checkUserInformation("","");

    }
}

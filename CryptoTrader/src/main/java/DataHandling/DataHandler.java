package DataHandling;

import DataHandling.Encryption.Crypt;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DataHandler {

    public static boolean saveAccountSettings(String username, String password, String binanceKey, String binanceSecret){
        //each account will have a line in the account.dat file to store their data
        //their account settings will be seperated by "  ,  ": two spaces , two spaces
        //last 2 characters of line will be \n obviously, but that will be added at the end
        String line = Crypt.encrypt(username) + "  ,  " + Crypt.encrypt(password) + "  ,  " + Crypt.encrypt(binanceKey) + "  ,  " + Crypt.encrypt(binanceSecret);


        //find and delete the line with the current settings
        //since we know the first word of each line will be the username, we can find it with that
        try {
            Scanner chop = new Scanner(new File("account.dat"));

            //loop through the file to take in the data
            ArrayList<String> accounts = new ArrayList();
            while (chop.hasNextLine()){
                accounts.add(chop.nextLine());
            }

            //find and delete the line with the current settings
            //since we know the first word of each line will be the username, we can find it with that
            int spot = 0;
            spot = getSpot(username, accounts, spot);
            accounts.remove(spot);

            //now that we have deleted the current save, we can send add the new one to the array list
            //and push it to the account.dat file
            accounts.add(line);

            //create text to push to the file
            String text = "";
            for (String account : accounts){
                text += account + "\n";
            }

            FileWriter f = new FileWriter("account.dat", false);
            f.write(text);
            f.close();

        } catch (IOException e){
            //error has occurred
            return false;
        }
        //successful save
        return true;
    }

    public static String[] openAccountSettings(String username){
        try {
            Scanner chop = new Scanner(new File("account.dat"));

            //loop through the file to take in the data
            ArrayList<String> accounts = new ArrayList();
            while (chop.hasNextLine()) {
                accounts.add(chop.nextLine());
            }

            //find spot of account
            int spot = 0;
            spot = getSpot(username, accounts, spot);

            //decrypt the data and return it
            String[] encrypted = accounts.get(spot).split("  ,  ");
            String[] decrypted = new String[encrypted.length];
            for (int i = 0; i<encrypted.length;i++){
                decrypted[i] = Crypt.decrypt(encrypted[i]);
            }

            return decrypted;
        } catch (IOException e){
            return null;
        }
    }

    private static int getSpot(String username, ArrayList<String> accounts, int spot) {
        for (String account : accounts) {
            String[] words = account.split("  ,  ");
            if (words[0].equals(Crypt.encrypt(username))) {
                break;
            }else {
                spot++;
            }
        }
        return spot;
    }
}

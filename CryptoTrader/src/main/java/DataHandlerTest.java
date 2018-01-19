import java.util.Arrays;

public class DataHandlerTest {
    public static void main (String[] args){
        //YOU CAN ADD USERS HERE FOR NOW
        //System.out.println(DataHandler.saveAccountSettings("legoman123", "password", "eLChU3yNN2P6I3LNWvyyVpSL8VNWsz0aA0N0TJABgbln3A3KpLRVz0o1MJ84hvhU", "5udJXvtWlgxQWp23F62hTuP4K1untQc3XNZaQz942UQyCPYmKXEWKU6DkxRxpkk"));

        //YOU CAN TEST READ HERE
        System.out.println(Arrays.toString(DataHandler.openAccountSettings("rocketrice")));
    }
}

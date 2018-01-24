package Coins;

public class Litecoin {
    boolean trading;
    String stratedgy;
    double amount;

    public Litecoin(){
        stratedgy = null;
        trading = false;
        amount = 0;
    }

    public Litecoin(String stratedgy){
        this.stratedgy = stratedgy;
        trading = false;
    }

    public String getStratedgy() {
        return stratedgy;
    }

    public void setStratedgy(String stratedgy) {
        this.stratedgy = stratedgy;
    }

    public double getAmount() {
        //@TODO this needs to return from binance
        return amount;
    }

    public void startTrading(){
        //@TODO fill in the algorithm here
    }
    public void stopTrading(){
        //@TODO tell it to stop trading
    }
}

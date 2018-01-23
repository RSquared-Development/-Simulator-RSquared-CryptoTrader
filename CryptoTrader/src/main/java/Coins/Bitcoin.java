package Coins;

public class Bitcoin {
    Boolean trading;
    String stratedgy;

    public Bitcoin(){
        stratedgy = null;
        trading = false;
    }

    public Bitcoin(String stratedgy){
        this.stratedgy = stratedgy;
        trading = false;
    }

    public String getStratedgy() {
        return stratedgy;
    }

    public void setStratedgy(String stratedgy) {
        this.stratedgy = stratedgy;
    }

    public void startTrading(){
        //@TODO fill in the algorithm here
    }
    public void stopTrading(){
        //@TODO tell it to stop trading
    }
}

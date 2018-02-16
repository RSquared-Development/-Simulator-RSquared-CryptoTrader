package Coins;

import Gui.GUITest;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Writer;
import java.util.concurrent.atomic.AtomicBoolean;

import static Gui.GUITest.amountBTC;
import static java.lang.System.out;

public class Looper implements Runnable{
    private AtomicBoolean keepRunning;

    double startTime; double threshold;
    boolean tradeADA; boolean tradeBNB; boolean tradeLTC; boolean tradeBTC; boolean tradeETH; boolean tradeETC; boolean tradeICX; boolean tradeNEO; boolean tradeXRP; boolean tradeTRX; boolean tradeVEN;
    Cardano ada; BinanceCoin bnb; Litecoin ltc; Ethereum eth; EthereumClassic etc; Icon icx; NEO neo; Ripple xrp; Tron trx; VeChain ven;

    public Looper() {
        keepRunning = new AtomicBoolean(false);
    }

    public void stop() {
        keepRunning.set(false);
    }

    public void setStuff(double startTime, double threshold,
                         boolean tradeADA, boolean tradeBNB, boolean tradeLTC, boolean tradeETH, boolean tradeETC, boolean tradeICX, boolean tradeNEO, boolean tradeXRP, boolean tradeTRX, boolean tradeVEN,
                         Cardano ada, BinanceCoin bnb, Litecoin ltc, Ethereum eth, EthereumClassic etc, Icon icx, NEO neo, Ripple xrp, Tron trx, VeChain ven) {
        this.startTime = startTime;
        this.threshold = threshold;
        this.tradeADA = tradeADA;
        this.tradeBNB = tradeBNB;
        this.tradeLTC = tradeLTC;
        tradeBTC = false;
        this.ada = ada;
        this.bnb = bnb;
        this.ltc = ltc;

    }

        @Override
    public void run() {
        keepRunning.set(true);
        while (keepRunning.get()) {
            if (((double) System.currentTimeMillis()) - startTime >= threshold) {
                if (tradeBTC) {
                    out.println("dont do btc plez");
                }
                if (tradeADA) {
                    ada.checkTrade();
                }
                if (tradeBNB) {
                    bnb.checkTrade();
                }
                if (tradeLTC) {
                    ltc.checkTrade();
                }
                if (tradeETC){
                    etc.checkTrade();
                }
                if (tradeETH){
                    eth.checkTrade();
                }
                if (tradeICX){
                    icx.checkTrade();
                }
                if (tradeNEO){
                    neo.checkTrade();
                }
                if (tradeTRX){
                    trx.checkTrade();
                }
                if (tradeVEN){
                    ven.checkTrade();
                }
                if (tradeXRP){
                    xrp.checkTrade();
                }

                out.print("\n\n\n[*] I JUST RAN THROUGH THE LOOP");
                startTime = (double) System.currentTimeMillis();
            }
        }
    }
}

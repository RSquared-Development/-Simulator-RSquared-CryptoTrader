package Coins;

import Gui.GUITest;

import javax.smartcardio.Card;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Writer;
import java.util.concurrent.atomic.AtomicBoolean;

import static Gui.GUITest.amountBTC;
import static java.lang.System.out;

public class Looper implements Runnable{
    private AtomicBoolean keepRunning;

    double startTime; double threshold; boolean tradeADA; boolean tradeBNB; boolean tradeLTC; boolean tradeBTC;
    Cardano ada; BinanceCoin bnb; Litecoin ltc;

    public Looper() {
        keepRunning = new AtomicBoolean(false);
    }

    public void stop() {
        keepRunning.set(false);
    }

    public void setStuff(double startTime, double threshold, boolean tradeADA, boolean tradeBNB, boolean tradeLTC, Cardano ada, BinanceCoin bnb, Litecoin ltc) {
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

                out.print("\n\n\n[*] I JUST RAN THROUGH THE LOOP");
                startTime = (double) System.currentTimeMillis();
            }
        }
    }
}

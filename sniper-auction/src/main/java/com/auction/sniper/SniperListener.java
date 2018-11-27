package com.auction.sniper;

import java.util.EventListener;

/**
 * Created by H on 2018. 11. 27.
 */
public interface SniperListener extends EventListener {
    void sniperLost();
    void sniperBidding();
    void sniperWinning();
    void sniperWon();
}

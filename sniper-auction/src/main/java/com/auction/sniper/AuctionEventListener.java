package com.auction.sniper;

/**
 * Created by H on 2018. 11. 27.
 */
public interface AuctionEventListener {
    enum PriceSource {
        FromSniper,
        FromOtherBidder;
    }

    void auctionClosed();
    void currentPrice(int price, int increment, PriceSource source);
}

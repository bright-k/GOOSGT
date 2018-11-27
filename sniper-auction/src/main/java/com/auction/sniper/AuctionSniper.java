package com.auction.sniper;

/**
 * Created by H on 2018. 11. 27.
 */
public class AuctionSniper implements AuctionEventListener {
    private boolean isWinning = false;
    private Auction auction;
    private SniperListener sniperListener;

    public AuctionSniper(Auction auction, SniperListener sniperListener) {
        this.auction = auction;
        this.sniperListener = sniperListener;
    }

    @Override
    public void auctionClosed() {
        if(isWinning) {
            sniperListener.sniperWon();
        } else {
            sniperListener.sniperLost();
        }
    }

    @Override
    public void currentPrice(int price, int increment, PriceSource source) {
        isWinning = PriceSource.FromSniper == source;
        if(isWinning) {
            sniperListener.sniperWinning();
        } else {
            auction.bid(price + increment);
            sniperListener.sniperBidding();
        }
    }
}

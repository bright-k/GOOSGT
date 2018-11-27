package com.auction.sniper;

import org.jivesoftware.smack.XMPPException;

/**
 * Created by H on 2018. 11. 27.
 */
public interface AuctionServer {

    void startSellingItem() throws XMPPException;
    void hasReceivedJoinRequestFromSniper() throws InterruptedException;
    void announceClosed() throws XMPPException;
    void stop();
    String getItemId();
}

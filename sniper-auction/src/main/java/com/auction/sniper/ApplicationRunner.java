package com.auction.sniper;

/**
 * Created by H on 2018. 11. 26.
 */
public class ApplicationRunner {
    public static final String XMPP_HOSTNAME = "localhost";
    public static final String SNIPER_ID = "sniper";
    public static final String SNIPER_PW = "sniper";

    public static final String STATUS_JOINING = "joining..";
    public static final String STATUS_LOST = "lost.";

    private AuctionSniperDriver driver;

    public void startBiddingIn(final AuctionServer auction) {
        Thread thread = new Thread("Test Application") {
            @Override
            public void run() {
                try {
                    Main.main(XMPP_HOSTNAME, SNIPER_ID, SNIPER_PW, auction.getItemId());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        thread.setDaemon(true);
        thread.start();
        driver = new AuctionSniperDriver(1000);
        driver.showsSniperStatus(STATUS_JOINING);
    }

    public void showsSniperHasLostAuction() {
        driver.showsSniperStatus(STATUS_LOST);
    }

    public void stop() {
        if(null != driver) {
            driver.dispose();
        }
    }
}

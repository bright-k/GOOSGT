package com.auction.sniper;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.packet.Message;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by H on 2018. 11. 27.
 */
public class AuctionMessageTranslator implements MessageListener {
    private final String sniperId;

    private final AuctionEventListener eventListener;

    public AuctionMessageTranslator(String sniperId, AuctionEventListener listener) {
        this.sniperId = sniperId;
        this.eventListener = listener;
    }

    @Override
    public void processMessage(Chat chat, Message message) {
        AuctionEvent event = AuctionEvent.from(message.getBody());

        System.out.println("AcutionMessageTranslator : " + message.getBody());
        String type = event.type();
        switch (type) {
            case "CLOSE":
                eventListener.auctionClosed();
                break;
            case "PRICE":
                eventListener.currentPrice(event.currentPrice(), event.increment(), event.isFrom(sniperId));
                break;
            default:
                break;
        }

    }

    private static class AuctionEvent {
        private final Map<String, String> fields = new HashMap<>();

        public String type() {
            return get("Event");
        }

        public int currentPrice() {
            return getInt("CurrentPrice");
        }

        public int increment() {
            return getInt("Increment");
        }

        public String bidder() {
            return get("Bidder");
        }

        public AuctionEventListener.PriceSource isFrom(String sniperId) {
            return sniperId.equalsIgnoreCase(bidder()) ? AuctionEventListener.PriceSource.FromSniper : AuctionEventListener.PriceSource.FromOtherBidder;
        }

        private int getInt(String fieldName) {
            return Integer.parseInt(get(fieldName));
        }

        private String get(String fieldName) {
            return fields.get(fieldName);
        }

        private void addField(String field) {
            String[] pair = field.split(":");
            fields.put(pair[0].trim(), pair[1].trim());
        }

        static AuctionEvent from(String messageBody) {
            AuctionEvent event = new AuctionEvent();
            for(String field : fieldIn(messageBody)) {
                event.addField(field);
            }
            return event;
        }
        static String[] fieldIn(String messageBody) {
            return messageBody.split(";");
        }
    }
}

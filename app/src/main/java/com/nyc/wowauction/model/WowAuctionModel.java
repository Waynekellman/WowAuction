package com.nyc.wowauction.model;

/**
 * Created by Wayne Kellman on 12/14/17.
 */

public class WowAuctionModel {
    private Realms[] realms;
    private Auction[] auctions;

    public Realms[] getRealms() {
        return realms;
    }

    public void setRealms(Realms[] realms) {
        this.realms = realms;
    }

    public Auction[] getAuctions() {
        return auctions;
    }

    public void setAuctions(Auction[] auctions) {
        this.auctions = auctions;
    }
}

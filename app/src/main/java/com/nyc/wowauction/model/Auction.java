package com.nyc.wowauction.model;

/**
 * Created by Wayne Kellman on 12/14/17.
 */

class Auction {
    /**
     * "auctions": [
     {"auc":1538284337,"item":3829,"owner":"Admin","ownerRealm":"Exodar","bid":4255429,
     "buyout":4479399,"quantity":1,"timeLeft":"VERY_LONG","rand":0,"seed":0,"context":0},
     */

    private String auc;
    private String item;
    private String owner;
    private String ownerRealm;
    private String bid;
    private long buyout;
    private long quantity;
    private String timeLeft;
    private long rand;
    private long seed;
    private long context;

    public String getAuc() {
        return auc;
    }

    public void setAuc(String auc) {
        this.auc = auc;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getOwnerRealm() {
        return ownerRealm;
    }

    public void setOwnerRealm(String ownerRealm) {
        this.ownerRealm = ownerRealm;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public long getBuyout() {
        return buyout;
    }

    public void setBuyout(long buyout) {
        this.buyout = buyout;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public String getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(String timeLeft) {
        this.timeLeft = timeLeft;
    }

    public long getRand() {
        return rand;
    }

    public void setRand(long rand) {
        this.rand = rand;
    }

    public long getSeed() {
        return seed;
    }

    public void setSeed(long seed) {
        this.seed = seed;
    }

    public long getContext() {
        return context;
    }

    public void setContext(long context) {
        this.context = context;
    }
}

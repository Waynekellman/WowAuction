package com.nyc.wowauction.controller;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nyc.wowauction.R;
import com.nyc.wowauction.model.Auction;

/**
 * Created by Wayne Kellman on 12/16/17.
 */

public class WowAuctionAdapter extends RecyclerView.Adapter<WowAuctionAdapter.WowAuctionViewHolder> {
    private Auction[] auctions;

    public WowAuctionAdapter(Auction[] auctions) {
        this.auctions = auctions;
    }

    @Override
    public WowAuctionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemview, parent, false);
        return new WowAuctionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(WowAuctionViewHolder holder, int position) {
        holder.onBind(auctions[position]);
    }

    @Override
    public int getItemCount() {
        return auctions.length;
    }

    class WowAuctionViewHolder extends RecyclerView.ViewHolder {
        private TextView auc,item,aucOwner,ownerRealm,bid,buyout,quantity,timeLeft,seed;
        public WowAuctionViewHolder(View itemView) {
            super(itemView);
            auc = itemView.findViewById(R.id.auc);
            item = itemView.findViewById(R.id.item);
            aucOwner = itemView.findViewById(R.id.aucOwner);
            ownerRealm = itemView.findViewById(R.id.ownerRealm);
            bid = itemView.findViewById(R.id.bid);
            buyout = itemView.findViewById(R.id.buyout);
            quantity = itemView.findViewById(R.id.quantity);
            timeLeft = itemView.findViewById(R.id.timeLeft);
            seed = itemView.findViewById(R.id.seed);

        }

        public void onBind(Auction auction){
            String aucString = "auc: " + auction.getAuc();
            auc.setText(aucString);
            String itemString = "item: " + auction.getItem();
            item.setText(itemString);
            String ownerString = "owner: " + auction.getOwner();
            aucOwner.setText(ownerString);
            String ownerRealmString = "owner realm: " + auction.getOwnerRealm();
            ownerRealm.setText(ownerRealmString);
            String bidString = "bid: " + auction.getBid();
            bid.setText(bidString);
            String buyoutString = "buyout: " + String.valueOf(auction.getBuyout());
            buyout.setText(buyoutString);
            String quantityString = "quantity: " +String.valueOf(auction.getQuantity());
            quantity.setText(quantityString);
            String timeLeftString = "timeLeft: " + auction.getTimeLeft();
            timeLeft.setText(timeLeftString);
            String seedString = "seed: " + String.valueOf(auction.getSeed());
            seed.setText(seedString);

        }
    }
}

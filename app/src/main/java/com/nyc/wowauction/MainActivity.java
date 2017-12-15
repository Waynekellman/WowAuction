package com.nyc.wowauction;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nyc.wowauction.model.WowAuctionModel;
import com.nyc.wowauction.model.WowAuctionUrl;
import com.nyc.wowauction.networking.WowApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /**
         * HackerApi hackService = retrofit.create(HackerApi.class);
         Call<Integer[]> getHackerNews = hackService.getmodel();
         getHackerNews.enqueue(new Callback<Integer[]>() {
         */

        final Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://us.api.battle.net/wow/auction/data/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        WowApi wowService = retrofit.create(WowApi.class);
        Call<WowAuctionUrl> getWowAuction = wowService.getUrl();
        getWowAuction.enqueue(new Callback<WowAuctionUrl>() {
            @Override
            public void onResponse(Call<WowAuctionUrl> call, Response<WowAuctionUrl> response) {
                WowAuctionUrl wowAuctionUrl = response.body();
                String baseUrl = wowAuctionUrl.getFiles()[0].getUrl().replace("auctions.json", "");
                Retrofit retrofit1 = new Retrofit.Builder()
                        .baseUrl(baseUrl)
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build();
                WowApi wowService2 = retrofit1.create(WowApi.class);
                Call<WowAuctionModel> getWowModel = wowService2.getModel("auctions.json");
                getWowModel.enqueue(new Callback<WowAuctionModel>() {
                    @Override
                    public void onResponse(Call<WowAuctionModel> call, Response<WowAuctionModel> response) {
                        WowAuctionModel wowAuctionModel = response.body();
                        Log.d(TAG,"wow model called " + wowAuctionModel.getRealms()[0].getName());
                    }

                    @Override
                    public void onFailure(Call<WowAuctionModel> call, Throwable t) {
                        t.printStackTrace();
                    }
                });

            }

            @Override
            public void onFailure(Call<WowAuctionUrl> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}

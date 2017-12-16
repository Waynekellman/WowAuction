package com.nyc.wowauction;

import android.app.ProgressDialog;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nyc.wowauction.controller.WowAuctionAdapter;
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
    private WowAuctionModel wowAuctionModel;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_view);
        /**
         * HackerApi hackService = retrofit.create(HackerApi.class);
         Call<Integer[]> getHackerNews = hackService.getmodel();
         getHackerNews.enqueue(new Callback<Integer[]>() {
         */
        wowAuctionModel = new WowAuctionModel();
        showProgress();
        getAuctionResults();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG,"wow model called " + wowAuctionModel.getRealms()[0].getName());
                initRecView();
            }
        }, 15000);

    }

    /**
     * private void initRecView() {
     HackerAdapter hackerAdapter = new HackerAdapter(hackerNewsArticles);
     LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
     recyclerView.setAdapter(hackerAdapter);
     recyclerView.setLayoutManager(linearLayoutManager);
     }
     */

    private void initRecView(){
        WowAuctionAdapter wowAuctionAdapter = new WowAuctionAdapter(wowAuctionModel.getAuctions());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setAdapter(wowAuctionAdapter);
        recyclerView.setLayoutManager(layoutManager);

    }
    public void showProgress() {
        final int time = 15000;
        final ProgressDialog dlg = new ProgressDialog(this);
        dlg.setMessage("Loading data...");
        dlg.setCancelable(false);
        dlg.show();
        new Handler().postDelayed(new Runnable() {
            public void run() {
                dlg.dismiss();
            }
        }, time);
    }

    private void getAuctionResults() {
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
                final WowAuctionUrl wowAuctionUrl = response.body();
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
                        wowAuctionModel = response.body();
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

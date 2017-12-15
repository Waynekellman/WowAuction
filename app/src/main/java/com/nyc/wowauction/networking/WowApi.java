package com.nyc.wowauction.networking;

import com.nyc.wowauction.model.WowAuctionModel;
import com.nyc.wowauction.model.WowAuctionUrl;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Wayne Kellman on 12/14/17.
 */

public interface WowApi {

    @GET("medivh?locale=en_US&apikey=yeud68n3eu4gtpwp4hrxbeahw7kppd4b")
    Call<WowAuctionUrl> getUrl();

    @GET("{base-url}")
    Call<WowAuctionModel> getModel(@Path("base-url") String base);
}

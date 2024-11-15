package com.huseyinkiran.cryptoretrofitjava.Service;

import com.huseyinkiran.cryptoretrofitjava.Model.CoinDetailModel;
import com.huseyinkiran.cryptoretrofitjava.Model.CryptoModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CryptoAPI {

    @GET("v1/coins")
    Call<List<CryptoModel>> getData();

    @GET("v1/coins/{coinId}")
    Call<CoinDetailModel> getDetailData(@Path("coinId") String coinId);

}


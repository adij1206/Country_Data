package com.aditya.countrydata.Retrofit;

import com.aditya.countrydata.Retrofit.Api;
import com.aditya.countrydata.Retrofit.Url;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitCountryData {
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Url.URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    public Api api = retrofit.create(Api.class);
}

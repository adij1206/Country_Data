package com.aditya.countrydata.Retrofit;

import com.aditya.countrydata.Model.Country;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    @GET("asia")
    Call<List<Country>> getAll();
}

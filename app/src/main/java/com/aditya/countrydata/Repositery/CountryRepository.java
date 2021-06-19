package com.aditya.countrydata.Repositery;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.aditya.countrydata.Model.Country;
import com.aditya.countrydata.Dao.CountryDao;
import com.aditya.countrydata.Database.CountryDatabase;
import com.aditya.countrydata.Model.CountryModelClass;
import com.aditya.countrydata.Model.Language;
import com.aditya.countrydata.Retrofit.Api;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CountryRepository {
    Retrofit retrofit;
    Api request;
    Call<List<Country>> call1;
    List<Country> modelClassList;
    LiveData<List<Country>> OfflineData;
    Context context;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    ExecutorService executorService;
    CountryDao dao;
    public CountryRepository(Application application){
        retrofit = new Retrofit.Builder()
                .baseUrl("https://restcountries.eu/rest/v2/region/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        request = retrofit.create(Api.class);
        call1=request.getAll();
        context=application.getApplicationContext();
        preferences=context.getSharedPreferences("Country Preference",Context.MODE_PRIVATE);
        editor=preferences.edit();
        executorService= Executors.newFixedThreadPool(1);
        CountryDatabase room=CountryDatabase.getInstance(application);
        dao=room.countryDao();
        OfflineData=dao.getOfflineData();
    }

    public LiveData<List<Country>> getCountryList(){
        modelClassList=new ArrayList<>();
        final MutableLiveData<List<Country>> data = new MutableLiveData<>();
        call1.enqueue(new  Callback<List<Country>>() {
            @Override
            public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {
                List<Country> countryData= (List<Country>) response.body();
                for(Country cd:countryData){
//                    String language="";
//                    String border="";
//                    border+=getBorder(cd.getBorders());
//                    language+=getLanguage(cd.getLanguages());
//
//                    CountryModelClass modelClass=new CountryModelClass(cd.getName(),cd.getCapital(),cd.getRegion(),
//                            cd.getSubregion(),cd.getPopulation(),language,border,cd.getFlag());
                    modelClassList.add(cd);
                    data.setValue(modelClassList);

                    if(preferences.getString("firstTime",null)==null){
                        //Insert
                        Insert(cd);
                    }

                }
                editor.putString("firstTime","no");
                editor.apply();

            }

            @Override
            public void onFailure(Call<List<Country>> call, Throwable t) {
                Log.i("Exceptionnnnn",t.getMessage());
            }
        });


        return data;
    }


    public void Insert(final Country country){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                dao.insert(country);
            }
        });
    }

    public LiveData<List<Country>> offlineData(){
        return OfflineData;
    }
    public void DeleteAllData(){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                dao.deleteAllCountry();
            }
        });
    }
}
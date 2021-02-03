package com.aditya.countrydata;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.aditya.countrydata.Adapter.CountryAdapter;
import com.aditya.countrydata.Retrofit.RetrofitCountryData;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Country> countryList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countryList = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        networkrequest();
    }

    private void networkrequest() {
        RetrofitCountryData retrofitCountryData = new RetrofitCountryData();
        Call<List<Country>> listCall = retrofitCountryData.api.getAll();
        listCall.enqueue(new Callback<List<Country>>() {
            @Override
            public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {
                if(response.isSuccessful()){
                    countryList = response.body();
                    Log.d("Aditya", countryList.toString());

                    CountryAdapter countryAdapter = new CountryAdapter(MainActivity.this,countryList);
                    recyclerView.setAdapter(countryAdapter);
                    countryAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Country>> call, Throwable t) {
                //Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("Aditya", t.getMessage());
            }
        });
    }
}

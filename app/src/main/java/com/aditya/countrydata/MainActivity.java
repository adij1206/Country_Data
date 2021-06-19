package com.aditya.countrydata;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.aditya.countrydata.Adapter.CountryAdapter;
import com.aditya.countrydata.Model.Country;
import com.aditya.countrydata.Model.CountryModelClass;
import com.aditya.countrydata.Repositery.CountryRepository;
import com.aditya.countrydata.Retrofit.RetrofitCountryData;
import com.aditya.countrydata.ViewModel.CountryViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Country> countryList;
    private CountryRepository countryRepositery;
    SharedPreferences preferences;
    private CountryViewModel countryViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countryList = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        preferences=this.getSharedPreferences("Country Preference",MODE_PRIVATE);

        countryViewModel=new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.
                getInstance(this.getApplication())).get(CountryViewModel.class);


        ConnectivityManager conMgr =  (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();
        if (netInfo == null){
            offline();
        }else{
            online();
        }

    }

    /*private void networkrequest() {
        RetrofitCountryData retrofitCountryData = new RetrofitCountryData();
        Call<List<Country>> listCall = retrofitCountryData.api.getAll();
        listCall.enqueue(new Callback<List<Country>>() {
            @Override
            public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {
                if(response.isSuccessful()){
                    countryList = response.body();
                    Log.d("Aditya", countryList.toString());

                    CountryAdapter countryAdapter = new CountryAdapter(MainActivity.this);
                    recyclerView.setAdapter(countryAdapter);
                    countryAdapter.notifyDataSetChanged();
                    countryViewModel.insert(countryList);
                }
            }

            @Override
            public void onFailure(Call<List<Country>> call, Throwable t) {
                //Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("Aditya", t.getMessage());
            }
        });
    }*/

    private void offlineNetworkRequest(){
        //List<Country> countries = countryViewModel.getAllCountry();
        //CountryAdapter countryAdapter = new CountryAdapter(MainActivity.this,countries);
        //recyclerView.setAdapter(countryAdapter);
        //countryAdapter.notifyDataSetChanged();
    }


    private boolean isDeviceOnline() {
        ConnectivityManager connMgr =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
//        Log.e(this.toString(),"Checking if device");
        return (networkInfo != null && networkInfo.isConnected());
    }

    public void offline(){
        //stateText.setText("Offline");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //stateText.setTextColor(this.getColor(R.color.offline));
            //stateColor.setCardBackgroundColor(this.getColor(R.color.offline));
        }

        if(getState()==null){
            final CountryAdapter adapter=new CountryAdapter(MainActivity.this);
            countryViewModel.getOfflineData().observe(MainActivity.this, new Observer<List<Country>>() {
                @Override
                public void onChanged(List<Country> countryModelClasses) {
                    Log.i("offlineeee", String.valueOf(countryModelClasses.size()));
                    adapter.post(countryModelClasses);
                }
            });

            recyclerView.setAdapter(adapter);
        }
        else {
            Toast.makeText(this, "You are Offline, No Data to Show!", Toast.LENGTH_SHORT).show();
        }
    }

    public void online(){
        //stateText.setText("Online");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //stateText.setTextColor(this.getColor(R.color.online));
            //stateColor.setCardBackgroundColor(this.getColor(R.color.online));
        }

        final CountryAdapter adapter=new CountryAdapter(MainActivity.this);
        countryViewModel.getCountryList().observe(MainActivity.this, new Observer<List<Country>>() {
            @Override
            public void onChanged(List<Country> countryModelClasses) {
                adapter.post(countryModelClasses);
            }
        });

        recyclerView.setAdapter(adapter);
    }
    public String getState(){
        return preferences.getString("offline",null);
    }


    public void deleteAll(View view) {
        if(getState()==null) {
            countryViewModel.Delete();
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("offline", "no");
            editor.apply();
            Toast.makeText(this, "Data Deleted Successfully!", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "No Data to delete!", Toast.LENGTH_SHORT).show();
        }
    }
}

package com.aditya.countrydata.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.aditya.countrydata.Model.Country;
import com.aditya.countrydata.Model.CountryModelClass;
import com.aditya.countrydata.Repositery.CountryRepository;

import java.util.List;

public class CountryViewModel extends AndroidViewModel {

    CountryRepository countryRepository;
    LiveData<List<Country>> countryList;
    LiveData<List<Country>> OfflineData;
    public CountryViewModel(@NonNull Application application) {
        super(application);
        countryRepository=new CountryRepository(application);
        countryList=countryRepository.getCountryList();
        OfflineData=countryRepository.offlineData();
    }

    public LiveData<List<Country>> getCountryList(){
        return countryList;
    }

    public LiveData<List<Country>> getOfflineData(){
        return OfflineData;
    }

    public void Delete(){
        countryRepository.DeleteAllData();
    }
}

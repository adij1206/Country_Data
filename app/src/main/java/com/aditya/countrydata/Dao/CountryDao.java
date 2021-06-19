package com.aditya.countrydata.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.aditya.countrydata.Model.Country;
import com.aditya.countrydata.Model.CountryModelClass;

import java.util.List;
@Dao
public interface CountryDao {

    @Insert
    void insert(Country country);

    @Query("SELECT * FROM country")
    LiveData<List<Country>> getOfflineData();

    @Query("DELETE FROM country")
    void deleteAllCountry();
}

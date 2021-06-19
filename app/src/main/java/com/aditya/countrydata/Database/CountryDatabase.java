package com.aditya.countrydata.Database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.aditya.countrydata.Dao.CountryDao;
import com.aditya.countrydata.GenreConvertor;
import com.aditya.countrydata.Model.Country;

@Database(entities={Country.class},version = 1)
@TypeConverters(GenreConvertor.class)
public abstract class CountryDatabase extends RoomDatabase {
    private static CountryDatabase instance;
    public abstract CountryDao countryDao();

    public static synchronized CountryDatabase getInstance(Context context){
        if(instance==null){
            instance= Room.databaseBuilder(context.getApplicationContext(),CountryDatabase.class,
                    "CountryRoomDatabase")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

}

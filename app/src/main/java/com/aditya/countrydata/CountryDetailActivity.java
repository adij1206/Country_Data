package com.aditya.countrydata;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.aditya.countrydata.Adapter.BorderAdapter;
import com.aditya.countrydata.Adapter.LanguageAdapter;

import java.util.List;

public class CountryDetailActivity extends AppCompatActivity {

    private TextView name,capital,region,subregion,borders,population,languages;
    private Country country;
    private ImageView imageView;
    private RecyclerView borderRecyclerView;
    private RecyclerView languageRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_detail);

        init();
        if(getIntent().getExtras()!=null){
            country = (Country) getIntent().getSerializableExtra("country");
        }
        String a="";
        List<Language> languageList = country.getLanguages();
        for(Language l:languageList){
            Language language = l;
            a += l.getName()+",";
        }
        String q="";
        List<String> borderList = country.getBorders();
        if(borderList.size()==0){
            q+="We Doesnot Share Border With Any Country";
        }

        Utils.fetchSvg(this, country.getFlag(), imageView);

        name.setText(country.getName());
        capital.setText( country.getCapital());
        region.setText(country.getRegion());
        subregion.setText(country.getSubregion());
        population.setText(country.getPopulation());

        borderRecyclerView.setHasFixedSize(true);
        borderRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        BorderAdapter borderAdapter = new BorderAdapter(borderList,CountryDetailActivity.this);
        borderRecyclerView.setAdapter(borderAdapter);
        borderAdapter.notifyDataSetChanged();

        languageRecyclerView.setHasFixedSize(true);
        languageRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        LanguageAdapter languageAdapter = new LanguageAdapter(languageList,CountryDetailActivity.this);
        languageRecyclerView.setAdapter(languageAdapter);
        languageAdapter.notifyDataSetChanged();
    }

    private void init() {
        name = (TextView) findViewById(R.id.name);
        capital = (TextView) findViewById(R.id.capital);
        region = (TextView) findViewById(R.id.region);
        subregion = (TextView) findViewById(R.id.subregion);
        population = (TextView) findViewById(R.id.population);
        languages = (TextView) findViewById(R.id.language);
        borders = (TextView) findViewById(R.id.borders);
        imageView = (ImageView) findViewById(R.id.imageview);
        languageRecyclerView = (RecyclerView) findViewById(R.id.recycler_language);
        borderRecyclerView = (RecyclerView) findViewById(R.id.recyler_border);

    }
}

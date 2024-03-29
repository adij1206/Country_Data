package com.aditya.countrydata.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.aditya.countrydata.Model.Language;

import java.io.Serializable;
import java.util.List;

@Entity
public class Country implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public int id;

    private String name;
    private String capital;
    private String region;
    private String subregion;
    private String population;
    private List<String> borders;
    private List<Language> languages;
    private String flag;

    public Country(String name, String capital, String region, String subregion, String population, List<String> borders, List<Language> languages, String flag) {
        this.name = name;
        this.capital = capital;
        this.region = region;
        this.subregion = subregion;
        this.population = population;
        this.borders = borders;
        this.languages = languages;
        this.flag = flag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSubregion() {
        return subregion;
    }

    public void setSubregion(String subregion) {
        this.subregion = subregion;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public List<String> getBorders() {
        return borders;
    }

    public void setBorders(List<String> borders) {
        this.borders = borders;
    }

    public List<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(List<Language> languages) {
        this.languages = languages;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}

package com.aditya.countrydata;

import androidx.room.TypeConverter;

import com.aditya.countrydata.Model.Language;

import java.util.ArrayList;
import java.util.List;

public class GenreConvertor {
    @TypeConverter
    public List<String> gettingListFromString(String border){
        List<String> strings = new ArrayList<>();
        String[] strings1 = border.split(",");

        for(String s:strings1){
            if(!s.isEmpty()){
                strings.add(s);
            }
        }
        return strings;
    }

    @TypeConverter
    public String writtingStringFromList(List<String> borders){
        String border="";
        for(int i=0;i<borders.size();i++){
            if(i!=borders.size()-1)
                border+=borders.get(i)+", ";
            else
                border+=borders.get(i);
        }

        return border;
    }

    @TypeConverter
    public List<Language> gettingLanguageFromString(String language){
        List<Language> languages = new ArrayList<>();
        String[] array = language.split(",");
        for(String s: array){
            Language language1 = new Language();
            if(!s.isEmpty()){
                language1.setName(s);
                languages.add(language1);
            }
        }
        return languages;
    }

    @TypeConverter
    public String writtingStringFromLanguage(List<Language> languages){
        String language="";
        for(int i=0;i<languages.size();i++){
            Language cd=languages.get(i);
            if(i!=languages.size()-1)
                language+=cd.getName()+", ";
            else
                language+=cd.getName();
        }
        return language;
    }

    }

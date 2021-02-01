package com.example.homeworkemployees.converters;

import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.example.homeworkemployees.pojo.Specialty;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class EmployeeConverter {
    @TypeConverter
    public String listSpecialityToString(List<Specialty> specialties){
        return new Gson().toJson(specialties);
    }
    @TypeConverter
    public List<Specialty> stringSpecialityToList(String speciality){
        Gson gson = new Gson();
        ArrayList objects = gson.fromJson(speciality,ArrayList.class);
        ArrayList<Specialty> specialties = new ArrayList<>();
        for(Object object:objects){
            specialties.add(gson.fromJson(object.toString(),Specialty.class));
        }
        return specialties;

    }
}

package com.sbizzera.mareu;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

/**
 * Creates by Boris SBIZZERA on 28/08/2019.
 */
public  class ListofStringTypeConverter {

    private  Gson gson = new Gson();

    @TypeConverter
    public  List<String> stringTotringList(String string) {
        if (string == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<String>>() {}.getType();

        return gson.fromJson(string, listType);
    }

    @TypeConverter
    public  String stringListToString(List<String> stringList) {
        return gson.toJson(stringList);
    }
}

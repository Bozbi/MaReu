package com.sbizzera.mareu.model.utils;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sbizzera.mareu.model.MeetingRoom;

import org.threeten.bp.LocalDateTime;
import org.threeten.bp.format.DateTimeFormatter;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Creates by Boris SBIZZERA on 09/09/2019.
 */
public class Converters {

    private static DateTimeFormatter mFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @TypeConverter
    public static MeetingRoom fromRoomName(String roomName) {
        Type roomType = new TypeToken<MeetingRoom>() {
        }.getType();
        return new Gson().fromJson(roomName, roomType);
    }

    @TypeConverter
    public static String fromMeetingRoom(MeetingRoom meetingRoom) {
        Gson gson = new Gson();
        String json = gson.toJson(meetingRoom);
        return json;
    }

    @TypeConverter
    public static String fromLocalDateTime(LocalDateTime localDateTime) {
        return localDateTime.format(mFormatter);
    }

    @TypeConverter
    public static LocalDateTime fromDateString(String string) {
        return LocalDateTime.parse(string, mFormatter);
    }

    @TypeConverter
    public static List<String> fromString(String string) {
        Type listType = new TypeToken<List<String>>() {
        }.getType();
        return new Gson().fromJson(string, listType);
    }

    @TypeConverter
    public static String fromListString(List<String> stringList) {
        Gson gson = new Gson();
        String json = gson.toJson(stringList);
        return json;
    }
}

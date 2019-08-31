package com.sbizzera.mareu;

import androidx.room.TypeConverter;

import org.threeten.bp.LocalDateTime;
import org.threeten.bp.format.DateTimeFormatter;


/**
 * Creates by Boris SBIZZERA on 28/08/2019.
 */
public class DateTimeTypeConverter {

    private DateTimeFormatter fmt = DateTimeFormatter.ofPattern("YYYY-MM-DD HH:mm");

    @TypeConverter
    public LocalDateTime stringToLocalDateTime (String string){
        if (string == null){
            return null;
        }

        return (LocalDateTime) fmt.parse(string);
    }

    @TypeConverter
    public String LocalDateTimeToString(LocalDateTime dateTime){


        return fmt.format(dateTime);
    }

}

package fi.asikainen.kalori;

import androidx.room.TypeConverter;

import java.util.Calendar;
import java.util.Date;


public class ADBConverters {

    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
}

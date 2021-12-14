package fi.asikainen.kalori;

import androidx.room.TypeConverter;

import java.util.Calendar;
import java.util.Date;
/**
 * @author Ricardo Nunes
 */

/**
 * Class used to convert objects into single field in Room database. In this case Java.util.Date gets converted
 * to number and then back to Date object.
 */
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

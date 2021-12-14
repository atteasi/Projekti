package fi.asikainen.kalori;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Ricardo Nunes
 */
/**
 * Database Class component of Room used to determine which tables will be present in the database
 * and database initialization. Singleton architecture. Sets up threading.
 */
@Database(entities = {User.class, CalAdd.class, CalSub.class, Weight.class}, version = 10, exportSchema = false)
@TypeConverters({ADBConverters.class})
public abstract class AppRoomDatabase extends RoomDatabase {

    public abstract UserDAO userDAO();
    public abstract CalAddDAO calAddDAO();
    public abstract CalSubDAO calSubDAO();
    public abstract WeightDAO weightDAO();

    private static volatile AppRoomDatabase roomInstance;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    static final ExecutorService databaseReadExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    /**
     * Method for initializing new/or calling current instance of the singleton Database (roomInstance)
     * @param context Application context given by Android
     * @return Instance of "roomInstance" that points to the database built by Room.databaseBuilder
     */
    static AppRoomDatabase getDatabase(final Context context) {
        if (roomInstance == null) {
            synchronized (AppRoomDatabase.class) {
                if (roomInstance == null) {
                    synchronized (AppRoomDatabase.class) {
                        if (roomInstance == null) {
                            roomInstance = Room.databaseBuilder(context.getApplicationContext(),
                                    AppRoomDatabase.class,
                                    "AppDataBase").fallbackToDestructiveMigration()
                                    .build();
                        }
                    }
                }
            }
        }
        return roomInstance;
    }
}

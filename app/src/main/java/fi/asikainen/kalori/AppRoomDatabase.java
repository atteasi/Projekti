package fi.asikainen.kalori;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {User.class, Weight.class}, version = 7, exportSchema = false)
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

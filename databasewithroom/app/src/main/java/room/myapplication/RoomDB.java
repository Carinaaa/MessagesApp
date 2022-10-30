package room.myapplication;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

// add db entities
@Database(entities = {MessagesTable.class}, version = 1, exportSchema = false)
public abstract class RoomDB extends RoomDatabase {

    // create database instance

    private static RoomDB database;
    private static String DATABASE_NAME = "database";

    public synchronized static RoomDB getInstance(Context context)
    {
        if(database == null)
        {
            // init database
            database = Room.databaseBuilder(context.getApplicationContext(), RoomDB.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return database;
    }

    // create Dao
    public abstract MainDao mainDao();


}

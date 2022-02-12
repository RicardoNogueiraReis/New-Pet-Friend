package pt.ips.pam.newpetfriend;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Animal.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase databaseInstance = null;

    public static AppDatabase getInstance(Context context) {
        if (databaseInstance == null)
            databaseInstance = Room.databaseBuilder(
                    context.getApplicationContext(), AppDatabase.class, "AnimalDB")
                    .fallbackToDestructiveMigration()
                    .build();

        return databaseInstance;
    }
    public abstract AnimalDao animalDao();
}

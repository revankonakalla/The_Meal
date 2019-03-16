package com.example.revanthkonakalla.themeal;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Jsonlistdata.class}, version = 1, exportSchema = false)
public abstract class Mealroom extends RoomDatabase {
    public abstract MealDao mealDao();

    private static Mealroom movieroom;

    static Mealroom getDatabase(final Context context) {
        if (movieroom == null) {
            synchronized (Mealroom.class) {
                if (movieroom == null) {
                    movieroom = Room.databaseBuilder(context.getApplicationContext(),
                            Mealroom.class, "mealdb")
                            .fallbackToDestructiveMigration()
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return movieroom;
    }
}

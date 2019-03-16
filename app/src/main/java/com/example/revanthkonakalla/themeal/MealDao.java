package com.example.revanthkonakalla.themeal;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface MealDao {
    @Insert
    void insert(Jsonlistdata table);

    @Delete
    void deleteAll(Jsonlistdata s);

    @Query("SELECT * from mealtable ")
    LiveData<List<Jsonlistdata>> getmeals();

    @Query("SELECT * from mealtable where dishId=:id")
    Jsonlistdata getonly(String id);
}

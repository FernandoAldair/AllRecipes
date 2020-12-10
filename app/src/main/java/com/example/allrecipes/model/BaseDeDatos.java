package com.example.allrecipes.model;

import android.content.Context;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.List;


@Database(entities = {Recetas.class}, version = 1, exportSchema = false)
public abstract class BaseDeDatos extends RoomDatabase{

    private static volatile BaseDeDatos INSTANCE;

    public static BaseDeDatos getInstance(final Context context){
        if (INSTANCE == null){
            synchronized (BaseDeDatos.class) {
                if(INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context, BaseDeDatos.class, "app.db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    @Dao
    abstract class AppDao{
        @Insert
        abstract void insertarReceta(Recetas recetas);

        @Query("SELECT * FROM Recetas")
        abstract LiveData<List<Recetas>> obtenerRecetas();
    }

}

package com.example.allrecipes;

import android.content.Context;


import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Insert;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {}, version = 1)
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
        abstract void InsertarReceta(Recetas recetas);
    }

}

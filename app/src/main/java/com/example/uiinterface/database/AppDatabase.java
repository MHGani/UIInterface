package com.example.uiinterface.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.uiinterface.dao.PersonDao;
import com.example.uiinterface.entities.Person;

@Database(entities = {Person.class}, version= 2, exportSchema = false)

public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase sInstance;
    private static final String DATABASE_NAME= "uiinterface";
    private static final Object LOCK= new Object();

    public static AppDatabase getInstance(Context context){
        if(sInstance==null) {synchronized (LOCK) {
            sInstance = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class,
                    AppDatabase.DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build();

        }
        }
        return sInstance;

    }

    public abstract PersonDao personDao();

}

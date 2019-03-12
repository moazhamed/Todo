package com.example.todo.DataBase;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.todo.DataBase.DOAs.TodoDao;
import com.example.todo.DataBase.Model.Todo;

@Database(entities = {Todo.class}, version = 1, exportSchema = false)
public abstract class MyDataBase extends RoomDatabase {


    public abstract TodoDao todoDao();

    private static MyDataBase myDataBase;

//    private MyDataBase() {
//    }

    public static MyDataBase getInstance(Context context) {
        if (myDataBase == null) {
            synchronized (MyDataBase.class) {
                myDataBase =
                        Room.databaseBuilder(context.getApplicationContext(),
                                MyDataBase.class, "Todo-DataBase")
                                // allow queries on the main thread.
                                // Don't do this on a real app! See PersistenceBasicSample for an example.
                                .allowMainThreadQueries()
                                .build();

            }
        }
        return myDataBase;


    }
}

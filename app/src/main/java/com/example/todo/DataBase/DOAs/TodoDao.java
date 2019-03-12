package com.example.todo.DataBase.DOAs;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.todo.DataBase.Model.Todo;

import java.util.List;

@Dao
public interface TodoDao {
    @Insert
    public void AddTodo(Todo item);

    @Delete
    public void RemoveTodo(Todo item);

    @Update
    public void UpdateTodo(Todo item);

    @Query("select * from Todo;")
    List<Todo> getAllTodo();

    @Query("select * from Todo where id=:id;")
    Todo searchById(int id );

}

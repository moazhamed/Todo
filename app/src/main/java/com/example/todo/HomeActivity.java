package com.example.todo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Toast;

import com.example.todo.DataBase.Model.Todo;
import com.example.todo.DataBase.MyDataBase;

import java.util.List;

public class HomeActivity extends BaseActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    TodoRecyclerAdapter adapter;
    SwipeRefreshLayout swipeRefreshLayout;
    Todo swipedItem = null;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
      //  Toolbar toolbar = findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);
        recyclerView = findViewById(R.id.recycler_view);
        swipeRefreshLayout = findViewById(R.id.swipeLayout);
        layoutManager = new LinearLayoutManager(activity);
        adapter = new TodoRecyclerAdapter(null);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        adapter.setOnItemClickListener(new TodoRecyclerAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int pos, Todo todo) {
                AddTodoActivity.todo = todo;
                Intent intent = new Intent(HomeActivity.this, AddTodoActivity.class);
                startActivity(intent);
            }
        });
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getAllTodosFromDatabase();
            }
        });

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.DOWN, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int pos = viewHolder.getAdapterPosition();
                Todo item = adapter.getSwipedItem(pos);
                swipedItem = item;
                DeleteTodo(item);

            }
        });


        itemTouchHelper.attachToRecyclerView(recyclerView);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                Intent intent = new Intent(HomeActivity.this, AddTodoActivity.class);
                startActivity(intent);
            }
        });
    }

    public void AddTodo(Todo item) {
        MyDataBase
                .getInstance(activity)
                .todoDao()
                .AddTodo(swipedItem);
        getAllTodosFromDatabase();
        swipedItem=null;

    }


    public void DeleteTodo(Todo item) {
        MyDataBase.getInstance(activity)
                .todoDao()
                .RemoveTodo(item);
        Snackbar.make(findViewById(R.id.swipeLayout), R.string.item_deleted, 4000)
                .setAction(R.string.undo, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AddTodo(swipedItem);
                    }
                }).show();
    }

    public void getAllTodosFromDatabase() {
        List<Todo> items = MyDataBase.getInstance(activity)
                .todoDao()
                .getAllTodo();
        adapter.changeData(items);
        swipeRefreshLayout.setRefreshing(false);

    }

    @Override
    protected void onStart() {
        super.onStart();
        getAllTodosFromDatabase();
    }
}



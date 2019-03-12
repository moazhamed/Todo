package com.example.todo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.todo.DataBase.DOAs.TodoDao;
import com.example.todo.DataBase.Model.Todo;
import com.example.todo.DataBase.MyDataBase;

public class AddTodoActivity extends BaseActivity implements View.OnClickListener {

    protected EditText title;
    protected EditText date;
    protected EditText content;
    protected Button addButton;
    static    Todo todo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_add_todo);
        initView();
        if(todo!=null){
           addButton.setText(R.string.update);
           title.setText(todo.getTitle());
           date.setText(todo.getDateTime());
           content.setText(todo.getContent());
        }


    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.add_button) {
            if (todo == null) {
                String sTitle = title.getText().toString();
                String sdate = date.getText().toString();
                String scontent = content.getText().toString();

                Todo todoItem = new Todo(sTitle, scontent, sdate);

                MyDataBase.getInstance(this)
                        .todoDao().AddTodo(todoItem);
                showConfirmationMessage(R.string.success, R.string.todo_added_successfully,
                        R.string.ok,
                        new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                dialog.dismiss();
                                finish();
                            }
                        }).setCancelable(false);


            }
       else{
            String sTitle = title.getText().toString();
            String sdate = date.getText().toString();
            String scontent = content.getText().toString();
            todo.setTitle(sTitle);
            todo.setContent(scontent);
            todo.setDateTime(sdate);
            MyDataBase.getInstance(activity)
                        .todoDao()
                    .UpdateTodo(todo);
            showConfirmationMessage(R.string.success, R.string.todo_updated_successfully,
                    R.string.ok,
                    new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            dialog.dismiss();
                            finish();
                        }
                    }).setCancelable(false);

            }
        }
    }

    @Override
    protected void onDestroy() {
        todo=null;
        super.onDestroy();
    }

    private void initView() {
        title =  findViewById(R.id.title);
        date =  findViewById(R.id.date);
        content =  findViewById(R.id.content);
        addButton =  findViewById(R.id.add_button);
        addButton.setOnClickListener(AddTodoActivity.this);
    }
}

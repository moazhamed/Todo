package com.example.todo;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.todo.DataBase.Model.Todo;

import java.util.List;

public class TodoRecyclerAdapter extends RecyclerView.Adapter<TodoRecyclerAdapter.ViewHolder> {

    List<Todo> items;

    OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public Todo getSwipedItem (int pos ){

        return  items.get(pos);
    }
    public interface OnItemClickListener{
        public void OnItemClick(int pos , Todo todo);

    }



    public TodoRecyclerAdapter(List<Todo> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.todo_card_item, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {

       final Todo item  = items.get(position);
        viewHolder.title.setText(item.getTitle());
        viewHolder.date.setText(item.getDateTime());
        if (onItemClickListener!=null){
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.OnItemClick(position , item);
                }
            });

        }


    }

    public void changeData(List<Todo> items){
        this.items = items;
        notifyDataSetChanged();

    }

    @Override
    public int getItemCount() {
        if(items==null){
            return 0;
        }
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView date;


        public ViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.title_);
            date = view.findViewById(R.id.date);
        }

    }
}

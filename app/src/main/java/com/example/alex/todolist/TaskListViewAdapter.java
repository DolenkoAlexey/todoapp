package com.example.alex.todolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.alex.todolist.jsons.MessageJson;

import java.util.List;

/**
 * Created by Alex on 11.06.2016.
 */
public class TaskListViewAdapter extends BaseAdapter {
    private List<MessageJson> messageJsons;
    private LayoutInflater inflater;

    public TaskListViewAdapter(List<MessageJson>  messageJsons, Context context){
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.messageJsons = messageJsons;
    }

    @Override
    public int getCount() {
        return messageJsons.size();
    }

    @Override
    public Object getItem(int position) {
        return messageJsons.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null){
            view = inflater.inflate(R.layout.list_view_item, parent, false);
        }

        TextView usernameTextView = (TextView)view.findViewById(R.id.itemTaskTextView);
        usernameTextView.setText(messageJsons.get(position).getMessageString());
        return view;
    }
}

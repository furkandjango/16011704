package com.example.mobilprog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    ArrayList<Person> userList=new ArrayList<>();
    LayoutInflater layoutInflater;
    Context context;

    public MyAdapter(ArrayList<Person> userList, Context context) {
        this.userList = userList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        layoutInflater = LayoutInflater.from(context);
        View v =layoutInflater.inflate(R.layout.row_list,parent,false);
        ViewHolder vv=new ViewHolder(v);

        return vv;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(userList.get(position).getName());
        holder.password.setText(userList.get(position).getPassword());
        holder.image.setImageResource(userList.get(position).getImage());
        holder.linearLayout.setTag(holder);

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView name,password;
        ImageView image;
        LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            password=itemView.findViewById(R.id.passw);
            image=itemView.findViewById(R.id.imagev);
            linearLayout=itemView.findViewById(R.id.linear);
        }
    }
}

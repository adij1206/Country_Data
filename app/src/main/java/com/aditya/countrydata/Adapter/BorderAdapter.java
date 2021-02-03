package com.aditya.countrydata.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aditya.countrydata.R;

import java.util.List;

public class BorderAdapter extends RecyclerView.Adapter<BorderAdapter.ViewHolder> {

    private List<String> stringList;
    private Context context;

    public BorderAdapter(List<String> stringList, Context context) {
        this.stringList = stringList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.column_list,parent,false);
        return new ViewHolder(view,context);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(stringList.get(position));
    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        public ViewHolder(@NonNull View itemView,Context ctx) {
            super(itemView);
            ctx = context;

            textView = itemView.findViewById(R.id.border_title);
        }
    }
}

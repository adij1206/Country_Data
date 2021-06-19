package com.aditya.countrydata.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aditya.countrydata.Model.Language;
import com.aditya.countrydata.R;

import java.util.List;

public class LanguageAdapter extends RecyclerView.Adapter<LanguageAdapter.ViewHolder> {
    private List<Language> languageList;
    private Context context;

    public LanguageAdapter(List<Language> languageList, Context context) {
        this.languageList = languageList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.language_list_column,parent,false);
        return new ViewHolder(view,context);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(languageList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return languageList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView textView;
        public ViewHolder(@NonNull View itemView,Context ctx) {
            super(itemView);
            ctx = context;

            textView = itemView.findViewById(R.id.language_title);
        }
    }
}

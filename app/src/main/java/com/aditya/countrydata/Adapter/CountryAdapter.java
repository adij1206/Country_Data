package com.aditya.countrydata.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aditya.countrydata.Model.Country;
import com.aditya.countrydata.CountryDetailActivity;
import com.aditya.countrydata.R;

import java.util.ArrayList;
import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder> {

    private Context context;
    private List<Country> countryList = new ArrayList<>();

    public CountryAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row,parent,false);
        return new ViewHolder(view,context);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.nameOfCountry.setText(countryList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return countryList.size();
    }

    public void post(List<Country> countryList){
        this.countryList=countryList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView nameOfCountry;

        public ViewHolder(@NonNull View itemView, Context ctx) {
            super(itemView);
            ctx = context;

            nameOfCountry = (TextView) itemView.findViewById(R.id.country_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();

                    Country country = countryList.get(position);
                    Intent intent = new Intent(context, CountryDetailActivity.class);
//                    intent.putExtra("name",country.getName());
//                    intent.putExtra("capital",country.getCapital());
//                    intent.putExtra("region",country.getRegion());
//                    intent.putExtra("subregion",country.getSubregion());
//                    intent.putExtra("population",country.getPopulation());
//                    intent.putExtra("capital",country.getCapital());
//                    intent.putExtra("capital",country.getCapital());
//                    intent.putExtra("flag",country.getFlag());
                    intent.putExtra("country",country);
                    context.startActivity(intent);

                }
            });

        }
    }


}

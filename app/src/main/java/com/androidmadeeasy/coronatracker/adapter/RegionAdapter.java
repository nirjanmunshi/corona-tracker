package com.androidmadeeasy.coronatracker.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.androidmadeeasy.coronatracker.R;
import com.androidmadeeasy.coronatracker.model.CountryData;

import java.util.ArrayList;
import java.util.List;

public class RegionAdapter extends RecyclerView.Adapter<RegionAdapter.RegionViewHolder> implements Filterable {

    private Context context;
    private List<CountryData> countries;
    private List<CountryData> copyCountries;

    private OnRecyclerViewItemClickListener listener;

    public interface OnRecyclerViewItemClickListener {
        void onItemClick (CountryData countryData);
    }

    public void setOnItemClickListener (OnRecyclerViewItemClickListener listener) {
        this.listener = listener;
    }

    public RegionAdapter(Context context, List<CountryData> countries) {
        this.context = context;
        this.countries = countries;
        copyCountries = new ArrayList<>();
        copyCountries.addAll(countries);
    }

    @NonNull
    @Override
    public RegionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_region, parent, false);
        return new RegionViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RegionViewHolder holder, int position) {
        CountryData countryData = countries.get(position);
        holder.tvRegionName.setText(countryData.getDisplayName());
        holder.tvTotalConfirmCase.setText(String.valueOf(countryData.getTotalConfirmed()));
//        holder.tvLastUpdated.setText(countryData.getLastUpdated().toString());
    }

    @Override
    public int getItemCount() {
        return (countries != null) ? countries.size() : 0;
    }

    @Override
    public Filter getFilter() {
        return null;
    }


    public class RegionViewHolder extends RecyclerView.ViewHolder {
        private TextView tvRegionName,tvTotalConfirmCase,tvLastUpdated;
        public RegionViewHolder(@NonNull View itemView) {
            super(itemView);
            tvRegionName = itemView.findViewById(R.id.row_region_name);
            tvTotalConfirmCase = itemView.findViewById(R.id.row_confirmed_cases_number);
            tvLastUpdated = itemView.findViewById(R.id.row_last_updated);

            itemView.setOnClickListener(view -> {
                int position = getAdapterPosition();
                if (listener != null && position != RecyclerView.NO_POSITION){
                    listener.onItemClick(countries.get(position));
                }
            });
        }
    }

    public void filter(String queryText) {
        countries.clear();
        if (queryText.isEmpty()) {
            countries.addAll(copyCountries);
        } else {
            for (CountryData data : copyCountries) {
                if (data.getDisplayName().toLowerCase().contains(queryText.toLowerCase())) {
                    countries.add(data);
                }
            }
        }
        notifyDataSetChanged();
    }
}

package com.example.sushma.hw06;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Sushma on 10/18/2016.
 */
public class CityAdapter extends RecyclerView.Adapter<CityAdapter.ViewHolder> {

    private List<City> mCities;
    private Context mContext;

    public CityAdapter(Context context, List<City> cities) {
        mCities = cities;
        mContext = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView nameTextView;
        public TextView tempTextView, lastUpdated;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            nameTextView = (TextView) itemView.findViewById(R.id.textViewCityCountry);
            tempTextView = (TextView) itemView.findViewById(R.id.textViewTemperature);
            lastUpdated = (TextView) itemView.findViewById(R.id.textViewLastUpdated);
        }
    }

    @Override
    public CityAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.item_contact, parent, false);

        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CityAdapter.ViewHolder holder, int position) {
        City city = mCities.get(position);

        TextView textView = holder.nameTextView;
        textView.setText(city.getCityName()+","+city.getCountry());
        TextView textViewTemp = holder.tempTextView;
        textViewTemp.setText(city.getTemperature());
        TextView textViewLast = holder.lastUpdated;
        textViewLast.setText(city.getDate());
    }

    @Override
    public int getItemCount() {
        return mCities.size();
    }
}

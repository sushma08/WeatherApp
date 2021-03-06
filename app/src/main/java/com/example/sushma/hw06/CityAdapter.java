package com.example.sushma.hw06;

import android.content.Context;
<<<<<<< HEAD
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Date;
import java.util.List;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Vinayak on 10/19/2016.
 */

/*
* Assignment #6
* Names: Vinayak Kolhapure and Sushma Reddy
* */

=======
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
>>>>>>> 1002f1ea628e8d0a21c59bf27a8703c51a181c0a
public class CityAdapter extends RecyclerView.Adapter<CityAdapter.ViewHolder> {

    private List<City> mCities;
    private Context mContext;
<<<<<<< HEAD
    ImageView image;
    /*Bitmap grayIcon;
    Bitmap goldIcon;*/
    City city;
=======
>>>>>>> 1002f1ea628e8d0a21c59bf27a8703c51a181c0a

    public CityAdapter(Context context, List<City> cities) {
        mCities = cities;
        mContext = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
<<<<<<< HEAD
        public TextView tvSaveCity,tvSaveTemp,tvSaveDate, tvSaveTest;
        public ImageView imageStar;

=======
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView nameTextView;
        public TextView tempTextView, lastUpdated;
>>>>>>> 1002f1ea628e8d0a21c59bf27a8703c51a181c0a

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

<<<<<<< HEAD
            tvSaveCity = (TextView) itemView.findViewById(R.id.tvSavedCity);
            tvSaveTemp = (TextView) itemView.findViewById(R.id.tvSavedTemp);
            tvSaveDate = (TextView) itemView.findViewById(R.id.tvSavedDate);
            imageStar = (ImageView) itemView.findViewById(R.id.tvSavedStarImage);
            tvSaveTest = (TextView) itemView.findViewById(R.id.tvSavedTest);
=======
            nameTextView = (TextView) itemView.findViewById(R.id.textViewCityCountry);
            tempTextView = (TextView) itemView.findViewById(R.id.textViewTemperature);
            lastUpdated = (TextView) itemView.findViewById(R.id.textViewLastUpdated);
>>>>>>> 1002f1ea628e8d0a21c59bf27a8703c51a181c0a
        }
    }

    @Override
    public CityAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

<<<<<<< HEAD
        View contactView = inflater.inflate(R.layout.saved_city_list, parent, false);
=======
        View contactView = inflater.inflate(R.layout.item_contact, parent, false);
>>>>>>> 1002f1ea628e8d0a21c59bf27a8703c51a181c0a

        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
<<<<<<< HEAD
    public void onBindViewHolder(final CityAdapter.ViewHolder viewHolder, final int position) {
        city = mCities.get(position);

        final TextView tvCity = viewHolder.tvSaveCity;
        TextView tvTemp = viewHolder.tvSaveTemp;
        TextView tvDate = viewHolder.tvSaveDate;
        image = viewHolder.imageStar;
        TextView tvTest = viewHolder.tvSaveTest;

        String nameOfCity = city.getCityName().substring(0,1).toUpperCase()+
                city.getCityName().substring(1).toLowerCase();
        tvCity.setText(nameOfCity + ", " + city.getCountry().toUpperCase());

        SharedPreferences getData = PreferenceManager.getDefaultSharedPreferences(mContext);
        String values = getData.getString("KEY","C");
        Log.d("Temp value",values);
        if(values.equals("C")){
            tvTemp.setText(city.getTemperature()+(char) 0x00B0+"C");
        }else{
            double i = Double.parseDouble(city.getTemperature())*9/5+32;
            i = Math.floor(i*100)/100;
            String temp = Double.toString(i);
            tvTemp.setText(temp+(char) 0x00B0+"F");
        }

        tvDate.setText(city.getDate());
        if (city.getFavorite().equals("false")) {
            image.setImageResource(R.drawable.star_gray);
        } else {
            image.setImageResource(R.drawable.star_gold);
        }

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("demo","Star Clicked"+ " " + position);
                city = mCities.get(position);
                if(city.getFavorite().equals("false")) {
                    image = viewHolder.imageStar;
                    city.setFavorite("true");
                    MainActivity.updateFavorite(position,"true");
                    image.setImageResource(R.drawable.star_gold);
                } else {
                    image = viewHolder.imageStar;
                    city.setFavorite("false");
                    MainActivity.updateFavorite(position,"false");
                    image.setImageResource(R.drawable.star_gray);
                }
            }
        });

        tvTest.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                MainActivity mainActivity = new MainActivity();
                mainActivity.deleteLongPressedFavorite(position);
                return true;
            }
        });

        tvTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = new MainActivity();
                mainActivity.openCityWeatherForSavedCity(position, mContext);
            }
        });

        tvCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = new MainActivity();
                mainActivity.openCityWeatherForSavedCity(position, mContext);
            }
        });

        tvTemp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = new MainActivity();
                mainActivity.openCityWeatherForSavedCity(position, mContext);
            }
        });

        tvCity.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                MainActivity mainActivity = new MainActivity();
                mainActivity.deleteLongPressedFavorite(position);
                return false;
            }
        });

        tvTemp.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                MainActivity mainActivity = new MainActivity();
                mainActivity.deleteLongPressedFavorite(position);
                return false;
            }
        });

        tvDate.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                MainActivity mainActivity = new MainActivity();
                mainActivity.deleteLongPressedFavorite(position);
                return false;
            }
        });

        image.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                MainActivity mainActivity = new MainActivity();
                mainActivity.deleteLongPressedFavorite(position);
                return false;
            }
        });
=======
    public void onBindViewHolder(CityAdapter.ViewHolder holder, int position) {
        City city = mCities.get(position);

        TextView textView = holder.nameTextView;
        textView.setText(city.getCityName()+","+city.getCountry());
        TextView textViewTemp = holder.tempTextView;
        textViewTemp.setText(city.getTemperature());
        TextView textViewLast = holder.lastUpdated;
        textViewLast.setText(city.getDate());
>>>>>>> 1002f1ea628e8d0a21c59bf27a8703c51a181c0a
    }

    @Override
    public int getItemCount() {
        return mCities.size();
    }
}

package com.example.sushma.hw06;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Vinayak on 10/18/2016.
 */

/*
* Assignment #6
* Names: Vinayak Kolhapure and Sushma Reddy
* */

public class CityWeatherAdapter extends RecyclerView.Adapter<CityWeatherAdapter.ViewHolder>{

    private List<Weather> mWeathers;
    private Context mContext;

    public static String webURLImage = "http://openweathermap.org/img/w/";

    public CityWeatherAdapter(Context context, List<Weather> weathers) {
        mWeathers = weathers;
        mContext = context;
    }

    private Context getContext() {
        return mContext;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewDate;
        public TextView textViewTemp;
        public ImageView imageTemp;

        public ViewHolder(View itemView) {
            super(itemView);

            textViewDate = (TextView) itemView.findViewById(R.id.tvDateDay);
            textViewTemp = (TextView) itemView.findViewById(R.id.tvDateTemp);
            imageTemp = (ImageView) itemView.findViewById(R.id.imageDate);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.day_weather_ite, parent, false);

        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CityWeatherAdapter.ViewHolder viewHolder, int position) {
        Weather weather = mWeathers.get(position);

        TextView tvDate = viewHolder.textViewDate;
        String date = new SimpleDateFormat("yyyy-MM-dd").format(weather.getDate());
        String dateFinal = WeatherUtil.convertDateToMMMddyyyy(date,0);

        tvDate.setText(dateFinal);
        TextView tvTemp = viewHolder.textViewTemp;
        SharedPreferences getData = PreferenceManager.getDefaultSharedPreferences(mContext);
        String values = getData.getString("KEY","C");
        if(values.equals("C")){
            tvTemp.setText(weather.getTemperature()+(char) 0x00B0+"C");
        }else{
            tvTemp.setText(weather.getTemperature()+(char) 0x00B0+"F");
        }

        ImageView image = viewHolder.imageTemp;
        String url = webURLImage+weather.getIconUrl()+".png";
        Picasso.with(getContext()).load(url).into(image);
    }

    @Override
    public int getItemCount() {
        return mWeathers.size();
    }
}

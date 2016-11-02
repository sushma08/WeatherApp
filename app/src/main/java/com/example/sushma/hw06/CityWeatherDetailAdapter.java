package com.example.sushma.hw06;

/**
 * Created by Vinayak on 10/19/2016.
 */
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
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
public class CityWeatherDetailAdapter extends RecyclerView.Adapter<CityWeatherDetailAdapter.ViewHolder>{

    private List<Weather> mWeathers;
    private Context mContext;

    public static String webURLImage = "http://openweathermap.org/img/w/";

    public CityWeatherDetailAdapter(Context context, List<Weather> weathers) {
        mWeathers = weathers;
        mContext = context;
    }

    private Context getContext() {
        return mContext;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvTime;
        public TextView tvTemp;
        public ImageView imageTemp;
        public TextView tvCondition;
        public TextView tvPressure;
        public TextView tvHumidity;
        public TextView tvWind;

        public ViewHolder(View itemView) {
            super(itemView);

            tvTime = (TextView) itemView.findViewById(R.id.tvDetailTime);
            tvTemp = (TextView) itemView.findViewById(R.id.tvDetailTemp);
            imageTemp = (ImageView) itemView.findViewById(R.id.imageDetail);
            tvCondition = (TextView) itemView.findViewById(R.id.tvDetailCondition);
            tvPressure = (TextView) itemView.findViewById(R.id.tvDetailPressure);
            tvHumidity = (TextView) itemView.findViewById(R.id.tvDetailHumidity);
            tvWind = (TextView) itemView.findViewById(R.id.tvDetailWind);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.detail_layout, parent, false);

        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CityWeatherDetailAdapter.ViewHolder viewHolder, int position) {
        Weather weather = mWeathers.get(position);

        TextView tvTime = viewHolder.tvTime;
        TextView tvTemp = viewHolder.tvTemp;
        TextView tvCondition = viewHolder.tvCondition;
        TextView tvPressure = viewHolder.tvPressure;
        TextView tvHumidity = viewHolder.tvHumidity;
        TextView tvWind = viewHolder.tvWind;
        ImageView image = viewHolder.imageTemp;

        tvTime.setText(weather.getTime());

        String url = webURLImage+weather.getIconUrl()+".png";
        Picasso.with(getContext()).load(url).into(image);

        SharedPreferences getData = PreferenceManager.getDefaultSharedPreferences(mContext);
        String values = getData.getString("KEY","C");
        if(values.equals("C")){
            tvTemp.setText(weather.getTemperature()+(char) 0x00B0+"C");
        }else{
            tvTemp.setText(weather.getTemperature()+(char) 0x00B0+"F");
        }

        tvCondition.setText(weather.getClimateType());
        tvPressure.setText(weather.getPressure());
        tvHumidity.setText(weather.getHumidity());
        tvWind.setText(weather.getWindSpeed()+", "+weather.getWindDirection());
    }

    @Override
    public int getItemCount() {
        return mWeathers.size();
    }


}

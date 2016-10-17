package com.example.sushma.hw06;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class CityWeather extends AppCompatActivity implements GetAsyncTemp.Temp{

    public static String intentCityNam, intentCountryNam, city;
    public static ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_weather);
        pd = new ProgressDialog(this);
        TextView tvLoc;
        //tvLoc = (TextView) findViewById(R.id.textViewLocation);

        if(getIntent().getExtras()!=null){
            Intent intent = getIntent();
            intentCityNam = intent.getStringExtra("city");
            intentCountryNam = intent.getStringExtra("state");
            city = intentCityNam.replace("_"," ");
            //tvLoc.setText(" "+city+", "+intentCountryNam);
            new GetAsyncTemp(this,this).execute("http://api.openweathermap.org/data/2.5/forecast?q="+intentCityNam+","+intentCountryNam+"&mode=json&units=metric&appid=69e734e8d7f3ecde5c495bbae50eff7f");
        }

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_file,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action:
                Toast.makeText(getApplicationContext(), "Saved City", Toast.LENGTH_SHORT).show();
            case R.id.action1:
                Toast.makeText(getApplicationContext(), "Settings", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void getTemp() {

    }
}

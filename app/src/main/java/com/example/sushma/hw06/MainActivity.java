package com.example.sushma.hw06;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    String cityNAme, countryName;
    DatabaseDataManager dm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dm = new DatabaseDataManager(this);
    }

    public void showCityWeather(View view) {
        cityNAme = ((EditText) findViewById(R.id.editTextCity)).getText().toString();
        countryName = ((EditText) findViewById(R.id.editTextState)).getText().toString();
        if (cityNAme.equals("") || countryName.equals("")) {
            Toast.makeText(getApplicationContext(), "Please enter both city and state fields...", Toast.LENGTH_LONG).show();
        } else {
            Intent intent = new Intent(this, CityWeather.class);
            cityNAme = cityNAme.replace(' ', '_');
            intent.putExtra("city", cityNAme);
            intent.putExtra("state", countryName);
            startActivity(intent);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_file,menu);
        menu.findItem(R.id.action).setVisible(false);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action1:
                Toast.makeText(getApplicationContext(), "Settings", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.setClassName(this, "com.example.sushma.hw06.MyPreferenceActivity");
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

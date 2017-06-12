package com.example.mamzky.weatherapp;

import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mamzky.weatherapp.data.Channel;
import com.example.mamzky.weatherapp.data.Item;
import com.example.mamzky.weatherapp.service.WeatherServiceCallback;
import com.example.mamzky.weatherapp.service.YahooWeatherService;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements WeatherServiceCallback {

    private ImageView iconWeatherImageView;
    private TextView temperatureTextview;
    private TextView conditionTextView;
    private TextView locationTextView;
    private TextView codeTextView;
    private ListView listView;

    private YahooWeatherService service; //masih error belum ke detect classnya
    private ProgressDialog dialog;
    int[] IMAGES = {R.drawable.ic_album_black_48dp,
            R.drawable.ic_play_circle_outline_black_48dp,
            R.drawable.ic_insert_photo_black_48dp,
            R.drawable.ic_playlist_play_black_48dp,
            R.drawable.ic_info_outline_black_48dp
    };

    String[] MENU1 = {"menu 1",
            "menu2",
            "menu3",
            "menu4",
            "menu5"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iconWeatherImageView    = (ImageView) findViewById(R.id.imgView_iconWeather);
        temperatureTextview     = (TextView) findViewById(R.id.textView_temperature);
        conditionTextView       = (TextView) findViewById(R.id.textView_condition);
        locationTextView        = (TextView) findViewById(R.id.textView_location);
        codeTextView            = (TextView) findViewById(R.id.texView_code);
        listView                = (ListView) findViewById(R.id.listView);

        service = new YahooWeatherService(this);
        dialog  = new ProgressDialog(this);
        dialog.setMessage("Loading...");
        dialog.show();

        service.refreshWeather("Depok, Indonesia");


    }

    @Override
    public void serviceSuccess(Channel channel) {

        dialog.hide();

        Item item = channel.getItem();

        int resourceId    = getResources().getIdentifier("drawable/icon_" + item.getCondition().getCode(), null, getPackageName());

        @SuppressWarnings("deprecation")
        Drawable weatherIconDrawable    = getResources().getDrawable(resourceId);
        iconWeatherImageView.setImageDrawable(weatherIconDrawable);

        temperatureTextview.setText(item.getCondition().getTemperature() + "\u00B0" + channel.getUnits().getTemperature());
        conditionTextView.setText(item.getCondition().getDescription());
        locationTextView.setText(service.getLocation());
        codeTextView.setText(item.getCondition().getCode()+" ");

        //CustomAdapter customAdapter = new CustomAdapter();
        //listView.setAdapter(customAdapter);

    }

    @Override
    public void serviceFailure(Exception exception) {
        dialog.hide();
        Toast.makeText(this, exception.getMessage(), Toast.LENGTH_LONG).show();
    }

    class CustomAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return IMAGES.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup parent) {
            view = getLayoutInflater().inflate(R.layout.customlayout,null);

            ImageView imageView = (ImageView)findViewById(R.id.imageView);
            TextView textView = (TextView)findViewById(R.id.textView);

            imageView.setImageResource(IMAGES[i]);
            textView.setText(MENU1[i]);

            return view;
        }
    }
}

package com.example.mamzky.weatherapp.data;


import org.json.JSONObject;
/**
 * Created by Reezky on 6/11/2017.
 */

public class Units implements JSONPopulator {
    private String temperature;

    public String getTemperature() {
        return temperature;
    }

    @Override
    public void populate(JSONObject data) {
        temperature = data.optString("temperature");
    }
}

package com.example.mamzky.weatherapp.service;

/**
 * Created by Imam on 6/11/2017.
 */

import com.example.mamzky.weatherapp.data.Channel;

public interface WeatherServiceCallback {
    void serviceSuccess(Channel channel);

    void serviceFailure(Exception exception);
}

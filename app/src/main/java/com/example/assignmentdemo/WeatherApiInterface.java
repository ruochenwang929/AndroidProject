package com.example.assignmentdemo;

import retrofit2.Call;
import retrofit2.http.GET;

//https://api.openweathermap.org/data/2.5/weather?lat=31.230391&lon=121.473701&appid=3bdb45244c9b2d4e6d4fbb5a5f176963

public interface WeatherApiInterface {
    @GET("weather?lat=31.230391&lon=121.473701&appid=3bdb45244c9b2d4e6d4fbb5a5f176963")
    Call<Root> getWeather();
}

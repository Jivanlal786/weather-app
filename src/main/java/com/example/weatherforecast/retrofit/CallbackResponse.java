package com.example.weatherforecast.retrofit;

import com.example.weatherforecast.model.Main;
import com.example.weatherforecast.model.Sys;
import com.example.weatherforecast.model.Weather;
import com.example.weatherforecast.model.Wind;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public class CallbackResponse {

    @SerializedName("weather")
    private List<Weather> weather;
    @SerializedName("main")
    private Main main;
    @SerializedName("wind")
    private Wind wind;
    private Long dt;
    @SerializedName("sys")
    private Sys sys;
    private Long timezone;
    private String name;

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Long getDt() {
        return dt;
    }

    public void setDt(Long dt) {
        this.dt = dt;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public Long getTimezone() {
        return timezone;
    }

    public void setTimezone(Long timezone) {
        this.timezone = timezone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

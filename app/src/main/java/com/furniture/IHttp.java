package com.furniture;

import com.furniture.bean.json.LimitResult;
import com.furniture.bean.json.PM25Result;
import com.furniture.bean.json.WeatherResult;
import com.furniture.bean.json.WeatherShanghaiResult;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * .  ┏┓　　　┏┓
 * .┏┛┻━━━┛┻┓
 * .┃　　　　　　　┃
 * .┃　　　━　　　┃
 * .┃　┳┛　┗┳　┃
 * .┃　　　　　　　┃
 * .┃　　　┻　　　┃
 * .┃　　　　　　　┃
 * .┗━┓　　　┏━┛
 * .    ┃　　　┃        神兽保佑
 * .    ┃　　　┃          代码无BUG!
 * .    ┃　　　┗━━━┓
 * .    ┃　　　　　　　┣┓
 * .    ┃　　　　　　　┏┛
 * .    ┗┓┓┏━┳┓┏┛
 * .      ┃┫┫　┃┫┫
 * .      ┗┻┛　┗┻┛
 *
 * @author lbx
 * @date 2018/12/3.
 */

public interface IHttp {
    @GET("index?")
    Observable<WeatherResult> getWeather(@Query("key") String key,
                                         @Query("format") String format,
                                         @Query("cityname") String cityname);

    @GET("data/cityinfo/101020100.html")
    Observable<WeatherShanghaiResult> getWeatherShanghai();

    @GET("environment/air/pm?")
    Observable<PM25Result> getPM25(@Query("key") String key,
                                   @Query("format") String format,
                                   @Query("city") String city);

    @GET("xianxing/index?")
    Observable<LimitResult> getLimit(@Query("key") String key,
                                     @Query("dtype") String dtype,
                                     @Query("city") String city);
}

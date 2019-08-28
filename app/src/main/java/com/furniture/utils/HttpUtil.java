package com.furniture.utils;

import com.furniture.Config;
import com.furniture.IHttp;
import com.furniture.bean.json.LimitResult;
import com.furniture.bean.json.PM25Result;
import com.furniture.bean.json.WeatherResult;
import com.furniture.bean.json.WeatherShanghaiResult;

import lbx.xtoollib.XTools;
import lbx.xtoollib.listener.OnHttpObservableCallBack;

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

public class HttpUtil {

    private static HttpUtil INSTANCE;

    public static HttpUtil getInstance() {
        if (INSTANCE == null) {
            synchronized (HttpUtil.class) {
                if (INSTANCE == null) {
                    INSTANCE = new HttpUtil();
                }
            }
        }
        return INSTANCE;
    }

    private HttpUtil() {
    }

    public void getWeather(OnHttpObservableCallBack<WeatherResult> callBack) {
        String local = "北京";
        if (Config.APP_TYPE == Config.TYPE_DEMO_JINAN) {
            local = "济南";
        } else if (Config.APP_TYPE == Config.TYPE_DEMO_SHANGHAI) {
            // local = "上海";
            XTools.HttpUtil().send(XTools.HttpUtil()
                    .getRetrofit("http://www.weather.com.cn/", IHttp.class, "查询上海天气")
                    .getWeatherShanghai(), new OnHttpObservableCallBack<WeatherShanghaiResult>() {
                @Override
                public void onSuccess(WeatherShanghaiResult weatherShanghaiResult) {
                    if (callBack != null) {
                        WeatherResult t = new WeatherResult();
                        WeatherResult.ResultBean result = new WeatherResult.ResultBean();
                        WeatherResult.ResultBean.TodayBean today = new WeatherResult.ResultBean.TodayBean();
                        today.setWeather("-");
                        result.setToday(today);
                        WeatherResult.ResultBean.SkBean sk = new WeatherResult.ResultBean.SkBean();
                        sk.setTemp(weatherShanghaiResult.getWeatherinfo().getTemp());
                        result.setSk(sk);
                        t.setResult(result);
                        callBack.onSuccess(t);
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    if (callBack != null) {
                        callBack.onFailure(t);
                    }
                }
            });
            return;
        }
        XTools.HttpUtil().send(XTools.HttpUtil()
                .getRetrofit("http://v.juhe.cn/weather/", IHttp.class, "查询天气")
                .getWeather("3a541afe424c94a58044f43e340bb2d2", "2", local), callBack);
    }

    public void getPM25(OnHttpObservableCallBack<PM25Result> callBack) {
        XTools.HttpUtil().send(XTools.HttpUtil()
                .getRetrofit("http://web.juhe.cn:8080/", IHttp.class, "查询PM2.5")
                .getPM25("6ab7ea3b4715c769430fba0c2152a49a", "2", "beijing"), callBack);
    }

    public void getLimit(OnHttpObservableCallBack<LimitResult> callBack) {
        XTools.HttpUtil().send(XTools.HttpUtil()
                .getRetrofit("http://v.juhe.cn/", IHttp.class, "查询限行")
                .getLimit("293a6713092753e617aab0d58f71e217", "2", "beijing"), callBack);
    }

}

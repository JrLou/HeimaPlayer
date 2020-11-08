package com.example.heimaplayer.util;
import android.util.Log;

public class URLProviderUtils {
    /**
     * 获取首页的url
     *
     * @param offset 数据偏移量
     * @param size   返回数据的条目个数
     * @return url
     */
    public static String getHomeUrl(int offset, int size) {
        String url = "http://192.168.8.167:3100/users/homeList?from=hmApp"
                + "&offset=" + offset
                + "&limits=" + size;
        Log.i("Main_url", url);
        return url;
    }
}

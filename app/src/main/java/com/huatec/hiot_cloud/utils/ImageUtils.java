package com.huatec.hiot_cloud.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.huatec.hiot_cloud.R;

/**
 * 图片工具类
 */
public class ImageUtils {

    public static void show(Context context, ImageView imageView, String url) {
        Glide.with(context).load(url).apply(getCommonOptions()).into(imageView);
    }


    /**
     * 获取图片默认配置
     *
     * @return
     */
    private static RequestOptions getCommonOptions() {
        RequestOptions options = new RequestOptions();
        options.placeholder(R.drawable.loading)
                .error(R.drawable.error)
                .centerCrop();
        return options;
    }
}

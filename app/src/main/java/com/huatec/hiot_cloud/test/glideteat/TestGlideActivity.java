package com.huatec.hiot_cloud.test.glideteat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.huatec.hiot_cloud.R;
import com.huatec.hiot_cloud.utils.ImageUtils;

public class TestGlideActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_glide);

        final String url = "http://pic1.win4000.com/wallpaper/2018-08-16/5b750e40cbed0.jpg";
        //glide加载按钮
        final ImageView iv = findViewById(R.id.iv_glide_test);
        Button btnGlideLoad = findViewById(R.id.btn_glide_load_test);
        btnGlideLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Glide.with(TestGlideActivity.this)
//                        .load(url)
//                        .asBitmap()
                        .load("http://p1.pstatp.com/large/166200019850062839d3")
                        .placeholder(R.drawable.loading)
                        .error(R.drawable.error)
                        //使用asBitmap时不用
                        .transition(new DrawableTransitionOptions().crossFade(1000))
                        .centerCrop()
                        .into(iv);

            }
        });

        //使用工具类显示
        Button btnUtil = findViewById(R.id.btn_glide_util_test);
        btnUtil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageUtils.show(TestGlideActivity.this, iv, url);
            }
        });
    }
}

package com.git.hkl.slidemenuapplication.base;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;

/**
 * 项目名称：SlideMenuApplication
 * 类描述：
 * 创建人：霍凯丽
 * 创建时间：2016/12/27 14:44
 */
public class MyApplication extends Application {
    private static MyApplication instance = null;


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        ImagePipelineConfig config = ImagePipelineConfig.newBuilder(getContext())
                .setDownsampleEnabled(true)//图片压缩
                .build();
        Fresco.initialize(getContext(), config);
    }
    public static Context getContext(){
        return instance.getApplicationContext();
    }
}

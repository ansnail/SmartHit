package top.a5ish.nbdatastatistics.application;

import android.app.Application;

import top.ish.smarthitlibrary.main.SmartHitkits;


/**
 * Created by yanjie on 17/6/26.
 */

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SmartHitkits.getInstance().initActivity(this);

    }

}

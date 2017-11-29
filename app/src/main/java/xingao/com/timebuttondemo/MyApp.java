package xingao.com.timebuttondemo;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by oldhigh on 2017/11/29.
 */

public class MyApp  extends Application{

    @Override
    public void onCreate() {
        super.onCreate();

        LeakCanary.install(this);
    }
}

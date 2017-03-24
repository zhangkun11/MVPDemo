package com.example.retrofitrxjava.mvpdemomine;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.example.retrofitrxjava.mvpdemomine.data.Data;

import java.util.Stack;

/**
 * Created by admin on 2017-03-24.
 */

public class App extends Application {
    public static Data data;
    public static App myApp;
    public static Stack<Activity> stack;
    @Override
    public void onCreate() {
        super.onCreate();
        data=new Data();
        myApp=this;
        stack=new Stack<>();
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle bundle) {
                stack.add(activity);
            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                stack.remove(activity);

            }
        });
    }

    public static App getMyApp() {
        return myApp;
    }

    public static Data getData() {
        return data;
    }

    public static Stack<Activity> getStack() {
        return stack;
    }

    /**
     * 获取当前activity
     * @return
     */
    public static Activity getCurActivity(){
        return stack.lastElement();
    }

}

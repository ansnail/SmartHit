package top.ish.smarthitlibrary.main;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import top.ish.smarthitlibrary.constants.VariableValue;
import top.ish.smarthitlibrary.listener.HitContentListener;
import top.ish.smarthitlibrary.utils.HitUtils;

/**
 * Created by yanjie on 17/7/24.
 * 无埋点打点主入口
 */

public class SmartHitkits {
    private static final SmartHitkits ourInstance = new SmartHitkits();

    public static SmartHitkits getInstance() {
        return ourInstance;
    }

    private SmartHitkits() {
    }

    /**
     * 初始化，记录activity的声明周期
     * @param application 全局application
     */
    public SmartHitkits initActivity(Application application){
        application.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle bundle) {

            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {
                HitUtils.onResumedMethod(activity.getClass().getSimpleName());
            }

            @Override
            public void onActivityPaused(Activity activity) {
                HitUtils.onPausedMethod(activity.getClass().getSimpleName());
            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });
        return this;
    }

    /**
     * 初始化，记录activity的声明周期
     * @param manager 全局application
     */
    public SmartHitkits initFragment(FragmentManager manager){
        manager.registerFragmentLifecycleCallbacks(new FragmentManager.FragmentLifecycleCallbacks() {
            @Override
            public void onFragmentResumed(FragmentManager fm, Fragment f) {
                super.onFragmentResumed(fm, f);
                HitUtils.onResumedMethod(f.getClass().getSimpleName());
            }

            @Override
            public void onFragmentPaused(FragmentManager fm, Fragment f) {
                super.onFragmentPaused(fm, f);
                HitUtils.onPausedMethod(f.getClass().getSimpleName());
            }
        },false);
     return this;
    }


    /**
     * 初始化打点日志监听
     * @param listener 监听
     */
    public SmartHitkits initHitContentListener(HitContentListener listener){
        VariableValue.recordPath = listener.hitRecordPath();
        VariableValue.recordContent = listener.hitContent();
        return this;
    }

}

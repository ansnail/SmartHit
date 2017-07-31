package top.ish.smarthitlibrary.utils;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import top.ish.smarthitlibrary.context.App;

/**
 * Created by yanjie on 17/7/5.
 * 打点工具
 */

public class HitUtils {

    public static void onClickMethod(View view, String who_am_i) {
        String viewId = who_am_i + "." + HitIdHelper.getInstance().getViewName(view.getId());
        String hitid = HitIdHelper.getInstance().getHitId(viewId);
        Toast.makeText(App.INSTANCE, viewId, Toast.LENGTH_LONG).show();
        if (!TextUtils.isEmpty(hitid)) {
            Log.e("hitidlog", "view的名字是：" + viewId + ",打点的ID值是：" + hitid);
        }
    }

    public static void onResumedMethod(String who_am_i) {
        String hitid = HitIdHelper.getInstance().getHitId(who_am_i);
        if (!TextUtils.isEmpty(hitid)) {
            Log.e("hitidlog", "进入页面：" + who_am_i + ",打点的ID值是：" + hitid);
        }
    }

    public static void onPausedMethod(String who_am_i) {
        String hitid = HitIdHelper.getInstance().getHitId(who_am_i);
        if (!TextUtils.isEmpty(hitid)) {
            Log.e("hitidlog", "退出页面：" + who_am_i + ",打点的ID值是：" + hitid);
        }
    }
}

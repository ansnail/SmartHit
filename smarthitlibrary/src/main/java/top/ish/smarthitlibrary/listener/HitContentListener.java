package top.ish.smarthitlibrary.listener;

/**
 * Created by yanjie on 17/7/25.
 * 打点的具体内容，需要用户去实现
 */

public interface HitContentListener {
    //日志记录位置
    String hitRecordPath();
    //用户自定义打点内容
    String hitContent();
}

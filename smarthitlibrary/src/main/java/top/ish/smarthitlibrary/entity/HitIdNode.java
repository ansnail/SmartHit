package top.ish.smarthitlibrary.entity;

/**
 * Created by yanjie on 17/7/5.
 * 打点节点实体类
 */

public class HitIdNode {

    private String describe;
    private String name;
    private String hitid;

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHitid() {
        return hitid;
    }

    public void setHitid(String hitid) {
        this.hitid = hitid;
    }

    @Override
    public String toString() {
        return "HitIdNode{" +
                "describe='" + describe + '\'' +
                ", name='" + name + '\'' +
                ", hitid='" + hitid + '\'' +
                '}';
    }
}

package top.ish.smarthitlibrary.utils;

import android.util.SparseArray;

import java.util.HashMap;

public class HitIdHelper {

    private static HitIdHelper helper = new HitIdHelper();
    private HashMap<String, String> hitIdNodes = new HashMap<>();
    private SparseArray<String> rParseResult = new SparseArray<>();


    private HitIdHelper() {
    }

    public static HitIdHelper getInstance() {
        return helper;
    }

    /**
     * 根据映射关系获得打点值
     *
     * @param name 点的名称
     * @return hitid
     */
    public String getHitId(String name) {
        if (hitIdNodes.size() == 0) {
            XmlParser xmlParser = new XmlParser();
            hitIdNodes = xmlParser.parseAssetsXML();
        }
        return hitIdNodes.get(name);
    }

    /**
     * 根据id值获得view的名称
     *
     * @param id ID值
     * @return view名称
     */
    public String getViewName(int id) {
        if (rParseResult.size() == 0) {
            RParser rParser = new RParser();
            rParseResult = rParser.parseR();
        }
        return rParseResult.get(id);
    }


}
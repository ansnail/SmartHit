package top.ish.smarthitlibrary.utils;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;

import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import top.ish.smarthitlibrary.context.App;
import top.ish.smarthitlibrary.entity.HitIdNode;

/**
 * Created by yanjie on 17/7/5.
 * xml打点解析器
 */

public class XmlParser {

    public HashMap<String,String> parseAssetsXML(){
        try {
            InputStream stream = App.INSTANCE.getAssets().open("hitid.xml");
            return parse(stream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * xml解析器
     * @param inputStream 输入流
     * @return 解析结果
     * @throws Exception
     */
    private HashMap<String,String> parse(InputStream inputStream) throws Exception {
        List<HitIdNode> hitIdNodes = null;
        HitIdNode hitIdNode = null;

        XmlPullParser xmlPullParser = Xml.newPullParser(); //由android.util.Xml创建一个XmlPullParser实例
        xmlPullParser.setInput(inputStream, "UTF-8");               //设置输入流 并指明编码方式

        int eventType = xmlPullParser.getEventType();
        while (eventType != XmlPullParser.END_DOCUMENT) {
            switch (eventType) {
                case XmlPullParser.START_DOCUMENT:
                    hitIdNodes = new ArrayList<>();
                    break;
                case XmlPullParser.START_TAG:
                    if (xmlPullParser.getName().equals("HitIdNode")) {
                        hitIdNode = new HitIdNode();
                        hitIdNode.setDescribe(xmlPullParser.getAttributeValue("", "describe"));
                    } else if (xmlPullParser.getName().equals("name")) {
                        eventType = xmlPullParser.next();
                        hitIdNode.setName(xmlPullParser.getText());
                    } else if (xmlPullParser.getName().equals("hitid")) {
                        eventType = xmlPullParser.next();
                        hitIdNode.setHitid(xmlPullParser.getText());
                    }
                    break;
                case XmlPullParser.END_TAG:
                    if (xmlPullParser.getName().equals("HitIdNode")) {
                        hitIdNodes.add(hitIdNode);
                        hitIdNode = null;
                    }
                    break;
            }
            eventType = xmlPullParser.next();
        }
        HashMap<String,String> map = new HashMap<>();
        for (HitIdNode idNode : hitIdNodes) {
            map.put(idNode.getName(),idNode.getHitid());
        }
        return map;
    }

    /**
     * XML文件生成工具
     * @param hitIdNodes list列表
     * @return 生成的XML
     * @throws Exception
     */
    private String serialize(List<HitIdNode> hitIdNodes) throws Exception {

        XmlSerializer serializer = Xml.newSerializer(); //由android.util.Xml创建一个XmlSerializer实例
        StringWriter writer = new StringWriter();
        serializer.setOutput(writer);   //设置输出方向为writer
        serializer.startDocument("UTF-8", true);
        serializer.startTag("", "HitId");
        for (HitIdNode hitIdNode : hitIdNodes) {
            serializer.startTag("", "HitIdNode");
            serializer.attribute("", "describe", hitIdNode.getDescribe());

            serializer.startTag("", "name");
            serializer.text(hitIdNode.getName());
            serializer.endTag("", "name");

            serializer.startTag("", "hitid");
            serializer.text(hitIdNode.getHitid());
            serializer.endTag("", "hitid");

            serializer.endTag("", "HitIdNode");
        }
        serializer.endTag("", "HitId");
        serializer.endDocument();

        return writer.toString();
    }
}

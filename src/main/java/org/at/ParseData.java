package org.at;


import com.google.gson.*;
import org.apache.log4j.Logger;
import org.at.entity.ShipGraph;
import org.at.tool.MyLog;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author ArchmageTony
 * @version 1.0
 * @date 2020/8/3 13:35
 */
public class ParseData {
    private static final Logger logger = Logger.getLogger(ParseData.class);

    /**
     * 解析数据，将所有数据转换成角色立绘ShipGraph对象的集合
     * 可以解析数据格式：原数据api_start2（TXT||Json），start2.json，api_mst_shipgraph.json三种类型
     *
     * @param parseFile 需要解析的文件
     * @return 解析是否成功的消息与解析后的角色立绘（ShipGraph）对象的集合
     */
    public ArrayList<ShipGraph> parseAPI(File parseFile) {
        String sg;//使用gson转换时的字符串内容，应该为api_mst_shipgraph字段的全部数组内容
        StringBuilder content = new StringBuilder();
        try (
                FileInputStream fis = new FileInputStream(parseFile);
                InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
                BufferedReader bf = new BufferedReader(isr)
        ) {
            String str;
            while (null != (str = bf.readLine())) {
                str = str.trim();
                content.append(str);
            }
        } catch (IOException e) {
            MyLog.log("解析失败：IO异常", "ERROR");
            logger.error("解析失败：IO异常", e);
            return null;
        }
        String json = content.toString();
        json = json.replaceAll("svdata=", "");//替换掉svdata=字符
        JsonElement element;
        try {
            element = JsonParser.parseString(json);
        } catch (JsonSyntaxException e) {
            MyLog.log("解析失败：请检查文件内容是否完整，编码是否为UTF-8，是否包含有api_data字段或者api_mst_shipgraph字段或者为已解析过得api_mst_shipgraph.json文件。详细错误请检查logs文件夹下的error.log文件", "ERROR");
            logger.error("解析失败：文件不完整", e);
            return null;
        }
        if (element.isJsonObject()) {
            JsonObject object = element.getAsJsonObject();
            if (object.has("api_data")) {//判断是否有api_data字段，是否为完整原数据api_start2.json
                JsonObject apiData = object.getAsJsonObject("api_data");
                sg = apiData.getAsJsonArray("api_mst_shipgraph").toString();
            } else if (object.has("api_mst_shipgraph")) {//判断是否含有api_mst_shipgraph字段，是否为解析过的start2.json
                sg = object.getAsJsonArray("api_mst_shipgraph").toString();
            } else {
                MyLog.log("解析失败：请检查文件内容是否完整，编码是否为UTF-8，是否包含有api_data字段或者api_mst_shipgraph字段或者为已解析过得api_mst_shipgraph.json文件", "ERROR");
                return null;
            }
        } else if (element.isJsonArray()) {//判断是否为JsonArray，是否为解析过的api_mst_shipgraph.json
            sg = json;
        } else {
            MyLog.log("解析失败：请检查文件内容是否完整，该文件是否可被解析为json格式", "ERROR");
            return null;
        }
        return parseSG(sg);
    }

    /**
     * 将json内容转化成ShipGraph的集合
     *
     * @param sg api_mst_shipgraph字段的json内容，不包括api_mst_shipgraph自己的tag
     * @return 角色立绘ShipGraph对象的集合
     */
    private ArrayList<ShipGraph> parseSG(String sg) {
        ArrayList<ShipGraph> shipGraphArrayList;
        if (null == sg) {
            return null;
        }
        ShipGraph[] graphs = new Gson().fromJson(sg, ShipGraph[].class);
        shipGraphArrayList = new ArrayList<>(Arrays.asList(graphs));
        return shipGraphArrayList;
    }
}

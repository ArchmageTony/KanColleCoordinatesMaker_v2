package org.at;

import org.at.entity.Setting;
import org.at.entity.ShipGraph;
import org.at.tool.MyLog;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * @author ArchmageTony
 * @version 1.0
 * @date 2020/8/4 13:54
 */
public class FindOutData {

    /**
     * 查找与其相符的角色立绘坐标
     *
     * @param keywords   需要查找的角色立绘名称，可多个
     * @param shipGraphs 解析完成后的角色立绘数据对象集合
     * @param setting    设置参数，用来判断用户选择了哪些立绘坐标信息需要输出
     */
    public void find(String[] keywords, ArrayList<ShipGraph> shipGraphs, Setting setting) {
        int success = 0;
        int fail = 0;
        boolean findResult;
        for (String keyword : keywords) {
            findResult = false;
            if (keyword.equals("")) {
                continue;
            }
            for (ShipGraph shipGraph : shipGraphs) {
                if (shipGraph.getApi_filename().equals(keyword)) {
                    findResult = true;
                    if (out(shipGraph, setting).equals("成功")) {
                        success++;
                    } else {
                        fail++;
                    }
                }
            }
            if (!findResult) {
                fail++;
                MyLog.log("查找失败：立绘 " + keyword + " 未查找到，请检查拼写。", "ERROR");
            }
        }
        MyLog.log("生成坐标文件完成：请检查程序所在目录的 output 文件夹", "BOLD");
        MyLog.log("成功文件数：" + success + "    失败文件数：" + fail, "BOLD");
        //        MyLog.log("-------------------------------------------------------------------------------------------------------", "BOLD");
        MyLog.log("-".repeat(MyLog.logEdtTxt.getWidth() / 8), "BOLD");
    }

    /**
     * 将查找到的角色立绘数据信息输出到相应的文件当中
     *
     * @param shipGraph 查找到的角色立绘对象
     * @param setting   设置参数，用来判断用户选择了哪些立绘坐标信息需要输出
     * @return 输出信息
     */
    private String out(ShipGraph shipGraph, Setting setting) {
        FileOutputStream fos = null;
        OutputStreamWriter osw = null;
        File file;
        File filepath = new File(App.USER_DIR + File.separator + "output");
        StringBuilder content = new StringBuilder();
        boolean mkdir = filepath.mkdir();
        file = new File(App.USER_DIR + File.separator + "output" + File.separator + shipGraph.getApi_filename() + ".config.ini");
        if (file.exists()) {
            MyLog.log("输出失败：文件 " + shipGraph.getApi_filename() + " 已经存在，无法创建。", "ERROR");
            return "失败";
        }
        try {
            boolean newFile = file.createNewFile();
            fos = new FileOutputStream(file);
            osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
            //输出文件内容
            content.append("[graph]");
            if (setting.isApi_boko_n() && null != shipGraph.getApi_boko_n()) {
                content.append("\r\nboko_n_left=").append(shipGraph.getApi_boko_n().get(0));
                content.append("\r\nboko_n_top=").append(shipGraph.getApi_boko_n().get(1));
            } else {
                content.append("\r\nboko_n_left=");
                content.append("\r\nboko_n_top=");
            }
            if (setting.isApi_boko_d() && null != shipGraph.getApi_boko_d()) {
                content.append("\r\nboko_d_left=").append(shipGraph.getApi_boko_d().get(0));
                content.append("\r\nboko_d_top=").append(shipGraph.getApi_boko_d().get(1));
            } else {
                content.append("\r\nboko_d_left=");
                content.append("\r\nboko_d_top=");
            }
            if (setting.isApi_kaisyu_n() && null != shipGraph.getApi_kaisyu_n()) {
                content.append("\r\nkaisyu_n_left=").append(shipGraph.getApi_kaisyu_n().get(0));
                content.append("\r\nkaisyu_n_top=").append(shipGraph.getApi_kaisyu_n().get(1));
            } else {
                content.append("\r\nkaisyu_n_left=");
                content.append("\r\nkaisyu_n_top=");
            }
            if (setting.isApi_kaisyu_d() && null != shipGraph.getApi_kaisyu_d()) {
                content.append("\r\nkaisyu_d_left=").append(shipGraph.getApi_kaisyu_d().get(0));
                content.append("\r\nkaisyu_d_top=").append(shipGraph.getApi_kaisyu_d().get(1));
            } else {
                content.append("\r\nkaisyu_d_left=");
                content.append("\r\nkaisyu_d_top=");
            }
            if (setting.isApi_kaizo_n() && null != shipGraph.getApi_kaizo_n()) {
                content.append("\r\nkaizo_n_left=").append(shipGraph.getApi_kaizo_n().get(0));
                content.append("\r\nkaizo_n_top=").append(shipGraph.getApi_kaizo_n().get(1));
            } else {
                content.append("\r\nkaizo_n_left=");
                content.append("\r\nkaizo_n_top=");
            }
            if (setting.isApi_kaizo_d() && null != shipGraph.getApi_kaizo_d()) {
                content.append("\r\nkaizo_d_left=").append(shipGraph.getApi_kaizo_d().get(0));
                content.append("\r\nkaizo_d_top=").append(shipGraph.getApi_kaizo_d().get(1));
            } else {
                content.append("\r\nkaizo_d_left=");
                content.append("\r\nkaizo_d_top=");
            }
            if (setting.isApi_map_n() && null != shipGraph.getApi_map_n()) {
                content.append("\r\nmap_n_left=").append(shipGraph.getApi_map_n().get(0));
                content.append("\r\nmap_n_top=").append(shipGraph.getApi_map_n().get(1));
            } else {
                content.append("\r\nmap_n_left=");
                content.append("\r\nmap_n_top=");
            }
            if (setting.isApi_map_d() && null != shipGraph.getApi_map_d()) {
                content.append("\r\nmap_d_left=").append(shipGraph.getApi_map_d().get(0));
                content.append("\r\nmap_d_top=").append(shipGraph.getApi_map_d().get(1));
            } else {
                content.append("\r\nmap_d_left=");
                content.append("\r\nmap_d_top=");
            }
            if (setting.isApi_ensyuf_n() && null != shipGraph.getApi_ensyuf_n()) {
                content.append("\r\nensyuf_n_left=").append(shipGraph.getApi_ensyuf_n().get(0));
                content.append("\r\nensyuf_n_top=").append(shipGraph.getApi_ensyuf_n().get(1));
            } else {
                content.append("\r\nensyuf_n_left=");
                content.append("\r\nensyuf_n_top=");
            }
            if (setting.isApi_ensyuf_d() && null != shipGraph.getApi_ensyuf_d()) {
                content.append("\r\nensyuf_d_left=").append(shipGraph.getApi_ensyuf_d().get(0));
                content.append("\r\nensyuf_d_top=").append(shipGraph.getApi_ensyuf_d().get(1));
            } else {
                content.append("\r\nensyuf_d_left=");
                content.append("\r\nensyuf_d_top=");
            }
            if (setting.isApi_ensyue_n() && null != shipGraph.getApi_ensyue_n()) {
                content.append("\r\nensyue_n_left=").append(shipGraph.getApi_ensyue_n().get(0));
                content.append("\r\nensyue_n_top=").append(shipGraph.getApi_ensyue_n().get(1));
            } else {
                content.append("\r\nensyue_n_left=");
                content.append("\r\nensyue_n_top=");
            }
            if (setting.isApi_battle_n() && null != shipGraph.getApi_battle_n()) {
                content.append("\r\nbattle_n_left=").append(shipGraph.getApi_battle_n().get(0));
                content.append("\r\nbattle_n_top=").append(shipGraph.getApi_battle_n().get(1));
            } else {
                content.append("\r\nbattle_n_left=");
                content.append("\r\nbattle_n_top=");
            }
            if (setting.isApi_battle_d() && null != shipGraph.getApi_battle_d()) {
                content.append("\r\nbattle_d_left=").append(shipGraph.getApi_battle_d().get(0));
                content.append("\r\nbattle_d_top=").append(shipGraph.getApi_battle_d().get(1));
            } else {
                content.append("\r\nbattle_d_left=");
                content.append("\r\nbattle_d_top=");
            }
            if (setting.isApi_weda() && null != shipGraph.getApi_weda()) {
                content.append("\r\nweda_left=").append(shipGraph.getApi_weda().get(0));
                content.append("\r\nweda_top=").append(shipGraph.getApi_weda().get(1));
            } else {
                content.append("\r\nweda_left=");
                content.append("\r\nweda_top=");
            }
            if (setting.isApi_wedb() && null != shipGraph.getApi_wedb()) {
                content.append("\r\nwedb_left=").append(shipGraph.getApi_wedb().get(0));
                content.append("\r\nwedb_top=").append(shipGraph.getApi_wedb().get(1));
            } else {
                content.append("\r\nwedb_left=");
                content.append("\r\nwedb_top=");
            }
            if (setting.isApi_pa() && null != shipGraph.getApi_pa()) {
                content.append("\r\npa_left=").append(shipGraph.getApi_pa().get(0));
                content.append("\r\npa_top=").append(shipGraph.getApi_pa().get(1));
            } else {
                content.append("\r\npa_left=");
                content.append("\r\npa_top=");
            }
            if (setting.isApi_pab() && null != shipGraph.getApi_pab()) {
                content.append("\r\npab_left=").append(shipGraph.getApi_pab().get(0));
                content.append("\r\npab_top=").append(shipGraph.getApi_pab().get(1));
            } else {
                content.append("\r\npab_left=");
                content.append("\r\npab_top=");
            }
            osw.write(content.toString());
            osw.flush();
        } catch (IOException e) {
            e.printStackTrace();
            MyLog.log("输出失败：输出文件失败，IO异常", "ERROR");
            return "失败";
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
                if (osw != null) {
                    osw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
//        MyLog.log("输出成功：文件 " + shipGraph.getApi_filename() + " 已成功输出");
        return "成功";
    }
}

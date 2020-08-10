package org.at;

import org.at.entity.Setting;
import org.at.entity.ShipGraph;
import org.at.tool.MyLog;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Enumeration;


/**
 * @author ArchmageTony
 * @version 1.0
 * @date 2020/8/5 9:41
 */
public class AppInit {

    private final JFrame jf = new JFrame("KanColle Coordinates Maker v2 by ArchmageTony");
    private final Setting setting = new Setting("", true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true);
    private JTextField fileEdtTxt, keywordEdtTxt;

    /**
     * 修改默认字体为黑体
     */
    private static void initGlobalFont() {
        FontUIResource fontUIResource = new FontUIResource(new Font("SimHei", Font.PLAIN, 12));
        for (Enumeration<?> keys = UIManager.getDefaults().keys(); keys.hasMoreElements(); ) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof FontUIResource) {
                UIManager.put(key, fontUIResource);
            }
        }
    }

    public void init() {
        initGlobalFont();//修改默认字体为雅黑
        getSetting();//获取设置参数
        initPanel();//初始化窗口
    }

    /**
     * 主体窗口
     */
    private void initPanel() {
        JPanel fileP = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel fileBtnP = new JPanel();
        JPanel keywordP = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel keywordBtnP = new JPanel();
        JScrollPane logP;
        fileEdtTxt = new JTextField(95);
        keywordEdtTxt = new JTextField(55);
        JButton fileBtn = new JButton("选择API文件");
        JButton settingBtn = new JButton("输出文件设置");
        JButton keywordBtn = new JButton("生成坐标文件");
        fileEdtTxt.setText(setting.getApiPath());
        MyLog.logEdtTxt.setEditable(false);
        MyLog.logEdtTxt.setPreferredSize(new Dimension(30, 300));
        fileBtn.addActionListener(e -> openFile());
        settingBtn.addActionListener(e -> outSettingPanel());
        keywordBtn.addActionListener(e -> find(keywordEdtTxt.getText(), fileEdtTxt.getText()));
        fileP.add(new JLabel("API文件地址："));
        fileP.add(fileEdtTxt);
        fileBtnP.add(fileBtn);
        keywordP.add(new JLabel("输入立绘名称，多个以英文逗号隔开："));
        keywordP.add(keywordEdtTxt);
        keywordP.add(settingBtn);
        keywordBtnP.add(keywordBtn);
        logP = new JScrollPane(MyLog.logEdtTxt);
        GridBagLayout gb = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        jf.setLayout(gb);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.gridwidth = 2;
        gb.setConstraints(fileP, gbc);
        jf.add(fileP);
        gbc.gridwidth = 1;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gb.setConstraints(fileBtnP, gbc);
        jf.add(fileBtnP);
        gbc.gridwidth = 2;
        gb.setConstraints(keywordP, gbc);
        jf.add(keywordP);
        gbc.gridwidth = 1;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gb.setConstraints(keywordBtnP, gbc);
        jf.add(keywordBtnP);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridwidth = 3;
        gb.setConstraints(logP, gbc);
        jf.add(logP);
        jf.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                setting.setApiPath(fileEdtTxt.getText());
                setSetting();
                System.exit(0);
            }
        });
        jf.setResizable(false);
        jf.pack();
        jf.setVisible(true);
    }

    /**
     * 输出文件设置窗口
     */
    private void outSettingPanel() {
        final JFrame settingJF = new JFrame("输出文件设置");
        settingJF.setLayout(new GridLayout(0, 2, 4, 4));
        getSetting();//重新获取配置信息
        JCheckBox[] checkBoxes = new JCheckBox[18];
        JButton nBtn = new JButton("仅普通");
        JButton dBtn = new JButton("仅中破");
        JButton allBtn = new JButton("全选");
        JButton saveBtn = new JButton("保存");
        checkBoxes[0] = new JCheckBox("母港 （boko_n）", setting.isApi_boko_n());
        checkBoxes[1] = new JCheckBox("母港 （boko_d）", setting.isApi_boko_d());
        checkBoxes[2] = new JCheckBox("近代化改修 （kaisyu_n）", setting.isApi_kaisyu_n());
        checkBoxes[3] = new JCheckBox("近代化改修 （kaisyu_d）", setting.isApi_kaisyu_d());
        checkBoxes[4] = new JCheckBox("改造中 （kaizo_n）", setting.isApi_kaizo_n());
        checkBoxes[5] = new JCheckBox("改造中 （kaizo_d）", setting.isApi_kaizo_d());
        checkBoxes[6] = new JCheckBox("出击中地图 （map_n）", setting.isApi_map_n());
        checkBoxes[7] = new JCheckBox("出击中地图 （map_d）", setting.isApi_map_d());
        checkBoxes[8] = new JCheckBox("演习我方 （ensyuf_n）", setting.isApi_ensyuf_n());
        checkBoxes[9] = new JCheckBox("演习我方 （ensyuf_d）", setting.isApi_ensyuf_d());
        checkBoxes[10] = new JCheckBox("演习敌方 （ensyue_n）", setting.isApi_ensyue_n());
        checkBoxes[11] = new JCheckBox("演习敌方 （无效）", false);
        checkBoxes[11].setEnabled(false);
        checkBoxes[12] = new JCheckBox("战斗中 （battle_n）", setting.isApi_battle_n());
        checkBoxes[13] = new JCheckBox("战斗中 （battle_d）", setting.isApi_battle_d());
        checkBoxes[14] = new JCheckBox("结婚戒指 位置a （weda）", setting.isApi_weda());
        checkBoxes[15] = new JCheckBox("结婚戒指 位置b （wedb）", setting.isApi_wedb());
        checkBoxes[16] = new JCheckBox("昼战特殊攻击 （pa）", setting.isApi_pa());
        checkBoxes[17] = new JCheckBox("夜战特殊攻击 （pab）", setting.isApi_pab());
        nBtn.addActionListener(e -> {
            for (int i = 0; i < 14; i++) {
                checkBoxes[i].setSelected(0 == i % 2);
            }
        });
        dBtn.addActionListener(e -> {
            for (int i = 0; i < 14; i++) {
                checkBoxes[i].setSelected(!(0 == i % 2));
            }
            checkBoxes[11].setSelected(false);
        });
        allBtn.addActionListener(e -> {
            for (JCheckBox checkBox :
                    checkBoxes) {
                checkBox.setSelected(true);
            }
            checkBoxes[11].setSelected(false);
        });
        saveBtn.addActionListener(e -> {
            setting.setApiPath(fileEdtTxt.getText());
            setting.setApi_boko_n(checkBoxes[0].isSelected());
            setting.setApi_boko_d(checkBoxes[1].isSelected());
            setting.setApi_kaisyu_n(checkBoxes[2].isSelected());
            setting.setApi_kaisyu_d(checkBoxes[3].isSelected());
            setting.setApi_kaizo_n(checkBoxes[4].isSelected());
            setting.setApi_kaizo_d(checkBoxes[5].isSelected());
            setting.setApi_map_n(checkBoxes[6].isSelected());
            setting.setApi_map_d(checkBoxes[7].isSelected());
            setting.setApi_ensyuf_n(checkBoxes[8].isSelected());
            setting.setApi_ensyuf_d(checkBoxes[9].isSelected());
            setting.setApi_ensyue_n(checkBoxes[10].isSelected());
            setting.setApi_battle_n(checkBoxes[12].isSelected());
            setting.setApi_battle_d(checkBoxes[13].isSelected());
            setting.setApi_weda(checkBoxes[14].isSelected());
            setting.setApi_wedb(checkBoxes[15].isSelected());
            setting.setApi_pa(checkBoxes[16].isSelected());
            setting.setApi_pab(checkBoxes[17].isSelected());
            setSetting();
            settingJF.dispose();
        });
        settingJF.add(new JLabel("通常立绘"));
        settingJF.add(new JLabel("中破立绘"));
        for (int i = 0; i < 14; i++) {
            settingJF.add(checkBoxes[i]);
        }
        settingJF.add(new JLabel());
        settingJF.add(new JLabel());
        for (int i = 14; i < 18; i++) {
            settingJF.add(checkBoxes[i]);
        }
        settingJF.add(new JLabel());
        settingJF.add(new JLabel());
        settingJF.add(nBtn);
        settingJF.add(dBtn);
        settingJF.add(allBtn);
        settingJF.add(saveBtn);
        settingJF.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                settingJF.dispose();
            }
        });
        settingJF.setResizable(false);
        settingJF.pack();
        settingJF.setLocationRelativeTo(jf);
        settingJF.setVisible(true);
    }

    /**
     * 获取配置文件中的设置参数
     */
    private void getSetting() {
        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        File settingFile = new File(App.KCCM_SETTING);
        String line;
        String[] content;
        if (!settingFile.exists()) {
            setSetting();
            return;
        }
        try {
            fis = new FileInputStream(settingFile);
            isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
            br = new BufferedReader(isr);
            while (null != (line = br.readLine())) {
                content = line.split("=");
                switch (content[0]) {
                    case "APIFilePath":
                        if (1 != content.length) {
                            setting.setApiPath(content[1]);
                        } else {
                            setting.setApiPath("");
                        }
                        break;
                    case "api_boko_n":
                        setting.setApi_boko_n(Boolean.parseBoolean(content[1]));
                        break;
                    case "api_boko_d":
                        setting.setApi_boko_d(Boolean.parseBoolean(content[1]));
                        break;
                    case "api_kaisyu_n":
                        setting.setApi_kaisyu_n(Boolean.parseBoolean(content[1]));
                        break;
                    case "api_kaisyu_d":
                        setting.setApi_kaisyu_d(Boolean.parseBoolean(content[1]));
                        break;
                    case "api_kaizo_n":
                        setting.setApi_kaizo_n(Boolean.parseBoolean(content[1]));
                        break;
                    case "api_kaizo_d":
                        setting.setApi_kaizo_d(Boolean.parseBoolean(content[1]));
                        break;
                    case "api_map_n":
                        setting.setApi_map_n(Boolean.parseBoolean(content[1]));
                        break;
                    case "api_map_d":
                        setting.setApi_map_d(Boolean.parseBoolean(content[1]));
                        break;
                    case "api_ensyuf_n":
                        setting.setApi_ensyuf_n(Boolean.parseBoolean(content[1]));
                        break;
                    case "api_ensyuf_d":
                        setting.setApi_ensyuf_d(Boolean.parseBoolean(content[1]));
                        break;
                    case "api_ensyue_n":
                        setting.setApi_ensyue_n(Boolean.parseBoolean(content[1]));
                        break;
                    case "api_battle_n":
                        setting.setApi_battle_n(Boolean.parseBoolean(content[1]));
                        break;
                    case "api_battle_d":
                        setting.setApi_battle_d(Boolean.parseBoolean(content[1]));
                        break;
                    case "api_weda":
                        setting.setApi_weda(Boolean.parseBoolean(content[1]));
                        break;
                    case "api_wedb":
                        setting.setApi_wedb(Boolean.parseBoolean(content[1]));
                        break;
                    case "api_pa":
                        setting.setApi_pa(Boolean.parseBoolean(content[1]));
                        break;
                    case "api_pab":
                        setting.setApi_pab(Boolean.parseBoolean(content[1]));
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
                if (isr != null) {
                    isr.close();
                }
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 将设置参数写入配置文件
     */
    private void setSetting() {
        FileOutputStream fos = null;
        OutputStreamWriter osw = null;
        StringBuilder content = new StringBuilder();
        File settingFile = new File(App.KCCM_SETTING);
        if (!settingFile.exists()) {
            try {
                boolean settingFileNewFile = settingFile.createNewFile();
            } catch (IOException e) {
                MyLog.log("创建设置文件失败！", "ERROR", jf);
                e.printStackTrace();
                return;
            }
        }
        try {
            fos = new FileOutputStream(settingFile);
            osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
            content.append("APIFilePath=").append(setting.getApiPath());
            content.append("\r\napi_boko_n=").append(setting.isApi_boko_n());
            content.append("\r\napi_boko_d=").append(setting.isApi_boko_d());
            content.append("\r\napi_kaisyu_n=").append(setting.isApi_kaisyu_n());
            content.append("\r\napi_kaisyu_d=").append(setting.isApi_kaisyu_d());
            content.append("\r\napi_kaizo_n=").append(setting.isApi_kaizo_n());
            content.append("\r\napi_kaizo_d=").append(setting.isApi_kaizo_d());
            content.append("\r\napi_map_n=").append(setting.isApi_map_n());
            content.append("\r\napi_map_d=").append(setting.isApi_map_d());
            content.append("\r\napi_ensyuf_n=").append(setting.isApi_ensyuf_n());
            content.append("\r\napi_ensyuf_d=").append(setting.isApi_ensyuf_d());
            content.append("\r\napi_ensyue_n=").append(setting.isApi_ensyue_n());
            content.append("\r\napi_battle_n=").append(setting.isApi_battle_n());
            content.append("\r\napi_battle_d=").append(setting.isApi_battle_d());
            content.append("\r\napi_weda=").append(setting.isApi_weda());
            content.append("\r\napi_wedb=").append(setting.isApi_wedb());
            content.append("\r\napi_pa=").append(setting.isApi_pa());
            content.append("\r\napi_pab=").append(setting.isApi_pab());
            osw.write(content.toString());
            osw.flush();

        } catch (IOException e) {
            e.printStackTrace();
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
    }

    /**
     * 选择API文件窗口
     */
    private void openFile() {
        JFileChooser chooser;
        if (fileEdtTxt.getText().equals("")) {
            chooser = new JFileChooser(App.USER_DIR);
        } else {
            File apiDir = new File(new File(fileEdtTxt.getText()).getParent());
            if (!apiDir.exists()) {
                chooser = new JFileChooser(App.USER_DIR);
            } else {
                chooser = new JFileChooser(apiDir);
            }
        }

        FileFilter fileFilter = new FileFilter() {
            @Override
            public boolean accept(File f) {
                if (f.isDirectory()) {
                    return true;
                }
                String fName = f.getName().toLowerCase();
                return fName.endsWith(".json") || fName.endsWith(".txt");
            }

            @Override
            public String getDescription() {
                return "api_start2（*.json,*.txt)";
            }
        };
        chooser.addChoosableFileFilter(fileFilter);
        chooser.setAcceptAllFileFilterUsed(false);
        if (chooser.showOpenDialog(jf) == JFileChooser.APPROVE_OPTION) {
            fileEdtTxt.setText(chooser.getSelectedFile().getPath());
        }

    }

    /**
     * 查找立绘信息
     *
     * @param keyword     立绘名称，可同时查找多个，用英文逗号隔开
     * @param apiFilePath API文件地址
     */
    private void find(String keyword, String apiFilePath) {
        //去除多余空格
        keyword = keyword.trim();
        keyword = keyword.replaceAll("[\\s]{2,}", "");
        if (keyword.equals("")) {
            return;
        }
        File apiFile = new File(apiFilePath);
        ArrayList<ShipGraph> shipGraphs;
        ParseData parseData = new ParseData();
        if (apiFile.exists()) {
            shipGraphs = parseData.parseAPI(apiFile);
        } else {
            MyLog.log("初始失败：未找到可解析的文件，请检查选择的api文件是否存在", "ERROR", jf);
            return;
        }
        if (null == shipGraphs) {
            return;
        }
        FindOutData fod = new FindOutData();
        fod.find(keyword.split(","), shipGraphs, setting);
    }
}

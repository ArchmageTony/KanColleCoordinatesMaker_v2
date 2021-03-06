package org.at;

import org.apache.log4j.Logger;
import org.at.entity.Setting;
import org.at.entity.ShipGraph;
import org.at.tool.MyLog;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Properties;


/**
 * @author ArchmageTony
 * @version 1.0
 * @date 2020/8/5 9:41
 */
public class AppInit {
    private static final Logger logger = Logger.getLogger(AppInit.class);
    private final JFrame jf = new JFrame("KanColle Coordinates Maker v2 by ArchmageTony        v" + getAppVersion());
    private final Setting setting = new Setting("", "FileNameF", true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true);
    private JTextField fileEdtTxt, keywordEdtTxt;
    private JRadioButton findTypeRdoBtn1, findTypeRdoBtn2, findTypeRdoBtn3;

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
        JPanel findTypeP = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JScrollPane logP;
        findTypeRdoBtn1 = new JRadioButton("以'FileName'模糊查找", setting.getFindType().equals("FileNameF"));
        findTypeRdoBtn2 = new JRadioButton("以'FileName'精确查找", setting.getFindType().equals("FileNameE"));
        findTypeRdoBtn3 = new JRadioButton("以'ID'查找", setting.getFindType().equals("ID"));
        ButtonGroup findTypeBG = new ButtonGroup();
        fileEdtTxt = new JTextField(100);
        keywordEdtTxt = new JTextField(65);
        JButton fileBtn = new JButton("选择API文件");
        JButton settingBtn = new JButton("输出文件设置");
        JButton keywordBtn = new JButton("生成坐标文件");
        JLabel findTypeTv = new JLabel("<HTML><font color='blue'><U>(?)</U></font></HTML>");
        ToolTipManager.sharedInstance().setDismissDelay(60000);//设置浮动提示信息的持续时间为1分钟
        fileEdtTxt.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (!fileEdtTxt.getText().trim().equals("")) {
                    setting.setApiPath(fileEdtTxt.getText());
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (!fileEdtTxt.getText().trim().equals("")) {
                    setting.setApiPath(fileEdtTxt.getText());
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                if (!fileEdtTxt.getText().trim().equals("")) {
                    setting.setApiPath(fileEdtTxt.getText());
                }
            }
        });
        findTypeTv.setToolTipText("点击问号查看详细解释");
        findTypeTv.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MyLog.log("以'FileName'模糊查找：仅仅匹配输入文件名的前10位，可以解决因为期间限定立绘将12位文件名的最后2位修改而查找不到的问题。" +
                        "但输出出来的文件名还是以api_start2中的为准，不以输入的内容为准。在这种查找方式下你可以只输入前10位文件名即可。\n" +
                        "以'FileName'精确查找：完全匹配12位文件名，若期间限定立绘文件名有所修改会提示查找失败。\n" +
                        "以'ID'查找：可以解决期间限定立绘文件名不同的问题，输出的文件名以api_start2中的为准。\n\n" +
                        "FileName：由英文字母组成的12位的字符串，你可以从acgpower的舰娘一览中的字符ID获取，或者从岛风go的战舰表中的语音路径获取。举例：schftfqkstha是朝潮改二丁万圣节期间的FileName。\n" +
                        "ID：你可以从acgpower的舰娘一览中的ID获取，或者从岛风go的战舰表中的战舰ID获取。举例：468是朝潮改二丁的立绘ID。\n\n" +
                        "角色的不同形态（未改，改，改二，等等）对应的FileName与ID均不相同，请注意区分。\n\n" +
                        "期间限定立绘的FileName的最后2位可能会与普通立绘不同。举例：grmdtyheocuc是朝潮改二丁的普通立绘FileName，grmdtyheocha是朝潮改二丁万圣节期间的立绘FileName。"
                );
                MyLog.log("", "SPLIT");
            }
        });
        findTypeRdoBtn1.setToolTipText("仅仅匹配输入文件名的前10位查找");
        findTypeRdoBtn1.addItemListener(e -> {
            if (findTypeRdoBtn1.isSelected()) {
                setting.setFindType("FileNameF");
            }
        });
        findTypeRdoBtn2.setToolTipText("精确匹配输入文件名查找");
        findTypeRdoBtn2.addItemListener(e -> {
            if (findTypeRdoBtn2.isSelected()) {
                setting.setFindType("FileNameE");
            }
        });
        findTypeRdoBtn3.setToolTipText("匹配ID查找");
        findTypeRdoBtn3.addItemListener(e -> {
            if (findTypeRdoBtn3.isSelected()) {
                setting.setFindType("ID");
            }
        });
        findTypeBG.add(findTypeRdoBtn1);
        findTypeBG.add(findTypeRdoBtn2);
        findTypeBG.add(findTypeRdoBtn3);
        fileEdtTxt.setText(setting.getApiPath());
        MyLog.logEdtTxt.setEditable(false);
        fileBtn.addActionListener(e -> openFile());
        settingBtn.addActionListener(e -> outSettingPanel());
        keywordBtn.addActionListener(e -> find(keywordEdtTxt.getText(), fileEdtTxt.getText()));
        fileP.add(new JLabel("API文件地址："));
        fileP.add(fileEdtTxt);
        fileBtnP.add(fileBtn);
        keywordP.add(new JLabel("请输入内容，多个英文逗号隔开："));
        keywordP.add(keywordEdtTxt);
        keywordP.add(settingBtn);
        keywordBtnP.add(keywordBtn);
        findTypeP.add(new JLabel("查找方式"));
        findTypeP.add(findTypeTv);
        findTypeP.add(new JLabel("："));
        findTypeP.add(findTypeRdoBtn1);
        findTypeP.add(findTypeRdoBtn2);
        findTypeP.add(findTypeRdoBtn3);
        logP = new JScrollPane(MyLog.logEdtTxt);
        logP.setPreferredSize(new Dimension(30, 300));
        GridBagLayout gb = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        jf.setLayout(gb);
        gbc.fill = GridBagConstraints.BOTH;
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
        gb.setConstraints(findTypeP, gbc);
        jf.add(findTypeP);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridwidth = 3;
        gb.setConstraints(logP, gbc);
        jf.add(logP);
        Image jfIcon = Toolkit.getDefaultToolkit().createImage(App.class.getClassLoader().getResource("icon.png"));
        jf.setIconImage(jfIcon);
        jf.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
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
        JDialog settingJD = new JDialog(jf, "输出文件设置", true);
        settingJD.setLayout(new GridLayout(0, 2, 4, 4));
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
            for (JCheckBox checkBox : checkBoxes) {
                checkBox.setSelected(true);
            }
            checkBoxes[11].setSelected(false);
        });
        saveBtn.addActionListener(e -> {
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
            settingJD.dispose();
        });
        settingJD.add(new JLabel("通常立绘"));
        settingJD.add(new JLabel("中破立绘"));
        for (int i = 0; i < 14; i++) {
            settingJD.add(checkBoxes[i]);
        }
        settingJD.add(new JLabel());
        settingJD.add(new JLabel());
        for (int i = 14; i < 18; i++) {
            settingJD.add(checkBoxes[i]);
        }
        settingJD.add(new JLabel());
        settingJD.add(new JLabel());
        settingJD.add(nBtn);
        settingJD.add(dBtn);
        settingJD.add(allBtn);
        settingJD.add(saveBtn);
        settingJD.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        settingJD.setResizable(false);
        settingJD.pack();
        settingJD.setLocationRelativeTo(jf);
        settingJD.setVisible(true);
    }

    /**
     * 获取配置文件中的设置参数
     */
    private void getSetting() {
        File settingFile = new File(App.KCCM_SETTING);
        String line;
        String[] content;
        if (!settingFile.exists()) {
            setSetting();
            return;
        }
        try (
                FileInputStream fis = new FileInputStream(settingFile);
                InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
                BufferedReader br = new BufferedReader(isr)
        ) {
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
                    case "findType":
                        setting.setFindType(content[1]);
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
        } catch (Exception e) {
            MyLog.log("初始失败：设置文件IO异常", "ERROR");
            logger.error("初始失败：设置文件IO异常", e);
        }
    }

    /**
     * 将设置参数写入配置文件
     */
    private void setSetting() {
        StringBuilder content = new StringBuilder();
        File settingFile = new File(App.KCCM_SETTING);
        if (!settingFile.exists()) {
            try {
                boolean settingFileNewFile = settingFile.createNewFile();
            } catch (IOException e) {
                MyLog.log("创建设置文件失败！", "ERROR", jf);
                logger.error("创建设置文件失败！", e);
                return;
            }
        }
        try (
                FileOutputStream fos = new FileOutputStream(settingFile);
                OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8)
        ) {
            content.append("APIFilePath=").append(setting.getApiPath());
            content.append("\r\nfindType=").append(setting.getFindType());
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
            MyLog.log("保存设置失败！", "ERROR", jf);
            logger.error("保存设置失败！", e);
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

    /**
     * 获得pom.xml当中程序的版本号
     *
     * @return 程序的版本号
     */
    public String getAppVersion() {
        String appVersion = null;
        Properties properties = new Properties();
        try {
            properties.load(App.class.getClassLoader().getResourceAsStream("app.properties"));
            if (!properties.isEmpty()) {
                appVersion = properties.getProperty("app.version");
            }
        } catch (IOException e) {
            MyLog.log("版本号获取失败！", "ERROR");
            logger.error("版本号获取失败！", e);
        }
        return appVersion;
    }
}


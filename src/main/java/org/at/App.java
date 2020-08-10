package org.at;

import java.io.File;

/**
 * @author ArchmageTony
 * @version 1.0
 * @date 2020/8/3 13:35
 */
public class App {
    public static final String USER_DIR = System.getProperty("user.dir");//获取用户程序所在地址
    public static final String KCCM_SETTING = USER_DIR + File.separator + "KCCM Setting.ini";//设置配置文件所在地址与名称

    public static void main(String[] args) {
        new AppInit().init();
    }
}
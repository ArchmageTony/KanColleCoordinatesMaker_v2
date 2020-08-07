package org.at;

import java.io.File;

/**
 * @author ArchmageTony
 * @version 1.0
 * @date 2020/8/3 13:35
 */
public class App {
    public static final String USER_DIR = System.getProperty("user.dir");
    public static final String KCCM_SETTING = USER_DIR + File.separator + "KCCM Setting.ini";

    public static void main(String[] args) {
        new AppInit().init();
    }
}
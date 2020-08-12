package org.at.entity;

/**
 * @author ArchmageTony
 * @version 1.0
 * @date 2020/8/6 10:25
 */

public class Setting {
    private String apiPath;//api文件地址
    //查找立绘方式 FileNameF：FileNameFuzzy以filename模糊查找   FileNameE：FileNameExact以filename精确查找    ID：以ID查找
    private String findType;
    private boolean api_boko_n;
    private boolean api_boko_d;
    private boolean api_kaisyu_n;
    private boolean api_kaisyu_d;
    private boolean api_kaizo_n;
    private boolean api_kaizo_d;
    private boolean api_map_n;
    private boolean api_map_d;
    private boolean api_ensyuf_n;
    private boolean api_ensyuf_d;
    private boolean api_ensyue_n;
    private boolean api_battle_n;
    private boolean api_battle_d;
    private boolean api_weda;
    private boolean api_wedb;
    private boolean api_pa;
    private boolean api_pab;

    public Setting(String apiPath, String findType, boolean api_boko_n, boolean api_boko_d, boolean api_kaisyu_n, boolean api_kaisyu_d, boolean api_kaizo_n, boolean api_kaizo_d, boolean api_map_n, boolean api_map_d, boolean api_ensyuf_n, boolean api_ensyuf_d, boolean api_ensyue_n, boolean api_battle_n, boolean api_battle_d, boolean api_weda, boolean api_wedb, boolean api_pa, boolean api_pab) {
        this.apiPath = apiPath;
        this.findType = findType;
        this.api_boko_n = api_boko_n;
        this.api_boko_d = api_boko_d;
        this.api_kaisyu_n = api_kaisyu_n;
        this.api_kaisyu_d = api_kaisyu_d;
        this.api_kaizo_n = api_kaizo_n;
        this.api_kaizo_d = api_kaizo_d;
        this.api_map_n = api_map_n;
        this.api_map_d = api_map_d;
        this.api_ensyuf_n = api_ensyuf_n;
        this.api_ensyuf_d = api_ensyuf_d;
        this.api_ensyue_n = api_ensyue_n;
        this.api_battle_n = api_battle_n;
        this.api_battle_d = api_battle_d;
        this.api_weda = api_weda;
        this.api_wedb = api_wedb;
        this.api_pa = api_pa;
        this.api_pab = api_pab;
    }

    @Override
    public String toString() {
        return "Setting{" +
                "apiPath='" + apiPath + '\'' +
                ", findType='" + findType + '\'' +
                ", api_boko_n=" + api_boko_n +
                ", api_boko_d=" + api_boko_d +
                ", api_kaisyu_n=" + api_kaisyu_n +
                ", api_kaisyu_d=" + api_kaisyu_d +
                ", api_kaizo_n=" + api_kaizo_n +
                ", api_kaizo_d=" + api_kaizo_d +
                ", api_map_n=" + api_map_n +
                ", api_map_d=" + api_map_d +
                ", api_ensyuf_n=" + api_ensyuf_n +
                ", api_ensyuf_d=" + api_ensyuf_d +
                ", api_ensyue_n=" + api_ensyue_n +
                ", api_battle_n=" + api_battle_n +
                ", api_battle_d=" + api_battle_d +
                ", api_weda=" + api_weda +
                ", api_wedb=" + api_wedb +
                ", api_pa=" + api_pa +
                ", api_pab=" + api_pab +
                '}';
    }

    public String getFindType() {
        return findType;
    }

    public void setFindType(String findType) {
        this.findType = findType;
    }

    public boolean isApi_pa() {
        return api_pa;
    }

    public void setApi_pa(boolean api_pa) {
        this.api_pa = api_pa;
    }

    public boolean isApi_pab() {
        return api_pab;
    }

    public void setApi_pab(boolean api_pab) {
        this.api_pab = api_pab;
    }

    public String getApiPath() {
        return apiPath;
    }

    public void setApiPath(String apiPath) {
        this.apiPath = apiPath;
    }

    public boolean isApi_boko_n() {
        return api_boko_n;
    }

    public void setApi_boko_n(boolean api_boko_n) {
        this.api_boko_n = api_boko_n;
    }

    public boolean isApi_boko_d() {
        return api_boko_d;
    }

    public void setApi_boko_d(boolean api_boko_d) {
        this.api_boko_d = api_boko_d;
    }

    public boolean isApi_kaisyu_n() {
        return api_kaisyu_n;
    }

    public void setApi_kaisyu_n(boolean api_kaisyu_n) {
        this.api_kaisyu_n = api_kaisyu_n;
    }

    public boolean isApi_kaisyu_d() {
        return api_kaisyu_d;
    }

    public void setApi_kaisyu_d(boolean api_kaisyu_d) {
        this.api_kaisyu_d = api_kaisyu_d;
    }

    public boolean isApi_kaizo_n() {
        return api_kaizo_n;
    }

    public void setApi_kaizo_n(boolean api_kaizo_n) {
        this.api_kaizo_n = api_kaizo_n;
    }

    public boolean isApi_kaizo_d() {
        return api_kaizo_d;
    }

    public void setApi_kaizo_d(boolean api_kaizo_d) {
        this.api_kaizo_d = api_kaizo_d;
    }

    public boolean isApi_map_n() {
        return api_map_n;
    }

    public void setApi_map_n(boolean api_map_n) {
        this.api_map_n = api_map_n;
    }

    public boolean isApi_map_d() {
        return api_map_d;
    }

    public void setApi_map_d(boolean api_map_d) {
        this.api_map_d = api_map_d;
    }

    public boolean isApi_ensyuf_n() {
        return api_ensyuf_n;
    }

    public void setApi_ensyuf_n(boolean api_ensyuf_n) {
        this.api_ensyuf_n = api_ensyuf_n;
    }

    public boolean isApi_ensyuf_d() {
        return api_ensyuf_d;
    }

    public void setApi_ensyuf_d(boolean api_ensyuf_d) {
        this.api_ensyuf_d = api_ensyuf_d;
    }

    public boolean isApi_ensyue_n() {
        return api_ensyue_n;
    }

    public void setApi_ensyue_n(boolean api_ensyue_n) {
        this.api_ensyue_n = api_ensyue_n;
    }

    public boolean isApi_battle_n() {
        return api_battle_n;
    }

    public void setApi_battle_n(boolean api_battle_n) {
        this.api_battle_n = api_battle_n;
    }

    public boolean isApi_battle_d() {
        return api_battle_d;
    }

    public void setApi_battle_d(boolean api_battle_d) {
        this.api_battle_d = api_battle_d;
    }

    public boolean isApi_weda() {
        return api_weda;
    }

    public void setApi_weda(boolean api_weda) {
        this.api_weda = api_weda;
    }

    public boolean isApi_wedb() {
        return api_wedb;
    }

    public void setApi_wedb(boolean api_wedb) {
        this.api_wedb = api_wedb;
    }

}

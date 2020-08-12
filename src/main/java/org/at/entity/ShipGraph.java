package org.at.entity;

import java.util.List;

/**
 * @author ArchmageTony
 * @version 1.0
 * @date 2020/8/3 16:53
 */
public class ShipGraph {
    private int api_id;
    private String api_filename;
    private List<Integer> api_boko_n;
    private List<Integer> api_boko_d;
    private List<Integer> api_kaisyu_n;
    private List<Integer> api_kaisyu_d;
    private List<Integer> api_kaizo_n;
    private List<Integer> api_kaizo_d;
    private List<Integer> api_map_n;
    private List<Integer> api_map_d;
    private List<Integer> api_ensyuf_n;
    private List<Integer> api_ensyuf_d;
    private List<Integer> api_ensyue_n;
    private List<Integer> api_battle_n;
    private List<Integer> api_battle_d;
    private List<Integer> api_weda;
    private List<Integer> api_wedb;
    private List<Integer> api_pa;
    private List<Integer> api_pab;

    /**
     * api_id : 1
     * api_filename : snohitatusbk
     * api_version : ["27","19","731"]
     * api_battle_n : [109,40]
     * api_battle_d : [43,31]
     * api_sortno : 31
     * api_boko_n : [187,36]
     * api_boko_d : [124,37]
     * api_kaisyu_n : [42,10]
     * api_kaisyu_d : [42,10]
     * api_kaizo_n : [105,-46]
     * api_kaizo_d : [43,-48]
     * api_map_n : [43,36]
     * api_map_d : [-19,22]
     * api_ensyuf_n : [193,27]
     * api_ensyuf_d : [-4,-48]
     * api_ensyue_n : [193,0]
     * api_weda : [168,162]
     * api_wedb : [217,229]
     * api_pa : [0,0]
     * api_pab : [0,0]
     */

/*

    private int api_sortno;
    private List<String> api_version;
*/

 /*



    public int getApi_sortno() {
        return api_sortno;
    }

    public void setApi_sortno(int api_sortno) {
        this.api_sortno = api_sortno;
    }

    public List<String> getApi_version() {
        return api_version;
    }

    public void setApi_version(List<String> api_version) {
        this.api_version = api_version;
    }*/
    public int getApi_id() {
        return api_id;
    }

    public void setApi_id(int api_id) {
        this.api_id = api_id;
    }

    public String getApi_filename() {
        return api_filename;
    }

    public void setApi_filename(String api_filename) {
        this.api_filename = api_filename;
    }

    public List<Integer> getApi_battle_n() {
        return api_battle_n;
    }

    public void setApi_battle_n(List<Integer> api_battle_n) {
        this.api_battle_n = api_battle_n;
    }

    public List<Integer> getApi_battle_d() {
        return api_battle_d;
    }

    public void setApi_battle_d(List<Integer> api_battle_d) {
        this.api_battle_d = api_battle_d;
    }

    public List<Integer> getApi_boko_n() {
        return api_boko_n;
    }

    public void setApi_boko_n(List<Integer> api_boko_n) {
        this.api_boko_n = api_boko_n;
    }

    public List<Integer> getApi_boko_d() {
        return api_boko_d;
    }

    public void setApi_boko_d(List<Integer> api_boko_d) {
        this.api_boko_d = api_boko_d;
    }

    public List<Integer> getApi_kaisyu_n() {
        return api_kaisyu_n;
    }

    public void setApi_kaisyu_n(List<Integer> api_kaisyu_n) {
        this.api_kaisyu_n = api_kaisyu_n;
    }

    public List<Integer> getApi_kaisyu_d() {
        return api_kaisyu_d;
    }

    public void setApi_kaisyu_d(List<Integer> api_kaisyu_d) {
        this.api_kaisyu_d = api_kaisyu_d;
    }

    public List<Integer> getApi_kaizo_n() {
        return api_kaizo_n;
    }

    public void setApi_kaizo_n(List<Integer> api_kaizo_n) {
        this.api_kaizo_n = api_kaizo_n;
    }

    public List<Integer> getApi_kaizo_d() {
        return api_kaizo_d;
    }

    public void setApi_kaizo_d(List<Integer> api_kaizo_d) {
        this.api_kaizo_d = api_kaizo_d;
    }

    public List<Integer> getApi_map_n() {
        return api_map_n;
    }

    public void setApi_map_n(List<Integer> api_map_n) {
        this.api_map_n = api_map_n;
    }

    public List<Integer> getApi_map_d() {
        return api_map_d;
    }

    public void setApi_map_d(List<Integer> api_map_d) {
        this.api_map_d = api_map_d;
    }

    public List<Integer> getApi_ensyuf_n() {
        return api_ensyuf_n;
    }

    public void setApi_ensyuf_n(List<Integer> api_ensyuf_n) {
        this.api_ensyuf_n = api_ensyuf_n;
    }

    public List<Integer> getApi_ensyuf_d() {
        return api_ensyuf_d;
    }

    public void setApi_ensyuf_d(List<Integer> api_ensyuf_d) {
        this.api_ensyuf_d = api_ensyuf_d;
    }

    public List<Integer> getApi_ensyue_n() {
        return api_ensyue_n;
    }

    public void setApi_ensyue_n(List<Integer> api_ensyue_n) {
        this.api_ensyue_n = api_ensyue_n;
    }

    public List<Integer> getApi_weda() {
        return api_weda;
    }

    public void setApi_weda(List<Integer> api_weda) {
        this.api_weda = api_weda;
    }

    public List<Integer> getApi_wedb() {
        return api_wedb;
    }

    public void setApi_wedb(List<Integer> api_wedb) {
        this.api_wedb = api_wedb;
    }

    public List<Integer> getApi_pa() {
        return api_pa;
    }

    public void setApi_pa(List<Integer> api_pa) {
        this.api_pa = api_pa;
    }

    public List<Integer> getApi_pab() {
        return api_pab;
    }

    public void setApi_pab(List<Integer> api_pab) {
        this.api_pab = api_pab;
    }

    @Override
    public String toString() {
        return "ShipGraph{" +
                "api_id=" + api_id +
                ", api_filename='" + api_filename + '\'' +
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
}

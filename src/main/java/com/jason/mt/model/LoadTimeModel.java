package com.jason.mt.model;

/**
 * Created by jason on 2017/7/24.
 */
public class LoadTimeModel {
    private int val_ref_load_time;

    private int val_load_time;

    private String ct_poi;

    private String page_type;

    private int checkin_city_id;

    public int getVal_ref_load_time() {
        return val_ref_load_time;
    }

    public void setVal_ref_load_time(int val_ref_load_time) {
        this.val_ref_load_time = val_ref_load_time;
    }

    public int getVal_load_time() {
        return val_load_time;
    }

    public void setVal_load_time(int val_load_time) {
        this.val_load_time = val_load_time;
    }

    public String getCt_poi() {
        return ct_poi;
    }

    public void setCt_poi(String ct_poi) {
        this.ct_poi = ct_poi;
    }

    public String getPage_type() {
        return page_type;
    }

    public void setPage_type(String page_type) {
        this.page_type = page_type;
    }

    public int getCheckin_city_id() {
        return checkin_city_id;
    }

    public void setCheckin_city_id(int checkin_city_id) {
        this.checkin_city_id = checkin_city_id;
    }

    @Override
    public String toString() {
        return "LoadTimeModel{" +
                "val_ref_load_time=" + val_ref_load_time +
                ", val_load_time=" + val_load_time +
                '}';
    }
}

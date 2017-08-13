package com.jason.mt.model;

/**
 * Created by jason on 2017/7/24.
 */
public class LoadTimeModel {
    private int val_ref_load_time;

    private int val_load_time;

    private String ct_poi_e;

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

    public String getCt_poi_e() {
        return ct_poi_e;
    }

    public void setCt_poi_e(String ct_poi_e) {
        this.ct_poi_e = ct_poi_e;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LoadTimeModel)) return false;

        LoadTimeModel that = (LoadTimeModel) o;

        if (val_load_time != that.val_load_time) return false;
        return ct_poi_e != null ? ct_poi_e.equals(that.ct_poi_e) : that.ct_poi_e == null;
    }

    @Override
    public int hashCode() {
        int result = val_load_time;
        result = 31 * result + (ct_poi_e != null ? ct_poi_e.hashCode() : 0);
        return result;
    }
}

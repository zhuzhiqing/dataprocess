package com.jason.mt.model;

/**
 * Created by jason on 2017/7/24.
 */
public class FlagModel {

    private long timestamp;
    private String curPage;
    private String fromPage;
    private LoadTimeModel loadTimeModel;

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getCurPage() {
        return curPage;
    }

    public void setCurPage(String curPage) {
        this.curPage = curPage;
    }

    public String getFromPage() {
        return fromPage;
    }

    public void setFromPage(String fromPage) {
        this.fromPage = fromPage;
    }

    public LoadTimeModel getLoadTimeModel() {
        return loadTimeModel;
    }

    public void setLoadTimeModel(LoadTimeModel loadTimeModel) {
        this.loadTimeModel = loadTimeModel;
    }

    @Override
    public String toString() {
        return "FlagModel{" +
                "timestamp=" + timestamp +
                ", curPage='" + curPage + '\'' +
                ", fromPage='" + fromPage + '\'' +
                ", loadTimeModel=" + loadTimeModel +
                '}';
    }
}

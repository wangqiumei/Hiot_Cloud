package com.huatec.hiot_cloud.data.bean;

/**
 * GPS数据实体类
 */
public class UpDataStreamGpsBean {
    /**
     * 通道ID
     */
    private String updatastreamId;
    /**
     * 时间
     */
    private String timing;
    /**
     * 经度
     */
    private String longitude;
    /**
     * 纬度
     */
    private String latitude;
    /**
     * 海拔
     */
    private String elevation;

    public String getUpdatastreamId() {
        return updatastreamId;
    }

    public void setUpdatastreamId(String updatastreamId) {
        this.updatastreamId = updatastreamId;
    }

    public String getTiming() {
        return timing;
    }

    public void setTiming(String timing) {
        this.timing = timing;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getElevation() {
        return elevation;
    }

    public void setElevation(String elevation) {
        this.elevation = elevation;
    }
}

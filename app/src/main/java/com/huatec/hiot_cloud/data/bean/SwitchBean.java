package com.huatec.hiot_cloud.data.bean;

import java.io.Serializable;

/**
 * 开关通道对象
 */
public class SwitchBean implements Serializable {
    /**
     * 下行通道id
     */
    private String downDataStreamId;
    /**
     * 操作时间
     */
    private String timing;
    /**
     * 状态
     */
    private int status;

    public String getDownDataStreamId() {
        return downDataStreamId;
    }

    public void setDownDataStreamId(String downDataStreamId) {
        this.downDataStreamId = downDataStreamId;
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTiming() {
        return timing;
    }

    public void setTiming(String timing) {
        this.timing = timing;
    }
}

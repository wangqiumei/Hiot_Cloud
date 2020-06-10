package com.huatec.hiot_cloud.ui.devicelist;

import com.huatec.hiot_cloud.data.bean.DeviceBean;
import com.huatec.hiot_cloud.ui.base.BaseView;

import java.util.List;

/**
 * 设备列表功能view层接口
 */
interface DeviceListView extends BaseView {
    /**
     * 显示设备列表
     *
     * @param deviceList
     */
    void showDeviceList(List<DeviceBean> deviceList);
}

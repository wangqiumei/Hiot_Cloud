package com.huatec.hiot_cloud.ui.gpsdatastreamhistory;

import com.huatec.hiot_cloud.data.bean.UpDataStreamGpsBean;
import com.huatec.hiot_cloud.ui.base.BaseView;

import java.util.List;

/**
 * GPS可视化功能模块View层接口
 */
interface GpsDataStreamHistoryView extends BaseView {
    /**
     * 显示数据
     *
     * @param dataList
     */
    void showData(List<UpDataStreamGpsBean> dataList);
}

package com.huatec.hiot_cloud.ui.gpsdatastreamhistory;

import com.huatec.hiot_cloud.data.DataManager;
import com.huatec.hiot_cloud.data.bean.UpDataStreamGpsBean;
import com.huatec.hiot_cloud.test.networktest.ResultBase;
import com.huatec.hiot_cloud.ui.base.BasePresenter;

import java.util.List;

import javax.inject.Inject;

/**
 * GPS数据可视化Presenter层类
 */
class GpsDataStreamHistoryPresenter extends BasePresenter<GpsDataStreamHistoryView> {
    @Inject
    DataManager dataManager;

    @Inject
    public GpsDataStreamHistoryPresenter() {

    }

    /**
     * 加载GPS数据
     *
     * @param upDataStreamId
     */
    public void loadGpsDataStreamHistory(String upDataStreamId) {
        subscribe(dataManager.getGpsUpDataStreamHistory(upDataStreamId), new RequestCallback<ResultBase<List<UpDataStreamGpsBean>>>() {
            @Override
            public void onNext(ResultBase<List<UpDataStreamGpsBean>> resultBase) {
                super.onNext(resultBase);
                if (resultBase.getData() != null) {
                    getView().showData(resultBase.getData());
                }
            }
        });

    }
}

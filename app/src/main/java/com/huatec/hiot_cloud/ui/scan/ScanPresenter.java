package com.huatec.hiot_cloud.ui.scan;

import com.huatec.hiot_cloud.data.DataManager;
import com.huatec.hiot_cloud.test.networktest.ResultBase;
import com.huatec.hiot_cloud.ui.base.BasePresenter;
import com.huatec.hiot_cloud.utils.Constants;

import javax.inject.Inject;

/**
 * 扫一扫模块presenter类
 */
class ScanPresenter extends BasePresenter<ScanView> {
    @Inject
    DataManager dataManager;

    @Inject
    public ScanPresenter() {
    }

    /**
     * 绑定设备
     *
     * @param deviceId
     */
    public void bindDevice(String deviceId) {
        subscribe(dataManager.bindDevice(deviceId), new RequestCallback<ResultBase>() {
            @Override
            public void onNext(ResultBase resultBase) {
                super.onNext(resultBase);
                getView().showMessage(Constants.BIND_SUCCESS);
                getView().bindDeviceSucc();
            }
        });
    }
}

package com.huatec.hiot_cloud.ui.updateemail;

import com.huatec.hiot_cloud.ui.base.BaseView;

/**
 * 修改邮箱View层接口
 */
interface EmailUpdateView extends BaseView {

    /**
     * 邮箱修改成功后处理
     */
    void emailUpdateSucc();
}

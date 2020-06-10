package com.huatec.hiot_cloud.ui.mine;

import com.huatec.hiot_cloud.data.bean.UserBean;
import com.huatec.hiot_cloud.ui.base.BaseView;

/**
 * 用户中心view层接口
 */
public interface MineView extends BaseView {
    /**
     * 刷新用户信息
     *
     * @param userBean
     */
    void refreshUserInfo(UserBean userBean);

    /**
     * 刷新用户头像
     *
     * @param url
     */
    void refreshUserHead(String url);

    /**
     * 重新登录的处理
     */
    void tokenOut();
}

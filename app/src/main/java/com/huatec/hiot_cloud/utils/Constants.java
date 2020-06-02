package com.huatec.hiot_cloud.utils;

/**
 * 常量类
 */
public class Constants {

    /**
     *
     */
    public static final int MAIN_FRAGMENT_COUNT = 4;

    /**
     *主页viewpager的消息fragment选择序号
     */
    public static final int MAIN_VIEWPAGER_INDEX_MESSAGE = 0;

    /**
     *主页viewpager的设备fragment选择序号
     */
    public static final int MAIN_VIEWPAGER_INDEX_EQUIPMENT= 1;

    /**
     *主页viewpager的我的fragment选择序号
     */
    public static final int MAIN_VIEWPAGER_INDEX_MINE = 2;

    /**
     *主页viewpager的场景fragment选择序号
     */
    public static final int MAIN_VIEWPAGER_INDEX_SCENE = 3;

    /**
     * APP登录的loginCode
     */
    public static final String LOGIN_CODE_APP = "app";

    /**
     * APP的注册用户类型
     */
    public static final String REGISTER_TYPE_NOMAL = "1";

    /**
     * 服务端返回信息状态属性成功
     */
    public static final int MSG_STATUS_SUCCESS = 1;

    /**
     * 网络请求失败吐司
     */
    public static final String TOAST_MSG_NETWORK_FAIL = "当前网络无法访问，请稍后再试";

    /**
     * 登录成功吐司
     */
    public static final String LOGIN_SUCC = "登录成功";

    /**
     * 注册成功吐司
     */
    public static final String REGISTER_SUCC = "注册成功";

    /**
     * 用户名不能为空，请重新输入
     */
    public static final CharSequence USERNAME_UNNULL_AGAIN = "用户名不能为空，请重新输入";

    /**
     * 用户名输入不正确，请重新输入
     */
    public static final CharSequence USERNAME_ERROR_AGAIN = "用户名输入不正确，请重新输入";

    /**
     * 密码不能为空，请重新输入
     */
    public static final CharSequence PASSWORD_UNNULL_AGAIN = "密码不能为空，请重新输入";

    /**
     * 密码输入不正确，请重新输入
     */
    public static final CharSequence PASSWORD_ERROR_AGAIN = "密码输入不正确，请重新输入";

    /**
     * 邮箱不能为空，请重新输入
     */
    public static final CharSequence EMAIL_UNNULL_AGAIN = "邮箱不能为空，请重新输入";

    /**
     * 邮箱输入不正确，请重新输入
     */
    public static final CharSequence EMAIL_ERROR_AGAIN = "邮箱输入不正确，请重新输入";

    /**
     * 正在登录...
     */
    public static final String LOGINING = "正在登录...";
    /**
     * onError失败吐司
     */
    public static final String ERROR_MESSAGE = "服务器开小差，请稍后再试";
    /**
     * form-data类
     */
    public static final String MULTIPART_FORM_DATA = "multipart/form-data";
    /**
     * 如果token失效
     */
    public static final int MSG_STATUS_TOKEN_OUT = -100;
}

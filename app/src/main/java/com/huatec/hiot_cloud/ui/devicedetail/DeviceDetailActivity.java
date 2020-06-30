package com.huatec.hiot_cloud.ui.devicedetail;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.huatec.hiot_cloud.R;
import com.huatec.hiot_cloud.data.bean.DeviceDetailBean;
import com.huatec.hiot_cloud.data.bean.SwitchBean;
import com.huatec.hiot_cloud.data.bean.UpdatastreamDataDto;
import com.huatec.hiot_cloud.ui.base.BaseActivity;
import com.huatec.hiot_cloud.ui.datastreamhistory.LineChartActivity;
import com.huatec.hiot_cloud.ui.gpsdatastreamhistory.GpsDataStreamHistoryActivity;
import com.huatec.hiot_cloud.utils.Constants;
import com.huatec.hiot_cloud.utils.ImageUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DeviceDetailActivity extends BaseActivity<DeviceDetailView, DeviceDetailPresenter> implements DeviceDetailView {

    @Inject
    DeviceDetailPresenter presenter;

    @BindView(R.id.iv_device_detail)
    ImageView ivDeviceDetail;

    @BindView(R.id.tv_device_title)
    TextView tvDeviceTitle;

    @BindView(R.id.tv_device_status)
    TextView tvDeviceState;

    @BindView(R.id.toolbar_device)
    Toolbar toolbarDevice;

    @BindView(R.id.iv_data_stream_histroy)
    ImageView ivDeviceStreamHistory;

    @BindView(R.id.tv_data_stream_type)
    TextView tvDeviceStreamType;

    @BindView(R.id.iv_switch)
    ImageView ivSwitch;

    @BindView(R.id.switch_data_stream)
    Switch switchDateStream;

    @BindView(R.id.srl_device_detail)
    SwipeRefreshLayout srlDeviceDetail;
    /**
     * 设备id
     */
    private String deviceId;

    /**
     * 当前上行通道id
     */
    private String upDataStreamId;

    /**
     * 当前通道类型
     */
    private String upDataStreamType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_detail);
        ButterKnife.bind(this);
        initView();
        deviceId = getIntent().getStringExtra(Constants.INTENT_EXTRA_DEVICE_ID);
    }


    @Override
    public DeviceDetailPresenter createPresenter() {
        return presenter;
    }

    @Override
    public void injectIndependies() {
        getActivityComponent().inject(this);
    }


    @Override
    protected void onResume() {
        super.onResume();
        loadDeviceDetail();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        //下拉刷新
        srlDeviceDetail.setColorSchemeColors(
                getResources().getColor(android.R.color.holo_red_dark),
                getResources().getColor(android.R.color.holo_blue_dark),
                getResources().getColor(android.R.color.holo_green_dark)
        );
        srlDeviceDetail.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //加载设备详情
                loadDeviceDetail();
            }
        });
        //toolBar
        setSupportActionBar(toolbarDevice);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarDevice.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    /**
     * 加载设备详情
     */
    private void loadDeviceDetail() {
        srlDeviceDetail.setRefreshing(true);
        presenter.loadDeviceDetail(deviceId);
        srlDeviceDetail.setRefreshing(false);
    }

    @Override
    public void setDeviceDetail(DeviceDetailBean data) {
        if (data == null) {
            return;
        }
        upDataStreamId = null;
        tvDeviceTitle.setText(data.getTitle());
        //方法1
//        if (Constants.DEVICE_STATUS_ACTIVITY.equals(data.getStatus())) {
//            tvDeviceState.setText("已激活");
//        }else {
//            tvDeviceState.setText("未激活");
//        }
        //方法2
        tvDeviceState.setText(Constants.DEVICE_STATUS_ACTIVITY.equals(data.getStatus()) ? "已激活" : "未激活");
        ImageUtils.show(this, ivDeviceDetail, ImageUtils.getFullUrl(data.getDeviceimg()));

        //解析通道信息
        switchDateStream.setVisibility(View.GONE);
        if (data.getUpdatastreamDataDtoList() != null && !data.getUpdatastreamDataDtoList().isEmpty()) {
            UpdatastreamDataDto updatastreamDataDto = data.getUpdatastreamDataDtoList().get(0);
            if (updatastreamDataDto == null) {
                return;
            }
            upDataStreamType = updatastreamDataDto.getData_type();
            if (Constants.DATA_STREAM_TYPE_GPS.equals(updatastreamDataDto.getData_type())) {
                upDataStreamId = updatastreamDataDto.getUpDataStreamId();
                tvDeviceStreamType.setText("GPS通道");
            }
            if (Constants.DATA_STREAM_TYPE_SWITCH.equals(updatastreamDataDto.getData_type())) {
                upDataStreamId = updatastreamDataDto.getUpDataStreamId();
                tvDeviceStreamType.setText("开关通道");
                switchDateStream.setVisibility(View.VISIBLE);
                if (updatastreamDataDto.getDataList() != null && !updatastreamDataDto.getDataList().isEmpty()) {
                    SwitchBean switchBean = updatastreamDataDto.getDataList().get(0);
                    if (switchBean == null) {
                        return;
                    }
                    //如果开关状态开
                    if (Constants.SWITCH_STATUS_ON == switchBean.getStatus()) {
                        ivSwitch.setImageResource(R.drawable.icon_on);
                        switchDateStream.setChecked(true);
                    } else {
                        //如果开关状态关
                        ivSwitch.setImageResource(R.drawable.icon_off);
                        switchDateStream.setChecked(false);
                    }
                    //设置switch事件
                    switchDateStream.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            presenter.changeSwitch(switchBean.getDownDataStreamId(), isChecked);
                        }
                    });
                }
            }
        }
    }

    @OnClick(R.id.iv_data_stream_histroy)
    public void onViewClicked() {

        if (TextUtils.isEmpty(upDataStreamId)) {
            return;
        }
        //打开开关可视化界面
        if (Constants.DATA_STREAM_TYPE_SWITCH.equals(upDataStreamType)) {
            Intent intent = new Intent(this, LineChartActivity.class);
            intent.putExtra(Constants.INTENT_EXTRA_UP_DATASTREAM_ID, upDataStreamId);
            startActivity(intent);
            return;
        }
        //打开gps数据可视化界面
        if (Constants.DATA_STREAM_TYPE_GPS.equals(upDataStreamType)) {
            Intent intent = new Intent(this, GpsDataStreamHistoryActivity.class);
            intent.putExtra(Constants.INTENT_EXTRA_UP_DATASTREAM_ID, upDataStreamId);
            startActivity(intent);
            return;
        }
    }
}

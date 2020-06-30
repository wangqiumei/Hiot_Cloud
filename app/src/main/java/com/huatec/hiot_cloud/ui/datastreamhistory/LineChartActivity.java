package com.huatec.hiot_cloud.ui.datastreamhistory;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.huatec.hiot_cloud.R;
import com.huatec.hiot_cloud.data.bean.UpDataStreamSwitchBean;
import com.huatec.hiot_cloud.ui.base.BaseActivity;
import com.huatec.hiot_cloud.utils.Constants;
import com.huatec.hiot_cloud.utils.widget.mpchart.MPChartHelper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LineChartActivity extends BaseActivity<LineChartView, LineChartPresenter> implements LineChartView {


    @Inject
    LineChartPresenter presenter;

    @BindView(R.id.tv_data_history)
    TextView tvDataHistory;

    @BindView(R.id.toolbar_data_history)
    Toolbar toolbarDataHistory;

    @BindView(R.id.line_chart_history)
    LineChart lineChartHistory;

    /**
     * 上行通道id
     */
    private String upDataStreamId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_chart);
        ButterKnife.bind(this);
        upDataStreamId = getIntent().getStringExtra(Constants.INTENT_EXTRA_UP_DATASTREAM_ID);
        initView();

    }

    @Override
    public LineChartPresenter createPresenter() {
        return presenter;
    }

    @Override
    public void injectIndependies() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.loadUpDataStreamHistory(upDataStreamId);
    }


    @Override
    public void showDataHistory(List<UpDataStreamSwitchBean> dataList) {
        if (dataList == null || dataList.isEmpty()) {
            return;
        }
        List<String> xAxisValue = new ArrayList<>();
        List<Float> yAxisValue = new ArrayList<>();
        List<String> timing = new ArrayList<>();
        for (int i = 0; i < dataList.size(); i++) {
            xAxisValue.add(String.valueOf(i));
            yAxisValue.add(Float.valueOf(dataList.get(i).getStatus()));
            timing.add(dataList.get(i).getTiming());
        }
        lineChartHistory.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                showMessage(timing.get((int) e.getX()));
            }

            @Override
            public void onNothingSelected() {

            }
        });
        MPChartHelper.setSingleLineChart(lineChartHistory, xAxisValue, yAxisValue, timing,
                "电平图 0：代表关 1：代表开", true, true);
    }

    /**
     * 初始化控件
     */
    private void initView() {
        setSupportActionBar(toolbarDataHistory);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarDataHistory.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}

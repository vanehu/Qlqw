package com.ascba.rebate.activities.merchant;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ascba.rebate.R;
import com.ascba.rebate.activities.success.TextInfoSuccessActivity;
import com.ascba.rebate.adapter.MctPayAdapter;
import com.ascba.rebate.appconfig.AppConfig;
import com.ascba.rebate.base.activity.BaseDefaultPayActivity;
import com.ascba.rebate.bean.MctBasePay;
import com.ascba.rebate.bean.MctPayClass;
import com.ascba.rebate.bean.MctPayDesc;
import com.ascba.rebate.bean.MctPayTitle;
import com.ascba.rebate.bean.Pay;
import com.ascba.rebate.bean.Result;
import com.ascba.rebate.net.AbstractRequest;
import com.ascba.rebate.utils.UrlUtils;
import com.squareup.picasso.Picasso;
import com.yanzhenjie.nohttp.RequestMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 李平 on 2017/12/5 14:27
 * Describe: 优享商家
 */

public class MctPayActivity extends BaseDefaultPayActivity implements View.OnClickListener {
    private List<MctBasePay> data;
    private MctPayAdapter mctAdapter;
    private View headView;
    private ImageView imHead;
    private TextView tvName;
    private TextView tvClass;
    private String money;

    @Override
    protected int bindLayout() {
        return R.layout.activity_mct_pay;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);
        addData();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mctAdapter = new MctPayAdapter(data);
        View footView = getLayoutInflater().inflate(R.layout.button, null);
        footView.findViewById(R.id.btn_apply).setOnClickListener(this);

        mctAdapter.addFooterView(footView);
        mRecyclerView.setAdapter(mctAdapter);
        requestData();
    }

    private void requestData() {
        AbstractRequest request = buildRequest(UrlUtils.mctPay, RequestMethod.GET, null);
        executeNetwork(0, "请稍后", request);
    }

    private void updatePayPsdStatus(JSONObject jObj) {
        AppConfig.getInstance().putInt("is_level_pwd", jObj.getIntValue("is_level_pwd"));
    }

    private void setList(JSONObject jObj) {
        List<MctPayClass> level = JSON.parseArray(jObj.getString("level"), MctPayClass.class);
        if (level != null && level.size() > 0) {
            data.add(new MctPayTitle("优享商家"));
            data.addAll(level);
        }

        List<MctPayDesc> interests = JSON.parseArray(jObj.getString("interests"), MctPayDesc.class);
        if (interests != null && interests.size() > 0) {
            data.add(new MctPayTitle("开通即可享受以下权益"));
            data.addAll(interests);
        }
        mctAdapter.notifyDataSetChanged();
    }

    private void setHead(JSONObject jObj) {
        if (headView == null) {
            headView = getLayoutInflater().inflate(R.layout.mct_pat_head, null);
            imHead = headView.findViewById(R.id.im_head);
            tvName = headView.findViewById(R.id.tv_name);
            tvClass = headView.findViewById(R.id.tv_class);
            mctAdapter.addHeaderView(headView);
        }
        Picasso.with(this).load(jObj.getString("seller_image")).placeholder(R.mipmap.logo).into(imHead);
        tvName.setText(jObj.getString("seller_name"));
        tvClass.setText(jObj.getString("seller_rule"));
    }

    private void addData() {
        data = new ArrayList<>();
    }

    @Override
    public void onClick(View v) {
        showPayDialog(((MctPayClass) data.get(mctAdapter.getIndex()-1)).getAfter(), money);
    }

    @Override
    protected Class<?> bindPaySuccessPage() {
        return TextInfoSuccessActivity.class;
    }

    @Override
    protected void requestPayInfo(String type, String money, int what) {
        AbstractRequest request = buildRequest(UrlUtils.sellerPayment, RequestMethod.POST, Pay.class);
        request.add("pay_type", type);
        request.add("level_id",((MctPayClass) data.get(mctAdapter.getIndex()-1)).getId());
        executeNetwork(what, "请稍后", request);
    }

    @Override
    protected <T> void mHandle200(int what, Result<T> result) {
        super.mHandle200(what,result);
        if (what == 0) {
            String data = (String) result.getData();
            JSONObject jObj = JSONObject.parseObject(data);
            money = jObj.getString("money");
            setHead(jObj);
            setList(jObj);
            updatePayPsdStatus(jObj);
        }
    }

}
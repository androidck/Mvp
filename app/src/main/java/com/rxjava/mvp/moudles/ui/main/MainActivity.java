package com.rxjava.mvp.moudles.ui.main;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.rxjava.mvp.R;
import com.rxjava.mvp.common.base.MyActivity;
import com.rxjava.mvp.common.constant.ActivityConstant;
import com.rxjava.mvp.moudles.bean.Elements;
import com.rxjava.mvp.moudles.bean.NewsList;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

@Route(path = ActivityConstant.MAIN)
public class MainActivity extends MyActivity implements MainContract.View  {


    @BindView(R.id.btn_login_commit)
    Button btnLoginCommit;
    @BindView(R.id.tv_date)
    TextView tvDate;

    private MainPresenter presenter;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected int getTitleBarId() {
        return 0;
    }

    @Override
    protected void initView() {

    }
    //设置圆角北京
    private Drawable setDrawable(String colorStr) {
        RoundRectShape rr = new RoundRectShape(new float[]{15, 15, 15, 15, 15, 15, 15, 15},
                null, null); //60px = 20dp
        ShapeDrawable drawable = new ShapeDrawable(rr);
        drawable.getPaint().setColor(Color.parseColor(colorStr)); //指定填充颜色
        drawable.getPaint().setStyle(Paint.Style.FILL); // 指定填充模式
        return drawable;
    }
    @Override
    protected void initData() {
        presenter = new MainPresenter(this, this);
    }

    @OnClick(R.id.btn_login_commit)
    public void onViewClicked() {
        getData();
    }


    public void getData() {
      try {
        presenter.getNewsList(10);
      }catch (Exception e){
          e.printStackTrace();
      }
    }


    @Override
    public void setContent(List<NewsList> elements) {
        tvDate.setText(elements.toString());
        toast(elements.toString());
    }

    @Override
    public void Error(String msg) {
        toast(msg);
    }
}

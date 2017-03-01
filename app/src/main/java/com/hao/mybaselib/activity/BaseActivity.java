package com.hao.mybaselib.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hao.mybaselib.presenter.BasePresenter;
import com.hao.mybaselib.view.BaseView;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by hao on 2017/3/1.
 */
public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements BaseView {
    protected T mPresenter;
    protected Activity mContext;
    private Unbinder mUnbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        //添加到activity栈列
//        ActivityController.addActivity(this);

        mUnbinder = ButterKnife.bind(this);
        mContext = this;
        initPresenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
        initEventAndData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //整个app当前运行的activity
//        ActivityController.setCurrentActivity(this);
//        IMClientManager.getInstance(this).getMessageQoSListener().setActivity(
//                ActivityController.getCurrentActivity());
    }

//    protected void setToolBar(Activity activity, Toolbar toolbar, String title,
//                              boolean isBack, boolean isRight) {
//        toolbar.setTitle("");
//        setSupportActionBar(toolbar);
//        ImageView iv = (ImageView) toolbar.findViewById(R.id.back);
//        TextView text = (TextView) toolbar.findViewById(R.id.toolbar_text);
//        ImageView ivRight = (ImageView) toolbar.findViewById(R.id.img_right);
//        ivRight.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                SearchActivity.launch(BaseActivity.this);
//            }
//        });
//        iv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
//        if (!isBack){
//            iv.setVisibility(View.GONE);
//        }else {
//            iv.setVisibility(View.VISIBLE);
//        }
//        if(!isRight){
//            ivRight.setVisibility(View.GONE);
//        }else {
//            ivRight.setVisibility(View.VISIBLE);
//        }
//        text.setText(title);
////        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
////        getSupportActionBar().setDisplayShowHomeEnabled(true);
////        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                finish();
////            }
////        });
//        StatusBarCompat.compat(activity, activity.getResources().getColor(R.color.colorPrimary));
//    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mPresenter != null){
            mPresenter.onStop();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //移除activity栈列
//        ActivityController.removeActivity(this);
        if (mPresenter != null)
            mPresenter.detachView();
        mUnbinder.unbind();
    }

    @Override
    public void showError(String msg) {
//        ToastUtils.showShort(this, msg);
    }

    protected abstract int getLayout();
    protected abstract void initEventAndData();
    protected abstract void initPresenter();

}

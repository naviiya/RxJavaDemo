package com.kuai.app.retrofit.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.kuai.app.retrofit.BaseActivity;
import com.kuai.app.retrofit.R;
import com.kuai.app.retrofit.manager.MediaManager;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 图片及gif动图预览activity
 */

public class ImageActivity extends BaseActivity {

    @BindView(R.id.iv_img_detail)
    ImageView mImageDetail;
    @BindView(R.id.root_view_img_detail)
    RelativeLayout mRootView;
    private String mUri;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.img_detail_activity_layout);
        ButterKnife.bind(this);
        mImageDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Intent intent = getIntent();
        if (intent != null) {
            mUri = intent.getStringExtra("img_uri");
        }
        if (mUri != null) {
            load();
            return;
        }
        // uri 为空，finish
        Toast.makeText(this, "获取图片资源错误！", Toast.LENGTH_LONG).show();
        finish();
    }

    private void load() {
        MediaManager.loadOriginResourceToImageView(mUri, R.mipmap.ic_launcher, mImageDetail, this);
    }


}

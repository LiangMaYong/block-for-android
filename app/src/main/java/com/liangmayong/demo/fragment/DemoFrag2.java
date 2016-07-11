package com.liangmayong.demo.fragment;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.liangmayong.demo.R;
import com.liangmayong.androidblock.actionbar.ActionBarController;
import com.liangmayong.androidblock.base.BlockFragment;

/**
 * Created by 007 on 2016/7/5.
 */
public class DemoFrag2 extends BlockFragment {
    private TextView textView;

    @Override
    protected void initViews(View containerView, RelativeLayout rootView) {
        textView = (TextView) containerView.findViewById(R.id.text);
        textView.setText("Page2");
    }

    @Override
    public boolean isShowActionBar() {
        return  false;
    }

    @Override
    protected int getContaierViewId() {
        return R.layout.activity_main2;
    }

    @Override
    protected void initActionBar(ActionBarController actionBarController) {
        actionBarController.title().text("Page2");
        actionBarController.left().text("返回").clicked(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                close();
            }
        });
    }
}

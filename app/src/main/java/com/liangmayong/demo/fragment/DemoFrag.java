package com.liangmayong.demo.fragment;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.liangmayong.demo.R;
import com.liangmayong.androidblock.actionbar.ActionBarController;
import com.liangmayong.androidblock.base.BlockFragment;

/**
 * Created by 007 on 2016/7/4.
 */
public class DemoFrag extends BlockFragment {
    TextView textView;

    @Override
    protected void initViews(View containerView, RelativeLayout rootView) {
        textView = (TextView) containerView.findViewById(R.id.text);
        textView.setText("new DemoFrag1");
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("open DemoFrag2");
                open(new DemoFrag2());
            }
        });
    }

    @Override
    protected int getContaierViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initActionBar(ActionBarController actionBarController) {
        actionBarController.title().text("Demo");
    }
}

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
public class DemoFrag2 extends BlockFragment {
    TextView textView;

    @Override
    protected void initViews(View containerView, RelativeLayout rootView) {

        textView = (TextView) containerView.findViewById(R.id.text);
        textView.setText("new DemoFrag2");
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("open DemoFrag1");
                open(new DemoFrag());
            }
        });
    }

    @Override
    protected int getContaierViewId() {
        return R.layout.activity_main2;
    }

    @Override
    protected void initActionBar(ActionBarController actionBarController) {
        actionBarController.title().text("Demo");
    }
}

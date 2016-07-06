# android-block
this is android block framework

## gradle
```
dependencies {
    compile 'com.liangmayong:androidblock:0.7.5'
}
```
## Extends BlockActivity
```
package com.liangmayong.demo;

import com.liangmayong.androidblock.base.BlockActivity;
import com.liangmayong.androidblock.base.BlockFragment;
import com.liangmayong.demo.fragment.DemoFrag;

public class MainActivity extends BlockActivity {

    @Override
    public BlockFragment getFristFragment() {
        return new DemoFrag();
    }

    @Override
    public boolean isShowActionBar() {
        return  true;
    }
}
```
## Extends BlockFragment
```
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
public class DemoFrag extends BlockFragment {
    private TextView textView;

    @Override
    protected void initViews(View containerView, RelativeLayout rootView) {
        textView = (TextView) containerView.findViewById(R.id.text);
        textView.setText("Page1");
    }

    @Override
    protected int getContaierViewId() {
        //fragment layout id
        return R.layout.activity_main;
    }

    @Override
    protected void initActionBar(ActionBarController actionBarController) {
        //init actionbar
        actionBarController.title().text("Page1");
        actionBarController.left().text("返回").clicked(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //close this fragment
                close();
            }
        });
        actionBarController.right().text("Page2").clicked(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //open fragment
                open(new DemoFrag2());
            }
        });
    }
}
```

##技术交流
交流：QQ群297798093

email：ibeam@qq.com
##License
```
The MIT License (MIT)

Copyright (c) 2016 LiangMaYong

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```

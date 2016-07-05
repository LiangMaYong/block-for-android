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
Copyright 2016 LiangMaYong

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

# block-for-android
this is android block framework

Welcome Star and [Issues](https://github.com/LiangMaYong/block-for-android/issues)

## gradle
```
dependencies {
    compile 'com.liangmayong:androidblock:1.0.2'
}
```
## Extends BlockActivity
```
public class MainActivity extends BlockActivity {
    @Override
    public BlockFragment getFristFragment() {
        return new DemoFrag();
    }
}
```
## Extends BlockFragment
```
public class DemoFrag extends BlockFragment {

    private TextView textView;
    @Override
    protected void initViews(View containerView, RelativeLayout rootView) {
        // init views
    }

    @Override
    protected int getContaierViewId() {
        //fragment layout id
        return R.layout.activity_main;
    }

    @Override
    protected void initActionBar(ActionBarController actionBarController) {
        //init actionbar
    }
}
```

##Technical exchange
QQGroup：297798093

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

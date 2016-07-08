package com.liangmayong.demo;

import com.liangmayong.androidblock.base.BlockActivity;
import com.liangmayong.androidblock.base.BlockFragment;
import com.liangmayong.demo.fragment.DemoFrag;

public class MainActivity extends BlockActivity {

    @Override
    public BlockFragment getFristFragment() {
        return new DemoFrag();
    }

}

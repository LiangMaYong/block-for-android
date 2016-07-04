package com.liangmayong.androidblock.base.interfaces;

import com.liangmayong.androidblock.base.BlockFragment;

public interface ICloseFragment {

	void close(BlockFragment fragment, boolean back);

	void show(BlockFragment fragment);
}

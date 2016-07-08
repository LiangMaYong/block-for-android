package com.liangmayong.androidblock.actionbar.configs;

import com.liangmayong.androidblock.actionbar.ActionBar;

/**
 * ActionGrayTheme
 * 
 * @author LiangMaYong
 * @version 1.0
 */
public class ActionBarThemeGray extends ActionBarTheme {

	@Override
	public void onAttachActionBar(ActionBar actionBar) {
		setBackgroundColor(0xff9e9e9e);
		setTitleColor(0xffffffff);
		setSubTitleColor(0xffffffff);
		setActionItemPadding(10);
		setProgressColor(0x99ffffff);
		setTitleSize(18);
		setActionItemSize(18);
		setLineHeight(1);
		setLineColor(0x05ffffff);
		setProgressHeight(1);
		setActionItemColor(0xffffffff, 0x90dddddd);
	}
}

package com.liangmayong.androidblock.actionbar.configs;

import com.liangmayong.androidblock.actionbar.ActionBar;

/**
 * ActionGreenTheme
 * 
 * @author LiangMaYong
 * @version 1.0
 */
public class ActionBarThemeGreen extends ActionBarTheme {

	@Override
	public void onAttachActionBar(ActionBar actionBar) {
		setBackgroundColor(0xff3399ff);
		setTitleColor(0xffffffff);
		setSubTitleColor(0xffffffff);
		setActionItemPadding(10);
		setProgressColor(0x99ffffff);
		setTitleSize(18);
		setActionItemSize(18);
		setLineHeight(1);
		setLineColor(0x05333333);
		setProgressHeight(1);
		setActionItemColor(0xffffffff, 0x90dddddd);
	}

}

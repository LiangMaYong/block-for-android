package com.liangmayong.androidblock.base.interfaces;

import android.view.KeyEvent;

/**
 * IFragment
 * 
 * @author LiangMaYong
 * @version 1.0
 */
public interface IFragment {

	/**
	 * onKeyDown
	 * @param keyCode keyCode
	 * @param event event
	 * @return flag
	 */
	boolean onKeyDown(int keyCode, KeyEvent event);

	/**
	 * onKeyUp
	 * @param keyCode keyCode
	 * @param event event
	 * @return flag
	 */
	boolean onKeyUp(int keyCode, KeyEvent event);

	/**
	 * onBackPressed
	 * @return flag
	 */
	boolean onBackPressed();

	/**
	 * onClosed
	 */
	void onClosed();

	/**
	 * onNewIntent
	 */
	void onNewIntent();

	/**
	 * onNowHidden
	 */
	void onNowHidden();

	/**
	 * onNextShow
	 */
	void onNextShow();

}

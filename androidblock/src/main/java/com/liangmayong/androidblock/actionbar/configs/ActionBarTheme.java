package com.liangmayong.androidblock.actionbar.configs;

import com.liangmayong.androidblock.actionbar.ActionBar;

import android.content.Context;

/**
 * ActionTheme
 * 
 * @author LiangMaYong
 * @version 1.0
 */
public abstract class ActionBarTheme {


	private boolean show = true;
	private int action_bar_show_anim = -1;
	private int action_bar_hide_anim = -1;
	private int action_bar_height = -1;
	private int action_item_padding = 10;
	private int action_item_text_size = 16;
	private int title_text_size = 18;
	private int sub_title_text_size = 12;
	private int action_bg_color = 0xff333333;

	private int title_color = 0xffffffff;

	private int line_color = 0x00ffffff;
	private int line_height = 1;
	private int sub_title_color = 0xdddddddd;
	private int progress_color = 0xff3399ff;
	private int progress_height = 2;

	private int action_item_color = 0xffffffff;
	private int action_item_pressed_color = 0xdddddddd;
	private boolean isAttached = false;
	
	
	public ActionBarTheme() {
	}

	/**
	 * attachActionBar
	 * 
	 * @param actionBar
	 *            actionBar
	 */
	public final void attachActionBar(ActionBar actionBar) {
		if (!isAttached) {
			action_bar_height = dip2px(actionBar.getContext(), 50);
			onAttachActionBar(actionBar);
			isAttached = true;
		}
	}

	/**
	 * onAttachActionBar
	 * 
	 * @param actionBar
	 *            actionBar
	 */
	public abstract void onAttachActionBar(ActionBar actionBar);

	/**
	 * dip2px
	 * 
	 * @param context
	 *            context
	 * @param dpValue
	 *            dpValue
	 * @return px
	 */
	protected int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	/**
	 * getBackgroundColor
	 * 
	 * @return action_bg_color
	 */
	public final int getBackgroundColor() {
		return action_bg_color;
	}

	/**
	 * setBackgroundColor
	 * 
	 * @param action_bg_color
	 *            action_bg_color
	 */
	public final void setBackgroundColor(int action_bg_color) {
		this.action_bg_color = action_bg_color;
	}

	/**
	 * getActionItemColor
	 * 
	 * @param pressed
	 *            pressed
	 * @return color
	 */
	public final int getActionItemColor(boolean pressed) {
		if (pressed) {
			return action_item_pressed_color;
		}
		return action_item_color;
	}

	/**
	 * setActionItemColor
	 * 
	 * @param color
	 *            color
	 * @param pressed
	 *            pressed
	 */
	public final void setActionItemColor(int color, int pressed) {
		this.action_item_color = color;
		this.action_item_pressed_color = pressed;
	}

	/**
	 * getSubTitleColor
	 * 
	 * @return sub_title_color
	 */
	public final int getSubTitleColor() {
		return sub_title_color;
	}

	/**
	 * setSubTitleColor
	 * 
	 * @param color
	 *            color
	 */
	public final void setSubTitleColor(int color) {
		this.sub_title_color = color;
	}

	/**
	 * getTitleColor
	 * 
	 * @return title_color
	 */
	public final int getTitleColor() {
		return title_color;
	}

	/**
	 * setTitleColor
	 * 
	 * @param color
	 *            color
	 */
	public void setTitleColor(int color) {
		this.title_color = color;
	}

	/**
	 * getSubTitleSize
	 * 
	 * @return sub_title_text_size
	 */
	public final int getSubTitleSize() {
		return sub_title_text_size;
	}

	/**
	 * setSubTitleSize
	 * 
	 * @param size
	 *            size
	 */
	public final void setSubTitleSize(int size) {
		this.sub_title_text_size = size;
	}

	/**
	 * getTitleSize
	 * 
	 * @return title_text_size
	 */
	public final int getTitleSize() {
		return title_text_size;
	}

	/**
	 * setTitleSize
	 * 
	 * @param size
	 *            size
	 */
	public final void setTitleSize(int size) {
		this.title_text_size = size;
	}

	/**
	 * getActionItemPadding
	 * 
	 * @return action_item_padding
	 */
	public final int getActionItemPadding() {
		return action_item_padding;
	}

	/**
	 * setActionItemPadding
	 * 
	 * @param padding
	 *            padding
	 */
	public final void setActionItemPadding(int padding) {
		this.action_item_padding = padding;
	}

	/**
	 * getActionItemSize
	 * 
	 * @return action_item_text_size
	 */
	public final int getActionItemSize() {
		return action_item_text_size;
	}

	/**
	 * setActionItemSize
	 * 
	 * @param size
	 *            size
	 */
	public final void setActionItemSize(int size) {
		this.action_item_text_size = size;
	}

	/**
	 * getProgressColor
	 * 
	 * @return progress_color
	 */
	public final int getProgressColor() {
		return progress_color;
	}

	/**
	 * setProgressColor
	 * 
	 * @param color
	 *            color
	 */
	public final void setProgressColor(int color) {
		this.progress_color = color;
	}

	/**
	 * getProgressHeight
	 * 
	 * @return progress_height
	 */
	public int getProgressHeight() {
		return progress_height;
	}

	/**
	 * setProgressHeight
	 * 
	 * @param height
	 *            height
	 */
	public void setProgressHeight(int height) {
		this.progress_height = height;
	}

	/**
	 * getLineColor
	 * 
	 * @return line_color
	 */
	public int getLineColor() {
		return line_color;
	}

	/**
	 * setLineColor
	 * 
	 * @param color
	 *            color
	 */
	public void setLineColor(int color) {
		this.line_color = color;
	}

	/**
	 * getLineHeight
	 * 
	 * @return line_height
	 */
	public int getLineHeight() {
		return line_height;
	}

	/**
	 * setLineHeight
	 * 
	 * @param height
	 *            height
	 */
	public void setLineHeight(int height) {
		this.line_height = height;
	}

	/**
	 * get action height
	 * 
	 * @return height
	 */
	public int getActionHeight() {
		return action_bar_height;
	}

	/**
	 * set Action Height
	 * 
	 * @param heightPx
	 *            height
	 */
	public void setActionHeight(int heightPx) {
		this.action_bar_height = heightPx;
	}

	/**
	 * is show
	 * 
	 * @return is show
	 */
	public boolean isShow() {
		return show;
	}

	/**
	 * set show
	 * 
	 * @param show
	 *            show
	 */
	public void setShow(boolean show) {
		this.show = show;
	}

	/**
	 * getActionBarHideAnim
	 * 
	 * @return anim id
	 */
	public int getActionBarHideAnim() {
		return action_bar_hide_anim;
	}

	/**
	 * getActionBarShowAnim
	 * 
	 * @return anim id
	 */
	public int getActionBarShowAnim() {
		return action_bar_show_anim;
	}

}

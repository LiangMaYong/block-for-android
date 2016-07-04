package com.liangmayong.androidblock.base.interfaces;

import com.liangmayong.androidblock.actionbar.ActionBar;
import com.liangmayong.androidblock.actionbar.ActionBarController;
import com.liangmayong.androidblock.actionbar.configs.ActionBarTheme;
import com.liangmayong.androidblock.base.BlockFragment;
import com.liangmayong.androidblock.base.stack.StackManager;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * IBlockActivity
 *
 * @author LiangMaYong
 * @version 1.0
 */
public interface IBlockActivity {

    /**
     * getStackManager
     *
     * @return StackManager
     */
    public StackManager getStackManager();

    /**
     * initViews
     *
     * @param rootView rootView
     */
    public void initViews(View rootView);

    /**
     * isShowActionBar
     *
     * @return true or false
     */
    public boolean isShowActionBar();

    /**
     * getRootView
     *
     * @return rootView
     */
    public RelativeLayout getRootView();

    /**
     * onBlockCreate
     *
     * @param savedInstanceState savedInstanceState
     */
    public void onBlockCreate(Bundle savedInstanceState);

    /**
     * onFragmentCreateView
     *
     * @param fragment fragment
     * @param view     view
     */
    public void onFragmentCreateView(BlockFragment fragment, View view);

    /**
     * get actionBar theme
     *
     * @return actionbar theme
     */
    public ActionBarTheme getActionBarTheme();

    /**
     * get actionBar controller
     *
     * @return actionBar controller
     */
    public ActionBarController getActionBarController();

    /**
     * get block actionbar
     *
     * @return actionbar
     */
    public ActionBar getBlockActionBar();

    /**
     * get frist fragment
     *
     * @return block fragment
     */
    public BlockFragment getFristFragment();

    /**
     * set anim
     *
     * @param nextIn  nextIn
     * @param nextOut nextOut
     * @param quitIn  quitIn
     * @param quitOut quitOut
     */
    public void setAnim(int nextIn, int nextOut, int quitIn, int quitOut);

    /**
     * onActivityCreate
     *
     * @param savedInstanceState savedInstanceState
     */
    public void onActivityCreate(Bundle savedInstanceState);

    /**
     * showToast
     *
     * @param text text
     */
    public void showToast(CharSequence text);

    /**
     * showToast
     *
     * @param text     text
     * @param duration duration
     */
    public void showToast(CharSequence text, int duration);

}

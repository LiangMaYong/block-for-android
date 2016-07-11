package com.liangmayong.androidblock.base;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.liangmayong.androidblock.BlockConstant;
import com.liangmayong.androidblock.actionbar.ActionBar;
import com.liangmayong.androidblock.actionbar.ActionBarController;
import com.liangmayong.androidblock.actionbar.configs.ActionBarTheme;
import com.liangmayong.androidblock.base.interfaces.IBlockActivity;
import com.liangmayong.androidblock.base.stack.StackManager;
import com.liangmayong.androidblock.utils.ToastUtils;

import java.lang.reflect.Field;

/**
 * BlockActivity
 *
 * @author LiangMaYong
 * @version 1.0
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public abstract class BlockActivity extends FragmentActivity implements IBlockActivity {

    private StackManager manager;
    private int backgroundColor = 0xffeeeeee;
    private RelativeLayout rootView;
    private FrameLayout frameView;
    private ActionBar actionBar;

    @Override
    public StackManager getStackManager() {
        return manager;
    }

    @Override
    public void initViews(View rootView) {

    }

    @Override
    public boolean isDefaultShowActionBar() {
        return true;
    }

    @Override
    public int getActionBarShadowColor() {
        return 0x30757575;
    }

    public RelativeLayout getRootView() {
        return rootView;
    }

    @Override
    public void onBlockCreate(Bundle savedInstanceState) {
    }

    @Override
    public void onFragmentCreateView(BlockFragment fragment, View view) {
    }

    @Override
    protected final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onBlockCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        rootView = new RelativeLayout(this);
        frameView = new FrameLayout(this);
        TextView textView = new TextView(this);
        frameView.addView(textView);
        frameView.setLayoutParams(
                new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        frameView.setId(BlockConstant.FRAGMENT_ID);
        frameView.setBackgroundColor(backgroundColor);
        rootView.addView(frameView);
        actionBar = new ActionBar(this);
        rootView.addView(actionBar);
        ActionBarTheme config = getActionBarTheme();
        if (config != null) {
            actionBar.setActionConfig(config);
        }
        if (!isDefaultShowActionBar()) {
            actionBar.setVisibility(View.GONE);
        }
        setContentView(rootView);
        BlockFragment fragment = null;
        fragment = getFristFragment();
        if (fragment != null) {
            manager = new StackManager(this);
            setField(fragment, "isFrist", true);
            manager.setFragment(fragment);
        } else {
            actionBar.setVisibility(View.GONE);
            textView.setTextSize(14);
            textView.setGravity(Gravity.CENTER);
            textView.setText("Frist fragment can't is NULL\nby AndroidBlock");
            textView.setTextColor(0xff18a28b);
        }
        initViews(rootView);
        initFragmentAnims();
        hiddenKeyboard();
        onActivityCreate(savedInstanceState);
    }

    /**
     * initAnim
     */
    protected void initFragmentAnims() {
        int anim_next_in = com.liangmayong.androidblock.R.anim.anim_next_in;
        int anim_next_out = com.liangmayong.androidblock.R.anim.anim_next_out;
        int anim_quit_in = com.liangmayong.androidblock.R.anim.anim_quit_in;
        int anim_quit_out = com.liangmayong.androidblock.R.anim.anim_quit_out;
        setAnim(anim_next_in, anim_next_out, anim_quit_in, anim_quit_out);
    }

    @Override
    public ActionBarTheme getActionBarTheme() {
        return null;
    }

    @Override
    public ActionBarController getActionBarController() {
        if (actionBar == null) {
            return null;
        }
        return actionBar.getController();
    }

    @Override
    public ActionBar getBlockActionBar() {
        return actionBar;
    }

    /**
     * Set page switch animation
     *
     * @param nextIn  The next page to enter the animation
     * @param nextOut The next page out of the animation
     * @param quitIn  The current page into the animation
     * @param quitOut Exit animation for the current page
     */
    @Override
    public void setAnim(int nextIn, int nextOut, int quitIn, int quitOut) {
        if (manager != null) {
            manager.setAnim(nextIn, nextOut, quitIn, quitOut);
        }
    }

    /**
     * Rewriting onCreate method
     *
     * @param savedInstanceState savedInstanceState
     */
    @Override
    public void onActivityCreate(Bundle savedInstanceState) {
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (getVisibleFragment() != null) {
            boolean flag = getVisibleFragment().onTouchEvent(event);
            if (flag) {
                return true;
            } else {
                return super.onTouchEvent(event);
            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (getVisibleFragment() != null) {
            return getVisibleFragment().onKeyUp(keyCode, event);
        }
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public final boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                if (getVisibleFragment() != null) {
                    boolean flag = getVisibleFragment().onKeyDown(keyCode, event);
                    if (!flag) {
                        if (manager != null) {
                            manager.onBackPressed();
                        } else {
                            return super.onKeyDown(keyCode, event);
                        }
                    }
                } else {
                    if (manager != null) {
                        manager.onBackPressed();
                    } else {
                        return super.onKeyDown(keyCode, event);
                    }
                }
                return true;
            default:
                if (getVisibleFragment() != null) {
                    return getVisibleFragment().onKeyDown(keyCode, event);
                }
                break;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * get visible fragment
     *
     * @return visible fragment
     */
    public final BlockFragment getVisibleFragment() {
        if (manager != null) {
            return manager.getVisibleFragment();
        }
        return null;
    }

    private boolean setField(Object object, String fieldName, Object value) {
        Field field = null;
        Class<?> clazz = object.getClass();
        for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {
                field = clazz.getDeclaredField(fieldName);
            } catch (Exception e) {
            }
        }
        if (field != null) {
            field.setAccessible(true);
            try {
                field.set(object, value);
                return true;
            } catch (Exception e) {
            }
        }
        return false;
    }

    public Object getField(Object object, String fieldName) {
        Class<?> clazz = object.getClass();
        for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {
                Field field = clazz.getDeclaredField(fieldName);
                return field.get(object);
            } catch (Exception e) {
            }
        }
        return null;
    }

    /**
     * show toast
     *
     * @param text text
     */
    @Override
    public void showToast(final CharSequence text) {
        showToast(text, 1500);
    }

    /**
     * showToast
     *
     * @param text     text
     * @param duration duration
     */
    @Override
    public void showToast(final CharSequence text, int duration) {
        if (duration < 500) {
            duration = 500;
        }
        ToastUtils.showToast(this, text, duration);
    }

    private void hiddenKeyboard() {
        if (getRootView() == null) return;
        getRootView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager)
                        BlockActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        });
    }

}

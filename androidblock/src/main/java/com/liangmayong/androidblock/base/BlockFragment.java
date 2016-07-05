package com.liangmayong.androidblock.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SlidingPaneLayout.LayoutParams;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.liangmayong.androidblock.actionbar.ActionBar;
import com.liangmayong.androidblock.actionbar.ActionBarController;
import com.liangmayong.androidblock.base.interfaces.IBlockActivity;
import com.liangmayong.androidblock.base.presenter.Presenter;
import com.liangmayong.androidblock.utils.GestureUtils;
import com.liangmayong.androidblock.utils.GestureUtils.LGestureType;
import com.liangmayong.androidblock.utils.GestureUtils.OnGestureListener;

/**
 * BlockFragment
 *
 * @author LiangMaYong
 * @version 1.0
 */
public abstract class BlockFragment extends BaseFragment {

    private static long LAST_ACTION = 0;
    public static long LAST_DELAY = 500;

    public BlockFragment() {
    }

    private BlockFragment(Fragment fragment) {
        super(fragment);
    }

    /**
     * isFragmentVisible
     *
     * @return true or false
     */
    public boolean isFragmentVisible() {
        return isFragmentVisible;
    }

    private boolean isFrist = false;

    private GestureDetector gestureDetector;
    private OnGestureListener gestureListener;
    private RelativeLayout rootView;
    private View containerView;
    private IBlockActivity activity;
    private boolean isFragmentVisible = false;
    private boolean isFragmentClose = false;
    private Context context;

    /**
     * isFragmentClosed
     *
     * @return isFragmentClose
     */
    public boolean isFragmentClosed() {
        return isFragmentClose;
    }

    /**
     * getContext
     *
     * @return context
     */
    public Context getContext() {
        return context;
    }

    /**
     * isFrist
     *
     * @return isFrist
     */
    public boolean isFrist() {
        return isFrist;
    }

    /**
     * onTouchEvent
     *
     * @param event event
     * @return true or false
     */
    public boolean onTouchEvent(MotionEvent event) {
        if (gestureDetector != null) {
            return gestureDetector.onTouchEvent(event);
        }
        return false;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        gestureDetector = GestureUtils.getGestureDetector(getActivity(), new OnGestureListener() {
            @Override
            public void onTouch(LGestureType gestureType, int direction) {
                if (gestureListener != null) {
                    gestureListener.onTouch(gestureType, direction);
                }
            }
        });
    }

    /**
     * getRootView
     *
     * @return rootView
     */
    public RelativeLayout getRootView() {
        return rootView;
    }

    /**
     * setOnGestureListener
     *
     * @param listener listener
     */
    public void setOnGestureListener(OnGestureListener listener) {
        this.gestureListener = listener;
    }

    @Override
    public final View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.context = inflater.getContext();
        RelativeLayout layout = new RelativeLayout(inflater.getContext());
        layout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        FrameLayout frameLayout = new FrameLayout(inflater.getContext());
        frameLayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        layout.addView(frameLayout);
        containerView = getContaierView(inflater, frameLayout, savedInstanceState);
        getActionBarController().reset(isShowActionBar());
        initActionBar(getActionBarController());
        if (isShowActionBar() && isContaierViewAlignActionBar()) {
            frameLayout.setPadding(0, getActionBar().getConfig().getActionHeight(), 0, 0);
        }
        frameLayout.addView(containerView);
        isFragmentVisible = true;
        isFragmentClose = false;
        getBlockActivity().onFragmentCreateView(this, containerView);
        initViews(containerView, layout);
        return layout;
    }

    /**
     * initViews
     *
     * @param containerView containerView
     * @param rootView      rootView
     */
    protected abstract void initViews(View containerView, RelativeLayout rootView);

    /**
     * isShowActionBar
     *
     * @return true or false
     */
    public boolean isShowActionBar() {
        return getBlockActivity().isShowActionBar();
    }

    /**
     * isContaierViewAlignActionBar
     *
     * @return true or false
     */
    public boolean isContaierViewAlignActionBar() {
        return true;
    }

    /**
     * findViewById
     *
     * @param id id
     * @return View
     */
    public View findViewById(int id) {
        if (containerView != null) {
            return containerView.findViewById(id);
        }
        return null;
    }

    /**
     * get contaier view Id
     *
     * @return viewId
     */
    protected abstract int getContaierViewId();

    /**
     * get contaier View
     *
     * @param inflater           inflater
     * @param container          container
     * @param savedInstanceState savedInstanceState
     * @return View
     */
    protected View getContaierView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(getContaierViewId(), container, false);
    }

    /**
     * setResult
     *
     * @param resultCode resultCode
     * @param data       data
     */
    public void setResult(int resultCode, Bundle data) {
        getBlockActivity().getStackManager().setResult(resultCode, data);
    }

    /**
     * getResult
     *
     * @param resultCode resultCode
     * @return data
     */
    public Bundle getResult(int resultCode) {
        return getBlockActivity().getStackManager().getResult(resultCode);
    }

    /**
     * open a new Fragment
     *
     * @param fragment fragment
     */
    public void open(BlockFragment fragment) {
        if (System.currentTimeMillis() - LAST_ACTION < LAST_DELAY) {
            return;
        }
        LAST_ACTION = System.currentTimeMillis();
        getBlockActivity().getStackManager().addFragment(this, fragment, null);
    }

    /**
     * open fragment
     *
     * @param fragment fragment
     * @param bundle   bundle
     */
    protected void open(BlockFragment fragment, Bundle bundle) {
        if (System.currentTimeMillis() - LAST_ACTION < LAST_DELAY) {
            return;
        }
        LAST_ACTION = System.currentTimeMillis();
        getBlockActivity().getStackManager().addFragment(this, fragment, bundle);
    }

    /**
     * closeAndOpen fragment
     *
     * @param fragment fragment
     * @param bundle   bundle
     */
    protected void closeAndOpen(BlockFragment fragment, Bundle bundle) {
        if (System.currentTimeMillis() - LAST_ACTION < LAST_DELAY) {
            return;
        }
        LAST_ACTION = System.currentTimeMillis();
        getBlockActivity().getStackManager().addFragment(this, fragment, bundle);
        finish(this);
    }

    /**
     * open fragment
     *
     * @param fragment  fragment
     * @param bundle    bundle
     * @param stackMode stackMode
     */
    protected void open(BlockFragment fragment, Bundle bundle, int stackMode) {
        if (System.currentTimeMillis() - LAST_ACTION < LAST_DELAY) {
            return;
        }
        LAST_ACTION = System.currentTimeMillis();
        getBlockActivity().getStackManager().addFragment(this, fragment, bundle, stackMode);
    }

    /**
     * closeAndOpen fragment
     *
     * @param fragment  fragment
     * @param bundle    bundle
     * @param stackMode stackMode
     */
    protected void closeAndOpen(BlockFragment fragment, Bundle bundle, int stackMode) {
        if (System.currentTimeMillis() - LAST_ACTION < LAST_DELAY) {
            return;
        }
        LAST_ACTION = System.currentTimeMillis();
        getBlockActivity().getStackManager().addFragment(this, fragment, bundle, stackMode);
        finish(this);
    }

    /**
     * Jump to the specified fragment and do not hide the current page.
     *
     * @param to To jump to the page
     */
    public void dialogFragment(Fragment to) {
        if (System.currentTimeMillis() - LAST_ACTION < LAST_DELAY) {
            return;
        }
        LAST_ACTION = System.currentTimeMillis();
        getBlockActivity().getStackManager().dialogFragment(to);
    }

    /**
     * Set the animation to add fragment in dialog mode
     *
     * @param dialog_in  The next page to enter the animation
     * @param dialog_out The next page out of the animation
     */
    public void setDialogAnim(int dialog_in, int dialog_out) {
        getBlockActivity().getStackManager().setDialogAnim(dialog_in, dialog_out);
    }

    /**
     * close this current Fragment
     */
    protected void close() {
        if (System.currentTimeMillis() - LAST_ACTION < LAST_DELAY) {
            return;
        }
        LAST_ACTION = System.currentTimeMillis();
        getBlockActivity().getStackManager().close(this, true);
        isFragmentClose = true;
    }

    /**
     * Closes the specified fragment
     *
     * @param fragment the specified fragment
     */
    protected void finish(BlockFragment fragment) {
        if (System.currentTimeMillis() - LAST_ACTION < LAST_DELAY) {
            return;
        }
        LAST_ACTION = System.currentTimeMillis();
        getBlockActivity().getStackManager().close(fragment, false);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    /**
     * Get fragment dependent Activity, many times this is very useful
     *
     * @return LFragmentActivity dependent Activity
     */
    public IBlockActivity getBlockActivity() {
        if (activity == null) {
            if (getActivity() instanceof IBlockActivity) {
                activity = (IBlockActivity) getActivity();
            } else {
                throw new ClassCastException("this activity must be interface IBlockActivity");
            }
        }
        return activity;
    }

    /**
     * Override this method in order to facilitate the singleTop mode to be
     * called in
     */
    @Override
    public void onNewIntent() {
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            boolean flag = onBackPressed();
            if (flag) {
                return flag;
            }
        }
        return false;
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        return false;
    }

    @Override
    public boolean onBackPressed() {
        if (System.currentTimeMillis() - LAST_ACTION < LAST_DELAY) {
            return true;
        }
        LAST_ACTION = System.currentTimeMillis();
        return false;
    }

    /**
     * show toast
     *
     * @param text text
     */
    public void showToast(final String text) {
        getBlockActivity().showToast(text);
    }

    /**
     * showToast
     *
     * @param text     text
     * @param duration duration
     */
    public void showToast(final String text, int duration) {
        getBlockActivity().showToast(text, duration);
    }

    /**
     * get action bar controller
     *
     * @return action bar controller
     */
    public ActionBarController getActionBarController() {
        return getBlockActivity().getActionBarController();
    }

    @Override
    public void onNextShow() {
        super.onNextShow();
        isFragmentVisible = true;
        getActionBarController().reset(isShowActionBar());
        initActionBar(getActionBarController());
    }

    @Override
    public void onNowHidden() {
        isFragmentVisible = false;
        super.onNowHidden();
    }

    /**
     * get block action bar
     *
     * @return action bar
     */
    public ActionBar getActionBar() {
        return getBlockActivity().getBlockActionBar();
    }

    /**
     * createByFragment
     *
     * @param fragment fragment
     * @return BlockFragment
     */
    public final static BlockFragment createByFragment(final Fragment fragment) {
        BlockFragment moduleFragment = new BlockFragment(fragment) {

            @Override
            protected View getContaierView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
                return fragment.onCreateView(inflater, container, savedInstanceState);
            }

            @Override
            protected int getContaierViewId() {
                return 0;
            }

            @Override
            protected void initActionBar(ActionBarController actionController) {

            }

            @Override
            protected void initViews(View containerView, RelativeLayout rootView) {

            }

        };
        return moduleFragment;
    }

    /**
     * initActionBar
     *
     * @param actionBarController actionBarController
     */
    protected abstract void initActionBar(ActionBarController actionBarController);

    private Presenter<? extends BlockFragment> presenter;

    @Override
    public void onClosed() {
        super.onClosed();
        if (presenter != null) {
            presenter.dettach();
        }
    }

    /**
     * getPresenter
     *
     * @return presenter
     */
    public Presenter<? extends BlockFragment> getPresenter() {
        return presenter;
    }

    /**
     * setPersenter
     *
     * @param presenter presenter
     */
    protected void setPersenter(Presenter<? extends BlockFragment> presenter) {
        if (presenter == null) {
            return;
        }
        if (this.presenter != null && this.presenter.equals(presenter)) {
            return;
        }
        this.presenter = presenter;
    }

}

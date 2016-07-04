package com.liangmayong.androidblock.actionbar;

import com.liangmayong.androidblock.actionbar.ActionBar.AMActionProgressBar;
import com.liangmayong.androidblock.iconfont.IconValue;

import android.annotation.TargetApi;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * ActionController
 *
 * @author LiangMaYong
 * @version 1.0
 */
public class ActionBarController {

    private ActionItem left0, left1, left2, left3;
    private ActionItem right0, right1, right2, right3;
    private ActionCenter title;
    private ActionCenter subTitle;
    private ActionBar actionBar;
    private RelativeLayout head;
    private AMActionProgressBar progressBar;
    private View lineView;

    public ActionBarController(ActionBar actionBar) {
        this.actionBar = actionBar;
        this.left0 = new ActionItem((ActionView) actionBar.findViewWithTag("L0"));
        this.left1 = new ActionItem((ActionView) actionBar.findViewWithTag("L1"));
        this.left2 = new ActionItem((ActionView) actionBar.findViewWithTag("L2"));
        this.left3 = new ActionItem((ActionView) actionBar.findViewWithTag("L3"));
        this.right0 = new ActionItem((ActionView) actionBar.findViewWithTag("R0"));
        this.right1 = new ActionItem((ActionView) actionBar.findViewWithTag("R1"));
        this.right2 = new ActionItem((ActionView) actionBar.findViewWithTag("R2"));
        this.right3 = new ActionItem((ActionView) actionBar.findViewWithTag("R3"));
        this.title = new ActionCenter((TextView) actionBar.findViewWithTag("C0"));
        this.subTitle = new ActionCenter((TextView) actionBar.findViewWithTag("C1"));
        this.progressBar = (AMActionProgressBar) actionBar.findViewWithTag("P");
        this.head = (RelativeLayout) actionBar.findViewWithTag("HEAD");
        this.lineView = actionBar.findViewWithTag("LINE");
    }

    public ActionBarController hideBackgroundColor() {
        actionBar.setBackgroundColor(0x00ffffff);
        hideLine();
        return this;
    }

    public ActionBarController setBackgroundColor(int color) {
        actionBar.setBackgroundColor(color);
        return this;
    }

    public ActionBarController hideLine() {
        lineView.setVisibility(View.GONE);
        return this;
    }

    /**
     * reset
     *
     * @param show show
     */
    public void reset(boolean show) {
        if (show && actionBar.getConfig().isShow()) {
            show();
        } else {
            hide();
        }
        showLine();
        progressBar().setProgress(0);
        progressBar().setVisibility(View.GONE);
        actionBar.setBackgroundColor(actionBar.getConfig().getBackgroundColor());
        left0.clicked(null);
        left1.clicked(null);
        left2.clicked(null);
        left3.clicked(null);
        right0.clicked(null);
        right1.clicked(null);
        right2.clicked(null);
        right3.clicked(null);
        left0.text("").iconToLeft(null).textSize(actionBar.getConfig().getActionItemSize()).enabled(false);
        left1.text("").iconToLeft(null).textSize(actionBar.getConfig().getActionItemSize()).enabled(false);
        left2.text("").iconToLeft(null).textSize(actionBar.getConfig().getActionItemSize()).enabled(false);
        left3.text("").iconToLeft(null).textSize(actionBar.getConfig().getActionItemSize()).enabled(false);
        right0.text("").iconToLeft(null).textSize(actionBar.getConfig().getActionItemSize()).enabled(false);
        right1.text("").iconToLeft(null).textSize(actionBar.getConfig().getActionItemSize()).enabled(false);
        right2.text("").iconToLeft(null).textSize(actionBar.getConfig().getActionItemSize()).enabled(false);
        right3.text("").iconToLeft(null).textSize(actionBar.getConfig().getActionItemSize()).enabled(false);
        actionBar.goneAllView();
    }

    public ActionBarController showLine() {
        lineView.setVisibility(View.VISIBLE);
        return this;
    }

    /**
     * get custom View
     *
     * @return view
     */
    public RelativeLayout getCustomView() {
        return head;
    }

    /**
     * progressBar
     *
     * @return progressBar
     */
    public AMActionProgressBar progressBar() {
        progressBar.setVisibility(View.VISIBLE);
        return progressBar;
    }

    /**
     * view visible
     */
    public void show() {
        if (actionBar != null) {
            actionBar.show();
        }
    }

    /**
     * view hide
     */
    public void hide() {
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    /**
     * view hide
     */
    public void hideViews() {
        if (actionBar != null) {
            actionBar.goneAllView();
        }
    }

    /**
     * left bar
     *
     * @return icon bar
     */
    public ActionItem left() {
        left0.visible();
        return left0;
    }

    /**
     * left two bar
     *
     * @return icon bar
     */
    public ActionItem leftTwo() {
        left1.visible();
        return left1;
    }

    /**
     * left three bar
     *
     * @return icon bar
     */
    public ActionItem leftThree() {
        left2.visible();
        return left2;
    }

    /**
     * left four bar
     *
     * @return icon bar
     */
    public ActionItem leftFour() {
        left3.visible();
        return left3;
    }

    /**
     * right bar
     *
     * @return icon bar
     */
    public ActionItem right() {
        right0.visible();
        return right0;
    }

    /**
     * right two bar
     *
     * @return icon bar
     */
    public ActionItem rightTwo() {
        right1.visible();
        return right1;
    }

    /**
     * right three bar
     *
     * @return icon bar
     */
    public ActionItem rightThree() {
        right2.visible();
        return right2;
    }

    /**
     * right four bar
     *
     * @return icon bar
     */
    public ActionItem rightFour() {
        right3.visible();
        return right3;
    }

    /**
     * center bar
     *
     * @return icon bar
     */
    public ActionCenter title() {
        title.visible();
        return title;
    }

    /**
     * small center bar
     *
     * @return icon bar
     */
    public ActionCenter subTitle() {
        subTitle.visible();
        return subTitle;
    }

    public class ActionCenter {
        private TextView actionView;

        /**
         * init
         *
         * @param actionView actionView
         */
        private ActionCenter(TextView actionView) {
            this.actionView = actionView;
        }

        public TextView getActionView() {
            return actionView;
        }

        public int getId() {
            return actionView.getId();
        }

        public ActionCenter textColor(int color, int pressed) {
            int statePressed = android.R.attr.state_pressed;
            int stateFocesed = android.R.attr.state_focused;
            int[][] state = {{statePressed}, {-statePressed}, {stateFocesed}, {-stateFocesed}};
            ColorStateList colors = new ColorStateList(state, new int[]{pressed, color, pressed, color});
            getActionView().setTextColor(colors);
            return this;
        }

        /**
         * setAlpha
         *
         * @param alpha alpha
         * @return ActionCenter
         */
        @TargetApi(Build.VERSION_CODES.HONEYCOMB)
        public ActionCenter setAlpha(float alpha) {
            this.getActionView().setAlpha(alpha);
            return this;
        }

        /**
         * set textColor
         *
         * @param color color
         * @return ActionCenter
         */
        public ActionCenter textColor(int color) {
            this.getActionView().setTextColor(color);
            return this;
        }

        /**
         * set textSize
         *
         * @param size size
         * @return ActionCenter
         */
        public ActionCenter textSize(float size) {
            this.getActionView().setTextSize(size);
            return this;
        }

        /**
         * icon bar visible
         *
         * @return ActionCenter
         */
        public ActionCenter visible() {
            if (actionView != null) {
                actionView.setVisibility(View.VISIBLE);
            }
            return this;
        }

        /**
         * icon bar gone
         *
         * @return ActionCenter
         */
        public ActionCenter gone() {
            if (actionView != null) {
                actionView.setVisibility(View.GONE);
            }
            return this;
        }

        /**
         * set text
         *
         * @param text text
         * @return ActionCenter
         */
        public ActionCenter text(CharSequence text) {
            this.getActionView().setText(text, null);
            return this;
        }

        /**
         * set enabled
         *
         * @param enabled enabled
         * @return ActionCenter
         */
        public ActionCenter enabled(boolean enabled) {
            getActionView().setEnabled(enabled);
            return this;
        }

        /**
         * set clicked
         *
         * @param clickListener clickListener
         * @return ActionCenter
         */
        public ActionCenter clicked(OnClickListener clickListener) {
            getActionView().setOnClickListener(clickListener);
            enabled(true);
            return this;
        }

        /**
         * longClicked
         *
         * @param longClickListener longClickListener
         * @return ActionCenter
         */
        public ActionCenter longClicked(OnLongClickListener longClickListener) {
            getActionView().setOnLongClickListener(longClickListener);
            enabled(true);
            return this;
        }

        /**
         * IBActionButton VIEW
         *
         * @return IBActionButton
         */
        public TextView getView() {
            return getActionView();
        }

        /**
         * setPaddingLeft
         *
         * @param left left
         * @return ActionItem
         */
        public ActionCenter setPaddingLeft(int left) {
            getActionView().setPadding(left, getActionView().getPaddingTop(), getActionView().getPaddingRight(),
                    getActionView().getPaddingBottom());
            return this;
        }

        /**
         * setPaddingLeftDip
         *
         * @param left left
         * @return ActionItem
         */
        public ActionCenter setPaddingLeftDip(int left) {
            getActionView().setPadding(dip2Px(left), getActionView().getPaddingTop(), getActionView().getPaddingRight(),
                    getActionView().getPaddingBottom());
            return this;
        }

        /**
         * setPaddingRight
         *
         * @param right right
         * @return ActionItem
         */
        public ActionCenter setPaddingRight(int right) {
            getActionView().setPadding(getActionView().getPaddingLeft(), getActionView().getPaddingTop(), right,
                    getActionView().getPaddingBottom());
            return this;
        }

        /**
         * setPaddingRightDip
         *
         * @param right right
         * @return ActionItem
         */
        public ActionCenter setPaddingRightDip(int right) {
            getActionView().setPadding(getActionView().getPaddingLeft(), getActionView().getPaddingTop(), dip2Px(right),
                    getActionView().getPaddingBottom());
            return this;
        }

        /**
         * drawable
         *
         * @param leftdrawable  leftdrawable
         * @param rightdrawable rightdrawable
         * @return ActionCenter
         */
        public ActionCenter drawable(Drawable leftdrawable, Drawable rightdrawable) {
            if (leftdrawable != null) {
                leftdrawable.setBounds(0, 0, leftdrawable.getMinimumWidth(), leftdrawable.getMinimumHeight());
            }
            if (rightdrawable != null) {
                rightdrawable.setBounds(0, 0, rightdrawable.getMinimumWidth(), rightdrawable.getMinimumHeight());
            }
            getActionView().setCompoundDrawables(leftdrawable, rightdrawable, null, null);
            return this;
        }

        /**
         * dip2Px
         *
         * @param dip dip
         * @return px
         */
        private int dip2Px(float dip) {
            return (int) (dip * getActionView().getContext().getResources().getDisplayMetrics().density + 0.5f);
        }
    }

    /**
     * icon bar
     *
     * @author LiangMaYong
     * @version 1.0
     */
    public class ActionItem {
        private ActionView actionView;

        public int getId() {
            return actionView.getId();
        }

        /**
         * init
         *
         * @param actionView actionView
         */
        private ActionItem(ActionView actionView) {
            this.actionView = actionView;
            mHiddenAction = new AlphaAnimation(1.0f, 0.1f);
            mHiddenAction.setDuration(hide_time);
            mShowAction = new AlphaAnimation(0.1f, 1.0f);
            mShowAction.setDuration(show_time);
        }

        private Animation mShowAction, mHiddenAction;
        private int hide_time = 200;
        private int show_time = 200;

        private void show(final Runnable runnable) {
            if (this.actionView.getVisibility() == View.GONE) {
                if (mShowAction != null) {
                    this.actionView.startAnimation(mShowAction);
                }
                handler.postDelayed(new Runnable() {
                    public void run() {
                        actionView.setVisibility(View.VISIBLE);
                        if (runnable != null) {
                            runnable.run();
                        }
                    }
                }, show_time);
            } else {
                if (runnable != null) {
                    runnable.run();
                }
            }
        }

        private Handler handler = new Handler();

        /**
         * hide
         *
         * @param runnable runnable
         */
        private void hide(final Runnable runnable) {
            if (actionView.getVisibility() == View.VISIBLE) {
                if (mHiddenAction != null) {
                    actionView.startAnimation(mHiddenAction);
                }
                handler.postDelayed(new Runnable() {
                    public void run() {
                        actionView.setVisibility(View.GONE);
                        if (runnable != null) {
                            runnable.run();
                        }
                    }
                }, hide_time);
            } else {
                if (runnable != null) {
                    runnable.run();
                }
            }
        }

        /**
         * textColor
         *
         * @param color   color
         * @param pressed pressed
         * @return ActionItem
         */
        public ActionItem textColor(int color, int pressed) {
            int statePressed = android.R.attr.state_pressed;
            int stateFocesed = android.R.attr.state_focused;
            int[][] state = {{statePressed}, {-statePressed}, {stateFocesed}, {-stateFocesed}};
            ColorStateList colors = new ColorStateList(state, new int[]{pressed, color, pressed, color});
            getActionView().setTextColor(colors);
            return this;
        }

        /**
         * set textColor
         *
         * @param color color
         * @return ActionItem
         */
        public ActionItem textColor(int color) {
            this.getActionView().setTextColor(color);
            return this;
        }

        /**
         * set textSize
         *
         * @param size size
         * @return ActionItem
         */
        public ActionItem textSize(float size) {
            this.getActionView().setTextSize(size);
            return this;
        }

        /**
         * set textSize
         *
         * @param size size
         * @return ActionItem
         */
        public ActionItem iconSize(float size) {
            this.getActionView().setIconSize(size);
            return this;
        }

        /**
         * set iconLeft
         *
         * @param left left
         * @return ActionItem
         */
        public ActionItem iconToLeft(IconValue left) {
            this.getActionView().setIcon(this.getActionView().getText(), left, ActionView.ICON_LEFT);
            return this;
        }

        /**
         * set iconRight
         *
         * @param right right
         * @return ActionItem
         */
        public ActionItem iconToRight(IconValue right) {
            this.getActionView().setIcon(this.getActionView().getText(), right, ActionView.ICON_RIGHT);
            return this;
        }

        private ActionView getActionView() {
            return actionView;
        }

        /**
         * icon bar visible
         *
         * @return ActionItem
         */
        public ActionItem visible() {
            visible(true);
            return this;
        }

        /**
         * visible
         *
         * @param anim anim
         * @return ActionItem
         */
        public ActionItem visible(boolean anim) {
            if (anim) {
                if (actionView != null) {
                    show(null);
                }
            } else {
                if (actionView != null) {
                    actionView.setVisibility(View.VISIBLE);
                }
            }
            return this;
        }

        /**
         * icon bar gone
         *
         * @return icon bar
         */
        public ActionItem gone() {
            gone(true);
            return this;
        }

        /**
         * gone
         *
         * @param anim anim
         * @return ActionItem
         */
        public ActionItem gone(boolean anim) {
            if (anim) {
                if (actionView != null) {
                    hide(null);
                }
            } else {
                if (actionView != null) {
                    actionView.setVisibility(View.GONE);
                }
            }
            return this;
        }

        /**
         * text
         *
         * @param text text
         * @return ActionItem
         */
        public ActionItem text(CharSequence text) {
            this.getActionView().setText(text, null);
            return this;
        }

        /**
         * set enabled
         *
         * @param enabled enabled
         * @return icon bar
         */
        public ActionItem enabled(boolean enabled) {
            getActionView().setEnabled(enabled);
            return this;
        }

        /**
         * set clicked
         *
         * @param clickListener clickListener
         * @return icon bar
         */
        public ActionItem clicked(OnClickListener clickListener) {
            getActionView().setOnClickListener(clickListener);
            enabled(true);
            return this;
        }

        /**
         * set clicked
         *
         * @param longClickListener longClickListener
         * @return icon bar
         */
        public ActionItem longClicked(OnLongClickListener longClickListener) {
            getActionView().setOnLongClickListener(longClickListener);
            enabled(true);
            return this;
        }

        /**
         * IBActionButton VIEW
         *
         * @return IBActionButton
         */
        public ActionView getView() {
            return getActionView();
        }

        /**
         * setPaddingLeft
         *
         * @param left left
         * @return ActionItem
         */
        public ActionItem setPaddingLeft(int left) {
            getActionView().setPadding(left, getActionView().getPaddingTop(), getActionView().getPaddingRight(),
                    getActionView().getPaddingBottom());
            return this;
        }

        /**
         * setPaddingLeftDip
         *
         * @param left left
         * @return ActionItem
         */
        public ActionItem setPaddingLeftDip(int left) {
            getActionView().setPadding(dip2Px(left), getActionView().getPaddingTop(), getActionView().getPaddingRight(),
                    getActionView().getPaddingBottom());
            return this;
        }

        /**
         * setPaddingRight
         *
         * @param right right
         * @return ActionItem
         */
        public ActionItem setPaddingRight(int right) {
            getActionView().setPadding(getActionView().getPaddingLeft(), getActionView().getPaddingTop(), right,
                    getActionView().getPaddingBottom());
            return this;
        }

        /**
         * setPaddingRightDip
         *
         * @param right right
         * @return ActionItem
         */
        public ActionItem setPaddingRightDip(int right) {
            getActionView().setPadding(getActionView().getPaddingLeft(), getActionView().getPaddingTop(), dip2Px(right),
                    getActionView().getPaddingBottom());
            return this;
        }

        /**
         * stop animation
         *
         * @return icon bar
         */
        public ActionItem stopAnimation() {
            if (getActionView() != null) {
                getActionView().stopAnimation();
            }
            return this;
        }

        /**
         * start animation
         *
         * @param animaRes animation res id
         * @return icon bar
         */
        public ActionItem startAnimation(int animaRes) {
            if (getActionView() != null) {
                getActionView().startAnimation(animaRes);
            }
            return this;
        }

        /**
         * set drawable
         *
         * @param leftdrawable  leftdrawable
         * @param rightdrawable rightdrawable
         * @return icon bar
         */
        public ActionItem drawable(Drawable leftdrawable, Drawable rightdrawable) {
            if (leftdrawable != null) {
                leftdrawable.setBounds(0, 0, leftdrawable.getMinimumWidth(), leftdrawable.getMinimumHeight());
            }
            if (rightdrawable != null) {
                rightdrawable.setBounds(0, 0, rightdrawable.getMinimumWidth(), rightdrawable.getMinimumHeight());
            }
            getActionView().setCompoundDrawables(leftdrawable, rightdrawable, null, null);
            return this;
        }

        /*
         * converts dip to px
         */
        private int dip2Px(float dip) {
            return (int) (dip * getActionView().getContext().getResources().getDisplayMetrics().density + 0.5f);
        }
    }

}

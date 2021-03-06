package com.liangmayong.androidblock.base.stack;

import java.util.ArrayList;

import com.liangmayong.androidblock.base.BlockFragment;
import com.liangmayong.androidblock.base.interfaces.ICloseFragment;

import android.support.v4.app.Fragment;

/**
 * FragmentStack
 *
 * @author LiangMaYong
 * @version 1.0
 */
public class FragmentStack {
    private ArrayList<ArrayList<BlockFragment>> stackList = new ArrayList<ArrayList<BlockFragment>>();
    private ICloseFragment listener;

    public FragmentStack() {
        ArrayList<BlockFragment> stack = new ArrayList<BlockFragment>();
        stackList.add(stack);
    }

    /**
     * standard mode,Directly add to the current task stack
     *
     * @param fragment Added fragment
     */
    public void putStandard(BlockFragment fragment) {
        synchronized (stackList) {
            stackList.get(stackList.size() - 1).add(fragment);
        }
    }

    /**
     * SingleTop mode ,If the top is not created
     *
     * @param fragment Added fragment
     * @return Whether to contain the current instance
     */
    public boolean putSingleTop(BlockFragment fragment) {
        synchronized (stackList) {
            ArrayList<BlockFragment> lastList = stackList.get(stackList.size() - 1);
            if (lastList.isEmpty()) {
                lastList.add(fragment);
                return false;
            } else {
                BlockFragment last = lastList.get(lastList.size() - 1);
                if (last.getClass().getName().equals(fragment.getClass().getName())) {
                    fragment.onNewIntent();
                    return true;
                } else {
                    lastList.add(fragment);
                    return false;
                }
            }
        }
    }

    /**
     * singTask mode ,If the current task stack does not create and empty all of
     * the upper instance
     *
     * @param fragment Added fragment
     * @return Whether to contain the current instance
     */
    public boolean putSingleTask(BlockFragment fragment) {
        synchronized (stackList) {
            boolean isClear = false;
            ArrayList<BlockFragment> lastList = stackList.get(stackList.size() - 1);
            if (lastList.isEmpty()) {
                lastList.add(fragment);
            } else {
                int tempIndex = 0;
                for (int x = 0; x <= lastList.size() - 1; x++) {
                    if (lastList.get(x).getClass().getName().equals(fragment.getClass().getName())) {
                        // clear all instance
                        isClear = true;
                        tempIndex = x;
                        break;
                    }
                }
                if (!isClear) {
                    lastList.add(fragment);
                } else {
                    if (listener != null) {
                        listener.show(lastList.get(tempIndex));
                        StackManager.isFirstClose = true;
                        for (int i = lastList.size() - 1; i > tempIndex; i--) {
                            listener.close(lastList.get(i), false);
                        }
                        for (int j = lastList.size() - 1; j > tempIndex; j--) {
                            lastList.remove(j);
                        }
                    }

                }
            }
            return isClear;
        }
    }

    /**
     * singleInstance mode,Create a new task stack at a time.
     *
     * @param fragment fragment
     */
    public void putSingleInstance(BlockFragment fragment) {
        synchronized (stackList) {
            ArrayList<BlockFragment> frags = new ArrayList<BlockFragment>();
            frags.add(fragment);
            stackList.add(frags);
        }
    }

    public void closeFragment(BlockFragment fragment) {
        synchronized (FragmentStack.this) {
            int i = stackList.size() - 1;
            if (i >= 0) {
                ArrayList<BlockFragment> lastStack = stackList.get(i);
                if (lastStack != null && (!lastStack.isEmpty())) {
                    lastStack.remove(fragment);
                    if (lastStack.isEmpty()) {
                        stackList.remove(lastStack);
                    }
                } else {
                    stackList.remove(lastStack);
                }
            } else {
                stackList.clear();
            }
        }
    }

    public void onBackPressed() {
        synchronized (stackList) {
            int i = stackList.size() - 1;
            if (i >= 0) {
                ArrayList<BlockFragment> lastStack = stackList.get(i);
                if (lastStack != null && (!lastStack.isEmpty())) {
                    lastStack.remove(lastStack.size() - 1);
                    if (lastStack.isEmpty()) {
                        stackList.remove(lastStack);
                    }
                } else {
                    stackList.remove(lastStack);
                }
            } else {
                stackList.clear();
            }
        }
    }

    protected void setCloseFragmentListener(ICloseFragment listener) {
        this.listener = listener;
    }

    protected Fragment[] getLast() {
        synchronized (stackList) {
            Fragment[] fagArr = new Fragment[2];
            boolean hasFirst = false;
            for (int x = stackList.size() - 1; x >= 0; x--) {
                ArrayList<BlockFragment> list = stackList.get(x);
                if (list != null && (!list.isEmpty())) {
                    if (hasFirst) {
                        fagArr[1] = list.get(list.size() - 1);
                        break;
                    } else {
                        hasFirst = true;
                        fagArr[0] = list.get(list.size() - 1);
                        if (list.size() > 1) {
                            fagArr[1] = list.get(list.size() - 2);
                        }
                    }

                }
            }
            return fagArr;
        }
    }
}

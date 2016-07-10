package com.liangmayong.androidblock.base.presenter;

import android.os.Bundle;

/**
 * Presenter
 *
 * @param <V> v
 * @author LiangMaYong
 * @version 1.0
 */
public abstract class Presenter<V> {

    private V view;

    public V getV() {
        return view;
    }

    public Presenter(V view) {
        this.view = view;
        this.attach();
    }

    /**
     * attach
     */
    public void attach() {
       // attach();
    }

    /**
     * dettach
     */
    public void dettach() {
        this.view = null;
    }

    /**
     * handleMessage
     *
     * @param what   what
     * @param bundle bundle
     * @param object object
     */
    public void handleMessage(int what, Bundle bundle, Object object) {

    }

}

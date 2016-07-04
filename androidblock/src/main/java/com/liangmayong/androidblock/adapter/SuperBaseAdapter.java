package com.liangmayong.androidblock.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * SuperBaseAdapter
 *
 * @param <T> t
 * @author LiangMaYong
 * @version 1.0
 */
public abstract class SuperBaseAdapter<T> extends BaseAdapter {

    private final List<T> datas;
    private Context context;

    /**
     * SuperAdapter
     *
     * @param context context
     * @param datas   datas
     */
    public SuperBaseAdapter(Context context, List<T> datas) {
        this.datas = datas;
        this.context = context;
    }

    /**
     * addItem
     *
     * @param item item
     */
    public void addItem(T item) {
        datas.add(item);
        notifyDataSetChanged();
    }

    /**
     * addAll
     *
     * @param items items
     */
    public void addAll(List<T> items) {
        datas.addAll(items);
        notifyDataSetChanged();
    }

    /**
     * removeItem
     *
     * @param item item
     */
    public void removeItem(T item) {
        if (datas.contains(item)) {
            datas.remove(item);
            notifyDataSetChanged();
        }
    }

    /**
     * replaceDatas
     *
     * @param datas datas
     */
    public void replaceDatas(List<T> datas) {
        this.datas.removeAll(this.datas);
        if (datas != null) {
            this.datas.addAll(datas);
        }
        notifyDataSetChanged();
    }

    /**
     * removeAll
     */
    public void removeAll() {
        this.datas.removeAll(this.datas);
        notifyDataSetChanged();
    }

    /**
     * removeItem
     *
     * @param location location
     */
    public void removeItem(int location) {
        if (location >= 0 && this.datas.size() > location) {
            this.datas.remove(location);
            notifyDataSetChanged();
        }
    }

    @Override
    public int getCount() {
        if (this.datas == null) {
            return 0;
        }
        return this.datas.size();
    }

    @Override
    public T getItem(int position) {
        if (this.datas == null) {
            return null;
        }
        return this.datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public final View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = newView(context, position, parent);
        }
        bindView(position, convertView, getItem(position));
        return convertView;
    }

    /**
     * new view
     *
     * @param context  context
     * @param position position
     * @param parent   parent
     * @return view
     */
    protected abstract View newView(Context context, int position, ViewGroup parent);

    /**
     * bind view
     *
     * @param position    position
     * @param convertView convertView
     * @param item        item
     */
    protected abstract void bindView(int position, View convertView, T item);

}

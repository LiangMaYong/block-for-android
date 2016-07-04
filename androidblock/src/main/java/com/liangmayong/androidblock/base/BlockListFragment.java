package com.liangmayong.androidblock.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup.LayoutParams;
import android.widget.RelativeLayout;

/**
 * BlockListFragment
 * 
 * @author LiangMaYong
 * @version 1.0
 */
public abstract class BlockListFragment extends BlockFragment {

	@Override
	protected final void initViews(View view, RelativeLayout rootView) {
		initListView((ListView) view, rootView);
	}

	private int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	protected abstract void initListView(ListView listView, RelativeLayout rootView);

	@Override
	protected final View getContaierView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		ListView listView = new ListView(getContext());
		listView.setDividerHeight(dip2px(getContext(), 1));
		listView.setSelector(android.R.color.transparent);
		container.setPadding(0, getActionBar().getConfig().getActionHeight(), 0, 0);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
		listView.setLayoutParams(params);
		return listView;
	}

	@Override
	protected final int getContaierViewId() {
		return 0;
	}

}

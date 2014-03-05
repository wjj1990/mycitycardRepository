package com.mcc.mycitycard.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;

/**
 * 导航栏 适配器
 * 
 * @author wjj
 */
public class TabSelectAdapter extends FragmentPagerAdapter implements
		OnPageChangeListener, OnTabChangeListener {

	private FragmentActivity mContext;
	private TabHost mTabHost;
	private ViewPager mViewPager;
	private RadioGroup mRadioGroup;

	private int mOneDistance;
	private ImageView mImage;
	private int mCurrIndex;
	private int mOffset;

	private ArrayList<TabInfo> mTabInfos = new ArrayList<TabSelectAdapter.TabInfo>();

	public TabSelectAdapter(FragmentActivity mContext, TabHost mTabHost,
			ViewPager mViewPager, RadioGroup mRadioGroup) {
		super(mContext.getSupportFragmentManager());
		this.mContext = mContext;
		this.mTabHost = mTabHost;
		this.mViewPager = mViewPager;
		this.mRadioGroup = mRadioGroup;

		this.mTabHost.setOnTabChangedListener(this);
		this.mViewPager.setOnPageChangeListener(this);
		this.mViewPager.setAdapter(this);
	}

	/**
	 * 添加导航项
	 * 
	 * @param spec
	 * @param clss
	 * @param args
	 */
	public void addTab(TabHost.TabSpec spec, Class<?> clss, Bundle args) {

		spec.setContent(new DummyTabFactory(mContext));
		String tag = spec.getTag();

		TabInfo info = new TabInfo(tag, clss, args);
		mTabInfos.add(info);
		mTabHost.addTab(spec);

		notifyDataSetChanged();
	}

	public void setOneDistance(int distance) {
		this.mOneDistance = distance;
	}

	public void setImageView(ImageView image) {
		this.mImage = image;
	}

	public void setOffSet(int offset) {
		this.mOffset = offset;
	}

	final class TabInfo {
		private final String tag;
		private final Class<?> clss;
		private final Bundle args;

		public TabInfo(String tag, Class<?> clss, Bundle args) {
			this.tag = tag;
			this.clss = clss;
			this.args = args;
		}
	}

	class DummyTabFactory implements TabHost.TabContentFactory {
		private final Context mContext;

		public DummyTabFactory(Context mContext) {
			this.mContext = mContext;
		}

		@Override
		public View createTabContent(String tag) {

			View view = new View(mContext);
			view.setMinimumHeight(0);
			view.setMinimumWidth(0);
			return view;
		}

	}

	@Override
	public Fragment getItem(int arg0) {

		TabInfo tabInfo = mTabInfos.get(arg0);
		return Fragment.instantiate(mContext, tabInfo.clss.getName(),
				tabInfo.args);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mTabInfos.size();
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
	}

	@Override
	public void onPageSelected(int arg0) {

		Animation anim = null;


		switch (arg0) {
		case 0:
			if (1 == mCurrIndex)
				anim = new TranslateAnimation(mOneDistance, 0, 0, 0);
			else if (2 == mCurrIndex)
				anim = new TranslateAnimation(mOneDistance * 2, 0, 0, 0);

			break;
		case 1:
			if (0 == mCurrIndex)
				anim = new TranslateAnimation(mOffset, mOneDistance, 0, 0);
			else if (2 == mCurrIndex)
				anim = new TranslateAnimation(mOneDistance * 2, mOneDistance,
						0, 0);

			break;
		case 2:
			if (0 == mCurrIndex)
				anim = new TranslateAnimation(mOffset, mOneDistance * 2, 0, 0);
			else if (1 == mCurrIndex)
				anim = new TranslateAnimation(mOneDistance, mOneDistance * 2,
						0, 0);
			break;
		}

		mCurrIndex = arg0;
		if (anim!=null) {
			anim.setFillAfter(true);// True:图片停在动画结束位置
			anim.setDuration(300);
			mImage.startAnimation(anim);
			
		}


		for (int i = 0; i < mRadioGroup.getChildCount(); i++) {
			RadioButton radioBtn = (RadioButton) mRadioGroup.getChildAt(i);
			if (i == arg0)
				radioBtn.setChecked(true);
			else
				radioBtn.setChecked(false);
		}

	}

	@Override
	public void onTabChanged(String arg0) {
		int currentTab = mTabHost.getCurrentTab();
		mViewPager.setCurrentItem(currentTab);
	}

}

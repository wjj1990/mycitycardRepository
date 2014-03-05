package com.mcc.myctiycard;

import com.mcc.mycitycard.UI.Fragment_Charge;
import com.mcc.mycitycard.UI.Fragment_CityDetails;
import com.mcc.mycitycard.UI.Fragment_MJH;
import com.mcc.mycitycard.adapter.TabSelectAdapter;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TabHost;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class BillActivity extends FragmentActivity implements OnCheckedChangeListener {
	private TabHost mTabHost;
	private ViewPager mViewPager;
	private RadioGroup mRadioGroup;
	private TabSelectAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bills);

		initViewWidget();

		addTab();

		if (savedInstanceState != null) {
			mTabHost.setCurrentTabByTag(savedInstanceState.getString("tab"));
		}

	}

	private void addTab() {
		
//		mAdapter.addTab(mTabHost.newTabSpec("ex1").setIndicator("Ex1"),
//				null, null);
		mAdapter.addTab(mTabHost.newTabSpec("hero").setIndicator("Hero"),
				Fragment_MJH.class, null);
		mAdapter.addTab(mTabHost.newTabSpec("prop").setIndicator("Prop"),
				Fragment_Charge.class, null);
		mAdapter.addTab(mTabHost.newTabSpec("emul").setIndicator("Emul"),
				Fragment_CityDetails.class, null);
//		mAdapter.addTab(mTabHost.newTabSpec("ex2").setIndicator("Ex2"),
//				null, null);
//
//		mViewPager.setCurrentItem(498);
	}

	/**
	 * 初始化组建
	 */
	private void initViewWidget() {
		mTabHost = (TabHost) findViewById(android.R.id.tabhost);
		mTabHost.setup();

		mViewPager = (ViewPager) findViewById(R.id.main_viewPager);

		mRadioGroup = (RadioGroup) findViewById(R.id.main_radioGroup);
		mRadioGroup.setOnCheckedChangeListener(this);

		mAdapter = new TabSelectAdapter(this, mTabHost, mViewPager, mRadioGroup);
	
		ImageView imageView = (ImageView) findViewById(R.id.main_tabline);
	
		int bitmapWidth = BitmapFactory.decodeResource(getResources(), R.drawable.tab_bottomline).getWidth();
		int screenwidth = getWindowManager().getDefaultDisplay().getWidth();

		int offset=(screenwidth/3-bitmapWidth)/2;
		Matrix matrix=new Matrix();
		matrix.postTranslate(offset, 0);
		imageView.setImageMatrix(matrix);
		
		mAdapter.setOneDistance(screenwidth/3);
		mAdapter.setImageView(imageView);
		mAdapter.setOffSet(offset);
	}

	/**
	 * tab 选中事件
	 */
	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		switch (checkedId) {
		case R.id.main_hero:
			mTabHost.setCurrentTabByTag("hero");
			break;
		case R.id.main_prop:
			mTabHost.setCurrentTabByTag("prop");

			break;
		case R.id.main_emulator:
			mTabHost.setCurrentTabByTag("emul");

			break;

		default:
			break;
		}
	}
}

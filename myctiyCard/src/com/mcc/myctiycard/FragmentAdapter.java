package com.mcc.myctiycard;

import java.util.LinkedList;
import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.viewpagerindicator.IconPagerAdapter;

public class FragmentAdapter extends FragmentPagerAdapter implements IconPagerAdapter{
	protected List<String> months = new LinkedList<String>();
    Fragment fragment;
    
    public FragmentAdapter(FragmentManager fm) {
        super(fm);
        months.add("美伽汇明细");
        months.add("预充值明细");
        months.add("城市卡明细");
    }
    
    @Override
    public Fragment getItem(int position) {
    	
//       switch(position){
//          case 0:
////        	  fragment = new MJHFragment();
//        	  break;
//          case 1:
//        	 // fragment = new FastFragment();
//        	  break;
//       }
       return new Fragment();
    }

    @Override
    public int getCount() {
        return months.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
      return months.get(position);
    }

    @Override
    public int getIconResId(int index) {
      return 0;
    }
}

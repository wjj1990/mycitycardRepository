package com.mcc.myctiycard;

import com.mcc.myctiycard.app.MyApplication;

import android.app.Activity;
import android.view.KeyEvent;
import android.widget.Toast;

public class BaseActivity extends Activity{

	private long exit_time;
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK){//是后退键
			
			long temp = System.currentTimeMillis();
			if(temp - exit_time <= 3000){
				MyApplication.getInstance().exit();
			}else{
				exit_time = temp;
				Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
			}
			return true;
		}
		
		return super.onKeyDown(keyCode, event);
	}
}

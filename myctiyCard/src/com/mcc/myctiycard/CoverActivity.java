package com.mcc.myctiycard;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AlphaAnimation;

public class CoverActivity extends Activity {
	private Timer timer;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		/**
		 * LayoutInflater的作用类似于 findViewById(),不同点是LayoutInflater是用来找layout文件夹下的xml布局文件，并且实例化！
		 * 而 findViewById()是找具体某一个xml下的具体 widget控件(如:Button,TextView等)
		 */
		View root = LayoutInflater.from(this).inflate(R.layout.activity_cover, null);
		setContentView(root);
		
		AlphaAnimation aa = new AlphaAnimation(0.3f, 1.0f);
		aa.setDuration(2000);
		root.startAnimation(aa);
		
		
		timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				//创建一个显式Intent
				Intent intent = new Intent(CoverActivity.this, TabsActivity.class);
				
				startActivity(intent);//发送这个启动Activity的Intent
				
				CoverActivity.this.finish();
			}
		}, 2000);
	}
}

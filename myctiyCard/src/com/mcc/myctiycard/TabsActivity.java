package com.mcc.myctiycard;

import com.mcc.mycitycard.UI.LoginDialog;
import com.mcc.myctiycard.app.MyApplication;

import android.app.AlertDialog;
import android.app.TabActivity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TabHost;
import android.widget.Toast;

/**
 * 仿新浪微博的导航栏（使用TabActivity实现的）
 * 
 * @author qiujy
 */
@SuppressWarnings("deprecation")
public class TabsActivity extends TabActivity {
	private static final String TABS_TAG_SEARCH = "SEARCH";
	private static final String TABS_TAG_ACC = "ACC";
	private static final String TABS_TAG_MSG = "MSG";
	private static final String TABS_TAG_HOME = "HOME";
	private long exitTimes;
	private TabHost tabHost;

	private Intent homeIntent;
	private Intent msgIntent;
	private Intent accIntent;
	private Intent searchIntent;

	private RadioButton btn_home;
	private RadioButton btn_msg;
	private RadioButton btn_acc;
	private RadioButton btn_search;

	private long exit_time;
	private Handler handler;
	private Boolean islogin = false;
	private boolean isonline = true;
	private boolean isKeyDown = false;
	private String session;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main_tabs);
		MyApplication.getInstance().addActivity(TabsActivity.this);
		
		tabHost = this.getTabHost();

		homeIntent = new Intent(this, IndexActivity.class);
		msgIntent = new Intent(this, BillActivity.class);
		accIntent = new Intent(this, ApplyActivity.class);
		searchIntent = new Intent(this, SettingActivity.class);

		// 添加选项页
		tabHost.addTab(tabHost.newTabSpec(TABS_TAG_HOME).setIndicator("")
				.setContent(homeIntent));
		tabHost.addTab(tabHost.newTabSpec(TABS_TAG_MSG).setIndicator("")
				.setContent(msgIntent));
		tabHost.addTab(tabHost.newTabSpec(TABS_TAG_ACC).setIndicator("")
				.setContent(accIntent));
		tabHost.addTab(tabHost.newTabSpec(TABS_TAG_SEARCH).setIndicator("")
				.setContent(searchIntent));

		// find View /
		btn_home = (RadioButton) this.findViewById(R.id.tab_main);
		btn_msg = (RadioButton) this.findViewById(R.id.tab_msg);
		btn_acc = (RadioButton) this.findViewById(R.id.tab_acc);
		btn_search = (RadioButton) this.findViewById(R.id.tab_search);

		MyRadioButtonClickListener listener = new MyRadioButtonClickListener();
		btn_home.setOnClickListener(listener);
		btn_msg.setOnClickListener(listener);
		btn_acc.setOnClickListener(listener);
		btn_search.setOnClickListener(listener);

		handler = new Handler() {
			public void handleMessage(android.os.Message msg) {// handler接受到消息后调用此方法
				if (msg.what == 0X11) {
					if (msg.obj.equals(0)) {
						Toast.makeText(TabsActivity.this, "登录失败，请您检查网络状态",
								Toast.LENGTH_SHORT).show();
					}
					if (msg.obj.equals(1)) {
						Toast.makeText(TabsActivity.this, "登录失败，用户名或密码错误",
								Toast.LENGTH_SHORT).show();
					}
				}
			};
		};

		// 判断是否联网
		ConnectionChangeReceiver();

		// 判断是否登录，并相应的提示
		isLogin();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent intent = new Intent();
		switch (item.getItemId()) {
		// 管理菜单栏
		// case R.id.menu_settings:
		// break;
		/*
		 * case R.id.menu_feedback: intent.setClass(this,
		 * FeedbackActivity.class); break; case R.id.menu_agreement:
		 * intent.setClass(this, AgreementActivity.class); break; case
		 * R.id.menu_aboutus: intent.setClass(this, AboutActivity.class); break;
		 * case R.id.menu_update: break;
		 */
		}

		if (null != intent.getComponent()) {
			startActivity(intent);
		}

		return true;
	}

	class MyRadioButtonClickListener implements View.OnClickListener {
		public void onClick(View v) {
			RadioButton btn = (RadioButton) v;
			switch (btn.getId()) {
			case R.id.tab_main:
				tabHost.setCurrentTabByTag(TABS_TAG_HOME); // 设置指定标记的标签页为当前选中页
				break;
			case R.id.tab_msg:
				tabHost.setCurrentTabByTag(TABS_TAG_MSG);
				break;
			case R.id.tab_acc:
				tabHost.setCurrentTabByTag(TABS_TAG_ACC);
				break;
			case R.id.tab_search:
				tabHost.setCurrentTabByTag(TABS_TAG_SEARCH);
				break;
			}
		}
	}

	public void ConnectionChangeReceiver() {

		ConnectivityManager connectivityManager = (ConnectivityManager) this
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
		NetworkInfo mobNetInfo = connectivityManager
				.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		if (activeNetInfo == null) {
			isonline = false;
			Builder b = new AlertDialog.Builder(this).setTitle("没有可用的网络")
					.setMessage("是否对网络进行设置？");

			b.setPositiveButton("是", new DialogInterface.OnClickListener() {

				public void onClick(DialogInterface dialog, int whichButton) {

					if (android.os.Build.VERSION.SDK_INT > 10) {
						// 3.0以上打开设置界面
						TabsActivity.this.startActivity(new Intent(
								android.provider.Settings.ACTION_SETTINGS));
					} else {
						TabsActivity.this
								.startActivity(new Intent(
										android.provider.Settings.ACTION_WIRELESS_SETTINGS));
					}
					// 如果在设置完成后需要再次进行操作，可以重写操作代码，在这里不再重写
				}
			}).setNeutralButton("否", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int whichButton) {
					dialog.cancel();
				}
			}).show();
			return;
		}
		if (mobNetInfo != null) {

		}
	}

	private void startup() {
		new Thread() {

			public void run() {
				Message message = new Message();
				islogin = MyApplication.isIslogin();
				if (islogin && isonline) {
					try {
						sleep(500);
						handler.sendEmptyMessage(0x111);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					try {
						sleep(2000);
						message.what = 0X11;
						message.obj = 2;
						handler.sendMessage(message);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			};
		}.start();
	}

	public void isLogin() {
		islogin = MyApplication.isIslogin();
		if (islogin == false) {
			new LoginDialog.Builder(TabsActivity.this)
					.setTitle("未登录")
					.setMessage("您确定登录吗？")
					.setPositiveButton("确定",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface arg0,
										int arg1) {
									// TODO Auto-generated method stub
									arg0.dismiss();
									Intent intent = new Intent(
											TabsActivity.this,
											LoginActivity.class);
									startActivity(intent);
								}
							})
					.setNegativeButton("取消",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface arg0,
										int arg1) {
									arg0.dismiss();
								}
							}).create().show();

		}
	}
	
	@Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
        	if (event.getAction() == KeyEvent.ACTION_DOWN && event.getRepeatCount() == 0) {  
            this.exitApp();  
        }  
        return true;  
        }
        return super.dispatchKeyEvent(event);
    }
	
	/** 
	 * 退出程序 
	 */  
	private void exitApp() {  
	  // 判断2次点击事件时间  
	  if ((System.currentTimeMillis() - exit_time) > 3000) {  
	    Toast.makeText(TabsActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();  
	    exit_time = System.currentTimeMillis();  
	  } else {  
		  MyApplication.getInstance().exit();
	  }  
	}  
	
}

package com.mcc.myctiycard;

import java.util.HashMap;

import com.mcc.myctiycard.R.id;
import com.mcc.myctiycard.app.MyApplication;
import com.mcc.myctiycard.common.AllUrl;

import afinal.FinalHttp;
import afinal.http.AjaxCallBack;
import afinal.http.AjaxParams;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity implements OnClickListener{
	
	private Button loginButton;
	private Button registerBtn;
	private EditText nameText;
	private EditText passwdText;
	private ProgressDialog dialog;
	private Handler handler;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.login);
		
		if(MyApplication.getInstance().getmList().size() > 0){
			MyApplication.getInstance().exit0();
		}
		MyApplication.getInstance().addActivity(LoginActivity.this);
		
		dialog = new ProgressDialog(LoginActivity.this);
		dialog.setTitle("正在登录");
		dialog.setCancelable(false);
		dialog.setMessage("请稍后。。。");
		
		loginButton = (Button) findViewById(id.loginBtn);
		loginButton.setOnClickListener(this);
		
		registerBtn = (Button) findViewById(id.registerBtn);
		registerBtn.setOnClickListener(this);
		
		handler = new Handler() {
			public void handleMessage(android.os.Message msg) {
				if (msg.what == 0X012) {
					Toast.makeText(LoginActivity.this, "登录名或密码错误，请重新登录",
							Toast.LENGTH_LONG).show();
					dialog.dismiss();
				}
				if (msg.what == 0X011) {
					Toast.makeText(LoginActivity.this, "访问后台程序失败",
							Toast.LENGTH_LONG).show();
					dialog.dismiss();
				}
			};

		};
	}
	
	public void onClick(View v) {
		switch (v.getId()) {
		case id.registerBtn:
			Intent rintent = new Intent(LoginActivity.this,RegisterActivity.class);
			startActivity(rintent);
			break;
			
		case id.loginBtn:
			nameText = (EditText) LoginActivity.this.findViewById(R.id.username_et);
			passwdText = (EditText) LoginActivity.this.findViewById(R.id.pwdEt);
			System.out.println(nameText);
			if(nameText.getText().toString().trim().equals("") ||
					passwdText.getText().toString().trim().equals("") ){
				Toast.makeText(LoginActivity.this, "登录名和密码不能为空",
						Toast.LENGTH_LONG).show();
				return;
			}
			
			String username = nameText.getText().toString().trim();
			String userpasswd = passwdText.getText().toString().trim();
			login(username,userpasswd);
			break;
		}
	}
	
	public void login(final String username , final String passwd){
		AjaxParams map = new AjaxParams();
		map.put("username", username);
		map.put("passwd", passwd);
		
		final FinalHttp http = new FinalHttp();
		http.post(AllUrl.loginUrl, map,new AjaxCallBack<Object>(){

			@Override
			public void onSuccess(Object t) {
				// TODO Auto-generated method stub
				super.onSuccess(t);
				String content = t.toString();
				if (content == null || content.equals("")
						|| content.contains("\"isuser\":\"false\"")) {
					handler.sendEmptyMessage(0x012);
					return;
				}
				
				MyApplication.setIslogin(true);
				MyApplication.setUsername(username);
				MyApplication.setPasswd(passwd);
				Intent intent = new Intent(LoginActivity.this,
						TabsActivity.class);
				startActivity(intent);
				
				finish();
			}

			@Override
			public void onFailure(Throwable t, String strMsg) {
				// TODO Auto-generated method stub
				super.onFailure(t, strMsg);
				if (strMsg != null) {
					Log.v("@#!!!!", strMsg);
					http.close();
					handler.sendEmptyMessage(0X011);
				}
			}

		});
		
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK){//是后退键
			Intent intent = new Intent(LoginActivity.this,
					TabsActivity.class);
			startActivity(intent);
			
			finish();
		}
		return true;
	}
}

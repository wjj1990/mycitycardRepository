package com.mcc.myctiycard;

import afinal.FinalHttp;
import afinal.http.AjaxCallBack;
import afinal.http.AjaxParams;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.mcc.myctiycard.app.MyApplication;
import com.mcc.myctiycard.common.AllUrl;

/**
 * 账户资料修改
 * @author jinjie
 */
public class AccountDataModifyActivity extends BaseActivity{
	private ImageButton btn_left;
	private ImageButton alterButton;
	private TextView title;
	private Handler handler;
	private EditText usernameEditText;
	private EditText phoneEditText;
	private EditText emailEditText;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.account_data_modify);
		
		title = (TextView) this.findViewById(R.id.titleTv);
		title.setText("账户资料修改");
		
		btn_left = (ImageButton)this.findViewById(R.id.btn_left);
		btn_left.setVisibility(View.VISIBLE);
		btn_left.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				AccountDataModifyActivity.this.finish();
			}
		});
		
		handler = new Handler() {
			public void handleMessage(android.os.Message msg) {
				if (msg.what == 0X011) {
					Toast.makeText(AccountDataModifyActivity.this, "访问后台程序失败",
							Toast.LENGTH_LONG).show();
				}
			};

		};
		
		String oldUsername = MyApplication.getUsername();
		String phone = MyApplication.getPhone();
		String email = MyApplication.getEmail();
		usernameEditText = (EditText) this.findViewById(R.id.xiugai_input);
		usernameEditText.setText(oldUsername);
		phoneEditText = (EditText) this.findViewById(R.id.xiugai_input1);
		phoneEditText.setText(phone);
		emailEditText = (EditText) this.findViewById(R.id.xiugai_input2);
		emailEditText.setText(email);
		
		
		alterButton = (ImageButton) this.findViewById(R.id.alterButton);
		alterButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		String username = usernameEditText.toString().trim();
		updateAccountData(oldUsername,username,passwd);
		
	}
	
	public void updateAccountData(final String oldUsername, final String username , final String passwd ,
			final String email){

		AjaxParams map = new AjaxParams();
		map.put("oldUsername", username);
		map.put("username", username);
		map.put("passwd", passwd);
		
		final FinalHttp http = new FinalHttp();
		http.post(AllUrl.updateAccountDataUrl, map,new AjaxCallBack<Object>(){

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
				
				MyApplication.setUsername(username);
				finish();
			}

			@Override
			public void onFailure(Throwable t, String strMsg) {
				super.onFailure(t, strMsg);
				if (strMsg != null) {
					http.close();
					handler.sendEmptyMessage(0X011);
				}
			}

		});
		
	
	}
}

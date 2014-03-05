package com.mcc.myctiycard;

import com.mcc.myctiycard.R.id;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends BaseActivity implements OnClickListener{

	private ImageButton btn_left;
	private TextView title;
	private EditText usernameText;
	private EditText passwdText;
	private EditText confirmPasswdText;
	private EditText phoneText;
	private EditText emailText;
	private Button registerButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register1);
		
		title = (TextView) this.findViewById(R.id.titleTv);
		title.setText("注册");
		
		btn_left = (ImageButton)this.findViewById(R.id.btn_left);
		btn_left.setVisibility(View.VISIBLE);
		btn_left.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				RegisterActivity.this.finish();
			}
		});
		
		usernameText = (EditText) this.findViewById(R.id.usernameET);
		passwdText = (EditText) this.findViewById(R.id.passwdEt);
		confirmPasswdText = (EditText) this.findViewById(R.id.confirmPasswdEt);
		phoneText = (EditText) this.findViewById(R.id.phoneNumberEt);
		emailText = (EditText) this.findViewById(R.id.emailEt);
		
		registerButton = (Button) findViewById(R.id.registerButton);
		registerButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(usernameText.getText().toString().trim().equals("")){
					Toast.makeText(RegisterActivity.this, "用戶名不能为空",
							Toast.LENGTH_LONG).show();
					return;
				}
				if(passwdText.getText().toString().trim().equals("")){
					Toast.makeText(RegisterActivity.this, "密码不能为空",
							Toast.LENGTH_LONG).show();
					return;
				}
				if(confirmPasswdText.getText().toString().trim().equals("")){
					Toast.makeText(RegisterActivity.this, "确认密码不能为空",
							Toast.LENGTH_LONG).show();
					return;
				}
				if(confirmPasswdText.getText().toString().trim().equals(passwdText.getText().toString().trim())){
					Toast.makeText(RegisterActivity.this, "确认密码与密码不符",
							Toast.LENGTH_LONG).show();
					return;
				}
				if(phoneText.getText().toString().trim().equals("")){
					Toast.makeText(RegisterActivity.this, "手机号码不能为空",
							Toast.LENGTH_LONG).show();
					return;
				}
				if(emailText.getText().toString().trim().equals("")){
					Toast.makeText(RegisterActivity.this, "邮箱地址不能为空",
							Toast.LENGTH_LONG).show();
					return;
				}
				
				String username = usernameText.getText().toString().trim();
				
			}
		});
	}
	
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
	
	
}
	

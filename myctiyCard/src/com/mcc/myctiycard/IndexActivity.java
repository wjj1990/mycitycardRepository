package com.mcc.myctiycard;

import com.mcc.myctiycard.app.MyApplication;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class IndexActivity extends Activity{
	private TextView usernameTextView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		this.setContentView(R.layout.account);
		
		usernameTextView = (TextView) this.findViewById(R.id.usernameTv);
		String username = MyApplication.getUsername();
		usernameTextView.setText(username);
	}
}

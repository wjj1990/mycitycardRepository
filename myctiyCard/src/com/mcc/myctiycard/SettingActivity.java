package com.mcc.myctiycard;

import com.mcc.myctiycard.R.id;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SettingActivity extends Activity implements OnClickListener{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.setting);
		
		Button aboutButton = (Button)findViewById(R.id.aboutBtn);//获取'关于美伽汇'按钮资源    
		aboutButton.setOnClickListener(this);
		Button accountButton = (Button)findViewById(R.id.accountDataModifyBtn);//获取'账户资料修改'按钮资源    
		accountButton.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case id.aboutBtn:
			startActivity(new Intent(SettingActivity.this, AboutActivity.class));
			break;
		case id.accountDataModifyBtn:
			startActivity(new Intent(SettingActivity.this,AccountDataModifyActivity.class));
			break;
		}
	}
	
	
	
}

package com.mcc.myctiycard;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * 关于美伽汇
 * @author jinjie
 */
public class AboutActivity extends BaseActivity{
	private ImageButton btn_left;
	private TextView title;
	private Button phoneButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.about);
		
		title = (TextView) this.findViewById(R.id.titleTv);
		title.setText("关于");
		
		phoneButton = (Button)this.findViewById(R.id.about_bt2);
		phoneButton.setVisibility(View.VISIBLE);
		phoneButton.setOnClickListener(new View.OnClickListener(){ 
			@Override
			public void onClick(View v) {
				String inputStr = "4006117555";
				Intent phoneIntent = new Intent("android.intent.action.CALL",Uri.parse("tel:" + inputStr));
				startActivity(phoneIntent);
				}});
			
		btn_left = (ImageButton)this.findViewById(R.id.btn_left);
		btn_left.setVisibility(View.VISIBLE);
		btn_left.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				AboutActivity.this.finish();
			}
		});
	}
}

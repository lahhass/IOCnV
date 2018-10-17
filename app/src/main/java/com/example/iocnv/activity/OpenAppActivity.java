package com.example.iocnv.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

import com.example.iocnv.R;

public class OpenAppActivity extends BaseActivity {
	
	private Button register;
	
	private Button login;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.open_app);
		
		register = (Button) findViewById(R.id.Open_register);
		login = (Button) findViewById(R.id.Open_login);
		register.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(OpenAppActivity.this, RegisterActivity.class);
				startActivity(intent);
			}
		});
		login.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(OpenAppActivity.this, LoginActivity.class);
				startActivity(intent);
			}
		});
	}
}

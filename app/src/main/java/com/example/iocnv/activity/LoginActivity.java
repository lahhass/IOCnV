package com.example.iocnv.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.example.iocnv.R;
import com.example.iocnv.model.Account;
import com.example.iocnv.model.IOCDB;

public class LoginActivity extends BaseActivity {

	private EditText accountLogin;
	private Button inputPwd4;
	private Button inputPwd6;
	private Button inputPwd8;
	private String name;
	private String pwd;
	private IOCDB iocDB;
	private int hour;
	private int minute;
	private int second;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		
		accountLogin = (EditText) findViewById(R.id.account_login);
		inputPwd4 = (Button) findViewById(R.id.password_input4);
		inputPwd6 = (Button) findViewById(R.id.password_input6);
		inputPwd8 = (Button) findViewById(R.id.password_input8);
		iocDB = IOCDB.getInstance(this);
		
		inputPwd4.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				name = accountLogin.getText().toString();
				Intent intent = new Intent(LoginActivity.this, IOCActivity.class);
				Bundle bundle = new Bundle();
				bundle.putInt("number", 4);
				intent.putExtras(bundle);
				startActivityForResult(intent, 1);
			}
		});
		inputPwd6.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				name = accountLogin.getText().toString();
				Intent intent = new Intent(LoginActivity.this, IOCActivity.class);
				Bundle bundle = new Bundle();
				bundle.putInt("number", 6);
				intent.putExtras(bundle);
				startActivityForResult(intent, 1);
			}
		});
		inputPwd8.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				name = accountLogin.getText().toString();
				Intent intent = new Intent(LoginActivity.this, IOCActivity.class);
				Bundle bundle = new Bundle();
				bundle.putInt("number", 8);
				intent.putExtras(bundle);
				startActivityForResult(intent, 1);
			}
		});
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case 1:
			if (resultCode == RESULT_OK) {
				pwd = data.getStringExtra("pwd_return");
				hour = data.getIntExtra("hour", 0);
				minute = data.getIntExtra("minute", 0);
				second = data.getIntExtra("second", 0);
				Account newAccount = new Account();
				newAccount.setName(name);
				newAccount.setPassword(pwd);
				if (iocDB.validate(newAccount, 1)) {
					Intent intent = new Intent(LoginActivity.this, SuccessActivity.class);
					Bundle bundle = new Bundle();
					bundle.putInt("hour", hour);
					bundle.putInt("minute", minute);
					bundle.putInt("second", second);
					intent.putExtras(bundle);
					startActivity(intent);
					finish();
				}
			}
		}
	}
}

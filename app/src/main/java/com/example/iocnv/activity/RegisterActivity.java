package com.example.iocnv.activity;


import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.iocnv.R;
import com.example.iocnv.model.Account;
import com.example.iocnv.model.IOCDB;
import com.example.iocnv.util.MyApplication;

public class RegisterActivity extends BaseActivity {

	private EditText accountName;
	private EditText passwordFirst;
	private EditText passwordAgain;
	private Button register;
	private IOCDB iocDB;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		
		accountName = (EditText) findViewById(R.id.account_register);
		passwordFirst = (EditText) findViewById(R.id.password_first);
		passwordAgain = (EditText) findViewById(R.id.password_again);
		register = (Button) findViewById(R.id.register);
		iocDB = IOCDB.getInstance(this);
		register.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String account = accountName.getText().toString();
				String passwordFst = passwordFirst.getText().toString();
				String passwordAgn = passwordAgain.getText().toString();
				//����������벻ͬ����������
				if (!passwordFst.equals(passwordAgn)) {
					Toast.makeText(RegisterActivity.this, "输入的两次密码不同，请重新输入",
							Toast.LENGTH_SHORT).show();
				} else {
					Account newAccount = new Account();
					newAccount.setName(account);
					newAccount.setPassword(passwordFst);
					//�û�����Ч��ע��ɹ�
					if (iocDB.validate(newAccount, 0)) {
						iocDB.saveAccount(newAccount);
						Toast.makeText(MyApplication.getContext(), "注册成功", Toast.LENGTH_SHORT).show();
						finish();
					}
					
				}
			}
		});
	}
}

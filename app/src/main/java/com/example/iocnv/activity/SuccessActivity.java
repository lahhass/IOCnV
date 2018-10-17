package com.example.iocnv.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.example.iocnv.R;
import com.example.iocnv.util.ActivityCollector;
import com.example.iocnv.util.MyApplication;

public class SuccessActivity extends BaseActivity {
	
	private int hour;
	private int minute;
	private int second;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_success);
		
		Button offline = (Button) findViewById(R.id.offline);
		TextView time = (TextView) findViewById(R.id.time);
		Intent intent = this.getIntent();
		Bundle bundle = intent.getExtras();
		hour = bundle.getInt("hour");
		minute = bundle.getInt("minute");
		second = bundle.getInt("second");
		time.setText(hour + ":" + minute + ":" + second);
		offline.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				ActivityCollector.finishAll();
				Intent intent = new Intent(MyApplication.getContext(),
						OpenAppActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				MyApplication.getContext().startActivity(intent);		
			}
		});
	}

}

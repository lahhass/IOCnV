package com.example.iocnv.activity;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.example.iocnv.R;
import com.example.iocnv.model.MyProgress;
import com.example.iocnv.model.Set;
import com.example.iocnv.util.LogUtil;

import java.io.IOException;
import java.util.Calendar;

public class IOCActivity extends BaseActivity {
	
	private Set set;
	private int flag;
	private int[] color = new int[10];
	private int[] pwd = new int[8];
	private String value = "";
	private TextView pwdShow;
	private Button del;
	private Button replay;
	private MyProgress pb = null;
	private int number = 0;
    private Calendar calendar1;
    private long unixTime1;//这是时间戳
    private int hour = 0;
    private int minute = 0;
    private int second = 0;



    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ioc);

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        number = bundle.getInt("number");
        set = new Set(number);
	    flag = 0;
	    pwdShow = (TextView) findViewById(R.id.password_show);
	    pb = (MyProgress) findViewById(R.id.bar);
	    final Button black = (Button) findViewById(R.id.black);
	    final Button white = (Button) findViewById(R.id.white);
	    del = (Button) findViewById(R.id.del);
        color = set.setDisplay();
        setDisplay(color);

        black.setOnClickListener(new OnClickListener() {
	    	@Override
	    	public void onClick(View view) {
                black();
            }

	    });
	    white.setOnClickListener(new OnClickListener() {
	    	@Override
	    	public void onClick(View view) {
                white();
	    	}
	    });
	    del.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                del();
            }
        });
	}

	public void black() {
        flag = flag + 1;
        set.setL(flag);
        process();
    }

	public void white() {
        flag = flag + 1;
        set.setR(flag);
        process();
    }
    public void process() {
        color = set.setDisplay();
        if (flag == 1) {
        	calendar1= Calendar.getInstance();
            unixTime1 = calendar1.getTimeInMillis();
        }

        if (flag % 4 == 0) {

            pb.setProgress(0);
            String data = pwdShow.getText().toString();
            data = data + "●";
            pwdShow.setText(data);
            if (flag == number * 4) {
                pb.setProgress(100);
            }
        }
        else if (flag % 4 == 1) {
            pb.setProgress(25);
        }
        else if (flag % 4 == 2) {
            pb.setProgress(50);
        }
        else if (flag % 4 == 3) {
            pb.setProgress(75);
        }

        if (flag < number * 4) {
            setDisplay(color);

        } else {
            calculate();
            pwd = set.getPwd(number);
            Intent intent = new Intent();
            int i;
            for (i = 0; i < number; i++) {
                value = value + pwd[i] + "";
            }
            intent.putExtra("pwd_return", value);
            intent.putExtra("hour", hour);
            intent.putExtra("minute", minute);
            intent.putExtra("second", second);
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    public void calculate() {
        Calendar calendar2 = Calendar.getInstance();
        long unixTime2 = calendar2.getTimeInMillis();
        hour = calendar2.get(Calendar.HOUR_OF_DAY) - calendar1.get(Calendar.HOUR_OF_DAY);
        minute = calendar2.get(Calendar.MINUTE) - calendar1.get(Calendar.MINUTE);
        second = calendar2.get(Calendar.SECOND) - calendar1.get(Calendar.SECOND);
        if (minute < 0) {
            hour = hour - 1;
            minute = minute + 60;
        }
        if (second < 0) {
            minute = minute - 1;
            second = second + 60;
        }
    }

	public void del(){
	    if (set.delPwd()) {
	        flag = flag - 4;
            String data = pwdShow.getText().toString();
            data = data.substring(0, data.length() - 1);
            pwdShow.setText(data);
        }
    }





	
	public void setDisplay(int[] color) {
		int i;
		for (i = 0; i < 10; i++) {
			if (color[i] == 0) {
				turningBlack(i);
			}
			else if (color[i] == 1) {
				turningWhite(i);
			}
		}
	}
	
	public void turningBlack(int i) {
		TextView view;
		switch (i) {
		case 0:
			view = (TextView) findViewById(R.id.view0);
			break;
		case 1:
			view = (TextView) findViewById(R.id.view1);
			break;
		case 2:
			view = (TextView) findViewById(R.id.view2);
			break;
		case 3:
			view = (TextView) findViewById(R.id.view3);
			break;
		case 4:
			view = (TextView) findViewById(R.id.view4);
			break;
		case 5:
			view = (TextView) findViewById(R.id.view5);
			break;
		case 6:
			view = (TextView) findViewById(R.id.view6);
			break;
		case 7:
			view = (TextView) findViewById(R.id.view7);
			break;
		case 8:
			view = (TextView) findViewById(R.id.view8);
			break;
		case 9:
			view = (TextView) findViewById(R.id.view9);
			break;
		default:
			view = (TextView) findViewById(R.id.view0);
			break;
		}
		view.setBackgroundColor(Color.BLACK);
		view.setTextColor(Color.WHITE);
	}
	
	public void turningWhite(int i) {
		TextView view;
		switch (i) {
		case 0:
			view = (TextView) findViewById(R.id.view0);
			break;
		case 1:
			view = (TextView) findViewById(R.id.view1);
			break;
		case 2:
			view = (TextView) findViewById(R.id.view2);
			break;
		case 3:
			view = (TextView) findViewById(R.id.view3);
			break;
		case 4:
			view = (TextView) findViewById(R.id.view4);
			break;
		case 5:
			view = (TextView) findViewById(R.id.view5);
			break;
		case 6:
			view = (TextView) findViewById(R.id.view6);
			break;
		case 7:
			view = (TextView) findViewById(R.id.view7);
			break;
		case 8:
			view = (TextView) findViewById(R.id.view8);
			break;
		case 9:
			view = (TextView) findViewById(R.id.view9);
			break;
		default:
			view = (TextView) findViewById(R.id.view0);
			break;
		}
		view.setBackgroundColor(Color.WHITE);
		view.setTextColor(Color.BLACK);
	}
}

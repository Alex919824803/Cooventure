package com.zh.bigwork;

import com.zh.bean.EventBean;
import com.zh.service.CooventureService;
import com.zh.service.support.CooventureServiceSupport;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class EventActivity extends BaseActivity {
	private EventBean event;
	private Button exit;
	private TextView title;
	private TextView time;
	private TextView address;
	private TextView info;
	private Button apply;
	private int code;
	private String userId;

	private CooventureService cooventureService;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.event_details);
		initView();
		initData();
		onClick();

	}

	private void onClick() {
		exit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				EventActivity.this.finish();
			}
		});
		apply.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
					code=cooventureService.baoming(EventActivity.this, userId);
					Toast.makeText(getApplicationContext(), "报名成功", Toast.LENGTH_SHORT).show();
					apply.setBackgroundColor(Color.GRAY);
					apply.setClickable(false);
					apply.setText("您已报名");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void initView() {
		cooventureService = new CooventureServiceSupport();
		exit = (Button) findViewById(R.id.exit);
		title = (TextView) findViewById(R.id.event_title);
		time = (TextView) findViewById(R.id.event_time);
		address = (TextView) findViewById(R.id.event_address);
		info = (TextView) findViewById(R.id.event_info);
		apply = (Button) findViewById(R.id.apply_btn);
	}

	private void initData() {
		event = (EventBean) getIntent().getSerializableExtra(
				EventBean.class.getName());
		userId=event.getId();
		title.setText(event.getName());
		time.setText(event.getTime());
		address.setText(event.getAddress());
		info.setText(event.getDetail());
		switch(Integer.valueOf(event.getState())){
			case 0:
				apply.setBackgroundColor(Color.GREEN);
				apply.setClickable(true);
				apply.setText("点击报名");
				break;
			case 1:
				apply.setBackgroundColor(Color.GRAY);
				apply.setClickable(true);
				apply.setText("活动结束");
				break;
			default:
				apply.setBackgroundColor(Color.GRAY);
				apply.setClickable(false);
				apply.setText("您已报名");
				break;
		}
	}

}

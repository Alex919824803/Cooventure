package com.zh.bigwork;

import java.util.List;
import com.zh.adapter.EventAdapter;
import com.zh.bean.EventBean;
import com.zh.service.CooventureService;
import com.zh.service.support.CooventureServiceSupport;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends BaseActivity {
	private TextView nodataTextView;
	private EventAdapter eventAdapter;
	private List<EventBean> eventList;
	private ListView listView;
	private Button me;
	private CooventureService cooventureService;
	private ProgressDialog progressDialog;
	private Handler handler = new Handler();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initView();
		initDate();
		onClick();
	}

	private void initView() {
		setContentView(R.layout.activity_main);
		listView = (ListView) findViewById(R.id.event_listview);
		nodataTextView = (TextView) findViewById(R.id.nodata);
		me = (Button) findViewById(R.id.me);
	}

	private void initDate() {
		cooventureService = new CooventureServiceSupport();
		progressDialog = new ProgressDialog(MainActivity.this);
		progressDialog.setTitle("请稍等");
		progressDialog.setMessage("正在获取活动列表");
		progressDialog.setCancelable(false);
		progressDialog.show();

		new Thread() {
			@Override
			public void run() {
				try {
					eventList = cooventureService.getEventList();
					handler.post(new Runnable() {
						@Override
						public void run() {
							progressDialog.dismiss();
							if (eventList == null || eventList.size() == 0) {
								nodataTextView.setVisibility(View.VISIBLE);
								listView.setVisibility(View.GONE);
							} else {
								eventAdapter = new EventAdapter(
										MainActivity.this, eventList);
								nodataTextView.setVisibility(View.GONE);
								listView.setVisibility(View.VISIBLE);
								listView.setAdapter(eventAdapter);
							}
						}
					});
				} catch (Exception e) {
					handler.post(new Runnable() {
						@Override
						public void run() {
							progressDialog.dismiss();
						}
					});
				}
			}
		}.start();
	}

	// 监听事件
	private void onClick() {
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
									int position, long id) {
				Intent intent = new Intent(MainActivity.this,
						EventActivity.class);
				intent.putExtra(EventBean.class.getName(),
						eventList.get(position));
				startActivity(intent);
			}
		});

		me.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, My.class);
				startActivity(intent);
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
			case 1:
				if (resultCode == RESULT_OK) {
					MainActivity.this.finish();
				}
				break;

			default:
				break;
		}
	}
}

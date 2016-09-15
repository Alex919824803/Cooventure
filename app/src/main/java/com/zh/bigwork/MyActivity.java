package com.zh.bigwork;

import java.util.ArrayList;
import java.util.List;

import com.zh.adapter.EventAdapter;
import com.zh.adapter.MyActivityAdapter;
import com.zh.bean.EventBean;
import com.zh.service.CooventureService;
import com.zh.service.support.CooventureServiceSupport;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class MyActivity extends BaseActivity {

	private CooventureService cooventureService;
	private ListView listView;
	private List<EventBean> activityList;
	private MyActivityAdapter mAdapter;
	private TextView nodataTextView;
	private Button exit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.myactivity);
		initView();
		initData();
		OnClick();
	}

	private void OnClick() {
		exit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				MyActivity.this.finish();
			}
		});
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(MyActivity.this,
						EventActivity.class);
				intent.putExtra(EventBean.class.getName(),
						activityList.get(position));
				startActivity(intent);
			}
		});
	}

	private void initView() {
		cooventureService = new CooventureServiceSupport();
		listView = (ListView) findViewById(R.id.myactivity_listview);
		nodataTextView = (TextView) findViewById(R.id.myactivity_nodata);
		exit = (Button) findViewById(R.id.myactivity_exit);
	}

	private void initData() {
		try {
			activityList = cooventureService.myactivity();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Log.e("", "111");
		}

		if (activityList == null || activityList.size() == 0) {
			nodataTextView.setVisibility(View.VISIBLE);
			listView.setVisibility(View.GONE);
		} else {
			mAdapter = new MyActivityAdapter(MyActivity.this, activityList);
			nodataTextView.setVisibility(View.GONE);
			listView.setVisibility(View.VISIBLE);
			listView.setAdapter(mAdapter);
		}
	}

}

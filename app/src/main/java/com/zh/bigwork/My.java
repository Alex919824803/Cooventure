package com.zh.bigwork;

import com.zh.db.SharedPreferencesDB;
import com.zh.bean.UserBean;
import com.zh.service.CooventureService;
import com.zh.service.support.CooventureServiceSupport;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class My extends BaseActivity {

	private Button exit;
	private UserBean userBean;
	private TextView my_id;
	private TextView my_name;
	private TextView my_sex;
	private TextView my_school;
	private TextView my_subject;
	private Button exit_Login;
	private ImageButton myactivity;
	private CooventureService cooventureService;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my);
		initView();
		initData();
		OnClick();
	}

	private void initView() {
		cooventureService = new CooventureServiceSupport();

		exit = (Button) findViewById(R.id.my_exit);
		my_id = (TextView) findViewById(R.id.my_id);
		my_name = (TextView) findViewById(R.id.my_name);
		my_sex = (TextView) findViewById(R.id.my_sex);
		my_school = (TextView) findViewById(R.id.my_school);
		my_subject = (TextView) findViewById(R.id.my_subject);
		myactivity = (ImageButton) findViewById(R.id.my_myactivity);
		exit_Login = (Button) findViewById(R.id.my_exitLogin);
	}

	private void initData() {
		userBean = SharedPreferencesDB.getUserOfSharedPreferences(My.this);
		my_id.setText(userBean.getUserAcount());
		my_name.setText(userBean.getUserName());
		my_school.setText(userBean.getUserUniversity());
		my_subject.setText(userBean.getUserSubject());

		if (Integer.valueOf(userBean.getUserSex()) == 0) {
			my_sex.setText("男");
		} else {
			my_sex.setText("女");
		}
	}

	private void OnClick() {
		exit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		myactivity.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(My.this, MyActivity.class);
				setResult(RESULT_OK);
				startActivity(intent);
				My.this.finish();
			}
		});
		exit_Login.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				cooventureService.exit();
				setResult(RESULT_OK);
				My.this.finish();
			}
		});
	}
}

package com.zh.bigwork;

import com.zh.service.CooventureService;
import com.zh.service.support.CooventureServiceSupport;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends BaseActivity {

	public Button btn_login;
	public Button btn_register;
	public EditText id;
	public EditText password;
	public EditText pswagain;
	private Intent intent;
	private ProgressDialog progressDialog;
	private CooventureService cooventureService;
	private int code;

	private static final int SUCCESS = 1;
	private static final int FAIL = 2;

	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			progressDialog.dismiss();
			switch (msg.what) {
				case SUCCESS:
					switch (code) {
						case 1:
							intent = new Intent(Login.this, MainActivity.class);
							startActivityForResult(intent, 1);
							Login.this.finish();
							break;
						case 2:
							Toast.makeText(getApplicationContext(), "不存在该用户",
									Toast.LENGTH_SHORT).show();
							break;
						case 3:
							Toast.makeText(getApplicationContext(), "用户名或密码错误",
									Toast.LENGTH_SHORT).show();
							break;
					}
					break;
				case FAIL:
					Toast.makeText(getApplicationContext(), "登录失败",
							Toast.LENGTH_SHORT).show();
					break;
			}
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		initView();
		initData();
		onClick();
	}

	// 初始化函数
	private void initView() {
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_register = (Button) findViewById(R.id.btn_register);
		id = (EditText) findViewById(R.id.et_id);
		password = (EditText) findViewById(R.id.et_psw);
	}

	private void initData() {
		cooventureService = new CooventureServiceSupport();
	}

	// 按钮功能
	private void onClick() {
		btn_login.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (TextUtils.isEmpty(id.getText().toString())
						|| TextUtils.isEmpty(password.getText().toString())) {
					Toast.makeText(getApplicationContext(), "请输入用户名与密码",
							Toast.LENGTH_SHORT).show();
				} else {
					progressDialog = new ProgressDialog(Login.this);
					progressDialog.setTitle("请稍候");
					progressDialog.setMessage("正在登陆...");
					progressDialog.setCancelable(false);
					progressDialog.show();

					new Thread() {
						@Override
						public void run() {
							Message message = new Message();
							try {
								code = cooventureService.login(Login.this, id
										.getText().toString(), password
										.getText().toString());
								message.what = SUCCESS;
								handler.sendMessage(message);
							} catch (Exception e) {
								message.what = FAIL;
								handler.sendMessage(message);
							}
						}
					}.start();
				}
			}
		});
		btn_register.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				intent = new Intent(Login.this, Register.class);
				startActivity(intent);
			}
		});
	}

}
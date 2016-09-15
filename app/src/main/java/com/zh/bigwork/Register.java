package com.zh.bigwork;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.zh.db.SharedPreferencesDB;
import com.zh.bean.UserBean;
import com.zh.service.support.CooventureServiceSupport;
import com.zh.service.CooventureService;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

public class Register extends BaseActivity {
	private Button btn_register;
	private Button exit;
	private EditText username;
	private EditText password;
	private EditText password2;
	private EditText school;
	private EditText subject;
	private EditText register_realname;
	private RadioGroup radioGroup;
	private RadioButton male;
	private RadioButton female;
	private ProgressDialog progressDialog;
	private CooventureService cooventureService;
	private String sex = "2";
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			progressDialog.dismiss();
			Intent intent = new Intent(Register.this, MainActivity.class);
			startActivity(intent);
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		initView();
		initData();
		OnClick();
	}

	private void initData() {
		cooventureService = new CooventureServiceSupport();
	}

	private void OnClick() {
		exit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});

		radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if (male.getId() == checkedId) {
					sex = "0";
				} else if (female.getId() == checkedId) {
					sex = "1";
				}
			}
		});

		btn_register.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (TextUtils.isEmpty(username.getText().toString())
						|| TextUtils.isEmpty(password.getText().toString())
						|| TextUtils.isEmpty(password2.getText().toString())
						|| TextUtils.isEmpty(school.getText().toString())
						|| TextUtils.isEmpty(subject.getText().toString())
						|| sex == "2") {
					Toast.makeText(getApplicationContext(), "任何一项不能为空",
							Toast.LENGTH_SHORT).show();
				} else if (!password.getText().toString()
						.equals(password2.getText().toString())) {
					Toast.makeText(getApplicationContext(), "两次输入的密码不相符",
							Toast.LENGTH_SHORT).show();
				} else if (accountFormat(username.getText().toString())==false) {
					Toast.makeText(getApplicationContext(), "用户名格式不正确",
							Toast.LENGTH_SHORT).show();
				} else {
					progressDialog = new ProgressDialog(Register.this);
					progressDialog.setTitle("请稍候");
					progressDialog.setMessage("正在注册...");
					progressDialog.setCancelable(false);
					progressDialog.show();

					new Thread() {
						@Override
						public void run() {
							Message message = new Message();

							UserBean userBean = new UserBean();
							userBean.setUserAcount(username.getText()
									.toString());
							userBean.setUserPassword(password.getText()
									.toString());
							userBean.setUserName(register_realname.getText()
									.toString());
							userBean.setUserSex(sex);
							userBean.setUserUniversity(school.getText()
									.toString());
							userBean.setUserSubject(subject.getText()
									.toString());
							SharedPreferencesDB.addUserToSharedPreferences(
									Register.this, userBean);

							try {
								cooventureService.register(Register.this,
										userBean);
							} catch (Exception e) {
								e.printStackTrace();
							}

							handler.sendMessage(message);
						}
					}.start();
				}
			}
		});
	}

	private void initView() {
		btn_register = (Button) findViewById(R.id.register_btn_register);
		exit = (Button) findViewById(R.id.register_exit);
		username = (EditText) findViewById(R.id.register_username);
		password = (EditText) findViewById(R.id.register_password);
		password2 = (EditText) findViewById(R.id.register_password2);
		school = (EditText) findViewById(R.id.register_school);
		subject = (EditText) findViewById(R.id.register_subject);
		register_realname = (EditText) findViewById(R.id.register_realname);
		radioGroup = (RadioGroup) findViewById(R.id.register_sex);
		male = (RadioButton) findViewById(R.id.register_sex_man);
		female = (RadioButton) findViewById(R.id.register_sex_women);
	}

	/**
	 * 验证邮箱
	 *
	 * @param email
	 * @return
	 */
	private boolean emailFormat(String email) {
		// 验证邮箱的正则表达式
		String format = "[\\w]{2,15}+@[\\w]{2,}+.[\\w]{2,3}+";
		// [\\w]{2,15}字母+数字2-15位
		// @[\\w]{2,}@之后至少2位
		// .[\\w]{1,3}.需要2-3位
		if (email.matches(format)) {
			return true;// 邮箱名合法，返回true
		} else {
			return false;// 邮箱名不合法，返回false
		}
	}

	/**
	 * 验证电话
	 *
	 * @param number
	 * @return
	 */
	private boolean phoneFormat(String number) {
		Pattern p = Pattern
				.compile("^((13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$");
		Matcher m = p.matcher(number);
		return m.matches();
	}

	/**
	 * 验证数字或字符串组
	 *
	 * @param string
	 * @return
	 */
	private boolean numberAndCharFormat(String string) {
		int number = 0;// 记录是否有数字
		int cha = 0;// 记录是否有字母
		boolean x = true;// 记录是否有其他符号
		for (int i = 0; i < string.length(); i++) {
			int chr = string.charAt(i);
			if ((chr >= 48 && chr <= 57))// 判断是否为数字
			{
				number = 1;
			} else if ((chr >= 97 && chr <= 122) || (chr >= 65 && chr <= 90))// 判断是否为英文字母
			{
				cha = 1;
			} else {
				x = false;
			}
		}// 最终判断正确
		if (cha == 1 && number == 1 && x == true)
			return true;
		else {
			return false;
		}

	}

	/**
	 * 判断输入账号是否正确
	 *
	 * @param account
	 * @return
	 */
	private boolean accountFormat(String account) {

		if (emailFormat(account) || phoneFormat(account)
				|| numberAndCharFormat(account)) {
			return true;
		} else {
			return false;
		}
	}
}

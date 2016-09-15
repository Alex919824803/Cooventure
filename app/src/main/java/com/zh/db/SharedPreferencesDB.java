package com.zh.db;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.zh.bean.UserBean;

public class SharedPreferencesDB {

	private static final String SHARED_NAME = "userInfo";

	public static void addUserToSharedPreferences(Context context,
			UserBean userBean) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(
				SHARED_NAME, Context.MODE_PRIVATE);
		Editor editor = sharedPreferences.edit();
		// 通过edit写入值
		editor.putString("userId", userBean.getUserId());
		editor.putString("userAcount", userBean.getUserAcount());
		editor.putString("userPassword", userBean.getUserPassword());
		editor.putString("userName", userBean.getUserName());
		editor.putString("userSex", userBean.getUserSex());
		editor.putString("userUniversity", userBean.getUserUniversity());
		editor.putString("userSubject", userBean.getUserSubject());
		editor.commit();
	}

	/*
	 * 将一条信息用SharedPreferences存储
	 */

	public void addToSharedPreferences(Context context, String key, String value) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(
				SHARED_NAME, Context.MODE_PRIVATE);
		Editor editor = sharedPreferences.edit();
		editor.putString(key, value);
		editor.commit();
	}

	/*
	 * 获取SharedPreferences的用户信息
	 */

	public static UserBean getUserOfSharedPreferences(Context context) {

		UserBean userBean = new UserBean();
		SharedPreferences sharedPreferences = context.getSharedPreferences(
				SHARED_NAME, Context.MODE_PRIVATE);
		userBean.setUserId(sharedPreferences.getString("userId", null));
		userBean.setUserAcount(sharedPreferences.getString("userAcount", null));
		userBean.setUserPassword(sharedPreferences.getString("userPassword",
				null));
		userBean.setUserName(sharedPreferences.getString("userName", null));
		userBean.setUserSex(sharedPreferences.getString("userSex", null));
		userBean.setUserUniversity(sharedPreferences.getString(
				"userUniversity", null));
		userBean.setUserSubject(sharedPreferences
				.getString("userSubject", null));
		return userBean;
	}
	/*
	 * 获取SharedPreferences中指定键位的值
	 */
	public static String getValueOfSharedPreferences(Context context,String key,String defaultValue){
		SharedPreferences sharedPreferences = context.getSharedPreferences(
				SHARED_NAME, Context.MODE_PRIVATE);
		return sharedPreferences.getString(key, defaultValue);

	}
}

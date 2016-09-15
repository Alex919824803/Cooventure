package com.zh.server;

import java.util.List;

import com.zh.bean.EventBean;
import com.zh.bean.UserBean;

import android.content.Context;

public interface CooventureServer {
	// 登陆的接口
	int login(Context context, String name, String password) throws Exception;

	// 注册接口
	int register(Context context, UserBean userBean) throws Exception;

	// 活动接口
	List<EventBean> getEventList() throws Exception;

	// 我的活动接口
	List<EventBean> myactivity() throws Exception;

	// 报名接口
	int baoming(Context context, String UserId) throws Exception;

	//退出登录
	void exit() throws Exception;
}

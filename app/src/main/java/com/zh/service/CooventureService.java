package com.zh.service;

import java.util.List;

import com.zh.bean.EventBean;
import com.zh.bean.UserBean;

import android.content.Context;

/*
 * 控制server和DB
 */
public interface CooventureService {
	/*
	 * 登录
	 */
	int login(Context context, String name, String password) throws Exception;

	/*
	 * 获取我的报名列表
	 */
	List<EventBean> getEventList() throws Exception;

	/*
	 * 注册
	 */
	int register(Context context, UserBean userInfoBean) throws Exception;

	/*
	 * 我的活动
	 */
	List<EventBean> myactivity() throws Exception;

	// 报名接口
	int baoming(Context context, String UserId) throws Exception;

	/*
	 * 退出接口
	 */
	void exit();
}

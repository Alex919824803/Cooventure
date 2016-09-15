package com.zh.service.support;

import java.util.List;

import android.content.Context;

import com.zh.bean.EventBean;
import com.zh.bean.UserBean;
import com.zh.server.CooventureServer;
import com.zh.server.support.CooventureServerSupport;
import com.zh.service.CooventureService;

public class CooventureServiceSupport implements CooventureService {
	private CooventureServer cooventureServer = new CooventureServerSupport();

	@Override
	public int login(Context context, String name, String password)
			throws Exception {
		// TODO Auto-generated method stub
		return cooventureServer.login(context, name, password);
	}

	@Override
	public List<EventBean> getEventList() throws Exception {
		// TODO Auto-generated method stub
		return cooventureServer.getEventList();
	}

	@Override
	public int register(Context context, UserBean userInfoBean)
			throws Exception {
		// TODO Auto-generated method stub
		return cooventureServer.register(context, userInfoBean);
	}

	@Override
	public List<EventBean> myactivity() throws Exception {
		// TODO Auto-generated method stub
		return cooventureServer.myactivity();
	}

	@Override
	public int baoming(Context context, String UserId) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void exit() {
		try {
			cooventureServer.exit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

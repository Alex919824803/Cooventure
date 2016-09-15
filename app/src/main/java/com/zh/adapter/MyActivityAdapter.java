package com.zh.adapter;

import java.util.List;

import com.zh.bean.EventBean;
import com.zh.bigwork.R;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyActivityAdapter extends BaseAdapter {

	private Context context;
	private List<EventBean> eventList;
	private LayoutInflater layoutInflater;


	public MyActivityAdapter(Context context, List<EventBean> eventList) {
		this.context = context;
		this.eventList = eventList;
		this.layoutInflater = LayoutInflater.from(this.context);
	}

	@Override
	public int getCount() {
		return eventList == null ? 0 : eventList.size();
	}

	@Override
	public Object getItem(int position) {// 指定索引对象
		return eventList == null ? null : eventList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}


	@Override
	public View getView(int position, View convertView, ViewGroup parent) {// 返回每一项内容
		ViewHolder viewHolder = null;
		EventBean eventBean = eventList.get(position);

		// 判断convertView是否被实例化,缓存池中是否有缓存，避免大量创建convertView
		if (convertView == null || convertView.getTag() == null) {
			viewHolder = new ViewHolder();
			convertView = layoutInflater
					.inflate(R.layout.myactivity_item, null);
			viewHolder.name = (TextView) convertView.findViewById(R.id.myactivity_name);
			viewHolder.time = (TextView) convertView.findViewById(R.id.myactivity_time);
			convertView.setTag(viewHolder);// 通过setTag将viewHolder与convertView绑定
		} else {
			viewHolder = (ViewHolder) convertView.getTag();// 通过getTag直接取出ViewHolder
		}

		viewHolder.name.setText(eventBean.getName());
		viewHolder.time.setText("活动时间:" + eventBean.getTime());
		return convertView;
	}

	// ViewHolder为了帮我们优化BaseAdapter
	// 每一个属性对应的都是XML里的控件
	// 通过ViewHolder类来实现显示的视图缓存，避免多次通过findviewbyid寻找控件
	class ViewHolder {
		TextView name;
		TextView time;
	}
}

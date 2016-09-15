package com.zh.http;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class HttpClintRemote {
	static HttpClient httpClient = new DefaultHttpClient();

	/*
	 * get方法提交数据
	 */

	public static String get(String url) throws NetWorkException {
		// 创建HTTPGET对象
		HttpGet getMethodGet = new HttpGet(url);
		// 发送请求
		try {
			HttpResponse response = httpClient.execute(getMethodGet);
			int stausCode = response.getStatusLine().getStatusCode();
			// 判断请求类型
			if (stausCode == HttpStatus.SC_OK) {
				// 取出服务器的数据
				return EntityUtils.toString(response.getEntity(), "utf-8");
			} else {
				getMethodGet.abort();// 断开
				throw new NetWorkException();
			}
		} catch (Exception e) {
			getMethodGet.abort();// 断开
			throw new NetWorkException();
		}
	}

	/*
	 * post方法提交数据
	 */
	public static String post(String url, Map<String, String> paramMap)
			throws NetWorkException {
		try {
			// 将paramMap转成body体
			JSONObject json = new JSONObject();
			for (Iterator<Entry<String, String>> it = paramMap.entrySet()
					.iterator(); it.hasNext();) {
				Entry<String, String> e = it.next();

				json.put(e.getKey(), e.getValue());
			}

			HttpPost postMethod = new HttpPost(url);
			postMethod.setHeader("Content-type", "application/json");// 两个值与服务器协定好的

			// 将请求内容加入到请求中
			postMethod.setEntity(new StringEntity(json.toString(), HTTP.UTF_8));

			// 客户端向服务器发送请求
			HttpResponse response = httpClient.execute(postMethod);

			// 获取类型做判断
			int stausCode = response.getStatusLine().getStatusCode();

			if (stausCode == HttpStatus.SC_OK) {
				return EntityUtils.toString(response.getEntity(), "utf-8");
			} else {
				postMethod.abort();// 断开
				throw new NetWorkException();
			}
		} catch (Exception e) {
			throw new NetWorkException();
		}
	}
}

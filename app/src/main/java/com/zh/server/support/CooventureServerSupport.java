package com.zh.server.support;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;
import com.zh.bean.EventBean;
import com.zh.db.SharedPreferencesDB;
import com.zh.bean.UserBean;
import com.zh.http.HttpClintRemote;
import com.zh.server.CooventureServer;

public class CooventureServerSupport implements CooventureServer {

	/*
	 * 登录接口
	 */
	private static final String BASE_URL = "http://10.25.59.3:8080/";

	@Override
	public int login(Context context, String name, String password)
			throws Exception {
		String paramUrl = BASE_URL + "CooVenture/login.action?userName=" + name
				+ "&userpassword=" + password;
		// JSONObject json = new JSONObject(HttpClintRemote.get(paramUrl));

		String s = "{\"msg\":\"1\",\"userSex\":\"0\",\"userPassword\":\"123456\",\"userRealName\":\"yuxiaorui\",\"userUniversity\":\"dkdaldk\",\"userName\":\"root\",\"userId\":\"11\",\"userSubject\":\"dsafd\"}";
		JSONObject json = new JSONObject(s);

		int code = Integer.valueOf(json.getString("msg"));
		if (code == 1) {
			UserBean userBean = new UserBean();
			userBean.setUserId(json.getString("userId"));
			userBean.setUserAcount(json.getString("userName"));
			userBean.setUserPassword(json.getString("userPassword"));
			userBean.setUserName(json.getString("userRealName"));
			userBean.setUserSex(json.getString("userSex"));
			userBean.setUserUniversity(json.getString("userUniversity"));
			userBean.setUserSubject(json.getString("userSubject"));
			SharedPreferencesDB.addUserToSharedPreferences(context, userBean);
		}
		return code;
	}

	/*
	 * 活动接口
	 */
	@Override
	public List<EventBean> getEventList() throws Exception {
		String parmaUrl = BASE_URL + "CooVenture/getActivityList.action";
		// JSONObject json = new JSONObject(HttpClintRemote.get(parmaUrl));

		String s = "{\"activityList\":[{\"activityAddress\":\"吉林\",\"activityDetail\":\"篮球运动是1891年由美国人詹姆斯奈史密斯发明的。当时，他在马萨诸塞州斯普林菲尔德基督教青年会国际训练学校任教。由于当地盛产桃子，这里的儿童又非常喜欢做用球投入桃子筐的游戏。这使他从中得到启发，并博采足球、曲棍球等其它球类项目的特点，创编了篮球游戏。 \",\"activityId\":\"1\",\"activityName\":\"篮球\",\"activityOrganization\":\"吉林大学\",\"activityState\":\"1\",\"activityTime\":\"2016-7-1 0:00:00\",\"activityUrl\":\"\"},{\"activityAddress\":\"北京\",\"activityDetail\":\"“北朝鲜不可能侵入索尼公司系统窃取《刺杀金正恩》这部电影，否则平壤将会呼吁那些对邮件处理不当和对影片续集融资的索尼高管作出反应，而并非朝鲜人民。我看过这部电影，其实影片本身并没有过多值得谈论的，它就是一部还不错的喜剧而已。电影放映中我笑了几次，但也发现了制片中不太完善的铺垫。如果我是在电影院观看这部影片的话，我虽并不致中途离场，但很可能要打几个小盹。”\",\"activityId\":\"2\",\"activityName\":\"采访\",\"activityOrganization\":\"neusoft\",\"activityState\":\"0\",\"activityTime\":\"2018-5-5 0:00:00\",\"activityUrl\":\"\"},{\"activityAddress\":\"沈阳\",\"activityDetail\":\"黄山地区水资源来自天然降水。该地区多年平均面雨量为1775.9mm，地区分布以黄山风景区为最大，是全国有名的暴雨中心之一。降雨年内分布极不均匀，最大月雨量一般出现在5、6、7月份，曾高达1037mm（黄山温泉站1954年6月）；最小月雨量一般出现在12月份，记录出现过0。境内降雨的年际变化也相当悬殊，最大最小年份的比值达2.5以上，甚至达到3.0。全市地表水资源总量丰富，多年平均年径流量达99.28亿m，地表径流的地区和时空分布与降雨的时空分布基本一致，地表径流年内分配也极不均匀，每年5～7月份降雨量大，径流量也大；年际分配不平衡，年降雨量越大，年产流量也越大，最小年径流量与最大年径流量之比约为1∶5。\",\"activityId\":\"3\",\"activityName\":\"登山\",\"activityOrganization\":\"东北大学\",\"activityState\":\"0\",\"activityTime\":\"2018-1-3 0:00:00\",\"activityUrl\":\"\"},{\"activityAddress\":\"北京\",\"activityDetail\":\"今长江的形成发在距今1.4亿年前的侏罗纪时的燕山运动，在长江游形成了唐古拉山脉，青藏高原缓缓抬高，形成许多高山深谷、洼地和裂谷。长江中下游大别山和川鄂间巫山等山脉隆起，四川盆地凹陷，古地中海进一步向西部退缩。距今1亿年前的白垩纪时。四川盆地缓慢上升。夷平作用不断发展，云梦、洞庭盆地继续下沉。今3000-4000万年前的始新世、发生强烈的喜马拉雅山运动、青藏高原隆起，古地中海消失，长江流域普遍间歇上升。其上升程度，东部和缓，西部急剧。金沙江两岸高山突起，青藏高原和云贵高原显著抬升，同时形成了一些断陷盆地。河流的强烈下切作用，出现了许多深邃险峻的峡谷，原来自北往南流的水系相互归并顺折向东流。长江中下游上升幅度较小，形成中、低山和丘陵，低凹地带下沉为平原(如两湖平原、南襄平原、都阳平原、苏皖平原等)。到了距今300万年前时，喜马拉雅山强烈隆起，长江流域西部进一步抬高。从湖北伸向四盆地的古长江溯源浸蚀作用加快，切穿巫山，使东西古长江贯通一起。\",\"activityId\":\"4\",\"activityName\":\"游泳\",\"activityOrganization\":\"北京大学\",\"activityState\":\"0\",\"activityTime\":\"2018-1-3 0:00:00\",\"activityUrl\":\"\"},{\"activityAddress\":\"大连\",\"activityDetail\":\"马丁•路德•金从讲坛上走下来时，人群震惊了，竟茫然不知所措。演讲结束得太突然，太令人泄气。按照演讲的规律，在结束时要出现第三次高潮，听众在等待他引导呢！几秒钟过去了，失望的心情被记忆和兴奋所取代。马丁•路德•金在走出教堂的时候，鼓掌声一直跟随着他，教徒还探着身想触摸他。抵制公车的运动就这样开始了。在他的第一次政治性演讲后短短几分钟里，他心里涌出一股与陌生人交流的强烈愿望，不论这些陌生人对他如同对所有先知一样既爱又恨。这一年他只有26岁，未来的生命还不足12年又4个月。\",\"activityId\":\"5\",\"activityName\":\"宣讲\",\"activityOrganization\":\"东软\",\"activityState\":\"0\",\"activityTime\":\"2018-1-3 0:00:00\",\"activityUrl\":\"\"},{\"activityAddress\":\"哈尔滨\",\"activityDetail\":\"阿里巴巴已经形成了一个通过自有电商平台沉积以及UC、高德地图、企业微博等端口导流，围绕电商核心业务及支撑电商体系的金融业务，以及配套的本地生活服务、健康医疗等，囊括游戏、视频、音乐等泛娱乐业务和智能终端业务的完整商业生态圈。这一商业生态圈的核心是数据及流量共享，基础是营销服务及云服务，有效数据的整合抓手是支付宝。\",\"activityId\":\"6\",\"activityName\":\"招聘\",\"activityOrganization\":\"哈工大\",\"activityState\":\"0\",\"activityTime\":\"2018-3-3 0:00:00\",\"activityUrl\":\"\"},{\"activityAddress\":\"威海\",\"activityDetail\":\"腾讯公司成立于1998年11月11日， 把为用户提供“一站式在线生活服务”作为战略目标，提供互联网增值服务、移动及电信增值服务和网络广告服务。通过即时通信QQ、腾讯网、腾讯游戏、QQ空间、无线门户、搜搜、拍拍、财付通等中国领先的网络平台，腾讯打造了中国最大的网络社区，满足互联网用户沟通、资讯、娱乐和电子商务等方面的需求。\",\"activityId\":\"7\",\"activityName\":\"求职\",\"activityOrganization\":\"哈工大\",\"activityState\":\"0\",\"activityTime\":\"2018-5-3 0:00:00\",\"activityUrl\":\"\"},{\"activityAddress\":\"长春\",\"activityDetail\":\"在今年央视“3•15”晚会中，饿了么合作商家被爆出诸多问题，除卫生状况令人作呕，更有“饿了么”员工主动协助无照黑作坊入驻平台。晚会播出后，“饿了么”高调展开危机公关，紧急下线涉事餐厅，对负责商户审核的相关责任人严肃处理，之后又成立专门工作小组，重新审核全国范围的餐厅资质，并推出千人客服24小时待命等“七剑下天山”措施。那么，在声势浩大的“刮骨疗毒”后，“饿了么”果真成了“明厨亮灶”？\",\"activityId\":\"8\",\"activityName\":\"聚餐\",\"activityOrganization\":\"东北师范\",\"activityState\":\"0\",\"activityTime\":\"2018-1-1 0:00:00\",\"activityUrl\":\"\"},{\"activityAddress\":\"西安\",\"activityDetail\":\"袋装炭生火很容易，只要按照纸袋背后的说明操作即可；有经验的人会带瓶酒精或者二锅头助燃。切得均匀整齐的肉片最好烤，将之在烧烤架的铁丝网上铺平，一面烤得颜色变化后再翻一面，注意别粘铁丝网；形状不规则的海鲜鱼类烤时需要勤翻动；体积大的鸡翅、鸡腿什么的事先就要用刀划出槽，或者干脆剖成一片，否则外面都烤焦了里面也不一定熟。事先准备得越详细，烧烤进行得就越顺利。等到香味冉冉生起，口水滴滴落下时，就可以大快朵颐啦！千万不要忘记带筷子或刀叉这类小东西！否则你会面临一个尴尬的境地。\",\"activityId\":\"9\",\"activityName\":\"野炊\",\"activityOrganization\":\"西安电子科技\",\"activityState\":\"0\",\"activityTime\":\"2018-1-5 0:00:00\",\"activityUrl\":\"\"},{\"activityAddress\":\"哈尔滨\",\"activityDetail\":\"现代羽毛球运动起源于英国。1873年，在英国格拉斯哥郡的伯明顿镇有一位叫鲍弗特的公爵， 在他的领地开游园会，有几个从印度回来的退役军官就向大家介绍了一种隔网用拍子来回击打毽球的游戏，人们对此产生了很大的兴趣。因这项活动极富趣味性，很快就在上层社会社交场上风行开来。“伯明顿”（Badminton）即成为英文羽毛球的名字。1893年，英国14个羽毛球俱乐部组成羽毛球协会，即全英公开赛的前身。自1992年起，羽毛球成为奥运会的正式比赛项目。\",\"activityId\":\"10\",\"activityName\":\"羽毛球\",\"activityOrganization\":\"哈工程\",\"activityState\":\"0\",\"activityTime\":\"2018-1-3 0:00:00\",\"activityUrl\":\"\"}]}";
		JSONObject json = new JSONObject(s);

		JSONArray jsonArray = json.getJSONArray("activityList");
		if (jsonArray == null) {
			return null;
		}
		List<EventBean> listEvents = new ArrayList<EventBean>();
		for (int i = 0, size = jsonArray.length(); i < size; i++) {
			JSONObject jsonEvent = jsonArray.getJSONObject(i);
			EventBean event = new EventBean();
			event.setId(jsonEvent.getString("activityId"));
			event.setName(jsonEvent.getString("activityName"));
			event.setAddress(jsonEvent.getString("activityAddress"));
			event.setOrganization(jsonEvent.getString("activityOrganization"));
			event.setTime(jsonEvent.getString("activityTime"));
			event.setState(jsonEvent.getString("activityState"));
			event.setDetail(jsonEvent.getString("activityDetail"));
			event.setUrl(jsonEvent.getString("activityUrl"));
			listEvents.add(event);
		}
		return listEvents;
	}

	/*
	 * 注册接口
	 */
	@Override
	public int register(Context context, UserBean UserInforBean)
			throws Exception {

		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("userName", UserInforBean.getUserAcount());
		paramsMap.put("userRealName", UserInforBean.getUserName());
		paramsMap.put("userPassword", UserInforBean.getUserPassword());
		paramsMap.put("userSex", UserInforBean.getUserSex());
		paramsMap.put("userUniversity", UserInforBean.getUserUniversity());
		paramsMap.put("userSubject", UserInforBean.getUserSubject());

		SharedPreferencesDB.addUserToSharedPreferences(context, UserInforBean);

		// String paramsUrl = BASE_URL + "CooVenture/login.action";
		// JSONObject json = new JSONObject(HttpClintRemote.post(paramsUrl,
		// paramsMap));

		String s = "{\"msg\":\"1\"}";
		JSONObject json = new JSONObject(s);
		int code = Integer.valueOf(json.getString("msg"));

		return code;
	}

	/*
	 * 我的活动接口
	 */
	@Override
	public List<EventBean> myactivity() throws Exception {
		String s = "{\"activityList\":[{\"activityAddress\":\"吉林\",\"activityDetail\":\"篮球运动是1891年由美国人詹姆斯奈史密斯发明的。当时，他在马萨诸塞州斯普林菲尔德基督教青年会国际训练学校任教。由于当地盛产桃子，这里的儿童又非常喜欢做用球投入桃子筐的游戏。这使他从中得到启发，并博采足球、曲棍球等其它球类项目的特点，创编了篮球游戏。 \",\"activityId\":\"1\",\"activityName\":\"篮球\",\"activityOrganization\":\"吉林大学\",\"activityState\":\"1\",\"activityTime\":\"2016-7-1 0:00:00\",\"activityUrl\":\"\"},{\"activityAddress\":\"北京\",\"activityDetail\":\"“北朝鲜不可能侵入索尼公司系统窃取《刺杀金正恩》这部电影，否则平壤将会呼吁那些对邮件处理不当和对影片续集融资的索尼高管作出反应，而并非朝鲜人民。我看过这部电影，其实影片本身并没有过多值得谈论的，它就是一部还不错的喜剧而已。电影放映中我笑了几次，但也发现了制片中不太完善的铺垫。如果我是在电影院观看这部影片的话，我虽并不致中途离场，但很可能要打几个小盹。”\",\"activityId\":\"2\",\"activityName\":\"采访\",\"activityOrganization\":\"neusoft\",\"activityState\":\"0\",\"activityTime\":\"2018-5-5 0:00:00\",\"activityUrl\":\"\"},{\"activityAddress\":\"沈阳\",\"activityDetail\":\"黄山地区水资源来自天然降水。该地区多年平均面雨量为1775.9mm，地区分布以黄山风景区为最大，是全国有名的暴雨中心之一。降雨年内分布极不均匀，最大月雨量一般出现在5、6、7月份，曾高达1037mm（黄山温泉站1954年6月）；最小月雨量一般出现在12月份，记录出现过0。境内降雨的年际变化也相当悬殊，最大最小年份的比值达2.5以上，甚至达到3.0。全市地表水资源总量丰富，多年平均年径流量达99.28亿m，地表径流的地区和时空分布与降雨的时空分布基本一致，地表径流年内分配也极不均匀，每年5～7月份降雨量大，径流量也大；年际分配不平衡，年降雨量越大，年产流量也越大，最小年径流量与最大年径流量之比约为1∶5。\",\"activityId\":\"3\",\"activityName\":\"登山\",\"activityOrganization\":\"东北大学\",\"activityState\":\"0\",\"activityTime\":\"2018-1-3 0:00:00\",\"activityUrl\":\"\"}]}";
		JSONObject json = new JSONObject(s);

		JSONArray jsonArray = json.getJSONArray("activityList");
		if (jsonArray == null) {
			return null;
		}
		List<EventBean> listEvents = new ArrayList<EventBean>();
		for (int i = 0, size = jsonArray.length(); i < size; i++) {
			JSONObject jsonEvent = jsonArray.getJSONObject(i);
			EventBean event = new EventBean();
			event.setId(jsonEvent.getString("activityId"));
			event.setName(jsonEvent.getString("activityName"));
			event.setAddress(jsonEvent.getString("activityAddress"));
			event.setOrganization(jsonEvent.getString("activityOrganization"));
			event.setTime(jsonEvent.getString("activityTime"));
			event.setState(jsonEvent.getString("activityState"));
			event.setDetail(jsonEvent.getString("activityDetail"));
			event.setUrl(jsonEvent.getString("activityUrl"));
			listEvents.add(event);
		}
		return listEvents;

	}

	/*
	 * 报名接口
	 */
	@Override
	public int baoming(Context context, String UserId) throws Exception {
		String parmaUrl = BASE_URL + "CooVenture/apply.action";
		String s = "{\"msg\":\"1\"}";
		JSONObject json = new JSONObject(HttpClintRemote.get(s));
		int code = Integer.valueOf(json.getString("msg"));
		return code;
	}

	/*
	 * 退出
	 */
	@Override
	public void exit() throws Exception{
		String parmaUrl = BASE_URL + "CooVenture/quit.action";
		HttpClintRemote.get(parmaUrl);
	}

}

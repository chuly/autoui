package com.lyc.autoui.selenium.thread;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import net.sourceforge.htmlunit.corejs.javascript.tools.debugger.Main;

import org.apache.http.HttpHost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.lyc.autoui.selenium.SeleniumByThread;
import com.lyc.autoui.selenium.bean.HttpProxyBean;
import com.lyc.autoui.selenium.conf.ConfigParam;
import com.lyc.autoui.utils.HttpClientProxy;
import com.lyc.autoui.utils.HttpProxySearcher;

public class ProxySearchThread extends Thread {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	private SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private int netType;//100以上为API，1-99为网页解析
	public ProxySearchThread(int netType){
		this.netType = netType;
	}
	@Override
	public void run() {
		setName("ProxySearchThread("+netType+")"+getName());
		log.info("代理查找线程启动。");
		while(true){
			try{
				log.info("当前检查任务数:" + ConfigParam.check_task_queue.size() + "/" + ConfigParam.max_check_task_num);
				while ((ConfigParam.check_task_queue.size() >= ConfigParam.max_check_task_num)
						|| ConfigParam.work_task_queue.size() >= ConfigParam.max_work_task_num) {
					log.info("检查任务数（" + ConfigParam.check_task_queue.size() + "）已达最大允许任务数（" + ConfigParam.max_check_task_num + "），"
							+ "或者工作任务数（"+ConfigParam.work_task_queue.size()+"）已达最大数（"+ConfigParam.max_work_task_num+"），等待60秒...");
					Thread.sleep(60 * 1000);
				}
				if(netType >= 100){
					if(netType == 100){
						List<HttpProxyBean> list = HttpProxySearcher.searchXiciAPIHttpProxy();
						List<HttpProxyBean> newProxyList = removeUsedProxy(list);
						submitCheckTask(newProxyList);
						Thread.sleep(15 * 60 * 1000);//15分钟
					}else if(netType == 101){
						List<HttpProxyBean> list = HttpProxySearcher.searchShitouAPIHttpProxy();
						List<HttpProxyBean> newProxyList = removeUsedProxy(list);
						submitCheckTask(newProxyList);
						Thread.sleep(15 * 1000);//15秒钟
					}else if(netType == 102){
						List<HttpProxyBean> list = HttpProxySearcher.searchWuyouHttpProxy(ConfigParam.wuyou_order_no,false);
						List<HttpProxyBean> newProxyList = removeUsedProxy(list);
						submitCheckTask(newProxyList);
						Thread.sleep(1000);//1秒钟
					}else if(netType == 103){
						List<HttpProxyBean> list = HttpProxySearcher.searchWuyouHttpProxy(ConfigParam.wuyou_order_no,true);
						List<HttpProxyBean> newProxyList = removeUsedProxy(list);
						submitCheckTask(newProxyList);
						Thread.sleep(1000);//1秒钟
					}
				}else{
					List<HttpProxyBean> list = getProxyHost();
					submitCheckTask(list);
				}
				
			}catch(Exception e){
				log.error("",e);
			}finally{
			}
		}
	}
	private void submitCheckTask(List<HttpProxyBean> list ){
		if(list == null || list.size() == 0){
			log.info("代理数为空，list="+list);
			return;
		}
		for (HttpProxyBean httpProxyBean : list) {
			ConfigParam.check_thread_pool.submit(new ProxyCheckThread(httpProxyBean));
		}
	}
	
	int netPage = 1;
	//获取代理，排除已使用的
	private List<HttpProxyBean> getProxyHost() throws Exception{
		List<HttpProxyBean> retList = Lists.newArrayList();
		int minSecond = 12 * 1000;//每分钟最多5次
		int maxSecond = 900 * 1000;
		int curSecond = minSecond;
		while(retList == null || retList.size() == 0){
			curSecond += 1000;
			int second = Math.min(curSecond, maxSecond);
			Thread.sleep(second);
			List<HttpProxyBean> list = getProxyHost(1);
			boolean isNetNew = false;//网站上的代理是否刷新
			for (HttpProxyBean httpProxyBean : list) {
				String key = httpProxyBean.getIp()+":"+httpProxyBean.getPort();
				if(!ConfigParam.used_proxy.containsKey(key)){
					isNetNew = true;
					break;
				}
			}
			if(!isNetNew){
				log.info("代理没有刷新，从原来的页码（"+netPage+"）开始获取");
				Thread.sleep(second);
				list = getProxyHost(++netPage);
			}else{
				log.info("代理已刷新，从第一页开始获取");
				netPage=1;
			}
			retList = removeUsedProxy(list);
		}
		curSecond = minSecond;;
		return retList;
	}
	//检查代理是否已用过，返回没用过的
	private List<HttpProxyBean> removeUsedProxy(List<HttpProxyBean> list){
		List<HttpProxyBean> retList = Lists.newArrayList();
		for (HttpProxyBean httpProxyBean : list) {
			String key = httpProxyBean.getIp()+":"+httpProxyBean.getPort();
			if(ConfigParam.used_proxy.containsKey(key)){
				log.info("此代理【"+key+"】已使用，忽略");
				continue;
			}else{
				retList.add(httpProxyBean);
				ConfigParam.used_proxy.put(key, new Date());
			}
		}
		return retList;
	}
	
	private List<HttpProxyBean> getProxyHost(int page){
		List<HttpProxyBean> list = Lists.newArrayList();
		try{
			// 从无忧免费代理取
			if(netType == 3){
				list = HttpProxySearcher.searchWuyouHttpProxy();
			}
			// 从快代理取
			else if(netType == 2){
				list = HttpProxySearcher.searchKuaidailiHttpProxy(page++);
			}
			// 从西祠代理取
			else if(netType == 1){
				list = HttpProxySearcher.searchXiciHttpProxy(page++);
			}
		}catch(Exception e){
			log.error("查询代理服务器异常",e);
			try {
				log.error("查询代理服务器异常,等待1分钟。");
				Thread.sleep(60 * 1000);
			} catch (Exception e1) {
			}
		}
		return list == null?Lists.newArrayList(new HttpProxyBean()):list;
	}
	
}

package com.lyc.autoui.selenium.thread;

import java.text.SimpleDateFormat;

import org.apache.http.HttpHost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lyc.autoui.selenium.bean.HttpProxyBean;
import com.lyc.autoui.selenium.conf.ConfigParam;
import com.lyc.autoui.selenium.persistence.JdbcUtil;
import com.lyc.autoui.utils.HttpClientProxy;

public class ProxyCheckThread extends Thread {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	private SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private HttpProxyBean httpProxyBean;
	public ProxyCheckThread(HttpProxyBean httpProxyBean){
		this.httpProxyBean = httpProxyBean;
	}
	@Override
	public void run() {
		setName("ProxyCheckThread-"+getName());
		log.info("代理检查线程启动。");
		try{
			log.info("待检查代理："+httpProxyBean);
			checkAndStartWork(httpProxyBean);
		}catch(Exception e){
			log.error("",e);
			log.info("代理验证发生异常，"+e.getMessage());
		}
	}
	
	//验证代理，并启动工作线程
	private void checkAndStartWork(HttpProxyBean httpProxyBean) throws Exception{
		while(ConfigParam.work_task_queue.size() >= ConfigParam.max_work_task_num){
			log.info("当前等待的工作任务数（"+ConfigParam.work_task_queue.size()+"）已达最大数（"+ConfigParam.max_work_task_num+"），等待5秒...");
			Thread.sleep(5 * 1000);
		}
		HttpHost hh = new HttpHost(httpProxyBean.getIp(), Integer.parseInt(httpProxyBean.getPort()), httpProxyBean.getHttpType());
		try {
			String checkResult = HttpClientProxy.checkProxy(hh);
			if(checkResult == null){
				log.info("代理验证结果：不可用");
			}else{
				log.info("代理验证结果：可用，开始启动工作线程...");
				httpProxyBean.setAddr(checkResult);
				ConfigParam.work_thread_pool.submit(new WorkThread(httpProxyBean));
				Long id = JdbcUtil.insert(httpProxyBean);
				if(id != null){
					httpProxyBean.setExtParam(String.valueOf(id));
				}
			}
		} catch (Exception e) {
			log.info("代理验证失败:"+e.getMessage());
			log.error("代理验证失败。"+e.getMessage());
		}
	}
	
}

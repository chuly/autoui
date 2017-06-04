package com.lyc.autoui.selenium;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lyc.autoui.selenium.conf.ConfigParam;
import com.lyc.autoui.selenium.persistence.JdbcUtil;
import com.lyc.autoui.selenium.thread.ProxySearchThread;


public class SeleniumByThread extends Thread{
	private static final Logger log = LoggerFactory.getLogger(SeleniumByThread.class);
	
	public static void main(String[] args) {
		log.info("主线程启动...");
		log.info("从DB中加载24小时内已用过的代理...");
		try {
			JdbcUtil.loadUserProxyFromDB();
		} catch (Exception e) {
			log.error("从DB中加载已用代理出错",e);
		}
		log.info("启动代理查找线程...");
		new ProxySearchThread(1).start();
		new ProxySearchThread(2).start();
		new ProxySearchThread(3).start();
		new ProxySearchThread(100).start();
		new ProxySearchThread(101).start();
//		new ProxySearchThread(102).start();//无忧代理付费接口，剩余时间长的优先获取
//		new ProxySearchThread(103).start();//无忧代理付费接口，随机
		while(true){
			try {
				Thread.sleep(60 * 1000);
			} catch (Exception e1) {
				log.error("",e1);
			}
			log.info("成功开始数:" + ConfigParam.success_start_count + "，成功结束数：" + ConfigParam.success_complete_count);
			log.info("等待任务数===工作任务:" + ConfigParam.work_task_queue.size() + "，检查任务：" + ConfigParam.check_task_queue.size());
			
		}
	}
	
}

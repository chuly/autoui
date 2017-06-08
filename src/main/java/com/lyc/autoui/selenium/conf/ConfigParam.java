package com.lyc.autoui.selenium.conf;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import com.google.common.collect.Maps;

public class ConfigParam {
	// 工作线程
	public static final int max_work_thread_num = 5;//工作线程最大数
	public static final int max_work_task_num = 10;//工作任务最大等待数
	public static final BlockingQueue<Runnable> work_task_queue = new LinkedBlockingQueue<Runnable>(max_work_task_num * 10);
	public static final ExecutorService work_thread_pool = new ThreadPoolExecutor(max_work_thread_num, max_work_thread_num, 0L, TimeUnit.MILLISECONDS, work_task_queue);
	
	// 检查线程
	public static final int max_check_thread_num = 50;//代理有效性检查线程最大数
	public static final int max_check_task_num = 100;//代理有效性检查任务最大数
	public static final BlockingQueue<Runnable> check_task_queue = new LinkedBlockingQueue<Runnable>(max_check_task_num * 10);
	public static final ExecutorService check_thread_pool = new ThreadPoolExecutor(max_check_thread_num, max_check_thread_num, 0L, TimeUnit.MILLISECONDS, check_task_queue);
	
	// 结果记录
	public static final AtomicInteger success_start_count = new AtomicInteger(0);//成功数（进入页面）
	public static final AtomicInteger success_complete_count = new AtomicInteger(0);//成功数 （完成页面内所有操作）
	
	//已使用的代理
	public static final Map<String,Date> used_proxy = Maps.newConcurrentMap();//已使用的代理
	
	// selenium驱动 见./file/目录下，或者自己从网上下载对应版本的驱动
	public static final String chrome_driver_file = "d:/selenium/chromedriver/v2.29/chromedriver.exe";//chrome驱动
	public static final String firefox_driver_file = "d:/selenium/firefoxdriver/v0.16.1/geckodriver.exe";//火狐驱动
	
	// DB
	public static final String mysql_url="jdbc:mysql://localhost:3306/test?autoReconnect=true&useSSL=false&characterEncoding=UTF-8&user=root&password=root";
	public static final boolean save_to_db = true;
	public static final boolean load_used_proxy_from_db = true;
	
	public static final String wuyou_order_no = "";
}

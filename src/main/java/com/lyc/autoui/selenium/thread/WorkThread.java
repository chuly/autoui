package com.lyc.autoui.selenium.thread;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lyc.autoui.constenum.PCUserAgentEnum;
import com.lyc.autoui.selenium.bean.HttpProxyBean;
import com.lyc.autoui.selenium.conf.ConfigParam;
import com.lyc.autoui.selenium.persistence.JdbcUtil;
import com.lyc.autoui.utils.DelayUtil;

public class WorkThread extends Thread {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	private SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private HttpProxyBean proxyHost;
	public WorkThread(HttpProxyBean proxyHost){
		this.proxyHost = proxyHost;
	}

	@Override
	public void run() {
		setName("WorkThread-"+getName());
		log.info("工作线程启动。");
		try{
			execAutoQuery();
		}catch(Exception e){
			log.error("",e);
		}finally{
		}
	}

	public void execAutoQuery() throws Exception{
		log.info("使用代理：" + proxyHost.getIp() + ":" + proxyHost.getPort());
		System.setProperty("webdriver.chrome.driver", ConfigParam.chrome_driver_file);
		String proxyIpAndPort = proxyHost.getIp()+":"+proxyHost.getPort();
		
		DesiredCapabilities cap = new DesiredCapabilities();
		Proxy proxy=new Proxy();
		proxy.setHttpProxy(proxyIpAndPort).setFtpProxy(proxyIpAndPort).setSslProxy(proxyIpAndPort);
		cap.setCapability(CapabilityType.ForSeleniumServer.AVOIDING_PROXY, true);
		cap.setCapability(CapabilityType.ForSeleniumServer.ONLY_PROXYING_SELENIUM_TRAFFIC, true);
		System.setProperty("http.nonProxyHosts", "localhost");
		cap.setCapability(CapabilityType.PROXY, proxy);
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		options.addArguments("user-agent="+PCUserAgentEnum.getRandomUserAgent());
		cap.setCapability(ChromeOptions.CAPABILITY, options);
		WebDriver dr = new ChromeDriver(cap);
		dr.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
		dr.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		try {
			runToutiao(dr);
		} finally {
			if(dr != null){
				dr.quit();
			}
		}
		
	}
	private void updateDB(String startOrEnd){
		if(proxyHost.getExtParam() != null){
			Long id = Long.parseLong(proxyHost.getExtParam());
			try {
				JdbcUtil.update(id, startOrEnd);
			} catch (Exception e) {
				log.error("更新DB出错",e);
			}
		}
		
	}
	// 头条
	private void runToutiao(WebDriver dr) {
		String adUrl = "http://www.toutiao.com/i6420379203010560513/";
		int maxH = 2000;
		dr.get(adUrl);
		WebElement we = dr.findElement(By.className("pgc-link"));
		if(we != null){
			updateDB("start");
			DelayUtil.delay(1000, 2000);
			ConfigParam.success_start_count.incrementAndGet();
			JavascriptExecutor driver_js= (JavascriptExecutor) dr;
			int scrollHeight = Integer.parseInt(driver_js.executeScript("return document.body.scrollHeight").toString());//4305
			int screenHeight = Integer.parseInt(driver_js.executeScript("return window.screen.height").toString());//864
			maxH = scrollHeight-screenHeight;
			log.info("页面高度："+scrollHeight+"-"+screenHeight+"="+maxH);
			int curH = 0;
			int scrollCount = 0;
			while(curH < maxH){
				curH += 200+new Random().nextInt(400);
				String js = "window.scrollTo(0,"+curH+")";
				log.info("执行js："+js);
				driver_js.executeScript(js);
				scrollCount++;
				DelayUtil.delay(1000, 2000);
			}
			if(scrollCount >= 1){
				ConfigParam.success_complete_count.incrementAndGet();
				updateDB("end");
			}
			DelayUtil.delay(500, 1500);
			log.info("退出");
		}
	}
	
}

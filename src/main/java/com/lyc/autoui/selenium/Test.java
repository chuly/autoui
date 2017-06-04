package com.lyc.autoui.selenium;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.http.HttpHost;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.lyc.autoui.constenum.PCUserAgentEnum;
import com.lyc.autoui.selenium.bean.HttpProxyBean;
import com.lyc.autoui.selenium.conf.ConfigParam;
import com.lyc.autoui.selenium.persistence.JdbcUtil;
import com.lyc.autoui.utils.DelayUtil;
import com.lyc.autoui.utils.HttpClientProxy;
import com.lyc.autoui.utils.HttpProxySearcher;


public class Test extends Thread{
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	private static Map<String,String> usedProxy = Maps.newHashMap();
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		try {
			new Test().testBaijia();
		} catch (Exception e) {
			e.printStackTrace();
		}
		long endTimme = System.currentTimeMillis();
		System.out.println((endTimme-startTime));
	}
	public void testBaijia() throws Exception{
		System.setProperty("webdriver.chrome.driver", ConfigParam.chrome_driver_file);
	    ChromeOptions options = new ChromeOptions();
	    options.addArguments("user-agent="+PCUserAgentEnum.getRandomUserAgent());
	    WebDriver dr = new ChromeDriver(options);
	    String adUrl = "http://www.toutiao.com/i6420379203010560513/";
		int maxH = 2000;
		dr.get(adUrl);
		WebElement we = dr.findElement(By.className("pgc-link"));
		if(we != null){
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
			}
			DelayUtil.delay(500, 1500);
			log.info("退出");
		}
	}
	
	private void delay(long t1,int randomT2) throws Exception{
		long sleepTime = t1 + new Random().nextInt(randomT2);
		System.out.println("睡眠ms："+sleepTime);
		Thread.sleep(sleepTime);
	}
	SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private void log(String s){
		System.out.println("SeleniumTest "+f.format(new Date())+" "+s);
//		log.info(s);
	}
	
}

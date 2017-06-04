package com.lyc.autoui.utils;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DelayUtil {
	private static final Logger log = LoggerFactory.getLogger(DelayUtil.class);
	
	public static void delay(long t1,int randomT2){
		long sleepTime = t1 + new Random().nextInt(randomT2);
		log.info("睡眠（ms）："+sleepTime);
		try {
			Thread.sleep(sleepTime);
		} catch (Exception e) {
			log.error("睡眠异常", e);
		}
	}
}

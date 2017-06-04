package com.lyc.autoui.utils.freeHttpProxyParser;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.lyc.autoui.selenium.bean.HttpProxyBean;

/**
 * 解析http://www.qiushibaike.com
 * @author chuly
 *
 */
public class XicidailiHtmlParser implements DailiHtmlParserItf{
	
	public List<HttpProxyBean> parseList(String html) {
		List<HttpProxyBean> list = new ArrayList();
		Document doc = Jsoup.parse(html);//connect("http://www.weather.com.cn/html/weather/101280101.shtml").get();
		Elements elements1 = doc.select("table#ip_list").select("tr");
		if(elements1 != null && elements1.size() > 1){
			for (int i = 1; i < elements1.size(); i++) {
				Element element = elements1.get(i);
				Elements tds = element.select("td");
				if(tds!= null && tds.size() > 0){
					String ip = tds.get(1).text();
					String port = tds.get(2).text();
					String addr = tds.get(3).select("a").text();
					String httpOrHttps = tds.get(5).text();
					HttpProxyBean hp = new HttpProxyBean();
					hp.setIp(ip);
					hp.setPort(port);
					hp.setAddr(addr);
					if("http".equalsIgnoreCase(httpOrHttps)){
						hp.setHttpType("http");
					}else if("https".equalsIgnoreCase(httpOrHttps)){
						hp.setHttpType("https");
					}
					list.add(hp);
				}
			}
		}
		return list;
	}


	private void log(String s){
		System.out.println(s);
	}
}

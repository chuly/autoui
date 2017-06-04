package com.lyc.autoui.utils.freeHttpProxyParser;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.lyc.autoui.selenium.bean.HttpProxyBean;

/**
 * 解析http://www.data5u.com/
 * @author chuly
 *
 */
public class WuyoudailiHtmlParser implements DailiHtmlParserItf{
	
	public List<HttpProxyBean> parseList(String html) {
		List<HttpProxyBean> list = new ArrayList();
		Document doc = Jsoup.parse(html);//connect("http://www.weather.com.cn/html/weather/101280101.shtml").get();
		Elements elements1 = doc.select("div.wlist").select("ul.l2");
		if(elements1 != null && elements1.size() > 1){
			for (int i = 0; i < elements1.size(); i++) {
				Element element = elements1.get(i);
				Elements tds = element.select("li");
				if(tds!= null && tds.size() > 0){
					String ip = tds.get(0).text();
					String port = tds.get(1).text();
					String addr = tds.get(4).select("a").text();
//					if(addr.indexOf("中国")<0){//排除国外的
//						continue;
//					}
					HttpProxyBean hp = new HttpProxyBean();
					hp.setIp(ip);
					hp.setPort(port);
					hp.setAddr(addr);
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

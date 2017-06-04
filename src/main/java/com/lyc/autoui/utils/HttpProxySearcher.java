package com.lyc.autoui.utils;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.lyc.autoui.selenium.bean.HttpProxyBean;
import com.lyc.autoui.utils.freeHttpProxyParser.DailiHtmlParserItf;
import com.lyc.autoui.utils.freeHttpProxyParser.KuaidailiHtmlParser;
import com.lyc.autoui.utils.freeHttpProxyParser.WuyoudailiHtmlParser;
import com.lyc.autoui.utils.freeHttpProxyParser.XicidailiHtmlParser;

public class HttpProxySearcher {

	public static void main(String[] args) throws Exception{
		List<HttpProxyBean> proxyHostList = searchShitouAPIHttpProxy();
		System.out.println(JSON.toJSONString(proxyHostList));
	}
	/**
	 * 快代理 www.kuaidaili.com
	 */
	public static List<HttpProxyBean> searchKuaidailiHttpProxy(int page) throws Exception{
		String html = searchHttpProxy("www.kuaidaili.com","http://www.kuaidaili.com/free/intr/"+page+"/");
		if(html != null && !"Invalid Page".equals(html) && html.length()>0){
			KuaidailiHtmlParser pa = new KuaidailiHtmlParser();
			List<HttpProxyBean> list = pa.parseList(html);
			setProxyOriUrl(list, "www.kuaidaili.com");
			System.out.println(JSON.toJSONString(list));
			return list;
		}
		return null;
	}
	
	/**
	 * 西祠代理 http://www.xicidaili.com
	 */
	public static List<HttpProxyBean> searchXiciHttpProxy(int page) throws Exception{
		String html = searchHttpProxy("www.xicidaili.com","http://www.xicidaili.com/nn/"+page);
		if(html != null && !"Invalid Page".equals(html) && html.length()>0){
			XicidailiHtmlParser pa = new XicidailiHtmlParser();
			List<HttpProxyBean> list = pa.parseList(html);
			setProxyOriUrl(list, "www.xicidaili.com");
			System.out.println(JSON.toJSONString(list));
			return list;
		}
		return null;
	}
	/**
	 * 西祠代理API获取  http://api.xicidaili.com/free2016.txt
	 */
	public static List<HttpProxyBean> searchXiciAPIHttpProxy() throws Exception{
		String html = HttpClientUtil.execGet("http://api.xicidaili.com/free2016.txt");
		List<HttpProxyBean> list = Lists.newArrayList();
		if(html != null && !"Invalid Page".equals(html) && html.length()>0){
			String[] arr = html.split("\n");
			if(arr != null && arr.length > 0){
				for(String ar : arr){
					if(ar == null || ar.length() == 0)
						continue;
					ar =ar.replace("\r", "");
					String[] pp = ar.split(":");
					if(pp == null || pp.length < 2)
						continue;
					HttpProxyBean h = new HttpProxyBean();
					h.setIp(pp[0]);
					h.setPort(pp[1]);
					list.add(h);
				}
			}
		}
//		System.out.println(html);
//		System.out.println(html.split("\n").length);
		setProxyOriUrl(list, "http://api.xicidaili.com/free2016.txt");
		return list;
	}
		
	/**
	 * http://www.shitou0707.com:3399/tools/proxyIP.ashx?action=GeProxyIP
	 */
	public static List<HttpProxyBean> searchShitouAPIHttpProxy() throws Exception{
		String html = HttpClientUtil.execGet("http://www.shitou0707.com:3399/tools/proxyIP.ashx?action=GeProxyIP");
		List<HttpProxyBean> list = Lists.newArrayList();
		if(html != null && !"Invalid Page".equals(html) && html.length()>0){
			String[] arr = html.split("\n");
			if(arr != null && arr.length > 0){
				for(String ar : arr){
					if(ar == null || ar.length() == 0)
						continue;
					ar =ar.replace("\r", "");
					String[] pp = ar.split(":");
					if(pp == null || pp.length < 2)
						continue;
					HttpProxyBean h = new HttpProxyBean();
					h.setIp(pp[0]);
					h.setPort(pp[1]);
					list.add(h);
				}
			}
		}
		setProxyOriUrl(list, "http://www.shitou0707.com:3399/tools/proxyIP.ashx?action=GeProxyIP");
		return list;
	}
	
	/**
	 * 无忧代理 http://www.data5u.com/
	 */
	public static List<HttpProxyBean> searchWuyouHttpProxy() throws Exception{
		String html = searchHttpProxy("www.data5u.com","http://www.data5u.com");
		if(html != null && html.length()>0){
			DailiHtmlParserItf pa = new WuyoudailiHtmlParser();
			List<HttpProxyBean> list = pa.parseList(html);
			setProxyOriUrl(list, "www.data5u.com");
			System.out.println(JSON.toJSONString(list));
			return list;
		}
		return null;
	}
	public static List<HttpProxyBean> searchWuyouHttpProxy(String orderNo,boolean random) throws Exception{
		String randomParam = "";
		if(random){
			randomParam = "&random";
		}
		java.net.URL url = new java.net.URL("http://api.ip.data5u.com/dynamic/get.html?order=" + orderNo + "&ttl"+randomParam);
    	HttpURLConnection connection = (HttpURLConnection)url.openConnection();
    	connection.setConnectTimeout(3000);
    	connection = (HttpURLConnection)url.openConnection();
    	
        InputStream raw = connection.getInputStream();  
        InputStream in = new BufferedInputStream(raw);  
        byte[] data = new byte[in.available()];
        int bytesRead = 0;  
        int offset = 0;  
        while(offset < data.length) {  
            bytesRead = in.read(data, offset, data.length - offset);  
            if(bytesRead == -1) {  
                break;  
            }  
            offset += bytesRead;  
        }  
        in.close();  
        raw.close();
        String respStr = new String(data, "UTF-8");
        System.out.println("获取的ip："+respStr);
		String[] res = respStr.split("\n");
		List<HttpProxyBean> ipList = new ArrayList<HttpProxyBean>();
		for (String ip : res) {
			try {
				String[] parts = ip.split(",");
				if (Integer.parseInt(parts[1]) > 0) {
					HttpProxyBean hb = new HttpProxyBean();
					String[] ipport = parts[0].split(":");
					hb.setIp(ipport[0]);
					hb.setPort(ipport[1]);
					ipList.add(hb);
				}else{
					System.out.println("此ip有效期已过："+ip);
				}
			} catch (Exception e) {
			}
		}
		setProxyOriUrl(ipList, "http://api.ip.data5u.com/dynamic/get.html");
		return ipList;
	}
	
	private static void setProxyOriUrl(List<HttpProxyBean> list, String oriUrl){
		if(list != null && list.size() > 0){
			for (HttpProxyBean httpProxyBean : list) {
				httpProxyBean.setOriUrl(oriUrl);
			}
		}
	}
	
	private static String searchHttpProxy(String host,String url) throws Exception{
		Map<String, String> headMap = new HashMap();
		headMap.put("Accept", "text/javascript, text/html, application/xml, text/xml, */*");
		headMap.put("Accept-Encoding", "gzip, deflate, sdch");
		headMap.put("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6,zh-TW;q=0.4,ja;q=0.2");
		headMap.put("Connection", "keep-alive");
		headMap.put("Host", host);
		headMap.put("Referer", "http://"+host+"/");
		headMap.put("Upgrade-Insecure-Requests", "1");
		headMap.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36");
		return HttpClientUtil.execGet(url,headMap);
	}
	
}

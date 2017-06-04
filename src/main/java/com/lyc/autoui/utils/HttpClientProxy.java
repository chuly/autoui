package com.lyc.autoui.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;

public class HttpClientProxy {

	public static void main(String[] args) throws Exception {
		String resp = sendGetRequest("http://s.ip-cdn.com/css/bootstrap.min.css");
		System.out.println(resp);
	}

	public static String checkProxy(HttpHost proxyHost) throws Exception {
//		String url ="http://s.ip-cdn.com/css/bootstrap.min.css";
//		String url ="http://1212.ip138.com/ic.asp";
		String url ="1212.ip138.com";
		String checkStr = "您的IP地址";
		String result = null;
		HttpHost target = new HttpHost(url, 80, "http");
		HttpGet httpGet = new HttpGet("/ic.asp");
//		Map<String, String> headMap = createHeader("1212.ip138.com");
//		for(Map.Entry<String, String> e : headMap.entrySet()){
//			httpGet.addHeader(e.getKey(), e.getValue());
//		}
		RequestConfig config = RequestConfig.custom()
				.setConnectTimeout(10000)
				.setSocketTimeout(5000)
				.setProxy(proxyHost).build();
		httpGet.setConfig(config);
		HttpClient hc = HttpClients.createDefault();
//		hc.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT,5000);
//		hc.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,5000);
		
		HttpResponse response = hc.execute(target, httpGet);
		int status = response.getStatusLine().getStatusCode();
		if (status == 200) {// 如果状态码为200,就是正常返回
			result = EntityUtils.toString(response.getEntity(),"GBK");
//			System.out.println(result);
			if(result.indexOf(checkStr)>=0){
				String ss = result.substring(result.indexOf("<center>")+8,result.indexOf("</center>"));
				ss = ss.substring(ss.indexOf("来自：")+3);
				System.out.println(ss);
				return ss;
			}
			return null;
		} else {
			System.out.println("错误的状态 ： status=" + status);
		}
		return null;
	}
	
	private static Map<String, String> createHeader(String host){
		Map<String, String> headMap = new HashMap();
		headMap.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		headMap.put("Accept-Encoding", "gzip, deflate, sdch");
		headMap.put("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6,zh-TW;q=0.4,ja;q=0.2");
		headMap.put("Connection", "keep-alive");
//		headMap.put("Cookie", "_qqq_uuid_=\"2|1:0|10:1488700163|10:_qqq_uuid_|56:ZDdjMTljZTRmZmZlMzZiMzA1NDI3ZGE3NTU1ODM3MGE2MmQ4NDM3Zg==|9bd0755c25e27aac82fa5c3b0a0aefca52f948137affe1ba6785bb37b4319b8c\"; Hm_lvt_18a964a3eb14176db6e70f1dd0a3e557=1488701485; _xsrf=2|9d7f9f6a|a883e1a963cdd88bb38665994182d6d6|1488701672; __cur_art_index=7801");
		headMap.put("Host", host);
//		headMap.put("Referer", "http://"+host+"/");
		headMap.put("Upgrade-Insecure-Requests", "1");
		headMap.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36");
		
		return headMap;
	}
	public static String sendGetRequest(String urlWithParam) throws Exception {
		String responseBody = null;
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpGet httpget = new HttpGet(urlWithParam);
			System.out.println("Executing request " + httpget.getRequestLine());
			// Create a custom response handler
			ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
				public String handleResponse(final HttpResponse response) throws ClientProtocolException, IOException {
					int status = response.getStatusLine().getStatusCode();
					if (status >= 200 && status < 300) {
						HttpEntity entity = response.getEntity();
						return entity != null ? EntityUtils.toString(entity) : null;
					} else {
						HttpEntity entity = response.getEntity();
						String ss = entity != null ? EntityUtils.toString(entity) : null;
						System.out.println(ss);
						throw new ClientProtocolException("Unexpected response status: " + status);
					}
				}
			};
			responseBody = httpclient.execute(httpget, responseHandler);
			System.out.println("----------------------------------------"+responseBody);
		} finally {
			httpclient.close();
		}
		return responseBody;
	}

	private static HttpHost getHttpHost() {
		return new HttpHost("222.187.227.40", 10000, "http");
	}

}

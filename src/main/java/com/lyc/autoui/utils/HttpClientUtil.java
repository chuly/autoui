package com.lyc.autoui.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

public class HttpClientUtil {

	public static String execGet(String urlWithParam) throws Exception {
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
	
	public static String execGet(String urlWithParam, Map<String, String> headMap) throws Exception {
		String responseBody = null;
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpGet httpget = new HttpGet(urlWithParam);
			if(headMap != null && headMap.size() > 0){
				for(Map.Entry<String, String> e : headMap.entrySet()){
					httpget.addHeader(e.getKey(), e.getValue());
				}
			}
			System.out.println("Executing request " + httpget.getRequestLine());
			// Create a custom response handler
			ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
				public String handleResponse(final HttpResponse response) throws ClientProtocolException, IOException {
					int status = response.getStatusLine().getStatusCode();
					if (status >= 200 && status < 300) {
						HttpEntity entity = response.getEntity();
						return entity != null ? EntityUtils.toString(entity) : null;
					} else {
						throw new ClientProtocolException("Unexpected response status: " + status);
					}
				}
			};
			responseBody = httpclient.execute(httpget, responseHandler);
		} finally {
			httpclient.close();
		}
		return responseBody;
	}
	
	public static String execPost(String urlWithParam, Map<String, String> headMap, Map<String, String> paramMap)
			throws Exception {
		List<NameValuePair>list = new ArrayList<NameValuePair>();  
		if(paramMap != null && paramMap.size() > 0){
			for(Map.Entry<String, String> e : paramMap.entrySet()){
				list.add(new BasicNameValuePair(e.getKey(), e.getValue()));  
			}
			 
		}
		return post(urlWithParam, headMap, new UrlEncodedFormEntity(list,HTTP.UTF_8));
	}
	public static String execPost(String urlWithParam, Map<String, String> headMap, String paramStr)
			throws Exception {
		StringEntity stringEntity = new StringEntity(paramStr);//param参数，可以为"key1=value1&key2=value2"的一串字符串  
	    stringEntity.setContentType("application/x-www-form-urlencoded");  
		return post(urlWithParam, headMap, stringEntity);
	}
	
	private static String post(String urlWithParam, Map<String, String> headMap, HttpEntity entity)
			throws Exception {
		String responseBody = null;
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpPost httppost = new HttpPost(urlWithParam);
			if(headMap != null && headMap.size() > 0){
				for(Map.Entry<String, String> e : headMap.entrySet()){
					httppost.addHeader(e.getKey(), e.getValue());
				}
			}
			httppost.setEntity(entity);
			System.out.println("Executing request " + httppost.getRequestLine());
			// Create a custom response handler
			ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
				public String handleResponse(final HttpResponse response) throws ClientProtocolException, IOException {
					int status = response.getStatusLine().getStatusCode();
					if (status >= 200 && status < 300) {
						HttpEntity entity = response.getEntity();
						return entity != null ? EntityUtils.toString(entity) : null;
					} else {
						throw new ClientProtocolException("Unexpected response status: " + status);
					}
				}
			};
			responseBody = httpclient.execute(httppost, responseHandler);
			System.out.println("----------------------------------------responseBody size:"+responseBody.length());
		} finally {
			httpclient.close();
		}
		return responseBody;
	}
	/**
	 * 下载文件
	 * @param remoteFileName
	 * @param localFileName
	 */
	public static void downLoad(String url, String remoteFileName, String localFileName) {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        OutputStream out = null;
        InputStream in = null;
        
        try {
            HttpGet httpGet = new HttpGet(url);
            httpGet.addHeader("fileName", remoteFileName);

            HttpResponse httpResponse = httpClient.execute(httpGet);
            HttpEntity entity = httpResponse.getEntity();
            in = entity.getContent();

            long length = entity.getContentLength();
            if (length <= 0) {
                System.out.println("下载文件不存在！");
                return;
            }

            System.out.println("The response value of token:" + httpResponse.getFirstHeader("token"));

            File file = new File(localFileName);
            
            out = new FileOutputStream(file);  
            byte[] buffer = new byte[4096];
            int readLength = 0;
            while ((readLength=in.read(buffer)) > 0) {
                byte[] bytes = new byte[readLength];
                System.arraycopy(buffer, 0, bytes, 0, readLength);
                out.write(bytes);
            }
            
            out.flush();
            
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                if(in != null){
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            try {
                if(out != null){
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

package com.lyc.autoui.utils.freeHttpProxyParser;

import java.util.List;

import com.lyc.autoui.selenium.bean.HttpProxyBean;

public interface DailiHtmlParserItf {
	
	List<HttpProxyBean> parseList(String html);

}

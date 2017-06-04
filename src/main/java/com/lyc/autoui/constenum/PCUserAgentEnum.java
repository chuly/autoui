package com.lyc.autoui.constenum;

import java.util.Random;

public class PCUserAgentEnum {
	public static final String[][] user_agent_enum = {
		{"1","Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.0.3) Gecko/2008092417 Firefox/3.0.3"}
		,{"1","Dalvik/1.6.0 (Linux; U; Android 4.4.2; Nexus 6 Build/KOT49H)"}
		,{"1","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36"}
		,{"1","Mozilla/5.0 (Windows NT 6.1; WOW64; rv:39.0) Gecko/20100101 Firefox/39.0"}
		,{"1","Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_6_8; en-us) AppleWebKit/534.50 (KHTML, like Gecko) Version/5.1 Safari/534.50"}//safari 5.1 – MAC
		,{"1","Mozilla/5.0 (Windows; U; Windows NT 6.1; en-us) AppleWebKit/534.50 (KHTML, like Gecko) Version/5.1 Safari/534.50"}//safari 5.1 – Windows
		,{"1","Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0;"}//IE 9.0
		,{"1","Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0; Trident/4.0)"}//IE 8.0
		,{"1","Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.0)"}//IE 7.0
		,{"1"," Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1)"}//IE 6.0
		,{"1","Mozilla/5.0 (Macintosh; Intel Mac OS X 10.6; rv:2.0.1) Gecko/20100101 Firefox/4.0.1"}//
		,{"1","Mozilla/5.0 (Windows NT 6.1; rv:2.0.1) Gecko/20100101 Firefox/4.0.1"}//
		,{"1","Opera/9.80 (Macintosh; Intel Mac OS X 10.6.8; U; en) Presto/2.8.131 Version/11.11"}//
		,{"1","Opera/9.80 (Windows NT 6.1; U; en) Presto/2.8.131 Version/11.11"}//
		,{"1","Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_0) AppleWebKit/535.11 (KHTML, like Gecko) Chrome/17.0.963.56 Safari/535.11"}//
		,{"1","Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; Maxthon 2.0)"}//傲游（Maxthon）
		,{"1","Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; TencentTraveler 4.0)"}//腾讯TT
		,{"1","Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)"}//世界之窗（The World） 2.x
		,{"1","Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; The World)"}//世界之窗（The World） 3.x
		,{"1","Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; Trident/4.0; SE 2.X MetaSr 1.0; SE 2.X MetaSr 1.0; .NET CLR 2.0.50727; SE 2.X MetaSr 1.0)"}//搜狗浏览器 1.x
		,{"1","Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; 360SE)"}//360浏览器
		,{"10","Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.1; Trident/4.0; .NET CLR 2.0.50727; .NET CLR 3.0.04506.30; .NET CLR 3.0.04506.648)"}
		,{"10","Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.1; Trident/4.0; .NET CLR 2.0.50727; InfoPath.1"}
		,{"10","Mozilla/4.0 (compatible; GoogleToolbar 5.0.2124.2070; Windows 6.0; MSIE 8.0.6001.18241)"}
		,{"10","Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.1; Trident/4.0; EasyBits GO v1.0; InfoPath.1; .NET CLR 2.0.50727; .NET CLR 3.0.4506.2152; .NET CLR 3.5.30729)"}
		,{"10","Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0)"}
		,{"10","Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0; Sleipnir/2.9.8)"}
		,{"10","Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.0; Trident/5.0)"}
		,{"10","Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.1; Trident/6.0)"}
		,{"10","Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.2; Win64; x64; Trident/6.0)"}
		,{"10","Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.2; Trident/6.0)"}
		,{"10","Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.2; WOW64; Trident/6.0)"}
		,{"10","Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.2; Win64; x64; Trident/6.0)"}
		,{"10","Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.2; ARM; Trident/6.0)"}
		,{"10","Mozilla/5.0 (Windows; U; Windows NT 6.1; en-US) AppleWebKit/534.7 (KHTML, like Gecko) Chrome/7.0.517.43 Safari/534.7"}
		,{"10","Mozilla/5.0 (Windows; U; Windows NT 6.0; en-US) AppleWebKit/534.10 (KHTML, like Gecko) Chrome/8.0.552.224 Safari/534.10 ChromePlus/1.5.2.0"}
		,{"10","Mozilla/5.0 (en-us) AppleWebKit/534.14 (KHTML, like Gecko; Google Wireless Transcoder) Chrome/9.0.597 Safari/534.14"}
		,{"10","Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US) AppleWebKit/534.16 (KHTML, like Gecko) Chrome/10.0.648.151 Safari/534.16"}
		,{"10","Mozilla/5.0 (Macintosh; Intel Mac OS X 10_6_8) AppleWebKit/534.24 (KHTML, like Gecko) Chrome/11.0.696.71 Safari/534.24"}
		,{"10","Mozilla/5.0 (Macintosh; Intel Mac OS X 10_6_8) AppleWebKit/534.24 (KHTML, like Gecko) Iron/11.0.700.2 Chrome/11.0.700.2 Safari/534.24"}
		,{"10","Mozilla/5.0 (Windows NT 6.1) AppleWebKit/534.24 (KHTML, like Gecko) Chrome/11.0.696.65 Safari/534.24"}
		,{"10","Mozilla/5.0 (Windows NT 5.1) AppleWebKit/534.30 (KHTML, like Gecko) Chrome/12.0.742.122 Safari/534.30 ChromePlus/1.6.3.1"}
		,{"10","Mozilla/5.0 (Macintosh; Intel Mac OS X 10_6_8) AppleWebKit/534.30 (KHTML, like Gecko) Chrome/12.0.742.112 Safari/534.30"}
		,{"10","Mozilla/5.0 (Windows NT 6.1) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.107 Safari/535.1"}
		,{"10","Mozilla/5.0 (Macintosh; Intel Mac OS X 10_6_8) AppleWebKit/535.1 (KHTML, like Gecko) RockMelt/0.9.64.361 Chrome/13.0.782.218 Safari/535.1"}
		,{"10","Mozilla/5.0 (Macintosh; Intel Mac OS X 10_6_8) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.112 Safari/535.1"}
		,{"10","Mozilla/5.0 (Macintosh; Intel Mac OS X 10_6_8) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.220 Safari/535.1"}
		,{"10","Mozilla/5.0 (Macintosh; Intel Mac OS X 10_6_8) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/14.0.835.202 Safari/535.1"}
		,{"10","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/14.0.835.202 Safari/535.1"}
		,{"10","Mozilla/5.0 (Windows NT 5.1) AppleWebKit/535.2 (KHTML, like Gecko) Chrome/15.0.874.120 Safari/535.2"}
		,{"10","Mozilla/5.0 (X11; Linux i686) AppleWebKit/535.2 (KHTML, like Gecko) Ubuntu/10.04 Chromium/15.0.874.106 Chrome/15.0.874.106 Safari/535.2"}
		,{"10","Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/535.2 (KHTML, like Gecko) Chrome/15.0.874.106 Safari/535.2"}
		,{"10","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.7 (KHTML, like Gecko) Chrome/16.0.912.75 Safari/535.7"}
		,{"10","Mozilla/5.0 (Macintosh; Intel Mac OS X 10_6_8) AppleWebKit/535.7 (KHTML, like Gecko) Chrome/16.0.912.75 Safari/535.7"}
		,{"10","Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.0; SLCC1; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30618; Lunascape 4.7.3)"}
		,{"10","Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; .NET CLR 1.1.4322; .NET CLR 2.0.50727; Lunascape 5.0 alpha2)"}
		,{"10","Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; Trident/4.0; .NET CLR 1.1.4322; .NET CLR 2.0.50727; InfoPath.1; .NET CLR 3.0.4506.2152; .NET CLR 3.5.30729; Lunascape 5.0 alpha2)"}
		,{"10","Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.0; Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1) ; SLCC1; .NET CLR 2.0.50727; Media Center PC 5.0; .NET CLR 3.0.04506; Tablet PC 2.0; Lunascape 5.0 alpha2)"}
		,{"10","Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; MAGW; .NET4.0C; Lunascape 6.5.8.24780)"}
		,{"10","Mozilla/5.0 (Macintosh; U; PPC Mac OS X Mach-O; ja-JP-mac; rv:1.8) Gecko/20051111 Firefox/1.5"}
		,{"10","Mozilla/5.0 (Windows; U; Windows NT 5.1; ja; rv:1.8) Gecko/20051111 Firefox/1.5"}
		,{"10","Mozilla/5.0 (X11; U; Linux i686; en-US; rv:1.8) Gecko/20051111 Firefox/1.5"}
		,{"10","Mozilla/5.0 (Windows; U; Windows NT 5.1; ja; rv:1.8.0.1) Gecko/20060111 Firefox/1.5.0.1"}
		,{"10","Mozilla/5.0 (X11; U; Linux i686; ja; rv:1.8.0.2) Gecko/20060308 Firefox/1.5.0.2"}
		,{"10","Mozilla/5.0 (Windows; U; Windows NT 5.1; ja; rv:1.8.0.3) Gecko/20060426 Firefox/1.5.0.3"}
		,{"10","Mozilla/5.0 (X11; U; Linux i686; ja; rv:1.8.0.4) Gecko/20060508 Firefox/1.5.0.4"}
		,{"10","Mozilla/5.0 (Windows; U; Windows NT 5.1; ja; rv:1.8.1.20) Gecko/20081217 Firefox/2.0.0.20"}
		,{"10","Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.8.1.12) Gecko/20080201 Firefox/2.0.0.12"}
		,{"10","Mozilla/5.0 (Macintosh; U; Intel Mac OS X; ja-JP-mac; rv:1.8.1.20) Gecko/20081217 Firefox/2.0.0.20"}
		,{"10","Mozilla/5.0 (Windows; U; Windows NT 5.1; ja; rv:1.9.0.6) Gecko/2009011913 Firefox/3.0.6"}
		,{"10","Mozilla/5.0 (Windows; U; Windows NT 6.0; ja; rv:1.9.0.6) Gecko/2009011913 Firefox/3.0.6 (.NET CLR 3.5.30729)"}
		,{"10","Mozilla/5.0 (Windows; U; Windows NT 6.0; ja; rv:1.9.0.17) Gecko/2009122116 Firefox/3.0.17 GTB6 (.NET CLR 3.5.30729)"}
		,{"10","Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.5; ja-JP-mac; rv:1.9.0.6) Gecko/2009011912 Firefox/3.0.6"}
		,{"10","Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.5; ja-JP-mac; rv:1.9.0.6) Gecko/2009011912 Firefox/3.0.6 GTB5"}
		,{"10","Mozilla/5.0 (Windows NT 6.1; rv:2.0) Gecko/20100101 Firefox/4.0"}
		,{"10","Mozilla/5.0 (Macintosh; Intel Mac OS X 10.5; rv:2.0) Gecko/20100101 Firefox/4.0"}
		,{"10","Mozilla/5.0 (Windows NT 6.1; WOW64; rv:5.0) Gecko/20100101 Firefox/5.0"}
		,{"10","Mozilla/5.0 (Macintosh; Intel Mac OS X 10.5; rv:5.0.1) Gecko/20100101 Firefox/5.0.1"}
		,{"10","Mozilla/5.0 (Windows NT 5.1; rv:6.0) Gecko/20100101 Firefox/6.0"}
		,{"10","Mozilla/5.0 (Macintosh; Intel Mac OS X 10.6; rv:6.0.2) Gecko/20100101 Firefox/6.0.2"}
		,{"10","Mozilla/5.0 (Windows NT 6.0; rv:7.0.1) Gecko/20100101 Firefox/7.0.1"}
		,{"10","Mozilla/5.0 (Macintosh; Intel Mac OS X 10.7; rv:7.0.1) Gecko/20100101 Firefox/7.0.1"}
		,{"10","Mozilla/5.0 (Macintosh; Intel Mac OS X 10.5; rv:8.0.1) Gecko/20100101 Firefox/8.0.1"}
		,{"10","Mozilla/5.0 (Windows NT 6.1; WOW64; rv:9.0.1) Gecko/20100101 Firefox/9.0.1"}
		,{"10","Mozilla/5.0 (Macintosh; Intel Mac OS X 10.7; rv:9.0.1) Gecko/20100101 Firefox/9.0.1"}
		,{"10","Mozilla/5.0 (Macintosh; U; PPC Mac OS X 10_4_11; ja-jp) AppleWebKit/525.13 (KHTML, like Gecko) Version/3.1 Safari/525.13"}
		,{"10","Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_5_5; ja-jp) AppleWebKit/525.26.2 (KHTML, like Gecko) Version/3.2 Safari/525.26.12"}
		,{"10","Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_5_6; ja-jp) AppleWebKit/525.27.1 (KHTML, like Gecko) Version/3.2.1 Safari/525.27.1"}
		,{"10","Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_5_6; ja-jp) AppleWebKit/528.16 (KHTML, like Gecko) Version/4.0 Safari/528.16"}
		,{"10","Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_6_2; ja-jp) AppleWebKit/531.21.8 (KHTML, like Gecko) Version/4.0.4 Safari/531.21.10"}
		,{"10","Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_6_3; ja-jp) AppleWebKit/531.21.11 (KHTML, like Gecko) Version/4.0.4 Safari/531.21.10"}
		,{"10","Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_6_3; ja-jp) AppleWebKit/533.16 (KHTML, like Gecko) Version/5.0 Safari/533.16"}
		,{"10","Mozilla/5.0 (Macintosh; Intel Mac OS X 10_6_8) AppleWebKit/534.52.7 (KHTML, like Gecko) Version/5.1.2 Safari/534.52.7"}
		,{"10","Mozilla/5.0 (Macintosh; Intel Mac OS X 10_6_8) AppleWebKit/534.57.2 (KHTML, like Gecko) Version/5.1.7 Safari/534.57.2"}
		,{"10","Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8) AppleWebKit/536.25 (KHTML, like Gecko) Version/6.0 Safari/536.25"}
		,{"10","Mozilla/4.0 (compatible; MSIE 5.0; Windows XP) Opera 6.06 [ja]"}
		,{"10","Mozilla/4.0 (compatible; MSIE 5.0; Windows 2000) Opera 6.06 [ja]"}
		,{"10","Mozilla/4.0 (compatible; MSIE 6.0; Mac_PowerPC Mac OS X; ja) Opera 8.01"}
		,{"10","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0; ja) Opera 8.01"}
		,{"10","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; tr) Opera 8.02"}
		,{"10","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; ja) Opera 8.02"}
		,{"10","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; ja) Opera 8.5"}
		,{"10","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0; ja) Opera 8.51"}
		,{"10","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; de) Opera 8.52"}
		,{"10","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; pl) Opera 8.52"}
		,{"10","Mozilla/5.0 (Windows NT 5.1; U; ja) Opera 8.52"}
		,{"10","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; ja) Opera 8.53"}
		,{"10","Mozilla/5.0 (X11; Linux i686; U; cs) Opera 8.54"}
		,{"10","Mozilla/4.0 (compatible; MSIE 6.0; KDDI-SA39) Opera 8.60 [ja]"}
		,{"10","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0; ja) Opera 9.00"}
		,{"10","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; en) Opera 9.00"}
		,{"10","Opera/9.60 (Windows NT 5.1; U; ja) Presto/2.1.1"}
		,{"10","Opera/9.61 (Windows NT 5.1; U; ja) Presto/2.1.1"}
		,{"10","Opera/9.62 (Windows NT 5.1; U; ja) Presto/2.1.1"}
		,{"10","Mozilla/4.0 (compatible; MSIE 6.0; X11; Linux i686; ja) Opera 10.10"}
		,{"10","Opera/9.80 (Windows NT 6.1; U; ja) Presto/2.9.168 Version/11.50"}
		,{"10","Opera/9.80 (Windows NT 6.1; U; ja) Presto/2.10.229 Version/11.60"}
		,{"10","Opera/9.80 (Macintosh; Intel Mac OS X 10.6.8; U; ja) Presto/2.10.289 Version/12.00"}
	};
	
	private static Random r = new Random();
	
	public static String getRandomUserAgent(){
		return user_agent_enum[r.nextInt(user_agent_enum.length)][1];
	}
	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) 
			System.out.println(getRandomUserAgent());
	}
}

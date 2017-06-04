package com.lyc.autoui.selenium.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lyc.autoui.selenium.bean.HttpProxyBean;
import com.lyc.autoui.selenium.conf.ConfigParam;

public class JdbcUtil {
	private static final Logger log = LoggerFactory.getLogger(JdbcUtil.class);

	private static Connection con;
	private static PreparedStatement insertPstmt;
	
	private static boolean needInit() {
		return con == null || insertPstmt == null;
	}
	public static void init(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(ConfigParam.mysql_url);
			String insertSQL = "INSERT INTO t_http_proxy (ip, port,ori_url,addr,can_use) "
					+ "VALUES(?,?,?,?,1)";
			insertPstmt = con.prepareStatement(insertSQL,Statement.RETURN_GENERATED_KEYS);
		} catch (Exception e) {
			log.error("数据库连接失败", e);
		}
	}
	public static Long insert(HttpProxyBean httpProxyBean){
		if(ConfigParam.save_to_db)
			return insert(httpProxyBean.getIp(), httpProxyBean.getPort(), httpProxyBean.getOriUrl(), httpProxyBean.getAddr());
		return null;
	}
	private static Long insert(String ip,String port,String oriUrl,String addr){
		if(needInit()){
			init();
		}
		Long id = null;
		try {
			insertPstmt.setString(1, ip);
			insertPstmt.setString(2, port);
			insertPstmt.setString(3, oriUrl);
			insertPstmt.setString(4, addr);
			int c = insertPstmt.executeUpdate();
			ResultSet rs = insertPstmt.getGeneratedKeys(); 
			if (rs.next()) {
				id = rs.getLong(1); 
			} 
			log.info("插入DB条数："+c);
		} catch (Exception e) {
			log.warn("插入数据库失败，ip={},port={},oriUrl={},addr={},canUse={}",ip,port,oriUrl,addr);
		}
		return id;
	}
	
	public static void loadUserProxyFromDB() throws Exception{
		if(!ConfigParam.load_used_proxy_from_db){
			return;
		}
		if(needInit()){
			init();
		}
		Statement st = con.createStatement();
		SimpleDateFormat yyyyMMddFormat = new SimpleDateFormat("yyyy-MM-dd");
		String ymdStr = yyyyMMddFormat.format(new Date())+" 00:00:00";
		
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date curDate = new Date();
		curDate.setTime(curDate.getTime() - 6*3600*1000);
		String whereSql = " (create_date>='"+f.format(curDate)+"' and create_date>='"+ymdStr+"') ";
		ResultSet rs = st.executeQuery("select count(1) from t_http_proxy where can_use=1 and "+whereSql);
		int totleCount = 0;
		int n = 0;
		int curCount = 0;
		int limitCount = 100000;
		int pageSize = 1000;
		if(rs.next()){
			totleCount = rs.getInt(1);
		}
		if(totleCount <= limitCount){
			while (curCount<totleCount){
				String querySql = "select * from t_http_proxy where can_use=1 and "+whereSql+" limit "+curCount+","+pageSize;
				log.info("querySql="+querySql);
				rs = st.executeQuery(querySql);
				while(rs.next()){
					String key = rs.getString("ip")+":"+rs.getString("port");
					Date createDate = rs.getTime("create_date");
					ConfigParam.used_proxy.put(key, createDate);
					log.info("添加已用代理("+(++n)+"/"+totleCount+"):"+key+","+f.format(createDate));
				}
				curCount+=pageSize;
			}
		}else{
			log.info("已用代理数据量超过"+limitCount+"("+totleCount+")，不加载");
		}
	}
	public static void update(Long id,String startOrEnd) throws Exception{
		if(needInit()){
			init();
		}
		Statement st = con.createStatement();
		if("start".equals(startOrEnd)){
			st.executeUpdate("update t_http_proxy set start_page=1 where id="+id);
		}else if("end".equals(startOrEnd)){
			st.executeUpdate("update t_http_proxy set end_page=1 where id="+id);
		}
	}
}

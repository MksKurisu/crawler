package mv_crawler.mv_crawler;

import java.sql.SQLException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import db.MYSQLControl;
import db.sql_data;
import model.employeeModel;
import parse.parseCount;
import util.PageCounter;
import util.URLFecter;

public class App {
	public static void main(String[] args) throws Exception {
		sql_data sd = new sql_data();
		//设置定时器，防止反爬
		final Timer timer = new Timer();
		//目标网址前缀
		final String url = "http://job.rc114.com";
		// 初始化一个httpclient
		final HttpClient client = new DefaultHttpClient();;
		// 循环获取页数
		for(int i=1; i<=24; i++) {
			final int page = i;
			timer.schedule(new TimerTask() {
				public void run() {
					//通过循环及拼接字符串实现翻页
					String url_page = url+"/JobSearchCate.aspx?JobCategory=15&Page="+page;
					try {
						//抓取翻页后的链接
						List<String> count = PageCounter.URLParser(client, url_page);
						//按页爬取
						for(String str:count) {
							//再次拼接，访问详情页
							final String url_info = url+"/"+str;
							//延时解析
							timer.schedule(new TimerTask(){
								public void run(){
									try {
										List<employeeModel> model = URLFecter.URLParser(client,url_info);
										// 将抓取的数据插入数据库
										MYSQLControl.executeInsert(model);
									}catch (Exception e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
								}
							}, 10000);
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}, 10000);
		}
	}
}

package parse;

import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import model.employeeModel;
/*
 * 用于获取爬取页数
 * 解析方式，采用Jsoup解析
 */
public class parseCount {
    public static List<String> getCount (String html) throws Exception{
        //获取的数据，存放在集合中
        List<String> data = new ArrayList<String>();
        //采用Jsoup解析
        Document doc = Jsoup.parse(html);
        //获取详细页面的url
        Elements elements=doc.select("div[class=searchjieguo]").select("div[class=joblist]");
        for (Element ele:elements) {
        	String detail=ele.select("li[class=jobname]").select("a").attr("href");
            //将每一个对象的值，保存到List集合中
            data.add(detail);
        }
        //返回数据
        return data;
    }
}

package parse;

import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import model.employeeModel;
/*
 * 用于将上面传下来的html解析，获取我们需要的内容
 * 解析方式，采用Jsoup解析
 */
public class parse {
    public static List<employeeModel> getData (String html) throws Exception{
        //获取的数据，存放在集合中
        List<employeeModel> data = new ArrayList<employeeModel>();
        //采用Jsoup解析
        Document doc = Jsoup.parse(html);
        //获取html标签中的内容
        Elements ele=doc.select("div[class=BorderDiv]");
        Elements JobItem = ele.select("div[class=JobItem]").select("li");
        	String oname=ele.select("div[class=TitileName]").text();
        	String cname=ele.select("a#hlUnitName").text();
        	String salary=JobItem.get(1).text();
            String education=JobItem.get(2).text();
            String requirement=JobItem.get(3).text();
            String area=JobItem.get(8).text();
            if(JobItem.size()==9) {
            	area=JobItem.get(7).text();
            }         
            String category=JobItem.get(0).select("a#hlJobCateSmall").text();
            //创建一个对象，这里可以看出，使用Model的优势，直接进行封装
            employeeModel model=new employeeModel();
            //对象的值
            model.setOccupationName(oname);
            model.setCompanyName(cname);
            model.setSalary(salary);
            model.setEducation(education);
            model.setRequirement(requirement);
            model.setArea(area);
            model.setCategory(category);
            //将每一个对象的值，保存到List集合中
            data.add(model);
        //返回数据
        return data;
    }
}

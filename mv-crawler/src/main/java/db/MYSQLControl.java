package db;

import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.apache.commons.dbutils.QueryRunner;
import model.employeeModel;

public class MYSQLControl {
	// 根据实际数据库地址修改
	static DataSource ds = MyDataSource.getDataSource("jdbc:mysql://182.149.196.16/xia");
	static QueryRunner qr = new QueryRunner(ds);

	// 第一类方法
	public static void executeUpdate(String sql) {
		try {
			qr.update(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 第二类数据库操作方法
	public static void executeInsert(List<employeeModel> model) throws SQLException {
        /*
         * 定义一个Object数组，行列
         * 3表示列数，根据自己的数据定义这里面的数字
         * params[i][0]等是对数组赋值，这里用到集合的get方法
         * 
         */
        Object[][] params = new Object[model.size()][7];
        for ( int i=0; i<params.length; i++ ){
            params[i][0] = model.get(i).getOccupationName();
            params[i][1] = model.get(i).getCompanyName();
            params[i][2] = model.get(i).getSalary();
            params[i][3] = model.get(i).getEducation();
            params[i][4] = model.get(i).getRequirement();
            params[i][5] = model.get(i).getArea();
            params[i][6] = model.get(i).getCategory();
        }
        qr.batch("insert into occasion (OccupationName, CompanyName, Salary, Education, Requirement, Area, Category )"
                + "values (?,?,?,?,?,?,?)", params);
        System.out.println("执行数据库完毕！"+"成功插入数据："+model.size()+"条");

    }
}

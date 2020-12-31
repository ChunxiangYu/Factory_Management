package JDBC;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCDemo {
    public static void main(String[] args){

        //System.out.println(con); // 直接输出没有异常就是成功的
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/factory", "root", "463995");

            if (!con.isClosed()) {
                System.out.println("数据库连接成功");
            }
            Statement statement = con.createStatement();

//            执行查询语句
            String sql = "select * from customer;";//我的表格叫persons
            ResultSet resultSet = statement.executeQuery(sql);


//            打印查询出来的东西
            String name;
            String num;
            while (resultSet.next()) {
                name = resultSet.getString("L_name");
                num = resultSet.getString("F_name");
                System.out.println(name + '\t' + num);
            }
            con.close();
        }catch (ClassNotFoundException | SQLException e) {
            System.out.println("数据库驱动没有安装");

        }

    }
// 7.
}

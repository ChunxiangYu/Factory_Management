package JDBC;
import java.sql.*;

public class JDBCDemo {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/factory", "root", "463995");
        System.out.println(con); // 直接输出没有异常就是成功的
//        //String sql = "delete from t_student where sno = ? ";
//        PreparedStatement pstm = con.prepareStatement(sql); // 参数是sql语句
//        pstm.setInt(1, 2);
//        int rs = pstm.executeUpdate();
//
//        if (rs > 0) {
//            System.out.println("恭喜你操作成功了!");
//        }else {
//            System.out.println("你个傻逼");
//        }
        //pstm.close();
        con.close();
    }
// 7.
}

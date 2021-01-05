import JDBC.JDBCDemo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class workerOperation {
    JDBCDemo bd = new JDBCDemo();
    List<worker> list1 = new ArrayList<>();
    Connection conn = null;
    Statement state = null;
    ResultSet rs = null;
    PreparedStatement pstate;
    String [] str = new String[8];

    //将数据库数据导出
    public List<worker> workerDao() {
        conn = bd.getConn();
        try {
            state = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String sql = "select * from worker";
        try {
            rs = state.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            while(rs.next()) {
                worker wor = new worker();
                wor.setW_number(rs.getInt("W_number"));
                wor.setL_name(rs.getString("L_name"));
                wor.setF_name(rs.getString("F_name"));
                wor.setSalary(rs.getInt("Salary"));
                wor.setAge(rs.getInt("age"));
                wor.setWorkingYear(rs.getInt("workingYear"));
                wor.setGender(rs.getString("gender"));
                wor.setIsRetire(rs.getString("isRetire"));
                list1.add(wor);
            }
            bd.close(rs, pstate, conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list1;
    }

    //添加数据入库
    @SuppressWarnings("finally")
    public String Insert(String W_number,String L_name ,String F_name, String Salary, String age, String workingYear, String gender, String isRetire) {
        conn = bd.getConn();
        int result = -1;
        String sql = "insert into worker values("+W_number+",'"+L_name+"','"+F_name+"',"+Salary+",'"+age+"','"+workingYear+"','"+gender+"','"+isRetire+"') ";
        try {
            pstate = conn.prepareStatement(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            result = pstate.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            bd.close(null, pstate, conn);
            if(result > 0) {
                return "添加成功";
            }else {
                return "添加失败";
            }
        }
    }


    //删除数据
    @SuppressWarnings("finally")
    public String Del(int row) {
        conn = bd.getConn();
        int result = -1;
        String sql = "delete from worker where W_number = ? ";
        try {
            pstate = conn.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            pstate.setInt(1, row);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            result = pstate.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            bd.close(null, pstate, conn);
            if(result > 0) {
                return "删除成功";
            }else {
                return "删除失败";
            }
        }
    }

    //修改数据，会把所有数据修改一遍id除外
    @SuppressWarnings("finally")
    public String Update(String W_number,String L_name ,String F_name, String Salary, String age, String workingYear, String gender, String isRetire) {
        conn = bd.getConn();
        int result = -1;
        String sql = "update worker set  W_number ="+W_number+",L_name ='"+L_name+"',F_name ='"+F_name+"',Salary="+Salary+",age ="+age+",workingYear ="+workingYear+",gender='"+gender+"',isRetire ='"+isRetire+"' where W_number="+W_number+"";
        try {
            pstate = conn.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            result = pstate.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            bd.close(null, pstate, conn);
            if(result > 0) {
                return "修改成功";
            }else {
                return "修改失败";
            }
        }
    }


    //查询，返回查询出来的数据
    public String[] Select(String id) {
        conn = bd.getConn();
        int result = -1;
        String sql = "select * from worker where W_number = ?";
        try {
            pstate = conn.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            pstate.setString(1, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            ResultSet rs = pstate.executeQuery();
            while(rs.next()) {
                str[0] = rs.getString("W_number");
                str[1] = rs.getString("L_name");
                str[2] = rs.getString("F_name");
                str[3] = rs.getString("Salary");
                str[4] = rs.getString("age");
                str[5] = rs.getString("workingYear");
                str[6] = rs.getString("gender");
                str[7] = rs.getString("isRetire");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            bd.close(null, pstate, conn);
        }return str;
    }
}

import JDBC.JDBCDemo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class customerOperation {
    JDBCDemo bd = new JDBCDemo();
    List<customer> list1 = new ArrayList<customer>();
    Connection conn = null;
    Statement state = null;
    ResultSet rs = null;
    PreparedStatement pstate;
    String [] str = new String[7];

    //将数据库数据导出
    public List<customer> customerDao() {
        conn = bd.getConn();
        try {
            state = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String sql = "select * from customer";
        try {
            rs = state.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            while(rs.next()) {
                customer cus = new customer();
                cus.setC_number(rs.getInt("C_number"));
                cus.setL_name(rs.getString("L_name"));
                cus.setF_name(rs.getString("F_name"));
                cus.setPhoneNumber(rs.getInt("phoneNumber"));
                cus.setAddress(rs.getString("address"));
                cus.setGender(rs.getString("gender"));
                cus.setLast_Purchase_Time(rs.getString("Last_Purchase_Time"));
                list1.add(cus);
            }
            bd.close(rs, pstate, conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list1;
    }

    //添加数据入库
    @SuppressWarnings("finally")
    public String Insert(String C_number,String L_name ,String F_name, String phoneNumber, String address, String gender, String Last_Purchase_Time) {
        conn = bd.getConn();
        int result = -1;
        String sql = "insert into customer values("+C_number+",'"+L_name+"','"+F_name+"',"+phoneNumber+",'"+address+"','"+gender+"',"+Last_Purchase_Time+") ";
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
        String sql = "delete from customer where C_number = ? ";
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
    public String Update(String C_number,String L_name ,String F_name, String phoneNumber, String address, String gender, String Last_Purchase_Time) {
        conn = bd.getConn();
        int result = -1;
        String sql = "update customer set  C_number ="+C_number+",L_name ='"+L_name+"',F_name ='"+F_name+"',phoneNumber="+phoneNumber+",address ='"+address+"',gender ='"+gender+"',Last_Purchase_Time="+Last_Purchase_Time+" where C_number="+C_number+"";
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
        String sql = "select * from customer where C_number = ?";
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
                str[0] = rs.getString("C_number");
                str[1] = rs.getString("L_name");
                str[2] = rs.getString("F_name");
                str[3] = rs.getString("phoneNumber");
                str[4] = rs.getString("address");
                str[5] = rs.getString("gender");
                str[6] = rs.getString("Last_Purchase_Time");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            bd.close(null, pstate, conn);
        }return str;
    }
}

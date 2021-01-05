import JDBC.JDBCDemo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class factoryOperation {
    JDBCDemo bd = new JDBCDemo();
    List<factory> list1 = new ArrayList<>();
    Connection conn = null;
    Statement state = null;
    ResultSet rs = null;
    PreparedStatement pstate;
    String [] str = new String[5];

    //将数据库数据导出
    public List<factory> factoryDao() {
        conn = bd.getConn();
        try {
            state = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String sql = "select * from factory";
        try {
            rs = state.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            while(rs.next()) {
                factory fac = new factory();
                fac.setF_number(rs.getInt("F_number"));
                fac.setArea(rs.getDouble("area"));
                fac.setAddress(rs.getString("address"));
                fac.setLast_Production_Time(rs.getString("Last_Production_Time"));
                fac.setProduct(rs.getString("product"));
                list1.add(fac);
            }
            bd.close(rs, pstate, conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list1;
    }

    //添加数据入库
    @SuppressWarnings("finally")
    public String Insert(String F_number,String area ,String address, String Last_Production_Time, String product) {
        conn = bd.getConn();
        int result = -1;
        String sql = "insert into factory values("+F_number+",'"+area+"','"+address+"',"+Last_Production_Time+",'"+product+"') ";
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
        String sql = "delete from factory where F_number = ? ";
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
    public String Update(String F_number,String area ,String address, String Last_Production_Time, String product) {
        conn = bd.getConn();
        int result = -1;
        String sql = "update factory set  F_number ="+F_number+",area ='"+area+"',address ='"+address+"',Last_Production_Time="+Last_Production_Time+",product ="+product+" where F_number="+F_number+"";
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
        String sql = "select * from factory where F_number = ?";
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
                str[0] = rs.getString("F_number");
                str[1] = rs.getString("area");
                str[2] = rs.getString("address");
                str[3] = rs.getString("Last_Production_Time");
                str[4] = rs.getString("product");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            bd.close(null, pstate, conn);
        }return str;
    }
}

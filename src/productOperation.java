import JDBC.JDBCDemo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class productOperation {
    JDBCDemo bd = new JDBCDemo();
    List<product> list1 = new ArrayList<>();
    Connection conn = null;
    Statement state = null;
    ResultSet rs = null;
    PreparedStatement pState;
    String[] str = new String[3];

    //将数据库数据导出
    public List<product> productDao() {
        conn = bd.getConn();
        try {
            state = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String sql = "select * from product";
        try {
            rs = state.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            while (rs.next()) {
                product pro = new product();
                pro.setP_id(rs.getInt("p_id"));
                pro.setProductName(rs.getString("productName"));
                pro.setPerPrice(rs.getInt("perPrice"));
                list1.add(pro);
            }
            bd.close(rs, pState, conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list1;
    }

    //添加数据入库
    @SuppressWarnings("finally")
    public String Insert(String p_id, String productName, String perPrice) {
        conn = bd.getConn();
        int result = -1;
        String sql = "insert into product values(" + p_id + ",'" + productName + "'," + perPrice + ") ";
        try {
            pState = conn.prepareStatement(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            result = pState.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            bd.close(null, pState, conn);
            if (result > 0) {
                return "添加成功";
            } else {
                return "添加失败";
            }
        }
    }


    //删除数据
    @SuppressWarnings("finally")
    public String Del(int row) {
        conn = bd.getConn();
        int result = -1;
        String sql = "delete from product where p_id = ? ";
        try {
            pState = conn.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            pState.setInt(1, row);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            result = pState.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            bd.close(null, pState, conn);
            if (result > 0) {
                return "删除成功";
            } else {
                return "删除失败";
            }
        }
    }

    //修改数据，会把所有数据修改一遍id除外
    @SuppressWarnings("finally")
    public String Update(String p_id, String productName, String perPrice) {
        conn = bd.getConn();
        int result = -1;
        String sql = "update product set  p_id =" + p_id + ",productName ='" + productName + "',perPrice=" + perPrice + " where p_id=" + p_id + "";
        try {
            pState = conn.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            result = pState.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            bd.close(null, pState, conn);
            if (result > 0) {
                return "修改成功";
            } else {
                return "修改失败";
            }
        }
    }


    //查询，返回查询出来的数据
    public String[] Select(String id) {
        conn = bd.getConn();
        int result = -1;
        String sql = "select * from product where p_id = ?";
        try {
            pState = conn.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            pState.setString(1, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            ResultSet rs = pState.executeQuery();
            while (rs.next()) {
                str[0] = rs.getString("p_id");
                str[1] = rs.getString("productName");
                str[2] = rs.getString("perPrice");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            bd.close(null, pState, conn);
        }
        return str;
    }
}

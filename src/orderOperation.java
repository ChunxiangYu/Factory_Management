import JDBC.JDBCDemo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class orderOperation {
    JDBCDemo bd = new JDBCDemo();
    List<order> list1 = new ArrayList<>();
    Connection conn = null;
    Statement state = null;
    ResultSet rs = null;
    PreparedStatement pState;
    String[] str = new String[9];

    //将数据库数据导出
    public List<order> orderDao() {
        conn = bd.getConn();
        try {
            state = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String sql = "select * from orderInfo";
        try {
            rs = state.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            while (rs.next()) {
                order ord = new order();
                ord.setOrderNumber(rs.getInt("orderNumber"));
                ord.setClientNumber(rs.getInt("clientNumber"));
                ord.setPurchaseDate(rs.getInt("purchaseDate"));
                ord.setDeliveryDate(rs.getInt("deliveryDate"));
                ord.setProductId(rs.getInt("productId"));
                ord.setProductAmount(rs.getInt("productAmount"));
                ord.setTotalPrice(rs.getInt("totalPrice"));
                ord.setDeliveryAddress(rs.getString("deliveryAddress"));
                ord.setPaymentStatus(rs.getString("paymentStatus"));
                list1.add(ord);
            }
            bd.close(rs, pState, conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list1;
    }

    //添加数据入库
    @SuppressWarnings("finally")
    public String Insert(String orderNumber, String clientNumber, String purchaseDate, String deliveryDate, String productId, String productAmount, String totalPrice, String deliveryAddress, String paymentStatus) {
        conn = bd.getConn();
        int result = -1;
        String sql = "insert into orderInfo values(" + orderNumber + "," + clientNumber + "," + purchaseDate + "," + deliveryDate + "," + productId + "," + productAmount + "," + totalPrice + ",'" + deliveryAddress + "','" + paymentStatus + "') ";
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
        String sql = "delete from orderInfo where orderNumber = ? ";
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
    public String Update(String orderNumber, String clientNumber, String purchaseDate, String deliveryDate, String productId, String productAmount, String totalPrice, String deliveryAddress, String paymentStatus) {
        conn = bd.getConn();
        int result = -1;
        String sql = "update orderinfo set  orderNumber =" + orderNumber + ",clientNumber =" + clientNumber + ",purchaseDate =" + purchaseDate + ",deliveryDate =" + deliveryDate + ",productId =" + productId + ",productAmount =" + productAmount + ",totalPrice =" + totalPrice + ",deliveryAddress ='" + deliveryAddress + "',paymentStatus='" + paymentStatus + "' where orderNumber=" + orderNumber + "";
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
        String sql = "select * from orderInfo where orderNumber = ?";
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
                str[0] = rs.getString("orderNumber");
                str[1] = rs.getString("clientNumber");
                str[2] = rs.getString("purchaseDate");
                str[3] = rs.getString("deliveryDate");
                str[4] = rs.getString("productId");
                str[5] = rs.getString("productAmount");
                str[6] = rs.getString("totalPrice");
                str[7] = rs.getString("deliveryAddress");
                str[8] = rs.getString("paymentStatus");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            bd.close(null, pState, conn);
        }
        return str;
    }
}


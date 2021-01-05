import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import java.util.List;
import java.util.Vector;

public class orderInfoFrame extends JFrame {
    orderOperation operation = new orderOperation();
    List<order> list = operation.orderDao();

    private final JButton jbt1 = new JButton("查询");
    private final JButton jbt2 = new JButton("添加");
    private final JButton jbt3 = new JButton("删除");
    private final JButton jbt4 = new JButton("修改");
    private final JButton jbt5 = new JButton("返回");
    private final JTextField jtf = new JTextField(10);
    private final JLabel lbl = new JLabel("请输入订单编号：");

    //窗口中添加表格
    private static final JTable table = new JTable();
    private final JScrollPane jsp = new JScrollPane(table);

    //把按钮放入面板
    private final JPanel jp1 = new JPanel();
    private final JPanel jp3 = new JPanel();


    public orderInfoFrame() {
        this.setTitle("查询");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(600, 400);
        this.setLocationRelativeTo(null);
        //上    查询面板
        jp1.add(lbl);
        jp1.add(jtf);
        jp1.add(jbt1);
//		jtf.setPreferredSize(new Dimension(10,10));设置文本框大小
        this.add(jp1, BorderLayout.NORTH);

        //中  查询表格
        DefaultTableModel dtm = new DefaultTableModel();
        table.setModel(dtm);
        dtm.addColumn("订单编号");
        dtm.addColumn("顾客编号");
        dtm.addColumn("购买日期");
        dtm.addColumn("交付日期");
        dtm.addColumn("产品编号");
        dtm.addColumn("产品数量");
        dtm.addColumn("总价格");
        dtm.addColumn("交付地址");
        dtm.addColumn("付款状态");


        //添加数据---list--为List数组用于储存Score数据
        for (order order : list) {
            Vector<Object> data = new Vector<>();
            data.add(order.getOrderNumber());
            data.add(order.getClientNumber());
            data.add(order.getPurchaseDate());
            data.add(order.getDeliveryDate());
            data.add(order.getProductId());
            data.add(order.getProductAmount());
            data.add(order.getTotalPrice());
            data.add(order.getDeliveryAddress());
            data.add(order.getPaymentStatus());
            dtm.addRow(data);
        }
        //添加空表格
        for (int i = 0; i < list.size(); i++) {
            Vector<Object> data = new Vector<>();
            data.add(null);
            data.add(null);
            data.add(null);
            data.add(null);
            data.add(null);
            data.add(null);
            data.add(null);
            data.add(null);
            data.add(null);
            dtm.addRow(data);
        }
        //放入中部
        this.add(jsp, BorderLayout.CENTER);
        //下 增删改查面板
        jp3.add(jbt2);
        jp3.add(jbt3);
        jp3.add(jbt4);
        jp3.add(jbt5);
        this.add(jp3, BorderLayout.SOUTH);
        this.setVisible(true);


        //添加按钮---鼠标点击不在输入框方可添加
        jbt2.addActionListener(arg0 -> {
            int row = table.getSelectedRow();
            String orderNumber = (String) dtm.getValueAt(row, 0);
            String clientNumber = (String) dtm.getValueAt(row, 1);
            String purchaseDate = (String) dtm.getValueAt(row, 2);
            String deliveryDate = (String) dtm.getValueAt(row, 3);
            String productId = (String) dtm.getValueAt(row, 4);
            String productAmount = (String) dtm.getValueAt(row, 5);
            String totalPrice = (String) dtm.getValueAt(row, 6);
            String deliveryAddress = (String) dtm.getValueAt(row, 7);
            String paymentStatus = (String) dtm.getValueAt(row, 8);
            String str = operation.Insert(orderNumber, clientNumber, purchaseDate, deliveryDate, productId, productAmount, totalPrice, deliveryAddress, paymentStatus);
            JOptionPane.showMessageDialog(null, str);
        });

        //删除按钮
        jbt3.addActionListener(e -> {
            int row = table.getSelectedRow();
            Object o = dtm.getValueAt(row, 0);
            dtm.removeRow(row);
            String str = operation.Del(row + 1);
            JOptionPane.showMessageDialog(null, str);
        });

        //修改按钮
        jbt4.addActionListener(arg0 -> {
            int row = table.getSelectedRow();
            String orderNumber = dtm.getValueAt(row, 0).toString();
            String clientNumber = dtm.getValueAt(row, 1).toString();
            String purchaseDate = dtm.getValueAt(row, 2).toString();
            String deliveryDate = dtm.getValueAt(row, 3).toString();
            String productId = dtm.getValueAt(row, 4).toString();
            String productAmount = dtm.getValueAt(row, 5).toString();
            String totalPrice = dtm.getValueAt(row, 6).toString();
            String deliveryAddress = (String) dtm.getValueAt(row, 7);
            String paymentStatus = (String) dtm.getValueAt(row, 8);
            String str = operation.Update(orderNumber, clientNumber, purchaseDate, deliveryDate, productId, productAmount, totalPrice, deliveryAddress, paymentStatus);
            JOptionPane.showMessageDialog(null, str);
        });
        //查询按钮
        jbt1.addActionListener(arg0 -> {
            //删除原有数据--把查询所得数据重新加入
            String[] str = operation.Select(jtf.getText());//获取输入的id
            dtm.setRowCount(0);
            Vector<Object> data = new Vector<>();
            data.add(str[0]);
            data.add(str[1]);
            data.add(str[2]);
            data.add(str[3]);
            data.add(str[4]);
            data.add(str[5]);
            data.add(str[6]);
            data.add(str[7]);
            data.add(str[8]);
            dtm.addRow(data);
        });
        //返回按钮
        jbt5.addActionListener(arg0 -> {
            //删除查询的数据重新加入所有数据
            dtm.setRowCount(0);
            for (int i = 0; i < list.size(); i++) {
                Vector<Object> data = new Vector<Object>();
                data.add(list.get(i).getOrderNumber());
                data.add(list.get(i).getClientNumber());
                data.add(list.get(i).getPurchaseDate());
                data.add(list.get(i).getDeliveryDate());
                data.add(list.get(i).getProductId());
                data.add(list.get(i).getProductAmount());
                data.add(list.get(i).getTotalPrice());
                data.add(list.get(i).getDeliveryAddress());
                data.add(list.get(i).getPaymentStatus());
                dtm.addRow(data);
            }
            for (int i = 0; i < list.size(); i++) {
                Vector<Object> data = new Vector<Object>();
                data.add(null);
                data.add(null);
                data.add(null);
                data.add(null);
                data.add(null);
                data.add(null);
                data.add(null);
                data.add(null);
                data.add(null);
                dtm.addRow(data);
            }

        });

    }


}


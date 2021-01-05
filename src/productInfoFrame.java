import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import java.util.List;
import java.util.Vector;

public class productInfoFrame extends JFrame {
    productOperation operation = new productOperation();
    List<product> list = operation.productDao();

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


    public productInfoFrame() {
        this.setTitle("产品查询");
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
        dtm.addColumn("产品编号");
        dtm.addColumn("产品名称");
        dtm.addColumn("产品单价");


        //添加数据---list--为List数组用于储存Score数据
        for (product product : list) {
            Vector<Object> data = new Vector<>();
            data.add(product.getP_id());
            data.add(product.getProductName());
            data.add(product.getPerPrice());

            dtm.addRow(data);
        }
        //添加空表格
        for (int i = 0; i < list.size(); i++) {
            Vector<Object> data = new Vector<>();
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
            String p_id = (String) dtm.getValueAt(row, 0);
            String productName = (String) dtm.getValueAt(row, 1);
            String perPrice = (String) dtm.getValueAt(row, 2);
            String str = operation.Insert(p_id, productName, perPrice);
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
            String p_id = dtm.getValueAt(row, 0).toString();
            String productName = dtm.getValueAt(row, 1).toString();
            String perPrice = dtm.getValueAt(row, 2).toString();

            String str = operation.Update(p_id, productName, perPrice);
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

            dtm.addRow(data);
        });
        //返回按钮
        jbt5.addActionListener(arg0 -> {
            //删除查询的数据重新加入所有数据
            dtm.setRowCount(0);
            for (int i = 0; i < list.size(); i++) {
                Vector<Object> data = new Vector<Object>();
                data.add(list.get(i).getP_id());
                data.add(list.get(i).getProductName());
                data.add(list.get(i).getPerPrice());
                dtm.addRow(data);
            }
            for (int i = 0; i < list.size(); i++) {
                Vector<Object> data = new Vector<Object>();
                data.add(null);
                data.add(null);
                data.add(null);
                dtm.addRow(data);
            }

        });

    }


}


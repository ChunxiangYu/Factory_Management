import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

public class workerInfoFrame extends JFrame{
    workerOperation sd = new workerOperation();
    worker s = new worker();
    List<worker> list = sd.workerDao();


    private JButton jbt1 = new JButton("查询");
    private JButton jbt2 = new JButton("添加");
    private JButton jbt3 = new JButton("删除");
    private JButton jbt4 = new JButton("修改");
    private JButton jbt5 = new JButton("返回");
    private JTextField jtf = new JTextField(10);
    private JLabel lbl = new JLabel("请输入工人编号：");

    //窗口中添加表格
    private static JTable table = new JTable();
    private JScrollPane jsp = new JScrollPane(table);

    //把按钮放入面板
    private JPanel jp1 = new JPanel();

    private JPanel jp3 = new JPanel();


    public workerInfoFrame() {
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
        dtm.addColumn("编号");
        dtm.addColumn("姓");
        dtm.addColumn("名");
        dtm.addColumn("薪资");
        dtm.addColumn("年龄");
        dtm.addColumn("工龄");
        dtm.addColumn("性别");
        dtm.addColumn("是否退休");

        //添加数据---list--为List数组用于储存Score数据
        for (int i = 0; i < list.size(); i++) {
            Vector<Object> data = new Vector<Object>();
            data.add(list.get(i).getW_number());
            data.add(list.get(i).getL_name());
            data.add(list.get(i).getF_name());
            data.add(list.get(i).getSalary());
            data.add(list.get(i).getAge());
            data.add(list.get(i).getWorkingYear());
            data.add(list.get(i).getGender());
            data.add(list.get(i).getIsRetire());
            dtm.addRow(data);
        }
        //添加空表格
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
            dtm.addRow(data);
        }
        //放入中部
        this.add(jsp,BorderLayout.CENTER);
        //下 增删改查面板
        jp3.add(jbt2);
        jp3.add(jbt3);
        jp3.add(jbt4);
        jp3.add(jbt5);
        this.add(jp3,BorderLayout.SOUTH);
        this.setVisible(true);


        //添加按钮---鼠标点击不在输入框方可添加
        jbt2.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {
                int row = table.getSelectedRow();
                String oid = (String)dtm.getValueAt(row, 0);
                String oLname = (String)dtm.getValueAt(row, 1);
                String oFname = (String)dtm.getValueAt(row, 2);
                String oSalary = (String) dtm.getValueAt(row, 3);
                String oage = (String) dtm.getValueAt(row, 4);
                String oworkingYear = (String) dtm.getValueAt(row, 5);
                String ogender = (String)dtm.getValueAt(row, 6);
                String oisRetire = (String)dtm.getValueAt(row, 7);
                String str = sd.Insert(oid, oLname, oFname, oSalary, oage, oworkingYear, ogender, oisRetire);
                JOptionPane.showMessageDialog(null, str);
            }
        });

        //删除按钮
        jbt3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                Object o = dtm.getValueAt(row, 0);
                dtm.removeRow(row);
                String str = sd.Del(row+1);
                JOptionPane.showMessageDialog(null, str);
            }
        });

        //修改按钮
        jbt4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                int row = table.getSelectedRow();
                String oid = dtm.getValueAt(row, 0).toString();
                String oLname = (String)dtm.getValueAt(row, 1);
                System.out.println(oLname);
                String oFname = (String)dtm.getValueAt(row, 2);
                System.out.println(oFname);
                String oSalary = dtm.getValueAt(row, 3).toString();
                String oage = dtm.getValueAt(row, 4).toString();
                String oworkingYear = dtm.getValueAt(row, 5).toString();
                String ogender = (String)dtm.getValueAt(row, 6);
                System.out.println(ogender);
                String oisRetire = (String)dtm.getValueAt(row, 7);
                System.out.println(oisRetire);
                String str = sd.Update(oid, oLname, oFname, oSalary, oage, oworkingYear, ogender, oisRetire);
                JOptionPane.showMessageDialog(null, str);
            }
        });
        //查询按钮
        jbt1.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {
                //删除原有数据--把查询所得数据重新加入
                String[] str = sd.Select(jtf.getText());//获取输入的id
                dtm.setRowCount(0);
                Vector<Object> data = new Vector<Object>();
                data.add(str[0]);
                data.add(str[1]);
                data.add(str[2]);
                data.add(str[3]);
                data.add(str[4]);
                data.add(str[5]);
                data.add(str[6]);
                data.add(str[7]);
                dtm.addRow(data);
            }
        });
        //返回按钮
        jbt5.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {
                //删除查询的数据重新加入所有数据
                dtm.setRowCount(0);
                for (int i = 0; i < list.size(); i++) {
                    Vector<Object> data = new Vector<Object>();
                    data.add(list.get(i).getW_number());
                    data.add(list.get(i).getL_name());
                    data.add(list.get(i).getF_name());
                    data.add(list.get(i).getSalary());
                    data.add(list.get(i).getAge());
                    data.add(list.get(i).getWorkingYear());
                    data.add(list.get(i).getGender());
                    data.add(list.get(i).getIsRetire());
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
                    dtm.addRow(data);
                }

            }
        });

    }


//    public static void main(String[] args) {
//        new customerInfoFrame();
//    }
}

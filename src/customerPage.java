import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class customerPage extends JFrame {
    private JScrollPane scpDemo;
    private JTableHeader jth;
    private JTable tabDemo;

    public customerPage(){
        setTitle("顾客信息查询");
        this.setSize(600,400);
        this.setLocation(300,400);
        this.setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Container c=getContentPane();
        c.setLayout(null);
        JButton btn1=new JButton("增加顾客"), btn2=new JButton("删除顾客"),btn3=new JButton("查询顾客"),btn4=new JButton("修改顾客");
        btn1.setBounds(0,0,100,30);
        btn2.setBounds(110,0,100,30);
        btn3.setBounds(220,0,100,30);
        btn4.setBounds(330,0,100,30);
        this.scpDemo = new JScrollPane();
        this.scpDemo.setBounds(10,50,500,270);
        c.add(btn1);
        c.add(btn2);
        c.add(btn3);
        c.add(btn4);

        c.add(scpDemo);
        btn3.addActionListener(new ActionListener(){
            String sql = "select * from customer;";
            public void actionPerformed(ActionEvent ae){
                btnShow_ActionPerformed(ae,sql);
            }
        });

    }
    public void btnShow_ActionPerformed(ActionEvent ae,String sql){
// 以下是连接数据源和显示数据的具体处理方法，请注意下在你的电脑上会不同
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/factory", "root", "463995");

            if (!con.isClosed()) {
                System.out.println("数据库连接成功");
            }
            Statement statement = con.createStatement();
            PreparedStatement pstm = con.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            int count = 0;
            while(rs.next()){
                count++;
            }
            rs = pstm.executeQuery();
            Object[][] info = new Object[count][7];
            count = 0;
            while(rs.next()){
                info[count][0] = Integer.valueOf( rs.getInt("C_number"));
                info[count][1] = rs.getString("L_name");
                info[count][2] = rs.getString("F_name");
                info[count][3] = Integer.valueOf( rs.getInt("phoneNumber") );
                info[count][4] = rs.getString("address");
                info[count][5] = rs.getString("gender");
                info[count][6] = rs.getString("Last_Purchase_Time");
                count++;
            }
            // 定义表头
            String[] title = {"编号","姓","名","电话号码","住址","姓名","上次购买"};
            this.tabDemo = new JTable(info,title);
            this.jth = this.tabDemo.getTableHeader();
            this.scpDemo.getViewport().add(tabDemo);
        }catch(ClassNotFoundException cnfe){
            JOptionPane.showMessageDialog(null,"数据源错误","错误",JOptionPane.ERROR_MESSAGE);
        }catch(SQLException sqle){
            JOptionPane.showMessageDialog(null,"数据操作错误","错误",JOptionPane.ERROR_MESSAGE);
        }
    }


}

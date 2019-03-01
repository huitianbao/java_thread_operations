
package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author 惠天宝
 */
public class Input {

    private static JTextField tn;
    private static JTextField tt;
    private static JTextField tp;
    private JButton btnQueding = new JButton("确定");
    private JButton btnQuxiao = new JButton("取消");

    JFrame frm = new JFrame("输入框");

    JLabel ln = new JLabel("名称：");
    JLabel lt = new JLabel("时间：");
    JLabel lp = new JLabel("优先级：");

    public Input() {
    }

    public String[] init() {

        tn = new JTextField(10);
        tt = new JTextField(10);
        tp = new JTextField(10);

        frm.setLayout(null);
        frm.setSize(300, 400);
        frm.setLocation(500, 400);

        GridLayout grid = new GridLayout(4, 2);
        frm.setLayout(grid);
        btnQueding.setSize(80, 60);
        btnQuxiao.setSize(80, 60);

        frm.add(ln);//加入  线程名标签
        frm.add(tn);//加入  文本框

        frm.add(lt);//加入  线程时间
        frm.add(tt);//加入  文本框

        frm.add(lp);//加入  线程优先级
        frm.add(tp);//加入  文本框

        frm.add(btnQueding);  //加入确定按钮
        frm.add(btnQuxiao);   //加入取消按钮
        frm.setVisible(true);
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        String message[] =new String [3];
        btnQueding.addActionListener(new ActionListener() {
            

            public void actionPerformed(ActionEvent e) {
                Object obj = e.getActionCommand();
                if (obj.equals("确定")) {
                    message[0] = tn.getText();
                    message[1] = tt.getText();
                    message[2] = tp.getText();
                    frm.dispose();//------------------------------------------------------------------------------------------------
                }
            }

        });
        btnQuxiao.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String label = e.getActionCommand();
                if (label.equals("取消")) {
                    //dispose();
                }

            }
        });

        return message;
    }

       // btnQueding.addActionListener(new MyActionListener());
    /*
     测试成功
     public static void main(String[] args){
     Input input=new Input();
        
     }
     */
}

/*
 public void display() {
 //Input in=new Input();
 }

 public String getTextName() {
 return tn.getText();
 }

 public String getTextTime() {
 return tt.getText();
 }

 public void close() {
 this.dispose();
 }

 }
    
 */

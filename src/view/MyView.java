/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 *
 * @author 惠天宝
 */
public class MyView implements ActionListener{

    JFrame frm = new JFrame("线程大作业");
    JPanel p1 = new JPanel();
    JPanel p2 = new JPanel();
    JPanel p3=new JPanel();
    

    
    public MyView( JButton author, JButton createp, JButton setr, JButton setw,JButton btndis,JButton btndisall, Table tb) {
                   //作者            创建线程       设为就绪      设为等待         销毁线程         销毁全部      传进表格

        author.setBounds(0, 10, 180, 80);
        createp.setBounds(0, 30, 180, 80);
        setr.setBounds(0, 50, 180, 80);
        setw.setBounds(0, 70, 180, 80);
        btndis.setBounds(0, 90, 180, 80);
        btndisall.setBounds(0, 110, 180, 80);
        
        p1.setSize(750, 350);
        p1.setLocation(200, 10);
        p1.setBorder(new TitledBorder("线程"));

        p2.setSize(750, 350);
        p2.setLocation(200, 370);
        p2.setBorder(new TitledBorder("运行栈"));
        
        p3.setSize(200,710);
        p3.setLocation(0, 10);
        p3.setBorder(new TitledBorder("按钮"));
        
        p1.add(tb.getJS());//===============显示表格====================================================================================================
        
        p3.add(author);
        p3.add(createp);
        p3.add(setr);
        p3.add(setw);
        p3.add(btndis);
        p3.add(btndisall);
        
       
        
        frm.add(p1);
        frm.add(p2);
        frm.add(p3);

        frm.setSize(1000, 900);
        frm.setLocation(100, 100);
        frm.setLayout(null);
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frm.setVisible(true);
        
        
        
        
      

    }
    
    
    
    
   

    //  测试之用
    /*
     public static void main(String[] args){
     MyView myview=new MyView();
        
     }
    */

    @Override
    public void actionPerformed(ActionEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

 
    
    
    
}

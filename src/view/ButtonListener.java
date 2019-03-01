/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import core.ClockInterrupt;
import core.ProcessManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author 惠天宝 JButton author, JButton createp, JButton setr, JButton
 * setw,JButton btndis,JButton btndisall, Table tb) { //作者 创建线程 设为就绪 设为等待 销毁线程
 * 销毁全部 传进表格
 */
public class ButtonListener implements ActionListener {

    private static JButton author = new JButton("作者");
    private static JButton createp = new JButton("创建进程");
    private static JButton setr = new JButton("设为就绪");
    private static JButton setw = new JButton("设为等待");
    private static JButton btndis = new JButton("结束进程");
    private static JButton btndisall = new JButton("结束所有");
    private static JButton queren = new JButton("确认");

    private Table tb = new Table();
    private MyView frm = new MyView(author, createp, setr, setw, btndis, btndisall, tb);
    ProcessManager manager = new ProcessManager(tb);
    private ClockInterrupt Colock;

    public ButtonListener() {
        author.addActionListener(this);
        createp.addActionListener(this);
        setw.addActionListener(this);
        setr.addActionListener(this);
        btndisall.addActionListener(this);
        btndis.addActionListener(this);
        queren.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Object obj = e.getSource();

        if (obj.equals(author)) {
            JOptionPane.showMessageDialog(null, "惠天宝 and 姜铭来");
        }
        if (obj.equals(createp)) {
            ProcessCreat();
        }

        if (obj.equals(setr)) {
            ready();
        }
        if (obj.equals(setw)) {
            wwait();
        }
        if (obj.equals(btndisall)) {
            distroyAll();
        }

        if (obj.equals(btndis)) {
            distroy();
        }

    }

    private void ProcessCreat() {

        String[] mes = new Input().init();

        int pid = manager.creatProcess(mes[0], Integer.parseInt(mes[1]), Integer.parseInt(mes[2]));
        System.out.println("pid     "+pid);

        tb.insert(pid + "", mes[0], mes[1], manager.getQueue().getPCB(pid).getStatus() + "", mes[2]);// pid name   pirority   status remainTime
    }

    private void wwait() {

        manager.setWaiting();

    }

    private void ready() {

        manager.setReady();
    }

    private void distroy() {
        try {   
            int row = tb.getTable().getSelectedRow();

            String pid = (String) tb.getTable().getValueAt(row, 0);
            
            manager.putBack(Integer.parseInt(pid));
            tb.delete();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "没有进程^ . ^");
        }
    }

    private void distroyAll() {

        manager.deleteAllProcess();
    }

}

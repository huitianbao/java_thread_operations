package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author 惠天宝
 */
public class Table {


    private JTable tb;
    private DefaultTableModel dm;
    private JScrollPane js;
    private String[][] rowData = null;
    private String[] columnNames = {"进程号", "进程名", "优先级", "状态", "时间"};// 0 1  2  3 4
    private final JTableHeader header;
    private DefaultTableCellRenderer trc;

    public Table() {

        dm = new DefaultTableModel(rowData, columnNames);
        tb = new JTable(dm);
        
        //设置内容居中
        trc = new DefaultTableCellRenderer();
        trc.setHorizontalAlignment(JLabel.CENTER);
        tb.setDefaultRenderer(Object.class, trc);// 渲染器

        //设置行高
        tb.setRowHeight(35);
        header = tb.getTableHeader();
        header.setPreferredSize(new Dimension(960, 30));
        header.setBackground(Color.lightGray);
                
        
        
        //在滚动窗口上加入表格
        js = new JScrollPane(tb);
        js.setBounds(0, 0, 960, 450);
        tb.setVisible(true);
        js.setVisible(true);

    }

    //返回 js 
    public JScrollPane getJS() {
        return js;
    }

    public JTable getTable() {
        return tb;
    }

    //增
    public void insert(String pid, String name, String priority, String status, String remainTime) {
        
        Object[] data = {pid, name, priority, status, remainTime};
        dm.addRow(data);

    }

    //改
    public void update(int row, int column, String value) {
        tb.getModel().setValueAt(value, row, column);
    }

    public void updateByPid(String pid, String status, String remainTime) {
        tb.getModel().setValueAt(status, getRowByPid(pid), 3); //优先级  //setValueAt(Object aValue,int row,int column)
        tb.getModel().setValueAt(remainTime, getRowByPid(pid), 4);
    }

    public String getStatusById(String Pid) {
        return (String) tb.getModel().getValueAt(getRowByPid(Pid), 3);
    }

    public void delete() {

        try {
            DefaultTableModel dm = (DefaultTableModel) tb.getModel();
            dm.removeRow(tb.getSelectedRow());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "请选择一行");
        }
    }

    public void deletePid(String pid) {
        DefaultTableModel dm = (DefaultTableModel) tb.getModel();
        dm.removeRow(getRowByPid(pid));
    }

    public void deleteAll() {
        DefaultTableModel dm = (DefaultTableModel) tb.getModel();
        while (dm.getRowCount() > 0) {
            dm.removeRow(dm.getRowCount() - 1);
        }
    }

    private int getRowByPid(String pid) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        int ret = -2;
        for (int i = 0; i < tb.getRowCount(); i++) {
            if (tb.getModel().getValueAt(i, 0).equals(pid)) {//public Object getValueAt(int rowIndex, int columnIndex)
                ret = i;
            }
        }
        return ret;//若返回负数则失败   返回进程所在的行-1
    }

    public String getSelectStatus() {
        int row = this.tb.getSelectedRow();
        String status = (String) this.tb.getValueAt(row, 3);

        return status;

    }

}

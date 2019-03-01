/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.swing.JOptionPane;
import view.Table;

/**
 *
 * @author 惠天宝
 */
public class ProcessManager {

    private PCBPool pool=new PCBPool();
    private PCBQueue queue;
    private AlgorithmPriority algo;
    private Table tb=new Table();//==================================================================================

    public ProcessManager(Table tb) {
        this.tb = tb;
        queue = new PCBQueue(tb);
        algo = new AlgorithmPriority(queue);
        System.out.println("PCB管理模块启动完毕");

    }

    public int creatProcess(String name, int priority, int time) {
        // 从PCB池中取得一个pcb
        PCB pcb = pool.getOnePCB();
        //设置PCB属性
        pcb.setName(name);
        pcb.setPriority(priority);
        pcb.setRemainTime(time * 1000);

        //加入就绪队列
        pcb.setReady();
        queue.addtoReady(pcb);
        //
        System.out.println("-------------------------");
        System.out.println("创建新进程" + "              --");
        System.out.println("PCBPool容量=" + pool.getPCBPoolMap().size() + "          --");
        System.out.println("运行队列进程个数" + queue.running.size() + "        --");
        System.out.println("就绪队列进程个数" + queue.ready.size() + "        --");
        System.out.println("等待队列进程个数" + queue.waiting.size() + "        --");
        return pcb.getPid();
    }

    public PCBQueue getQueue() {
        return queue;
    }

    public void setWaiting() {
        PCB pcb = null;
        int pid;
        pcb = algo.getOneRunningProcess();
        try {
            if (tb.getSelectStatus().equals(Status.RUNNING)) {
                if (pcb != null) {
            
                    queue.addtoWaiting(queue.removeFromRunning(pcb.getPid()));
                    tb.updateByPid(pcb.getPid()+"", "WAITING", pcb.getRemainTime() + "");

                } else {
                    return;
                }
            } else {
                JOptionPane.showMessageDialog(null, "未选中RUNNING进程^.^");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "没有进程^.^");
        }

    }

    public void setReady() {
        try {
            PCB pcb = null;
         
            String status = tb.getSelectStatus();
            if (status.equals(Status.RUNNING)) {
                pcb = algo.getOneRunningProcess();
                if (pcb != null) {
                    queue.addtoReady(queue.removeFromRunning(pcb.getPid()));
                    tb.updateByPid(pcb.getPid()+"", "READY", pcb.getRemainTime() + "");
                }
            }
            if (status.equals(Status.WAITING)) {
                pcb = algo.getOneWaitingProcess();
            }
            if (pcb != null) {
                queue.addtoReady(queue.removeFromWaiting(pcb.getPid()));
                tb.updateByPid(pcb.getPid()+"", "READY", pcb.getRemainTime() + "");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "没有进程^.^");
        }
    }

    //放回 pcb
    public void putBack(int pid) {
        PCB pcb = queue.getPCB(pid);
        if (pcb == null) {
            return;
        } else {
           
           String statu = tb.getSelectStatus();
            if (statu != null) {
                switch (statu) {
                    case "RUNNING":
                        queue.removeFromRunning(pid);
                    case "READY":
                        queue.removeFromReady(pid);
                    case "WAITING":
                        queue.removeFromWaiting(pid);
                }
            } else {
                return;
            }
//            } catch (Exception e) {
//                JOptionPane.showMessageDialog(null, "1没有进程^.^");
//            }
            System.out.println("-------------------------");
            System.out.println("删除一个进程");
            System.out.println("运行队列进程个数" + queue.running.size());
            System.out.println("就绪队列进程个数" + queue.ready.size());
            System.out.println("等待队列进程个数" + queue.waiting.size());

            pool.putBack(pcb);

            System.out.println("-------------------------");

        }
    }

    public void delete(int pid) {
        PCB pcb = queue.getPCB(pid);
        if (pcb == null) {
            return;
        } else {
            queue.removeFromRunning(pid);
            pool.putBack(pcb);
        }
    }

    public synchronized void deleteAllProcess() {
        //System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
        //把三个hash表合成一个hash表
        //每个hash表加一个iterator 分别遍历三个hash表
        Map<Integer, PCB> map = new HashMap();
        map.putAll(queue.ready);
        map.putAll(queue.running);
        map.putAll(queue.waiting);
        Iterator<PCB> ite = map.values().iterator();

        while (ite.hasNext()) {
            PCB p = ite.next();

            queue.removePCB(p.getPid());
            pool.putBack(p);
        }

    }

}

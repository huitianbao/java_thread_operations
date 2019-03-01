/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.util.Iterator;
import view.Table;

/**
 *
 * @author 惠天宝
 */
public class Scheduler {

    AlgorithmPriority algo;
    PCBQueue queue=new PCBQueue();//=============================================================
    private ProcessManager manager;
    private Table tb=new Table();

    public Scheduler(ProcessManager manager, Table tb) {
        this.tb = tb;
        this.manager = manager;
        queue = manager.getQueue();
        algo = new AlgorithmPriority(queue);
        System.out.println("进程调度器启动完成");
    }

    public void schedule() {
        PCB pcb_in = null;
        if (hasProcess() == false) {
            System.out.println("没有进程!");
            return;
        }

        //检查剩余时间，小于等于0则删除进程
        distoryProcess();

        //检查运行队列是否有进程
        if (hasRunningProcess() == false) {

            int pid = algo.getSwitchIn();
            if (pid != -1) {

                pcb_in = queue.removeFromReady(pid);
            }
            //将进程从就绪加入到运行队列
            if (pcb_in != null) {
                pcb_in.setRunning();
                queue.addtoRunning(pcb_in);
                System.out.println("-------------------------");
                System.out.println("开始运行！");
                System.out.println("运行队列进程个数" + queue.running.size());
                System.out.println("就绪队列进程个数" + queue.ready.size());
                System.out.println("等待队列进程个数" + queue.waiting.size());
                System.out.println("-------------------------");

            }

            //减少时间，更行表格
            updateTable();
        }
    }

    private boolean hasRunningProcess() {
        if (queue.running.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    private boolean hasProcess() {
        if (queue.running.size() <= 0 && queue.ready.size() <= 0 && queue.waiting.size() <= 0) {
            return false;
        } else {
            return true;
        }

    }

    private boolean hasReadyProcess() {
        if (queue.ready.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    private boolean hasWaitingProcess() {
        if (queue.waiting.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    private void updateTable() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        PCB pcb = queue.getRunningPcb();
        if (pcb != null) {
            System.out.println("【进程" + pcb.getPid() + "】----------------------------------------");
            pcb.decreaseTime(1000);
            tb.updateByPid(pcb.getPid() + "", "RUNNING", pcb.getRemainTime() + "");
        } else {
            System.out.println("没有进程！");
        }
    }

    private void distoryProcess() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        PCB pcb = queue.getRunningPcb();

        if (pcb != null) {
            if (pcb.getRemainTime() <= 0) {
                manager.delete(pcb.getPid());
                tb.deletePid(pcb.getPid() + "");
            }

            System.out.println("时间结束，销毁进程");
            System.out.println("-------------------------");

        }
    }

}

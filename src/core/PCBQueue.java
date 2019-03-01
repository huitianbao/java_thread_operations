/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import view.Table;

/**
 *
 * @author 惠天宝
 */
public class PCBQueue {

    final Map<Integer, PCB> running;    // 运行队列
    final Map<Integer, PCB> ready;      // 就绪队列
    final Map<Integer, PCB> waiting;    // 等待队列
    Table tb=new Table();  //=================================================================================================================

    PCBQueue() {
        this.running = new HashMap();
        this.ready = new HashMap();
        this.waiting = new HashMap();
    }

    PCBQueue(Table tb) {
        this.running = new HashMap();
        this.ready = new HashMap();
        this.waiting = new HashMap();
        this.tb = tb;

    }

    // 加入一个进程到运行队列
    void addtoRunning(PCB process) {
        if (process == null) {
            return;
        }

        if (!this.running.containsKey(process.getPid())) {
            this.running.put(process.getPid(), process);
        }
    }

    // 从运行队列中取出一个Process
    PCB removeFromRunning(int pid) {
        return this.running.remove(pid);
    }

    // 加入一个Process到就绪队列
    void addtoReady(PCB process) {
        if (process == null) {
            return;
        }
        this.ready.put(process.getPid(), process);
    }

    // 从就绪队列中取出一个
    PCB removeFromReady(int pid) {
        return this.ready.remove(pid);
    }

    // 加入到等待队列
    void addtoWaiting(PCB process) {
        if (process == null) {
            return;
        }
        this.waiting.put(process.getPid(), process);
    }

    // 从等待队列中取出一个
    PCB removeFromWaiting(int pid) {
        return this.waiting.remove(pid);
    }

    // 通过pid获得这个进程
    public PCB getPCB(int pid) {
        PCB ret = null;
        ret = this.running.get(pid);
        if (ret != null) {
            return ret;
        }

        ret = this.ready.get(pid);
        if (ret != null) {
            return ret;
        }

        return this.waiting.get(pid);
    }

    // 通过pid和状态获得这个进程
   public PCB getPCB(int pid, Status status) {
        switch (status) {
            case RUNNING:
                return this.running.get(pid);
            case READY:
                return this.ready.get(pid);
            case WAITING:
                return this.waiting.get(pid);
        }
        return null;
    }
    
    
    
     public synchronized PCB getRunningPcb() {

        PCB p = null;
        Iterator<PCB> ite = running.values().iterator();     // 返回集合中元素的迭代器。对于返回元素的顺序没有任何保证（除非这个集合是某些类提供担保的实例）。
        while (ite.hasNext()) {  //  取得最后一个
            p = ite.next();
        }
        return p;

    }

    public synchronized PCB getWaitingPcb() {
        PCB p = null;
        Iterator<PCB> ite = waiting.values().iterator();
        while (ite.hasNext()) {
            p = ite.next();
        }
        return p;

    }


    PCB removePCB(int pid) {
        PCB pcb = null;
        if (running.containsKey(pid)) {
            pcb = running.remove(pid);
        } else if (ready.containsKey(pid)) {
            pcb = ready.remove(pid);
        } else if (waiting.containsKey(pid)) {
            pcb = waiting.remove(pid);
        }
        return pcb;
    }
    
    
    
    
        public int getReadyTopPcbId() {
        synchronized (ready) {
            List<PCB> s = new LinkedList();
            Map<Integer, PCB> map = new HashMap();
            map.putAll(ready);
            Iterator<PCB> ite = map.values().iterator();
            int i = 10;
            int id = -1;
            while (ite.hasNext()) {
                PCB p = ite.next();
                if (p.getPriority() < i) {
                    i = p.getPriority();
                    id = p.getPid();
                }
            }
            return id;
        }
    }
    
    
    

}


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package core;

/**
 *
 * @author 惠天宝
 */
public class PCB {
    
    private int pid;//进程号
    private int priority;//优先级
    private String name;
    private Status status;//状态（运行，就绪，等待）
    private long time;//剩余时间

    public PCB(int pid) {
        this.pid = pid;
    }

//获得进程好
    public int getPid() {
        return this.pid;
    }
//获得优先级

    public int getPriority() {
        return this.priority;
    }

//设置优先级
    void setPriority(int prio) {
        if (prio < 0) {
            throw new IllegalArgumentException("优先级数值必须大于0");
        }
        this.priority = prio;
    }
//设置剩余时间

    void setRemainTime(long time) {
        this.time = time;
    }
//获得剩余时间

    long getRemainTime() {
        return this.time / 1000;
    }
//时间片
// 减少时间
    void decreaseTime(long remaintime) {
        this.time -= remaintime;
        if (time >= 0) {
            
            System.out.println("剩余时间=" + time / 1000);
        } else {
            System.out.println("剩余时间为0");
            this.time = 0;
        }

    }
//获得进程名

    String getName() {
        return name;
    }
//设置进程名

    void setName(String name) {
        this.name = name;
    }
//获得进程状态

    public Status getStatus() {
        return status;
    }
//设为运行态

    void setRunning() {
        status = Status.RUNNING;
    }
//设为准备态

    void setReady() {
        status = Status.READY;
    }
//设为等待态

    void setWaiting() {
        status = Status.WAITING;
    }

}

    


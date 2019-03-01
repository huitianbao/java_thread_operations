/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.util.Timer;
import java.util.TimerTask;
import view.Table;

/**
 *
 * @author 惠天宝
 */
public class ClockInterrupt {

    private static final int BASE = 1000;       // 时间间隔基数
    static final long delay = (long)BASE; // 5秒调度一次
     private Scheduler sched;
     Timer timer;        // 定时器
     
     
     ProcessManager manager;
     private Table tb=new Table();
     
     
      public ClockInterrupt(ProcessManager manager,Table tb) {//  因为schedule 要用 manager 和 tb
           this.manager=manager;
           this.tb=tb;
           sched=new Scheduler(manager,tb);
           
        // 定时任务线程
        TimerTask task = new TimerTask() {

            @Override
            public void run() {
                //log.debug("调度程序运行 ...");
                interrupt();
                System.out.println("调度程序运行 ...");
            }
        };
        this.timer = new Timer(true);
        this.timer.schedule(task, delay, delay);
        //log.info("时钟中断启动完成");
        System.out.println("时钟中断启动完成");
    }

    // 产生一次中断信号，然后调用调度器进行进程调度
    // 通知内核监听放在切面里进行
    private void interrupt() {
        if (this.sched != null) {
            this.sched.schedule();
        }
    }

}

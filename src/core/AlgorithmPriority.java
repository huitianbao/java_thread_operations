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
class AlgorithmPriority {

    private PCBQueue queue = new PCBQueue();

    public AlgorithmPriority(PCBQueue queue) {
        this.queue = queue;

    }

    //从就绪队列里选取优先级最高的进程  换进进程
    public int getSwitchIn() {
        //  Iterator<Pcb> ite = queue.ready.values().iterator();
        return queue.getReadyTopPcbId();

    }

    public PCB getOneRunningProcess() {
        return queue.getRunningPcb();
    }

    public PCB getOneWaitingProcess() {
        return queue.getWaitingPcb();
    }

}

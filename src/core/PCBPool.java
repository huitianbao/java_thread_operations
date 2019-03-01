/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author 惠天宝
 *
 *
 * map is a interface hashmap is a class that implements that
 * interface.???????????????????????????????????????? that is the basic
 * different. 就是说 map 是一个接口，hash 是一个实现了map 的类
 *
 */

/*

 clear() 
 从此映射中移除所有映射关系（可选操作）。 
 boolean containsKey(Object key) 
 如果此映射包含指定键的映射关系，则返回 true。 
 boolean containsValue(Object value) 
 如果此映射为指定值映射一个或多个键，则返回 true。 
 Set<Map.Entry<K,V>> entrySet() 
 返回此映射中包含的映射关系的 set 视图。 
 boolean equals(Object o) 
 比较指定的对象与此映射是否相等。 
 V get(Object key) 
 返回此映射中映射到指定键的值。 
 int hashCode() 
 返回此映射的哈希码值。 
 boolean isEmpty() 
 如果此映射未包含键-值映射关系，则返回 true。 
 Set<K> keySet() 
 返回此映射中包含的键的 set 视图。 
 V put(K key, V value) 
 将指定的值与此映射中的指定键相关联（可选操作）。  相当于set 函数
 void putAll(Map<? extends K,? extends V> t) 
 从指定映射中将所有映射关系复制到此映射中（可选操作）。 
 V remove(Object key) 
 如果存在此键的映射关系，则将其从映射中移除（可选操作）。 
 int size() 
 返回此映射中的键-值映射关系数。 
 Collection<V> values() 
 返回此映射中包含的值的 collection 视图。 






 第一次调用Iterator的next()方法时，它返回序列的第一个元素

 */
public class PCBPool {

    Map<Integer, PCB> map;   //用来存放 PCB
    final int CAPACITY = 100;
    
    public Map getPCBPoolMap(){
        return map;
    }

    public PCBPool() {    //      为什么PCBPool 没有被调用呢？？？？？？？？？？？
        map = new HashMap();
        map.clear();//  每次先清除map 里面的内容
        init();

    }

    void init() {
        for (int i = 0; i < map.size(); i++) {
            map.put(i, new PCB(i));  //从0开始

        }
        System.out.print("PCBPool 初始化完成");//      为什么PCBPool 没有被调用呢？？？？？？？？？？？
    }

    public synchronized PCB getOnePCB() {
        if (map.isEmpty()) {
            throw new IllegalStateException("PCBPool 当前为空");

        }
            Iterator<PCB> ite = this.map.values().iterator();
            PCB p = ite.next();  //  此处是返回第一个
            ite.remove();
            return p;
        

    }

    //放回一个Pcb
    public synchronized void putBack(PCB pcb) {
        if (this.map.size() == CAPACITY) {
            return;
        }
        //运行时间设为0，优先级设为0
        pcb.setRemainTime(0);
        pcb.setPriority(0);
        this.map.put(pcb.getPid(), pcb);
        System.out.println("PcbPool容量=" + map.size());

    }
    
    
    
    
    

}

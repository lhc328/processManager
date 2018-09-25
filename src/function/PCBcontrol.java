/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package function;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import service.PCB;

/**
 *
 * @author kOX
 */
public class PCBcontrol {

    public static int maxPcb = 10;              //最大进程数
    public static int minx = 0;                 //进程数id
    public static int time = 0;                 //运行时间
    public static ArrayList<PCB> relist = new ArrayList();      //就绪队列
    public static ArrayList<PCB> savelist = new ArrayList();    //进程保存队列
    public static ArrayList<PCB> waitlist = new ArrayList();    //到达队列
    public static ArrayList<PCB> loglist = new ArrayList();     //记录运行队列

    //初始化环境
    public static void processinit() {
        maxPcb = 10;
        minx = 0;
        time = 0;
        relist.removeAll(relist);
        loglist.removeAll(loglist);
    }

    //创建PCB
    public static boolean initPCB() {
        if (maxPcb <= 0) {
            return false;
        } else {
            PCB newPcb = new PCB();
            waitlist.add(newPcb);
            savelist.add(newPcb);
            maxPcb--;
            return true;
        }
    }

    //新建pcb按到达时间排序
    public static void waitsort() {
        Comparator<PCB> comparator = new Comparator<PCB>() {
            public int compare(PCB p1, PCB p2) {
                if (p1.starttime != p2.starttime) {
                    return p2.starttime - p1.starttime;
                } else {
                    return p1.name - p2.name;
                }
            }
        };
        Collections.sort(waitlist, comparator);
    }

    //取出到达队列第一个
    public static boolean readyPCB() {
        boolean flag = false;
        PCB pcb = waitlist.get(0);
        while (pcb.starttime <= time) {
            relist.add(pcb);
            waitlist.remove(pcb);
            pcb = waitlist.get(0);
            flag = true;
        }
        return flag;
    }

    //时间片轮转 
    public static void RR() {
        int slicetime = 1;                          //时间片
        PCB runPcb = null;
        while (!relist.isEmpty() || !waitlist.isEmpty()) {
            if (!relist.isEmpty()) {                    //就绪队列不为空，cpu继续工作
                runPcb = relist.remove(0);              //取第一个进程
                if (runPcb.time <= slicetime) {            //第一个进程剩余时间少于等于时间片
                    time += runPcb.time;
                    runPcb.time = 0;
                    runPcb.status = 1;
                } else {
                    time += slicetime;
                    runPcb.time -= slicetime;
                    relist.add(runPcb);
                }
                loglist.add(runPcb);                    //记录每一步的执行
            } else {                                     //就绪队列为空，到达队列还有进程，进行空操作
                time++;
                loglist.add(null);
            }
            readyPCB();
        }
    }

    //优先数优先
    public static void PF() {
        Comparator<PCB> comparator = new Comparator<PCB>() {
            public int compare(PCB p1, PCB p2) {
                if (p1.priority != p2.priority) {
                    return p1.priority - p2.priority;
                } else {
                    return p1.name - p2.name;
                }
            }
        };
        int slicetime = 1;
        PCB runPcb = null;
        Collections.sort(relist, comparator);
        while (!relist.isEmpty() || !waitlist.isEmpty()) {
            if (!relist.isEmpty()) {
                runPcb = relist.remove(0);
                if (runPcb.time <= slicetime) {            //第一个进程剩余时间少于等于时间片
                    time += runPcb.time;
                    runPcb.time = 0;
                    runPcb.status = 1;
                } else {
                    time += slicetime;
                    runPcb.time -= slicetime;
                    runPcb.priority--;
                    relist.add(runPcb);
                }
                loglist.add(runPcb);
            } else {
                time++;
                loglist.add(null);
            }
            readyPCB();
            Collections.sort(relist, comparator);
        }
    }

    //最短进程优先
    public static void SPN() {
        Comparator<PCB> comparator = new Comparator<PCB>() {
            public int compare(PCB p1, PCB p2) {
                if (p1.time != p2.time) {
                    return p2.time - p1.time;
                } else {
                    return p1.name - p2.name;
                }
            }
        };
        PCB runPcb = null;
        Collections.sort(relist, comparator);
        while (!relist.isEmpty() || !waitlist.isEmpty()) {
            if (!relist.isEmpty()) {
                runPcb = relist.remove(0);
                time += runPcb.time;
                runPcb.time = 0;
                runPcb.status = 1;
                loglist.add(runPcb);
            } else {
                time++;
                loglist.add(null);
            }
            if (readyPCB()) {                                 //有到达新进程，就要重新排序
                Collections.sort(relist, comparator);
            }
        }
    }

    //最短剩余时间优先
    public static void SRT() {
        Comparator<PCB> comparator = new Comparator<PCB>() {
            public int compare(PCB p1, PCB p2) {
                if (p1.time != p2.time) {
                    return p2.time - p1.time;
                } else {
                    return p1.name - p2.name;
                }
            }
        };
        int slicetime = 1;
        PCB runPcb = null;
        Collections.sort(relist, comparator);
        while (!relist.isEmpty() || !waitlist.isEmpty()) {
            if (!relist.isEmpty()) {
                runPcb = relist.remove(0);
                if (runPcb.time <= slicetime) {
                    time += runPcb.time;
                    runPcb.time = 0;
                    runPcb.status = 1;
                } else {
                    time += slicetime;
                    runPcb.time -= slicetime;
                    relist.add(runPcb);
                }
                loglist.add(runPcb);
            } else {
                time++;
                loglist.add(null);
            }
            readyPCB();
            Collections.sort(relist, comparator);
        }
    }
}

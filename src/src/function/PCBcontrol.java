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
    public static ArrayList<Integer> timelist = new ArrayList();
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
        for (int i = 0; i < 5; i++) {
            //timelist.add(i);
            //loglist.add(new PCB());
           initPCB();
        }
        waitsort();
    }

    //创建PCB
    public static boolean initPCB() {
        if (maxPcb <= 0) {
            return false;
        } else {
            PCB newPcb = new PCB();
            waitlist.add(newPcb);
            savelist.add(new PCB(newPcb));
            maxPcb--;
            return true;
        }
    }

    //新建pcb按到达时间排序
    public static void waitsort() {
        Comparator<PCB> comparator = new Comparator<PCB>() {
            public int compare(PCB p1, PCB p2) {
                if (p1.getStarttime() != p2.getStarttime()) {
                    return p1.getStarttime() - p2.getStarttime();
                } else {
                    return p1.getName() - p2.getName();
                }
            }
        };
        Collections.sort(waitlist, comparator);
    }

    //取出到达队列第一个
    public static boolean readyPCB() {
        boolean flag = false;
        if (!waitlist.isEmpty()) {
            PCB pcb = waitlist.get(0);
            while (pcb.getStarttime() <= time) {
                relist.add(pcb);
                waitlist.remove(0);
                if (!waitlist.isEmpty()){
                    pcb = waitlist.get(0);
                }else{
                    break;
                }
                flag = true;
            }
        }
        return flag;
    }

    //时间片轮转 
    public static void RR() {
        int slicetime = 1;                          //时间片
        readyPCB();
        PCB runPcb = null;
        while (!relist.isEmpty() || !waitlist.isEmpty()) {
            timelist.add(time);
            if (!relist.isEmpty()) {                    //就绪队列不为空，cpu继续工作
                runPcb = relist.remove(0);              //取第一个进程
                if (runPcb.getTime() <= slicetime) {            //第一个进程剩余时间少于等于时间片
                    time += runPcb.getTime();
                    runPcb.setTime(0);
                    runPcb.setStatus(1);
                } else {
                    time += slicetime;
                    runPcb.setTime(runPcb.getTime() - slicetime);
                    relist.add(new PCB(runPcb));
                }
                loglist.add(new PCB(runPcb));                    //记录每一步的执行
            } else {                                     //就绪队列为空，到达队列还有进程，进行空操作
                time++;
                loglist.add(new PCB(0,3,0,0,0));
            }
            
            readyPCB();
        }
    }

    //优先数优先
    public static void PF() {
        Comparator<PCB> comparator = new Comparator<PCB>() {
            public int compare(PCB p1, PCB p2) {
                if (p1.getPriority() != p2.getPriority()) {
                    return p2.getPriority() - p1.getPriority();
                } else {
                    return p1.getName() - p2.getName();
                }
            }
        };
        readyPCB();
        int slicetime = 1;
        PCB runPcb = null;
        Collections.sort(relist, comparator);
        while (!relist.isEmpty() || !waitlist.isEmpty()) {
            timelist.add(time);
            if (!relist.isEmpty()) {
                runPcb = relist.remove(0);
                if (runPcb.getTime() <= slicetime) {            //第一个进程剩余时间少于等于时间片
                    time += runPcb.getTime();
                    runPcb.setTime(0);
                    runPcb.setPriority(runPcb.getPriority() - 1);
                    runPcb.setStatus(1);
                } else {
                    time += slicetime;
                    runPcb.setTime(runPcb.getTime() - slicetime);
                    runPcb.setPriority(runPcb.getPriority() - 1);
                    relist.add(runPcb);
                }
                loglist.add(new PCB(runPcb));
            } else {
                time++;
                loglist.add(new PCB(0,3,0,0,0));
            }
            readyPCB();
            Collections.sort(relist, comparator);
        }
    }

    //最短进程优先
    public static void SPN() {
        Comparator<PCB> comparator = new Comparator<PCB>() {
            public int compare(PCB p1, PCB p2) {
                if (p1.getTime() != p2.getTime()) {
                    return p1.getTime() - p2.getTime();
                } else {
                    return p1.getName() - p2.getName();
                }
            }
        };
        readyPCB();
        PCB runPcb = null;
        Collections.sort(relist, comparator);
        while (!relist.isEmpty() || !waitlist.isEmpty()) {
            timelist.add(time);
            if (!relist.isEmpty()) {
                runPcb = relist.remove(0);
                time += runPcb.getTime();
                runPcb.setTime(0);
                runPcb.setStatus(1);
                loglist.add(new PCB(runPcb));
            } else {
                time++;
                loglist.add(new PCB(0,3,0,0,0));
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
                if (p1.getTime() != p2.getTime()) {
                    return p1.getTime() - p2.getTime();
                } else {
                    return p1.getName() - p2.getName();
                }
            }
        };
        readyPCB();
        int slicetime = 1;
        PCB runPcb = null;
        Collections.sort(relist, comparator);
        while (!relist.isEmpty() || !waitlist.isEmpty()) {
            timelist.add(time);
            if (!relist.isEmpty()) {
                runPcb = relist.remove(0);
                if (runPcb.getTime() <= slicetime) {
                    time += runPcb.getTime();
                    runPcb.setTime(0);
                    runPcb.setStatus(1);
                } else {
                    time += slicetime;
                    runPcb.setTime(runPcb.getTime() - slicetime);
                    relist.add(runPcb);
                }
                loglist.add(new PCB(runPcb));
            } else {
                time++;
                loglist.add(new PCB(0,3,0,0,0));
            }
            readyPCB();
            Collections.sort(relist, comparator);
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import function.PCBcontrol;

/**
 *
 * @author kOX
 */
public class LogTable {
    
    private int runtime;            //运行时间点
    private int processname;        //进程名字
    private String status;          //进程状态
    private int priority;           //优先数
    private int stime;              //到达时间
    private int etime;              //剩余时间
    
    public LogTable(PCB logPcb, int i){
        this.runtime = PCBcontrol.timelist.get(i);
        this.processname = PCBcontrol.loglist.get(i).name;
        this.priority = PCBcontrol.loglist.get(i).priority;
        this.stime = PCBcontrol.loglist.get(i).starttime;
        this.etime = PCBcontrol.loglist.get(i).time;
        if(PCBcontrol.loglist.get(i).status == 1){
            this.status = "结束";
        }else{
            this.status = "运行/就绪";
        }   
    }
    
    public LogTable(int runtime, int processname, String status, int priority, int stime, int etime){
        this.runtime = runtime;
        this.processname = processname;
        this.priority = priority;
        this.stime = stime;
        this.etime = etime;
        this.status = status;
    }

    /**
     * @return the runtime
     */
    public int getRuntime() {
        return runtime;
    }

    /**
     * @param runtime the runtime to set
     */
    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    /**
     * @return the processname
     */
    public int getProcessname() {
        return processname;
    }

    /**
     * @param processname the processname to set
     */
    public void setProcessname(int processname) {
        this.processname = processname;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the priority
     */
    public int getPriority() {
        return priority;
    }

    /**
     * @param priority the priority to set
     */
    public void setPriority(int priority) {
        this.priority = priority;
    }

    /**
     * @return the stime
     */
    public int getStime() {
        return stime;
    }

    /**
     * @param stime the stime to set
     */
    public void setStime(int stime) {
        this.stime = stime;
    }

    /**
     * @return the etime
     */
    public int getEtime() {
        return etime;
    }

    /**
     * @param etime the etime to set
     */
    public void setEtime(int etime) {
        this.etime = etime;
    }
    
    
}

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
    
    private int runtime;
    private int processname;
    private String status;
    private int priority;
    private int stime;
    private int etime;
    
    public LogTable(PCB logPcb, int i){
        runtime = PCBcontrol.timelist.get(i);
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

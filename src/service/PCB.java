/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import processmanager.ProcessManager;

/**
 *
 * @author kOX
 */
public class PCB {
    public int name;
    public int status;
    public int priority;
    public int time;
    public int starttime;
    
    public PCB(){
        this.name = ++ProcessManager.minx;
        this.status = 0;
        this.priority = (int)(Math.random()*3);
        this.time = (int)(Math.random()*9+1);
        this.starttime = (int)(Math.random()*9+1);
    }
}

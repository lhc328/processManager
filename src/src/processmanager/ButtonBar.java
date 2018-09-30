/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package processmanager;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

/**
 *
 * @author kOX
 */
public class ButtonBar {
    
    public static HBox initBar(){
        HBox bar = new HBox();
        bar.setPadding(new Insets(30,80,30,80));  //上左下右
        bar.setSpacing(30);   //控件间隔
        Button btNew = new Button("添加数据");
        Button btStop = new Button("暂停");
        Button btRun = new Button("开始运行");
        Button btClear = new Button("清空数据");      
        bar.getChildren().addAll(btNew, btStop, btRun, btClear);
        return bar;
    }
}

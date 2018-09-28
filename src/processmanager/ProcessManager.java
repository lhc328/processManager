/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package processmanager;

import function.PCBcontrol;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import service.LogTable;
import service.PCB;



/**
 *
 * @author kOX
 */
public class ProcessManager extends Application {


    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("单处理调度系统v1.0");
        Scene scene = new Scene(new VBox(), 500, 700);
        MenuBar  menuBar = new MenuBar();
        Menu menuEdit = new Menu("Edit");
        
        MenuItem rrm = new MenuItem("时间片调度");
        
        MenuItem pfm = new MenuItem("优先数优先");
        
        MenuItem spnm = new MenuItem("最短进程优先");
        
        MenuItem srtm = new MenuItem("最短剩余时间优先");
        
        menuEdit.getItems().addAll(rrm, pfm, spnm, srtm);
        
        Menu menuHelp = new Menu("Help");
        
        MenuItem about = new MenuItem("about");
        
        MenuItem instruction = new MenuItem("说明书");
        
        menuHelp.getItems().addAll(about, instruction);
        
        
        
        menuBar.getMenus().addAll(menuEdit, menuHelp);
        
        PCBcontrol.processinit();
        PCBcontrol.RR();
        
        /////这里新建类吧
        LogTableView table = new LogTableView();
        ArrayList<LogTable> data = new ArrayList<LogTable>();
        for (int i = 0; i < PCBcontrol.loglist.size(); i++)
        {
            
            LogTable entry = new LogTable(i);
            data.add(entry);
        }
        
        
        TableView view = table.addTableView();
        view.setItems(FXCollections.observableArrayList(data));
        
        PCBTableView tt = new PCBTableView();
        ArrayList<PCB> Pdata = new ArrayList<PCB>();
        for (int i = 0; i < PCBcontrol.savelist.size(); i++)
        {
            Pdata.add(PCBcontrol.savelist.get(i));
        }
        TableView tv = tt.addTableView();
        tv.setItems(FXCollections.observableArrayList(Pdata));
        
        
        ScrollPane scrollpane = new ScrollPane();
        scrollpane.setFitToWidth(true);
        scrollpane.setFitToHeight(true);
        scrollpane.setPrefSize(500, 400);
        scrollpane.setContent(view);
        
        ScrollPane dscrollpane = new ScrollPane();
        dscrollpane.setFitToWidth(true);
        dscrollpane.setFitToHeight(true);
        dscrollpane.setPrefSize(500, 150);
        dscrollpane.setContent(tv);
        
        HBox buttonBar = new HBox();
        
        Button btNew = new Button("添加数据");
        Button btRun = new Button("开始运行");
        Button btClear = new Button("清空数据");
        
        buttonBar.getChildren().addAll(btNew, btRun, btClear);
        
        ((VBox)scene.getRoot()).getChildren().addAll(menuBar, scrollpane, dscrollpane, buttonBar);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}

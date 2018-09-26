/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package processmanager;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


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
        
        LogTableView table = new LogTableView();
        TableView view = table.addTableView();
        ((VBox)scene.getRoot()).getChildren().addAll(menuBar, view);
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

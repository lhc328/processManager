/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package processmanager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import service.LogTable;

/**
 *
 * @author kOX
 */
public class LogTableView {

    public TableView addTableView() {
        TableView table = new  TableView();
        
        TableColumn timeCol = new TableColumn("run-time");
        timeCol.setMinWidth(50);
        timeCol.setCellValueFactory(new PropertyValueFactory<>("runtime"));
        
        TableColumn nameCol = new TableColumn("process-name");
        nameCol.setMinWidth(100);
        nameCol.setCellValueFactory(new PropertyValueFactory<>("processname"));
        
        TableColumn statusCol = new TableColumn("status");
        statusCol.setMinWidth(80);
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        
        TableColumn prCol = new TableColumn("priority");
        prCol.setMinWidth(55);
        prCol.setCellValueFactory(new PropertyValueFactory<>("priority"));
        
        TableColumn startCol = new TableColumn("s-time");
        startCol.setMinWidth(90);
        startCol.setCellValueFactory(new PropertyValueFactory<>("stime"));
        
        TableColumn endCol = new TableColumn("r-time");
        endCol.setMinWidth(80);
        endCol.setCellValueFactory(new PropertyValueFactory<>("etime"));
        
        
        table.getColumns().addAll(timeCol, nameCol, statusCol, prCol, startCol, endCol);
        
        return table;
    }
    
}

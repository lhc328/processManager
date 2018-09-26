/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package processmanager;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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
        TableColumn statusCol = new TableColumn("status");
        statusCol.setMinWidth(60);
        TableColumn prCol = new TableColumn("priority");
        prCol.setMinWidth(50);
        TableColumn startCol = new TableColumn("s-time");
        startCol.setMinWidth(80);
        TableColumn endCol = new TableColumn("r-time");
        endCol.setMinWidth(80);
        table.getColumns().addAll(timeCol, nameCol, statusCol, prCol, startCol, endCol);
        
        return table;
    }
}

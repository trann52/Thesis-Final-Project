package GUI.controller;

import Client.Client;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ResourceBundle;

/**
 * @author Nicky Tran
 * @version 27/07/2020: 1.2
 * This class is the controller class for the 'MissingView' fxml, so it contains
 * all appropriate methods for the GUI such as moving between different GUI scenes.
 */
public class MissingViewController implements Initializable {

    Client clientCommand;
    public static Connection connection;
    String username;
    String barcodeNumber;

    @FXML
    private Button menuBtn;

    @FXML
    private Label userLabel;

    @FXML
    private Button searchAgainBtn;

    @FXML
    private TableView tableView;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        getColumns();
//        getRowData();
//        tableView.columnResizePolicyProperty(TableView.UNCONSTRAINED_RESIZE_POLICY);
    }


    /**
     * This method is attached to the home button. It will send the user to the StaffMenu
     *
     * @param menuEvent The action of sending the user to the staff menu
     * @throws IOException
     */
    @FXML
    void goMenu(MouseEvent menuEvent) throws IOException {
        Node n = (Node) menuEvent.getSource();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/fxml/StaffMenu.fxml"));
        Parent root = (Parent) loader.load();
        StaffMenuController staffMenuController = loader.getController();
        staffMenuController.getUsername(userLabel.getText());
        Scene scn = new Scene(root);
        Stage stg = (Stage) n.getScene().getWindow();
        stg.setScene(scn);
        stg.show();
    }


    @FXML
    void backToMissSearch(MouseEvent missingEvent) throws IOException {
        Node n = (Node) missingEvent.getSource();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/fxml/MissingSearch1.fxml"));
        Parent root = (Parent) loader.load();
        MissingSearch1Controller missingSearchController = loader.getController();
        missingSearchController.getUsername(userLabel.getText());
        Scene scn = new Scene(root);
        Stage stg = (Stage) n.getScene().getWindow();
        stg.setScene(scn);
        stg.show();

    }

    /**
     * -----------------------------------------------------------------------------------------------------------------
     * The following code between the doted lines is based of:
     * https://github.com/k33ptoo/JavaFX-MySQL-Login/blob/master/src/controllers/HomeController.java
     * The author is Amos Chepchieng
     * This was posted on 13/09/2018
     */
//    private ObservableList<ObservableList> data;
//
//    public void getColumns() {
//
//        try {
//            PreparedStatement ps = connection.prepareStatement("SELECT * FROM luggageproject.public.luggagestatus WHERE barcode = ?");
//
//            ps.setString(1, barcode);
//            ResultSet rs = ps.executeQuery();
//
//            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
//                final int j = i;
//                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1).toUpperCase());
//                col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
//                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
//                        return new SimpleStringProperty(param.getValue().get(j).toString());
//                    }
//                });
//
//                tableView.getColumns().addAll(col);
//
//
//            }
//
//        }catch(SQLException e){
//            e.printStackTrace();
//        }
//    }
//
//    public void getRowData(){
//
//        data = FXCollections.observableArrayList();
//
//        try{
//            PreparedStatement ps = connection.prepareStatement("SELECT * FROM luggageproject.public.luggagestatus WHERE barcode = ?");
//
//            ps.setString(1, barcode);
//            ResultSet rs = ps.executeQuery();
//
//            while (rs.next()){
//                ObservableList row = FXCollections.observableArrayList();
//                for (int i = 1; i<= rs.getMetaData().getColumnCount(); i++){
//                    row.add(rs.getString(i));
//                }
//                    data.add(row);
//            }
//            tableView.setItems(data);
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

        /**
     * -----------------------------------------------------------------------------------------------------------------
     */
    public void getUsername(String passOnUsername) {
        username = passOnUsername;
        userLabel.setText(username);
    }

    public void getBarcode(String passOnBarcode) {
        barcodeNumber = passOnBarcode;
    }


}

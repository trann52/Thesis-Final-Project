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
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


/**
 * @author Nicky Tran
 * @version 17/07/2020: 1.1
 * This class is the controller class for the 'CurrentStatus' fxml, so it contains
 * all appropriate methods for the GUI such as moving between different GUI scenes.
 */
public class CurrentStatusController implements Initializable {

    Client clientCommand = new Client();
    public static Connection connection;
    String barcodeNumber;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private Button homeBtn;

    @FXML
    private TableView tableView;

    public CurrentStatusController() throws IOException {
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    @FXML
    void goHome(MouseEvent homeEvent) throws IOException {
        Node n = (Node) homeEvent.getSource();
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/fxml/StartMenu.fxml"));
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
    private ObservableList<ObservableList> data;

    public void getColumns() {

        try {
            PreparedStatement ps = connection.prepareStatement("SELECT location, status, datetime " +
                    "FROM luggageproject.public.viewstatus WHERE barcode = ? ");

            ps.setString(1, barcodeNumber);
            ResultSet rs = ps.executeQuery();

            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1).toUpperCase());
                col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });

                tableView.getColumns().addAll(col);


            }

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void getRowData(){

        data = FXCollections.observableArrayList();

        try{
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM luggageproject.public.luggagestatus WHERE barcode = ?");

            ps.setString(1, barcodeNumber);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                ObservableList row = FXCollections.observableArrayList();
                for (int i = 1; i<= rs.getMetaData().getColumnCount(); i++){
                    row.add(rs.getString(i));
                }
                data.add(row);
            }
            tableView.setItems(data);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * -----------------------------------------------------------------------------------------------------------------
     */

    public void testBar(){
        progressBar.setProgress(0.5);
    }

    public void getBarcode(String passOnBarcode){
        barcodeNumber = passOnBarcode;
    }

}

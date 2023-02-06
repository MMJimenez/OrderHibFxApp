package com.example.orderhibfx;

import com.example.orderhibfx.dao.ProductDAO;
import com.example.orderhibfx.dao.RequestDAO;
import com.example.orderhibfx.models.Request;
import com.example.orderhibfx.utils.DataBase;
import com.example.orderhibfx.utils.DataHolder;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class MainTableController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;



    @FXML
    private TableView<Request> tableRequest;

    @FXML
    private TableColumn<Request, String> columnClientName;

    @FXML
    private TableColumn<Request, Date> columnDate;

    @FXML
    private TableColumn<Request, Boolean> columnDelivered;

    @FXML
    private TableColumn<Request, Integer> columnId;

    @FXML
    private TableColumn<Request, String> columnProductName;

    @FXML
    private Button btnShowOrder;

    @FXML
    private ChoiceBox<String> choiceBox;

    @FXML
    private ComboBox<String> dataBaseComboBox;

    private ArrayList<Request> requestsTableData;

    private String choiceSelected;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        RequestDAO requestDAO = new RequestDAO();
        requestsTableData = requestDAO.getAllByDate();

        inflateChoiceBox();
        inflateTable();
        updateTable();
    }

    @FXML
    void showOrderAction(ActionEvent event) {
        RequestDAO requestDAO = new RequestDAO();
        choiceSelected = choiceBox.getValue();

        updateTable();
    }

    private void inflateChoiceBox() {
        choiceBox.getItems().add("Todos");
        choiceBox.getItems().add("Hoy");
        choiceBox.getItems().add("Última semana");
        choiceBox.getItems().add("Última mes");
        choiceBox.getItems().add("Última año");
        choiceBox.setValue("Todos");
        choiceSelected = "Todos";

        dataBaseComboBox.getItems().add("Hibernate");
        dataBaseComboBox.getItems().add("ObjectDB");

        if(DataBase.getSelectedDB().equals(DataBase.SelectedDB.OBJECTDB))
            dataBaseComboBox.setValue("ObjectDB");
        else if(DataBase.getSelectedDB().equals(DataBase.SelectedDB.HIBERNATE))
            dataBaseComboBox.setValue("Hibernate");

    }



    private void inflateTable() {
        tableRequest.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        columnId.setCellValueFactory(new PropertyValueFactory("id"));
        columnDate.setCellValueFactory(new PropertyValueFactory("date"));
        columnClientName.setCellValueFactory(new PropertyValueFactory("client"));
        columnDelivered.setCellValueFactory(new PropertyValueFactory("delivered"));
        columnProductName.setCellValueFactory(new PropertyValueFactory("product"));

        columnDelivered.setCellValueFactory((var fila) -> {
            Request request = fila.getValue();
            var delivered = new ReadOnlyObjectWrapper();
            if (request.getDelivered()) {
                delivered.setValue("Sí");
            } else {
                delivered.setValue("No");
            }
            return delivered;
        });

        columnProductName.setCellValueFactory((var fila) -> {
            Request request = fila.getValue();
            var productName = new ReadOnlyObjectWrapper();
            ProductDAO productDAO = new ProductDAO();
            productName.setValue(productDAO.get(request.getProduct()).getName());
            return productName;
        });
    }

    private void updateTable() {
        RequestDAO requestDAO = new RequestDAO();

        //Si se comenta este switch no da error
        switch (choiceSelected) {
            case "Todos":
                requestsTableData = requestDAO.getAllByDate();
                break;
            case "Hoy":
                requestsTableData = requestDAO.getAllToday();
                break;
            case "Última semana":
                requestsTableData = requestDAO.getAllLastWeek();
                break;
            case "Última año":
                requestsTableData = requestDAO.getAllLastYear();

                break;
        }

        tableRequest.getItems().clear();
        tableRequest.getItems().addAll(requestsTableData);
    }

    private Request getSelectedRow() {
        return tableRequest.getSelectionModel().getSelectedItem();
    }

    @FXML
    void filterAvaliableAction(ActionEvent event) {
        RequestDAO requestDAO = new RequestDAO();
        requestsTableData = requestDAO.getAllNotDelivered();
        tableRequest.getItems().clear();
        tableRequest.getItems().addAll(requestsTableData);
    }

    public void checkDelivered(ActionEvent event) throws IOException{
        if (getSelectedRow() == null) {
            errorMessage();
        }else {
            var request = getSelectedRow();
            request.setDelivered(true);
            var requestDAO = new RequestDAO();
            requestDAO.update(request);
            updateTable();
        }
    }

    public void deleteRequest(ActionEvent event) throws IOException{
        if (getSelectedRow() == null) {
            errorMessage();
        }else {
            Alert important = new Alert(Alert.AlertType.CONFIRMATION);
            important.setHeaderText("IMPORTANTE");
            important.setContentText("¿Está seguro de eliminar este pedido?");
            var confirmation = important.showAndWait();
            if(confirmation.get() == ButtonType.OK){
                var request = getSelectedRow();
                var requestDAO = new RequestDAO();
                requestDAO.delete(request);
                updateTable();
            }
            important.close();
        }
    }
    public void errorMessage(){
        Alert fail = new Alert(Alert.AlertType.INFORMATION);
        fail.setHeaderText("ERROR");
        fail.setContentText("No hay producto seleccionado");
        fail.showAndWait();
    }

    public void changeToCreateRequest(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("create-request-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void changeToModifyRequest(ActionEvent event) throws IOException {
        Request request = getSelectedRow();

        if (request != null) {
            DataHolder dataHolder = DataHolder.getInstance();
            dataHolder.setData(request);

            Parent root = FXMLLoader.load(getClass().getResource("modify-request-view.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            errorMessage();
        }
    }

    public void changeCombo(ActionEvent event) throws IOException {
        String frameworkSelected = dataBaseComboBox.getValue();
        if (frameworkSelected.equals("Hibernate")) {
            DataBase.setSelectedDB(DataBase.SelectedDB.HIBERNATE);
        } else {
            DataBase.setSelectedDB(DataBase.SelectedDB.OBJECTDB);
        }
        updateTable();
    }

    public void createReport(ActionEvent event) throws IOException {

       try{
        Parent root = FXMLLoader.load(getClass().getResource("jaspersoft-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        }catch (Exception e){
           e.printStackTrace();

        }
    }
    //Colapsa esta clase
}

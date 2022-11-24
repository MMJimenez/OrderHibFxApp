package com.example.orderhibfx;

import com.example.orderhibfx.dao.ProductDAO;
import com.example.orderhibfx.dao.RequestDAO;
import com.example.orderhibfx.models.Product;
import com.example.orderhibfx.models.Request;
import com.example.orderhibfx.utils.DataHolder;
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
import java.sql.Date;
import java.util.HashSet;
import java.util.ResourceBundle;

public class ModifyRequestController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void changeToMainTable(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("main-table-view.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private TableColumn<Product, Boolean> columnAvaliable;

    @FXML
    private TableColumn<Product, Integer> columnId;

    @FXML
    private TableColumn<Product, String> columnName;

    @FXML
    private TableColumn<Product, Boolean> columnPrice;

    @FXML
    private TableColumn<Product, String> columnType;

    @FXML
    private TableView<Product> tableView;

    @FXML
    private TextField editRequestClient;

    @FXML
    private ChoiceBox<String> choiceBox;

    @FXML
    private DatePicker datePicker;

    private HashSet<String> clientsNames;

    private Request requestToModify;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DataHolder dataHolder = DataHolder.getInstance();
        Request request = (Request) dataHolder.getData();

        RequestDAO requestDAO = new RequestDAO();
        clientsNames = new HashSet<>(requestDAO.getAllClients());

        inflateChoiceBox();

        if (request != null) {
            requestToModify = request;
            updateRequest();
        }

        inflateTable();
        updateTabla();
    }

    private void inflateTable() {
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        columnId.setCellValueFactory(new PropertyValueFactory("id"));
        columnName.setCellValueFactory(new PropertyValueFactory("name"));
        columnType.setCellValueFactory(new PropertyValueFactory("type"));
        columnPrice.setCellValueFactory(new PropertyValueFactory("price"));
        columnAvaliable.setCellValueFactory(new PropertyValueFactory("availibity"));
    }

    private void updateTabla() {
        ProductDAO productDAO = new ProductDAO();

        tableView.getItems().clear();
        tableView.getItems().addAll(productDAO.getAll());
    }

    private void inflateChoiceBox() {
        choiceBox.getItems().clear();
        choiceBox.getItems().addAll(clientsNames);
    }

    private void updateRequest() {
        choiceBox.setValue(requestToModify.getClient());
        datePicker.setValue(requestToModify.getDate().toLocalDate());
    }

    @FXML
    void addNewClientAction(ActionEvent event) {
        String newClient = editRequestClient.getText();
        clientsNames.add(newClient);
        inflateChoiceBox();
        choiceBox.setValue(newClient);
        editRequestClient.setText("");
    }

    private Product getSelectedRow() {
        return tableView.getSelectionModel().getSelectedItem();
    }

    public void modifyRequest(ActionEvent event) throws IOException {

        if (requestToModify != null) {
            if (choiceBox.getSelectionModel().getSelectedItem().trim().isEmpty()) {
                Alert fail = new Alert(Alert.AlertType.INFORMATION);
                fail.setHeaderText("ERROR");
                fail.setContentText("No se ha definido cliente");
                fail.showAndWait();
            } else if (datePicker.getValue() == null) {
                Alert fail = new Alert(Alert.AlertType.INFORMATION);
                fail.setHeaderText("ERROR");
                fail.setContentText("No se ha definido fecha");
                fail.showAndWait();
            } else if (getSelectedRow() == null) {
                Alert fail = new Alert(Alert.AlertType.INFORMATION);
                fail.setHeaderText("ERROR");
                fail.setContentText("No hay producto seleccionado");
                fail.showAndWait();
            } else {
                Product product = getSelectedRow();
                Request request = new Request();
                request.setProduct(product.getId());
                request.setClient(choiceBox.getSelectionModel().getSelectedItem());
                request.setDate(Date.valueOf(datePicker.getValue()));
                request.setDelivered(false);
                request.setId(requestToModify.getId());

                RequestDAO requestDAO = new RequestDAO();
                requestDAO.update(request);
                changeToMainTable(event);
            }
        } else {
            changeToMainTable(event);
        }

    }

}

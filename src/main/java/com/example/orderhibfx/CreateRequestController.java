package com.example.orderhibfx;

import com.example.orderhibfx.dao.ProductDAO;
import com.example.orderhibfx.dao.RequestDAO;
import com.example.orderhibfx.models.Product;
import com.example.orderhibfx.models.Request;
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
import java.time.LocalDate;
import java.util.ResourceBundle;

public class CreateRequestController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void changeToMainTable(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("main-table-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void actualDate(ActionEvent event) throws IOException{
        datePicker.setValue(LocalDate.now());
    }

    public void createRequest(ActionEvent event) throws IOException {

        if (clientField.getText().trim().isEmpty()) {
            Alert fail = new Alert(Alert.AlertType.INFORMATION);
            fail.setHeaderText("ERROR");
            fail.setContentText("No se ha definido cliente");
            fail.showAndWait();

        } else if(datePicker.getValue() == null){
            Alert fail = new Alert(Alert.AlertType.INFORMATION);
            fail.setHeaderText("ERROR");
            fail.setContentText("No se ha definido fecha");
            fail.showAndWait();
        } else if(getSelectedRow() == null){
            Alert fail = new Alert(Alert.AlertType.INFORMATION);
            fail.setHeaderText("ERROR");
            fail.setContentText("No hay producto seleccionado");
            fail.showAndWait();
        } else{
            Product product = getSelectedRow();
            Request request = new Request();
            request.setProduct(product.getId());
            request.setClient(clientField.getText());
            request.setDate(Date.valueOf( datePicker.getValue()));
            request.setDelivered(false);
            request.setId(1);
            //RequestDAO.save(request);
        }
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
    private DatePicker datePicker;

    @FXML
    private TextField clientField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        columnId.setCellValueFactory(new PropertyValueFactory("id"));
        columnName.setCellValueFactory(new PropertyValueFactory("name"));
        columnType.setCellValueFactory(new PropertyValueFactory("type"));
        columnPrice.setCellValueFactory(new PropertyValueFactory("price"));
        columnAvaliable.setCellValueFactory(new PropertyValueFactory("availibity"));

        actualizarTabla();
    }

    private void actualizarTabla() {
        ProductDAO productDAO = new ProductDAO();

        tableView.getItems().clear();
        tableView.getItems().addAll(productDAO.getAll());
    }

    private Boolean isRowSelected() {
        return tableView.getSelectionModel().getSelectedItem() != null;
    }

    private Product getSelectedRow() {
        return tableView.getSelectionModel().getSelectedItem();
    }
}

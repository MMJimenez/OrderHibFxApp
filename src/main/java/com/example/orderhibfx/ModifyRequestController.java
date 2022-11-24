package com.example.orderhibfx;

import com.example.orderhibfx.dao.ProductDAO;
import com.example.orderhibfx.models.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ModifyRequestController implements Initializable {

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
}
